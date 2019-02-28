package com.vaadin.flow.component.textfield;

import com.vaadin.flow.component.CompositionNotifier;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.InputNotifier;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableFunction;

public abstract class AbstractNumberField<R extends AbstractNumberField<R, T>, T>
    extends GeneratedVaadinNumberField<R, T>
    implements HasSize, HasValidation, HasValueChangeMode, HasPrefixAndSuffix, InputNotifier,
    KeyNotifier, CompositionNotifier, HasAutocomplete, HasAutocapitalize, HasAutocorrect {
  private ValueChangeMode currentMode;

  private boolean isConnectorAttached;

  public <P> AbstractNumberField(T initialValue, T defaultValue, Class<P> elementPropertyType,
      SerializableFunction<P, T> presentationToModel,
      SerializableFunction<T, P> modelToPresentation) {
    super(initialValue, defaultValue, elementPropertyType, presentationToModel,
        modelToPresentation);
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
    setSynchronizedEvent(ValueChangeMode.eventForMode(valueChangeMode, "value-changed"));
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
  }

  /**
   * The minimum value of the field.
   * 
   * @return the {@code min} property from the webcomponent
   */
  public double getMin() {
    return super.getMinDouble();
  }

  @Override
  public void setStep(double step) {
    super.setStep(step);
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
   * Maximum number of characters (in Unicode code points) that the user can enter.
   *
   * @param maxLength the maximum length
   */
  public void setMaxLength(int maxLength) {
    super.setMaxlength(maxLength);
  }

  /**
   * Maximum number of characters (in Unicode code points) that the user can enter.
   *
   * @return the {@code maxlength} property from the webcomponent
   */
  public int getMaxLength() {
    return (int) getMaxlengthDouble();
  }

  /**
   * Minimum number of characters (in Unicode code points) that the user can enter.
   *
   * @param minLength the minimum length
   */
  public void setMinLength(int minLength) {
    super.setMinlength(minLength);
  }

  /**
   * Minimum number of characters (in Unicode code points) that the user can enter.
   *
   * @return the {@code minlength} property from the webcomponent
   */
  public int getMinLength() {
    return (int) getMinlengthDouble();
  }

  /**
   * When set to <code>true</code>, user is prevented from typing a value that conflicts with the
   * given {@code pattern}.
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
   * A regular expression that the value is checked against. The pattern must match the entire
   * value, not just some subset.
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
   * Specifies if the field value gets automatically selected when the field gains focus.
   *
   * @return <code>true</code> if autoselect is active, <code>false</code> otherwise
   */
  public boolean isAutoselect() {
    return super.isAutoselectBoolean();
  }

  /**
   * Set to <code>true</code> to always have the field value automatically selected when the field
   * gains focus, <code>false</code> otherwise.
   *
   * @param autoselect <code>true</code> to set auto select on, <code>false</code> otherwise
   */
  @Override
  public void setAutoselect(boolean autoselect) {
    super.setAutoselect(autoselect);
  }

  /**
   * Gets the visibility state of the button which clears the number field.
   *
   * @return <code>true</code> if the button is visible, <code>false</code> otherwise
   */
  public boolean isClearButtonVisible() {
    return isClearButtonVisibleBoolean();
  }

  /**
   * Set to <code>false</code> to hide the clear button which clears the number field.
   *
   * @param clearButtonVisible <code>true</code> to set the button visible, <code>false</code>
   *        otherwise
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
   * Sets the value of this number field. If the new value is not equal to {@code getValue()}, fires
   * a value change event.
   *
   * @param value the new value
   */
  @Override
  public void setValue(T value) {
    super.setValue(value);
  }

  /**
   * Returns the current value of the number field. By default, the empty number field will return
   * {@code null} .
   *
   * @return the current value.
   */
  @Override
  public T getValue() {
    return super.getValue();
  }

  @Override
  public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
    super.setRequiredIndicatorVisible(requiredIndicatorVisible);
    if (!isConnectorAttached) {
      RequiredValidationUtil.attachConnector(this);
      isConnectorAttached = true;
    }
    RequiredValidationUtil.updateClientValidation(requiredIndicatorVisible, this);
  }
}
