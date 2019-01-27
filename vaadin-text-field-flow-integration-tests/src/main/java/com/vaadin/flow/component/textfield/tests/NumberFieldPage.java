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
package com.vaadin.flow.component.textfield.tests;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

/**
 * Test view for {@link NumberField}.
 */
@Route("number-field-test")
public class NumberFieldPage extends Div {

    /**
     * Constructs a basic layout with a text field.
     */
    public NumberFieldPage() {
        Div message = new Div();
        NumberField NumberField = new NumberField();
        NumberField.addValueChangeListener(event -> message
                .setText(String.format("Old value: '%s'. New value: '%s'.",
                        event.getOldValue(), event.getValue())));
        add(NumberField, message);

        NativeButton button = new NativeButton(
                "Set/unset text field read-only");
        button.setId("read-only");
        button.addClickListener(event -> NumberField
                .setReadOnly(!NumberField.isReadOnly()));
        add(button);

        NativeButton required = new NativeButton(
                "Set/unset field required property");
        required.setId("required");
        required.addClickListener(
                event -> NumberField.setRequiredIndicatorVisible(
                        !NumberField.isRequiredIndicatorVisible()));
        add(required);

        NumberField NumberFieldClear = new NumberField();
        NumberFieldClear.setId("clear-number-field");
        NumberFieldClear.getStyle().set("display", "block");
        NumberFieldClear.setClearButtonVisible(true);
        Div clearValueMessage = new Div();
        clearValueMessage.setId("clear-message");
        NumberFieldClear.addValueChangeListener(event -> clearValueMessage
                .setText(String.format("Old value: '%s'. New value: '%s'.",
                        event.getOldValue(), event.getValue())));
        add(NumberFieldClear, clearValueMessage);

        NumberField numberFieldStep = new NumberField();
        numberFieldStep.setId("step-number-field");
        numberFieldStep.setStep(0.5);
        numberFieldStep.setMin(0);
        numberFieldStep.setMax(10);
        numberFieldStep.setHasControls(true);
        Div stepValueMessage = new Div();
        stepValueMessage.setId("step-message");
        numberFieldStep.addValueChangeListener(event -> stepValueMessage
                .setText(String.format("Old value: '%s'. New value: '%s'.",
                        event.getOldValue(), event.getValue())));

        add(numberFieldStep, stepValueMessage);
    }
}