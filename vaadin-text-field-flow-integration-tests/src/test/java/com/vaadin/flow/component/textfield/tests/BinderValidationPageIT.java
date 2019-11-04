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

import com.vaadin.flow.component.textfield.testbench.BigDecimalFieldElement;
import com.vaadin.flow.component.textfield.testbench.EmailFieldElement;
import com.vaadin.flow.component.textfield.testbench.IntegerFieldElement;
import com.vaadin.flow.component.textfield.testbench.NumberFieldElement;
import com.vaadin.flow.component.textfield.testbench.PasswordFieldElement;
import com.vaadin.flow.component.textfield.testbench.TextAreaElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;

/**
 * Verify that when component's internal validation passes, but Binder
 * validation fails, Binder validation status is effective.
 */
@TestPath("binder-validation")
public class BinderValidationPageIT extends AbstractComponentIT {

    @Before
    public void init() {
        open();
    }

    @Test
    public void textField_internalValidationPass_binderValidationFail_fieldInvalid() {
        TextFieldElement field = $(TextFieldElement.class).first();
        field.setValue("a");
        assertInvalid(field);
    }

    @Test
    public void textField_internalValidationPass_binderValidationFail_validateClient_fieldInvalid() {
        TextFieldElement field = $(TextFieldElement.class).first();
        field.setValue("a");
        field.getCommandExecutor().executeScript(
                "arguments[0].validate(); arguments[0].immediateInvalid = arguments[0].invalid;",
                field);
        assertInvalid(field);
        // State before server roundtrip (avoid flash of valid state)
        Assert.assertTrue("Unexpected immediateInvalid state",
                field.getPropertyBoolean("immediateInvalid"));
    }

    @Test
    public void textField_internalValidationPass_binderValidationFail_setClientValid_serverFieldInvalid() {
        TextFieldElement field = $(TextFieldElement.class).first();
        field.setValue("a");
        field.getCommandExecutor().executeScript("arguments[0].invalid = false",
                field);
        Assert.assertEquals(field.getLabel(), "invalid");
    }

    @Test
    public void textField_internalValidationPass_binderValidationFail_checkValidity() {
        TextFieldElement field = $(TextFieldElement.class).first();
        field.setValue("a");
        field.getCommandExecutor().executeScript(
                "arguments[0].checkedValidity = arguments[0].checkValidity()",
                field);
        // Ensure checkValidity still works (used by preventinvalidinput)
        Assert.assertTrue("Unexpected checkedValidity state",
                field.getPropertyBoolean("checkedValidity"));
    }

    @Test
    public void textArea_internalValidationPass_binderValidationFail_fieldInvalid() {
        TextAreaElement field = $(TextAreaElement.class).first();
        field.setValue("a");
        assertInvalid(field);
    }

    @Test
    public void passwordField_internalValidationPass_binderValidationFail_fieldInvalid() {
        PasswordFieldElement field = $(PasswordFieldElement.class).first();
        field.setValue("a");
        assertInvalid(field);
    }

    @Test
    public void emailField_internalValidationPass_binderValidationFail_fieldInvalid() {
        EmailFieldElement field = $(EmailFieldElement.class).first();
        field.setValue("foo@bar.com");
        assertInvalid(field);
    }

    @Test
    public void bigDecimalField_internalValidationPass_binderValidationFail_fieldInvalid() {
        BigDecimalFieldElement field = $(BigDecimalFieldElement.class).first();
        field.setValue("1");
        assertInvalid(field);
    }

    @Test
    public void numberField_internalValidationPass_binderValidationFail_fieldInvalid() {
        NumberFieldElement field = $(NumberFieldElement.class).first();
        field.setValue("1");
        assertInvalid(field);
    }

    @Test
    public void integerField_internalValidationPass_binderValidationFail_fieldInvalid() {
        IntegerFieldElement field = $(IntegerFieldElement.class).first();
        field.setValue("1");
        assertInvalid(field);
    }

    private void assertInvalid(TestBenchElement field) {
        Assert.assertTrue("Unexpected invalid state",
                field.getPropertyBoolean("invalid"));
        Assert.assertEquals(
                "Expected to have error message configured in the Binder Validator",
                BinderValidationPage.BINDER_ERROR_MSG,
                field.getPropertyString("errorMessage"));
    }
}
