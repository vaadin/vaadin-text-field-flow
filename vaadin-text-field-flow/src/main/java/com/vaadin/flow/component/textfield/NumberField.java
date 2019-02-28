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

import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableFunction;

/**
 * Server-side component for the {@code vaadin-number-field} element.
 *
 * @author Vaadin Ltd.
 */
public class NumberField
        extends AbstractNumberField<NumberField, Double>  {
    
    private static final SerializableFunction<String, Double> PARSER = valueFromClient -> valueFromClient == null
            || valueFromClient.isEmpty() ? null
                    : Double.parseDouble(valueFromClient);

    private static final SerializableFunction<Double, String> FORMATTER = valueFromModel -> valueFromModel == null
            ? ""
            : valueFromModel.toString();

    /**
     * Constructs an empty {@code NumberField}.
     */
    public NumberField() {
        super(null, null, String.class, PARSER, FORMATTER);
        setValueChangeMode(ValueChangeMode.ON_CHANGE);
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
     * Constructs an empty {@code NumberField} with a value change listener
     * and a label.
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
}
