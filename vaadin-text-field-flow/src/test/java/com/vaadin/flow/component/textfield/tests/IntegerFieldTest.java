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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.textfield.IntegerField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntegerFieldTest extends TextFieldTest {

    private IntegerField field;

    @Before
    public void init() {
        field = new IntegerField();
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
                "The default max of IntegerField should be the largest possible int value",
                2147483647, field.getMax());
        Assert.assertEquals(
                "The default min of IntegerField should be the smallest possible int value",
                -2147483648, field.getMin());
        Assert.assertEquals("The default step of IntegerField should be 1", 1,
                field.getStep());
    }

    @Test
    public void assertMinValidation() {
        field.setValue(-10);
        Assert.assertFalse(field.isInvalid());
        field.setMin(-9);
        Assert.assertTrue(field.isInvalid());
        field.setValue(-9);
        Assert.assertFalse(field.isInvalid());
    }

    @Test
    public void assertMaxValidation() {
        field.setValue(100);
        Assert.assertFalse(field.isInvalid());
        field.setMax(99);
        Assert.assertTrue(field.isInvalid());
        field.setValue(99);
        Assert.assertFalse(field.isInvalid());
    }

}
