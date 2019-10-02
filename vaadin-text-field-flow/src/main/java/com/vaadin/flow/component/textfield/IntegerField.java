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

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;

/**
 * Server-side component for the {@code vaadin-integer-field} element.
 *
 * @author Vaadin Ltd.
 */
@Tag("vaadin-integer-field")
@HtmlImport("frontend://bower_components/vaadin-text-field/src/vaadin-integer-field.html")
@JsModule("@vaadin/vaadin-text-field/src/vaadin-integer-field.js")
public class IntegerField extends AbstractNumberField<IntegerField, Integer> {

    private static Integer presentationToModel(String presentation) {
        if (presentation == null || presentation.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(presentation);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String modelToPresentation(Integer model) {
        return model == null ? "" : model.toString();
    }

    /**
     * Constructs an empty {@code IntegerField}.
     */
    public IntegerField() {
        super(IntegerField::presentationToModel,
                IntegerField::modelToPresentation, Integer.MIN_VALUE,
                Integer.MAX_VALUE, 1);
    }

    /**
     * Constructs an empty {@code IntegerField} with the given label.
     *
     * @param label
     *            the text to set as the label
     */
    public IntegerField(String label) {
        this();
        setLabel(label);
    }

    /**
     * Constructs an empty {@code IntegerField} with the given label and
     * placeholder text.
     *
     * @param label
     *            the text to set as the label
     * @param placeholder
     *            the placeholder text to set
     */
    public IntegerField(String label, String placeholder) {
        this(label);
        setPlaceholder(placeholder);
    }

    /**
     * Constructs an empty {@code IntegerField} with a value change listener.
     *
     * @param listener
     *            the value change listener
     *
     * @see #addValueChangeListener(ValueChangeListener)
     */
    public IntegerField(
            ValueChangeListener<? super ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this();
        addValueChangeListener(listener);
    }

    /**
     * Constructs an empty {@code IntegerField} with a value change listener and
     * a label.
     *
     * @param label
     *            the text to set as the label
     * @param listener
     *            the value change listener
     *
     * @see #setLabel(String)
     * @see #addValueChangeListener(ValueChangeListener)
     */
    public IntegerField(String label,
            ValueChangeListener<? super ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this(label);
        addValueChangeListener(listener);
    }

    /**
     * Constructs a {@code IntegerField} with a value change listener, a label
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
     * @see #addValueChangeListener(ValueChangeListener)
     */
    public IntegerField(String label, Integer initialValue,
            ValueChangeListener<? super ComponentValueChangeEvent<IntegerField, Integer>> listener) {
        this(label);
        setValue(initialValue);
        addValueChangeListener(listener);
    }

}
