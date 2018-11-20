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
package com.vaadin.flow.component.textfield.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;

import static com.vaadin.flow.component.textfield.TextFieldVariant.LUMO_ALIGN_RIGHT;
import static com.vaadin.flow.component.textfield.TextFieldVariant.LUMO_SMALL;

public class ValueChangeModeButtonProvider {
    private final HasValueChangeMode elementWithChangeMode;

    public static String getRadioId(ValueChangeMode mode, String prefix) {
        return prefix + "-" + mode.name();
    }

    ValueChangeModeButtonProvider(
            HasValueChangeMode elementWithChangeMode) {
        this.elementWithChangeMode = elementWithChangeMode;
    }

    private Div addRadio(ValueChangeMode changeMode) {
        Input radioBtn = new Input();
        radioBtn.setType("radio");
        String simpleName = elementWithChangeMode.getClass().getSimpleName();
        radioBtn.setId(getRadioId(changeMode, simpleName));
        radioBtn.getElement().setAttribute("name", simpleName + "-change-mode");
        if (elementWithChangeMode.getValueChangeMode() == changeMode) {
            radioBtn.getElement().setAttribute("checked", "on");
        }

        Label label = new Label(changeMode.name());
        label.setFor(radioBtn);

        radioBtn.getElement().addEventListener("change",
                event -> elementWithChangeMode.setValueChangeMode(changeMode));
        return new Div(radioBtn, label);
    }

    private Component getTimeoutInput() {
        TextField field = new TextField("Value change timeout");
        field.setSuffixComponent(new Span("msec"));
        field.setTitle("ValueChangeTimeout");
        field.setPattern("[0-9]*");
        field.setMaxLength(4);
        field.setPreventInvalidInput(true);
        field.addThemeVariants(LUMO_ALIGN_RIGHT, LUMO_SMALL);
        field.setValue(elementWithChangeMode.getValueChangeTimeout() + "");
        field.addValueChangeListener(this::onTimeoutChange);
        field.getStyle().set("vertical-align", "bottom");
        field.getStyle().set("padding-left", "50px");
        return field;
    }

    private void onTimeoutChange(HasValue.ValueChangeEvent<String> event) {
        try {
            elementWithChangeMode.setValueChangeTimeout(new Integer(event.getValue()));
        } catch (NumberFormatException e) {
            event.getHasValue().setValue(elementWithChangeMode.getValueChangeTimeout() + "");
        }
    }

    Component getValueChangeModeRadios() {
        Div container = new Div();
        Div radios = new Div();
        for (ValueChangeMode changeMode : ValueChangeMode.values()) {
            Div radioLine = addRadio(changeMode);
            radios.add(radioLine);
        }
        radios.getStyle().set("display", "inline-block");
        container.add(radios, getTimeoutInput());

        return container;
    }

}
