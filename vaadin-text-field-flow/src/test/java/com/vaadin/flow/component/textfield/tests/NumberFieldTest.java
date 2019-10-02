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

import com.vaadin.flow.component.textfield.NumberField;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for the {@link NumberField}.
 */
public class NumberFieldTest extends TextFieldTest{

    @Test
    public void setValueNull() {
        NumberField numberField = new NumberField();
        assertNull("Value should be null", numberField.getValue());
        numberField.setValue(null);
    }

    @Test
    public void initialValuePropertyValue() {
        NumberField numberField = new NumberField();
        assertEquals(numberField.getEmptyValue(),
                numberField.getElement().getProperty("value"));
    }

    @Test
    public void testPropertyValue() {
        testValueProperty(1.0d, "1");
        testValueProperty(2.0d, "2");
        testValueProperty(5.0d, "5");
        testValueProperty(9.0d, "9");
        testValueProperty(21.4d, "21.4");
        testValueProperty(123456789.01d, "123456789.01");
        testValueProperty(-1.050d, "-1.05");
    }

    private void testValueProperty(double value, String expected) {
        final NumberField numberField = new NumberField();
        numberField.setValue(value);
        assertEquals(expected, numberField.getElement().getProperty("value"));
    }
}
