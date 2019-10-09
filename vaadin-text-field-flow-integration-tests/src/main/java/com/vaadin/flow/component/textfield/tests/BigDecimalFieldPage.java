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
package com.vaadin.flow.component.textfield.tests;

import java.math.BigDecimal;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.router.Route;

@Route("big-decimal-field-test")
public class BigDecimalFieldPage extends Div {

    private Div messageContainer;

    public BigDecimalFieldPage() {
        messageContainer = new Div();
        messageContainer.setId("messages");

        BigDecimalField field = new BigDecimalField();
        field.addValueChangeListener(this::logValueChangeEvent);

        add(field, messageContainer);
    }

    private void logValueChangeEvent(
            ComponentValueChangeEvent<BigDecimalField, BigDecimal> event) {
        String text = String.format("Old value: '%s'. New value: '%s'.",
                event.getOldValue(), event.getValue());
        Paragraph paragraph = new Paragraph(text);
        messageContainer.add(paragraph);
    }
}
