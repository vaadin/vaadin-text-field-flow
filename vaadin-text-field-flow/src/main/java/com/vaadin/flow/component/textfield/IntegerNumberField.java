package com.vaadin.flow.component.textfield;

import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableFunction;

public class IntegerNumberField extends AbstractNumberField<IntegerNumberField, Integer> {
  private static final SerializableFunction<String, Integer> PARSER =
      valueFromClient -> valueFromClient == null || valueFromClient.isEmpty() ? null
          : Integer.parseInt(valueFromClient);

  private static final SerializableFunction<Integer, String> FORMATTER =
      valueFromModel -> valueFromModel == null ? "" : valueFromModel.toString();

  /**
   * Constructs an empty {@code NumberField}.
   */
  public IntegerNumberField() {
    super(null, null, String.class, PARSER, FORMATTER);
    setValueChangeMode(ValueChangeMode.ON_CHANGE);
  }

  /**
   * Constructs an empty {@code NumberField} with the given label.
   *
   * @param label the text to set as the label
   */
  public IntegerNumberField(String label) {
    this();
    setLabel(label);
  }

  /**
   * Constructs an empty {@code NumberField} with the given label and placeholder text.
   *
   * @param label the text to set as the label
   * @param placeholder the placeholder text to set
   */
  public IntegerNumberField(String label, String placeholder) {
    this(label);
    setPlaceholder(placeholder);
  }

  /**
   * Constructs an empty {@code NumberField} with a value change listener.
   *
   * @param listener the value change listener
   *
   * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
   */
  public IntegerNumberField(
      ValueChangeListener<? super ComponentValueChangeEvent<IntegerNumberField, Integer>> listener) {
    this();
    addValueChangeListener(listener);
  }

  /**
   * Constructs an empty {@code NumberField} with a value change listener and a label.
   *
   * @param label the text to set as the label
   * @param listener the value change listener
   *
   * @see #setLabel(String)
   * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
   */
  public IntegerNumberField(String label,
      ValueChangeListener<? super ComponentValueChangeEvent<IntegerNumberField, Integer>> listener) {
    this(label);
    addValueChangeListener(listener);
  }

  /**
   * Constructs a {@code NumberField} with a value change listener, a label and an initial value.
   *
   * @param label the text to set as the label
   * @param initialValue the initial value
   * @param listener the value change listener
   *
   * @see #setLabel(String)
   * @see #setValue(Object)
   * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
   */
  public IntegerNumberField(String label, Integer initialValue,
      ValueChangeListener<? super ComponentValueChangeEvent<IntegerNumberField, Integer>> listener) {
    this(label);
    setValue(initialValue);
    addValueChangeListener(listener);
  }
}
