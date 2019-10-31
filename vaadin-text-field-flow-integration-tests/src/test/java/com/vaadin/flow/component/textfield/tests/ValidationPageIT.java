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

import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

@TestPath("validation")
public class ValidationPageIT extends AbstractComponentIT {

    private TextFieldElement field;

    @Before
    public void init() {
        open();
        field = $(TextFieldElement.class).first();
    }

    @Test
    public void validPattern_invalidBinderValidator_fieldInvalid() {
        field.setValue("a");
        assertInvalid(true);
        assertErrorMessageFromBinderValidator();
    }

    private void assertInvalid(boolean expected) {
        Assert.assertTrue("Unexpected invalid state",
                field.getPropertyBoolean("invalid"));
    }

    private void assertErrorMessageFromBinderValidator() {
        Assert.assertEquals(
                "Expected to have error message configured in the Binder Validator",
                "binder", field.getPropertyString("errorMessage"));
    }
}
