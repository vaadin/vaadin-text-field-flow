/*
 * Copyright 2000-2019 Vaadin Ltd.
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
 * Abstract base class for components based on {@code vaadin-number-field}
 * element and its subclasses.
 *
 * @author Vaadin Ltd.
 */
public abstract class AbstractNumberField<C extends AbstractNumberField<C, T>, T extends Number>
        extends GeneratedVaadinNumberField<C, T>
        implements HasSize, HasValidation, HasValueChangeMode,
        HasPrefixAndSuffix, InputNotifier, KeyNotifier, CompositionNotifier,
        HasAutocomplete, HasAutocapitalize, HasAutocorrect {

    private ValueChangeMode currentMode;

    private boolean isConnectorAttached;

    private int valueChangeTimeout = DEFAULT_CHANGE_TIMEOUT;

    private T max;
    private T min;
    private T step;

    private boolean required;

    /**
     * Constructs an empty {@code NumberField}.
     */
    public AbstractNumberField(SerializableFunction<String, T> parser,
            SerializableFunction<T, String> formatter, T defaultStep) {
        super(null, null, String.class, parser, formatter);
        setStep(defaultStep);

        setValueChangeMode(ValueChangeMode.ON_CHANGE);
        addInvalidChangeListener(e -> {
            // If invalid is updated from client to false, check it
            if (e.isFromClient() && !e.isInvalid()) {
                setInvalid(isInvalid(getValue()));
            }
        });
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
     * Performs a server-side validation of the given value. This is needed
     * because it is possible to circumvent the client side validation
     * constraints using browser development tools.
     */
    private boolean isInvalid(T value) {
        final boolean isRequiredButEmpty = required
                && Objects.equals(getEmptyValue(), value);
        final boolean isGreaterThanMax = value != null && max != null
                && value.doubleValue() > max.doubleValue();
        final boolean isSmallerThanMin = (value != null && min != null
                && value.doubleValue() < min.doubleValue());
        return isRequiredButEmpty || isGreaterThanMax || isSmallerThanMin;
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

    /**
     * 
     * @param max
     */
    public void setMax(T max) {
        super.setMax(max.doubleValue());
        this.max = max;
    }

    /**
     * The maximum value of the field.
     * 
     * @return the {@code max} property from the webcomponent
     */
    public T getMax() {
        return max;
    }

    /**
     * 
     * @param min
     */
    public void setMin(T min) {
        super.setMin(min.doubleValue());
        this.min = min;
    }

    /**
     * The minimum value of the field.
     * 
     * @return the {@code min} property from the webcomponent
     */
    public T getMin() {
        return min;
    }

    /**
     * 
     * @param step
     */
    public void setStep(T step) {
        super.setStep(step.doubleValue());
        this.step = step;
    }

    /**
     * Specifies the allowed number intervals of the field.
     * 
     * @return the {@code step} property from the webcomponent
     */
    public T getStep() {
        return step;
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
    public T getEmptyValue() {
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
    public void setValue(T value) {
        super.setValue(value);
    }

    /**
     * Returns the current value of the number field. By default, the empty
     * number field will return {@code null} .
     *
     * @return the current value.
     */
    @Override
    public T getValue() {
        return super.getValue();
    }

    @Override
    protected void setModelValue(T newModelValue, boolean fromClient) {
        super.setModelValue(newModelValue, fromClient);
        setInvalid(isInvalid(newModelValue));
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        super.setRequiredIndicatorVisible(requiredIndicatorVisible);
        if (!isConnectorAttached) {
            RequiredValidationUtil.attachConnector(this);
            isConnectorAttached = true;
        }
        RequiredValidationUtil.updateClientValidation(requiredIndicatorVisible,
                this);
        this.required = requiredIndicatorVisible;
    }
}
