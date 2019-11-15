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

import org.junit.Test;

import com.vaadin.flow.component.textfield.NumberField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for the {@link NumberField}.
 */
public class NumberFieldTest extends TextFieldTest {

    @Override
    @Test
    public void setValueNull() {
        NumberField numberField = new NumberField();
        assertNull("Value should be null", numberField.getValue());
        numberField.setValue(null);
    }

    @Override
    @Test
    public void initialValuePropertyValue() {
        NumberField numberField = new NumberField();
        assertEquals(numberField.getEmptyValue(),
                numberField.getElement().getProperty("value"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertStepIsNotNegative() {
        NumberField numberField = new NumberField();
        numberField.setStep(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertStepGreaterThanZero() {
        NumberField numberField = new NumberField();
        numberField.setStep(0);
    }

    @Test
    public void setValue_valuePropertyFormatted() {
        testValuePropertyFormatting(1.0d, "1");
        testValuePropertyFormatting(2.0d, "2");
        testValuePropertyFormatting(5.0d, "5");
        testValuePropertyFormatting(9.0d, "9");
        testValuePropertyFormatting(0.3d, "0.3");
        testValuePropertyFormatting(0.5d, "0.5");
        testValuePropertyFormatting(0.7d, "0.7");
        testValuePropertyFormatting(21.4d, "21.4");
        testValuePropertyFormatting(123456789.01d, "123456789.01");
        testValuePropertyFormatting(-1.050d, "-1.05");
    }

    private void testValuePropertyFormatting(double value, String expected) {
        final NumberField numberField = new NumberField();
        numberField.setValue(value);
        assertEquals(expected, numberField.getElement().getProperty("value"));
    }
}
