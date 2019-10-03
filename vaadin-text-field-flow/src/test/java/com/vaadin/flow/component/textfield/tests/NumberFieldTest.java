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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.textfield.NumberField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        // For some reason assertEquals with doubles (at least when using
        // Double.MAX_VALUE) is not reliable
        Assert.assertTrue(
                "The default max of NumberField should be the largest possible double value",
                field.getMax() - Double.MAX_VALUE == 0.0);
        Assert.assertTrue(
                "The default min of NumberField should be the smallest possible double value",
                field.getMin() + Double.MAX_VALUE == 0.0);
        Assert.assertTrue("The default step of NumberField should be 1.0",
                field.getStep() - 1.0 == 0.0);
    }

    @Test
    public void assertMinValidation() {
        field.setValue(-10.5);
        Assert.assertFalse(field.isInvalid());
        field.setMin(-10.4);
        Assert.assertTrue(field.isInvalid());
        field.setValue(-10.4);
        Assert.assertFalse(field.isInvalid());
    }

    @Test
    public void assertMaxValidation() {
        field.setValue(100.0);
        Assert.assertFalse(field.isInvalid());
        field.setMax(99.999);
        Assert.assertTrue(field.isInvalid());
        field.setValue(99.999);
        Assert.assertFalse(field.isInvalid());
    }

}
