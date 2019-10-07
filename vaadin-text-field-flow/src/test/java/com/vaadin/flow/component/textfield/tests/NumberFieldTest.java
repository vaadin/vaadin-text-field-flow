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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.textfield.NumberField;

/**
 * Tests for the {@link NumberField}.
 */
public class NumberFieldTest extends TextFieldTest {

    private NumberField field;

    @Before
    public void init() {
        field = new NumberField();
    }

    @Override
    @Test
    public void setValueNull() {
        assertNull("Value should be null", field.getValue());
        field.setValue(null);
    }

    @Override
    @Test
    public void initialValuePropertyValue() {
        assertEquals(field.getEmptyValue(),
                field.getElement().getProperty("value"));
    }

    @Test
    public void assertDefaultValuesForMinMaxStep() {
        Assert.assertEquals(
                "The default max of NumberField should be the largest possible double value",
                Double.POSITIVE_INFINITY, field.getMax(), 0);
        Assert.assertEquals(
                "The default min of NumberField should be the smallest possible double value",
                Double.NEGATIVE_INFINITY, field.getMin(), 0);
        Assert.assertEquals("The default step of NumberField should be 1.0",
                1.0, field.getStep(), 0);
    }

    @Test
    public void setInitialMinMaxRequired_shouldNotInvalidateField() {
        field.setRequiredIndicatorVisible(true);
        field.setMin(3);
        Assert.assertFalse(field.isInvalid());
        field.setMin(-5);
        field.setMax(-1);
        Assert.assertFalse(field.isInvalid());
    }

    @Test
    public void assertMinValidation() {
        field.setValue(-10.5);
        Assert.assertFalse(field.isInvalid());

        field.setMin(-10.3);
        field.setValue(-10.4); // need to update value to run validation
        Assert.assertTrue(field.isInvalid());

        field.setValue(-10.3);
        Assert.assertFalse(field.isInvalid());
    }

    @Test
    public void assertMaxValidation() {
        field.setValue(100.0);
        Assert.assertFalse(field.isInvalid());

        field.setMax(99.999);
        field.setValue(99.9991); // need to update value to run validation
        Assert.assertTrue(field.isInvalid());

        field.setValue(99.999);
        Assert.assertFalse(field.isInvalid());
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
