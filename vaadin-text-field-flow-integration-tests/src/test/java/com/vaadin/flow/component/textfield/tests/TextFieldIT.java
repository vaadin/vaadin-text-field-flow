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
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.demo.ValueChangeModeButtonProvider;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.flow.demo.ComponentDemoTest;

/**
 * Integration tests for the {@link TextField}.
 */
public class TextFieldIT extends ComponentDemoTest {

    @Override
    protected String getTestPath() {
        return "/vaadin-text-field";
    }

    @Test
    public void valueChangeListenerReportsCorrectValues() {
        WebElement textFieldValueDiv = layout
                .findElement(By.id("text-field-value"));
        WebElement textField = layout
                .findElement(By.id("text-field-with-value-change-listener"));

        updateValues(textFieldValueDiv, textField, true);
        layout.findElement(
                By.id(ValueChangeModeButtonProvider.TOGGLE_BUTTON_ID)).click();
        updateValues(textFieldValueDiv, textField, false);
    }

    private void updateValues(WebElement textFieldValueDiv,
            WebElement textField, boolean toggleBlur) {
        textField.sendKeys("a");
        if (toggleBlur) {
            blur();
        }
        waitUntilTextsEqual("Text field value changed from '' to 'a'",
                textFieldValueDiv);

        textField.sendKeys(Keys.BACK_SPACE);
        if (toggleBlur) {
            blur();
        }
        waitUntilTextsEqual("Text field value changed from 'a' to ''",
                textFieldValueDiv);
    }

    @Test
    public void textFieldHasPlaceholder() {
        WebElement textField = layout
                .findElement(By.id("text-field-with-value-change-listener"));
        Assert.assertEquals(textField.getAttribute("placeholder"),
                "placeholder text");
    }

    private void waitUntilTextsEqual(String expected, WebElement valueDiv) {
        waitUntil(driver -> expected.equals(valueDiv.getText()));
    }

    @Test
    public void dollarFieldHasDollarPrefix() {
        WebElement dollarField = layout.findElement(By.id("dollar-field"));
        WebElement span = dollarField.findElement(By.tagName("span"));

        Assert.assertEquals("$", span.getText());

        int spanX = span.getLocation().getX();
        int middleX = dollarField.getLocation().getX()
                + dollarField.getSize().getWidth() / 2;

        Assert.assertTrue(
                "The dollar sign should be located on the left side of the text field",
                spanX < middleX);
    }

    @Test
    public void euroFieldHasEuroSuffix() {
        WebElement euroField = layout.findElement(By.id("euro-field"));
        WebElement span = euroField.findElement(By.tagName("span"));

        Assert.assertEquals("€", span.getText());

        int spanX = span.getLocation().getX();
        int middleX = euroField.getLocation().getX()
                + euroField.getSize().getWidth() / 2;

        Assert.assertTrue(
                "The euro sign should be located on the right side of the text field",
                spanX > middleX);
    }

    @Test
    public void disabledTextFieldNotUpdating() {
        WebElement textField = layout.findElement(By.id("disabled-text-field"));
        WebElement message = layout
                .findElement(By.id("disabled-text-field-message"));
        Assert.assertEquals("", message.getText());

        executeScript("arguments[0].removeAttribute(\"disabled\");", textField);
        textField.sendKeys("abc");
        blur();

        message = layout.findElement(By.id("disabled-text-field-message"));
        Assert.assertEquals("", message.getText());
    }

    @Test
    public void assertVariants() {
        verifyThemeVariantsBeingToggled();
    }

    @Test
    public void assertFocusShortcut(){
        TextFieldElement shortcutField = $(TextFieldElement.class).id("shortcut-field");
        Assert.assertNull("TextField should not be focused before the shortcut event is triggered.",
                shortcutField.getAttribute("focused"));

        SendKeysHelper.sendKeys(driver, Keys.ALT, "1");
        Assert.assertTrue("TextField should be focused after the shortcut event is triggered.",
                shortcutField.getAttribute("focused").equals("true")
                        || shortcutField.getAttribute("focused").equals(""));
    }
}
