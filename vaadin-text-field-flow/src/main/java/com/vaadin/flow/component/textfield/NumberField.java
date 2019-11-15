/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.flow.component.textfield;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

import com.vaadin.flow.component.CompositionNotifier;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.InputNotifier;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableFunction;

/**
 * Server-side component for the {@code vaadin-number-field} element.
 *
 * @author Vaadin Ltd.
 */
public class NumberField extends GeneratedVaadinNumberField<NumberField, Double>
        implements HasSize, HasValidation, HasValueChangeMode,
        HasPrefixAndSuffix, InputNotifier, KeyNotifier, CompositionNotifier,
        HasAutocomplete, HasAutocapitalize, HasAutocorrect {

    private ValueChangeMode currentMode;

    private boolean isConnectorAttached;

    private int valueChangeTimeout = DEFAULT_CHANGE_TIMEOUT;

    private Double max;
    private Double min;
    private Double step;

    private boolean required;

    private boolean stepSetByUser;
    private boolean minSetByUser;

    /**
     * Constructs an empty {@code NumberField}.
     */
    public NumberField() {
        this(new Formatter());
    }

    /**
     * Constructs an empty {@code NumberField} with the given label.
     *
     * @param label
     *            the text to set as the label
     */
    public NumberField(String label) {
        this();
        setLabel(label);
    }

    /**
     * Constructs an empty {@code NumberField} with the given label and
     * placeholder text.
     *
     * @param label
     *            the text to set as the label
     * @param placeholder
     *            the placeholder text to set
     */
    public NumberField(String label, String placeholder) {
        this(label);
        setPlaceholder(placeholder);
    }

    /**
     * Constructs an empty {@code NumberField} with a value change listener.
     *
     * @param listener
     *            the value change listener
     *
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public NumberField(
            ValueChangeListener<? super ComponentValueChangeEvent<NumberField, Double>> listener) {
        this();
        addValueChangeListener(listener);
    }

    /**
     * Constructs an empty {@code NumberField} with a value change listener and
     * a label.
     *
     * @param label
     *            the text to set as the label
     * @param listener
     *            the value change listener
     *
     * @see #setLabel(String)
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public NumberField(String label,
            ValueChangeListener<? super ComponentValueChangeEvent<NumberField, Double>> listener) {
        this(label);
        addValueChangeListener(listener);
    }

    /**
     * Constructs a {@code NumberField} with a value change listener, a label
     * and an initial value.
     *
     * @param label
     *            the text to set as the label
     * @param initialValue
     *            the initial value
     * @param listener
     *            the value change listener
     *
     * @see #setLabel(String)
     * @see #setValue(Object)
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public NumberField(String label, Double initialValue,
            ValueChangeListener<? super ComponentValueChangeEvent<NumberField, Double>> listener) {
        this(label);
        setValue(initialValue);
        addValueChangeListener(listener);
    }

    /**
     * Constructs an empty {@code NumberField}.
     *
     * @param formatter
     *            Formatter for the field.
     */
    private NumberField(Formatter formatter) {
        super(null, null, String.class, formatter::parse, formatter);

        // workaround for https://github.com/vaadin/flow/issues/3496
        setInvalid(false);

        setValueChangeMode(ValueChangeMode.ON_CHANGE);

        addValueChangeListener(e -> validate());

        FieldValidationUtil.disableClientValidation(this);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@link ValueChangeMode#ON_CHANGE}.
     */
    @Override
    public ValueChangeMode getValueChangeMode() {
        return currentMode;
    }

    @Override
    public void setValueChangeMode(ValueChangeMode valueChangeMode) {
        currentMode = valueChangeMode;
        setSynchronizedEvent(
                ValueChangeMode.eventForMode(valueChangeMode, "value-changed"));
        applyChangeTimeout();
    }

    @Override
    public void setValueChangeTimeout(int valueChangeTimeout) {
        this.valueChangeTimeout = valueChangeTimeout;
        applyChangeTimeout();
    }

    @Override
    public int getValueChangeTimeout() {
        return valueChangeTimeout;
    }

    private void applyChangeTimeout() {
        ValueChangeMode.applyChangeTimeout(getValueChangeMode(),
                getValueChangeTimeout(), getSynchronizationRegistration());
    }

    @Override
    public String getErrorMessage() {
        return super.getErrorMessageString();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
    }

    @Override
    public boolean isInvalid() {
        return isInvalidBoolean();
    }

    /**
     * Performs server-side validation of the current value. This is needed
     * because it is possible to circumvent the client-side validation
     * constraints using browser development tools.
     */
    @Override
    protected void validate() {
        Double value = getValue();

        final boolean isRequiredButEmpty = required
                && Objects.equals(getEmptyValue(), value);
        final boolean isGreaterThanMax = value != null && max != null
                && value > max;
        final boolean isSmallerThanMin = value != null && min != null
                && value < min;

        setInvalid(isRequiredButEmpty || isGreaterThanMax || isSmallerThanMin);
        setInvalid(isRequiredButEmpty || isGreaterThanMax || isSmallerThanMin
                || !isValidByStep(value));
    }

    private boolean isValidByStep(Double value) {

        if (!stepSetByUser// Don't use step in validation if it's not explicitly
                // set by user. This follows the web component logic.
                || value == null || step == 0) {
            return true;
        }

        // When min is not defined by user, min should not be considered
        // in the step validation.
        double stepBasis = minSetByUser ? getMinDouble() : 0.0;

        // (value - stepBasis) % step == 0
        return new BigDecimal(String.valueOf(value))
                .subtract(BigDecimal.valueOf(stepBasis))
                .remainder(BigDecimal.valueOf(step))
                .compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public void setInvalid(boolean invalid) {
        super.setInvalid(invalid);
    }

    @Override
    public void setLabel(String label) {
        super.setLabel(label);
    }

    /**
     * String used for the label element.
     *
     * @return the {@code label} property from the webcomponent
     */
    public String getLabel() {
        return getLabelString();
    }

    @Override
    public void setMax(double max) {
        super.setMax(max);
        this.max = max;
    }

    /**
     * The maximum value of the field.
     *
     * @return the {@code max} property from the webcomponent
     */
    public double getMax() {
        return super.getMaxDouble();
    }

    @Override
    public void setMin(double min) {
        super.setMin(min);
        this.min = min;
        minSetByUser = true;
    }

    /**
     * The minimum value of the field.
     *
     * @return the {@code min} property from the webcomponent
     */
    public double getMin() {
        return super.getMinDouble();
    }

    /**
     * Sets the allowed number intervals of the field. This specifies how much
     * the value will be increased/decreased. It is also used to
     * invalidate the field, if the value doesn't align with the specified step
     * and {@link #setMin(double) min} (if specified by user).
     *
     * @param step
     *            the new step to set
     * @throws IllegalArgumentException
     *             if the argument is less or equal to zero.
     */
    @Override
    public void setStep(double step) {
        if (step <= 0) {
            throw new IllegalArgumentException("The step cannot be less or equal to zero.");
        }
        super.setStep(step);
        this.step = step;
        stepSetByUser = true;
    }

    /**
     * Specifies the allowed number intervals of the field.
     *
     * @return the {@code step} property from the webcomponent
     */
    public double getStep() {
        return super.getStepDouble();
    }

    @Override
    public void setPlaceholder(String placeholder) {
        super.setPlaceholder(placeholder);
    }

    @Override
    public void setHasControls(boolean hasControls) {
        super.setHasControls(hasControls);
    }

    /**
     * Set to true to display value increase/decrease controls.
     *
     * @return the {@code hasControls} property from the webcomponent
     */
    public boolean hasControls() {
        return super.hasControlsBoolean();
    }

    /**
     * A hint to the user of what can be entered in the component.
     *
     * @return the {@code placeholder} property from the webcomponent
     */
    public String getPlaceholder() {
        return getPlaceholderString();
    }

    @Override
    public void setAutofocus(boolean autofocus) {
        super.setAutofocus(autofocus);
    }

    /**
     * Specify that this control should have input focus when the page loads.
     *
     * @return the {@code autofocus} property from the webcomponent
     */
    public boolean isAutofocus() {
        return isAutofocusBoolean();
    }

    /**
     * Maximum number of characters (in Unicode code points) that the user can
     * enter.
     *
     * @param maxLength
     *            the maximum length
     */
    public void setMaxLength(int maxLength) {
        super.setMaxlength(maxLength);
    }

    /**
     * Maximum number of characters (in Unicode code points) that the user can
     * enter.
     *
     * @return the {@code maxlength} property from the webcomponent
     */
    public int getMaxLength() {
        return (int) getMaxlengthDouble();
    }

    /**
     * Minimum number of characters (in Unicode code points) that the user can
     * enter.
     *
     * @param minLength
     *            the minimum length
     */
    public void setMinLength(int minLength) {
        super.setMinlength(minLength);
    }

    /**
     * Minimum number of characters (in Unicode code points) that the user can
     * enter.
     *
     * @return the {@code minlength} property from the webcomponent
     */
    public int getMinLength() {
        return (int) getMinlengthDouble();
    }

    /**
     * When set to <code>true</code>, user is prevented from typing a value that
     * conflicts with the given {@code pattern}.
     *
     * @return the {@code preventInvalidInput} property from the webcomponent
     */
    public boolean isPreventInvalidInput() {
        return isPreventInvalidInputBoolean();
    }

    @Override
    public void setPreventInvalidInput(boolean preventInvalidInput) {
        super.setPreventInvalidInput(preventInvalidInput);
    }

    @Override
    public void setPattern(String pattern) {
        super.setPattern(pattern);
    }

    /**
     * A regular expression that the value is checked against. The pattern must
     * match the entire value, not just some subset.
     *
     * @return the {@code pattern} property from the webcomponent
     */
    public String getPattern() {
        return getPatternString();
    }

    /**
     * Message to show to the user when validation fails.
     *
     * @return the {@code title} property from the webcomponent
     */
    public String getTitle() {
        return super.getTitleString();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    /**
     * Specifies if the field value gets automatically selected when the field
     * gains focus.
     *
     * @return <code>true</code> if autoselect is active, <code>false</code>
     *         otherwise
     */
    public boolean isAutoselect() {
        return super.isAutoselectBoolean();
    }

    /**
     * Set to <code>true</code> to always have the field value automatically
     * selected when the field gains focus, <code>false</code> otherwise.
     *
     * @param autoselect
     *            <code>true</code> to set auto select on, <code>false</code>
     *            otherwise
     */
    @Override
    public void setAutoselect(boolean autoselect) {
        super.setAutoselect(autoselect);
    }

    /**
     * Gets the visibility state of the button which clears the number field.
     *
     * @return <code>true</code> if the button is visible, <code>false</code>
     *         otherwise
     */
    public boolean isClearButtonVisible() {
        return isClearButtonVisibleBoolean();
    }

    /**
     * Set to <code>false</code> to hide the clear button which clears the
     * number field.
     *
     * @param clearButtonVisible
     *            <code>true</code> to set the button visible,
     *            <code>false</code> otherwise
     */
    @Override
    public void setClearButtonVisible(boolean clearButtonVisible) {
        super.setClearButtonVisible(clearButtonVisible);
    }

    /**
     * Returns the value that represents an empty value.
     */
    @Override
    public Double getEmptyValue() {
        return null;
    }

    /**
     * Sets the value of this number field. If the new value is not equal to
     * {@code getValue()}, fires a value change event.
     *
     * @param value
     *            the new value
     */
    @Override
    public void setValue(Double value) {
        super.setValue(value);
    }

    /**
     * Returns the current value of the number field. By default, the empty
     * number field will return {@code null} .
     *
     * @return the current value.
     */
    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        super.setRequiredIndicatorVisible(requiredIndicatorVisible);
        this.required = requiredIndicatorVisible;
    }

    private static class Formatter
            implements SerializableFunction<Double, String> {

        // Using Locale.ENGLISH to keep format independent of JVM locale
        // settings. The value property always uses period as the decimal
        // separator regardless of the browser locale.
        private final DecimalFormat decimalFormat = new DecimalFormat("#.#",
                DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        private Formatter() {
            decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
        }

        @Override
        public String apply(Double valueFromModel) {
            return valueFromModel == null ? ""
                    : decimalFormat.format(valueFromModel.doubleValue());
        }

        private Double parse(String valueFromClient) {
            try {
                return valueFromClient == null || valueFromClient.isEmpty()
                        ? null
                        : decimalFormat.parse(valueFromClient).doubleValue();
            } catch (ParseException e) {
                throw new NumberFormatException(valueFromClient);
            }
        }
    }
}
