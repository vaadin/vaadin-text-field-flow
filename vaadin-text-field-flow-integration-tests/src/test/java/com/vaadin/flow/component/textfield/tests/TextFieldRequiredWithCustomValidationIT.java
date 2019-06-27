package com.vaadin.flow.component.textfield.tests;

import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

@TestPath("required-field-custom-validator")
public class TextFieldRequiredWithCustomValidationIT extends AbstractComponentIT {

    @Test
    public void requiredAndCustomValidationOnServerSide_initialStateIsInvalid_changingToValidValueResetsInvalidFlag() {
        open();

        TestBenchElement textField = $("vaadin-text-field").first();
        TestBenchElement input = textField.$("input").first();

        Assert.assertEquals(Boolean.TRUE.toString(), textField.getAttribute("invalid"));

        while (!input.getAttribute("value").isEmpty()) {
            input.sendKeys(Keys.BACK_SPACE);
        }
        input.sendKeys("Valid");
        input.sendKeys(Keys.ENTER);

        Assert.assertEquals(Boolean.FALSE.toString(), textField.getAttribute("invalid"));
    }
}
