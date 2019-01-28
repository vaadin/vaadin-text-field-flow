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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

/**
 * Integration tests for {@link NumberField}.
 */
@TestPath("number-field-test")
public class NumberFieldPageIT extends AbstractComponentIT {

    @Before
    public void init() {
        open();
    }

    @Test
    public void assertReadOnly() {
        WebElement webComponent = findElement(
                By.tagName("vaadin-number-field"));

        Assert.assertNull(webComponent.getAttribute("readonly"));

        WebElement button = findElement(By.id("read-only"));
        button.click();

        waitUntil(
                driver -> "true".equals(getProperty(webComponent, "readonly")));

        button.click();

        waitUntil(driver -> "false"
                .equals(getProperty(webComponent, "readonly")));
    }

    @Test
    public void assertRequired() {
        WebElement webComponent = findElement(
                By.tagName("vaadin-number-field"));

        Assert.assertNull(webComponent.getAttribute("required"));

        WebElement button = findElement(By.id("required"));
        button.click();
        waitUntil(
                driver -> "true".equals(getProperty(webComponent, "required")));

        button.click();
        waitUntil(driver -> "false"
                .equals(getProperty(webComponent, "required")));
    }

    @Test
    public void assertClearValue() {
        WebElement field = findElement(By.id("clear-number-field"));

        WebElement input = getInShadowRoot(field, By.cssSelector("input"));
        input.sendKeys("300");
        blur();

        WebElement clearButton = getInShadowRoot(field, By.cssSelector("[part~='clear-button']"));
        clearButton.click();

        String value = findElement(By.id("clear-message")).getText();
        Assert.assertEquals("Old value: '300.0'. New value: 'null'.", value);
    }

    @Test
    public void assertStepValue() {
        WebElement field = findElement(By.id("step-number-field"));

        WebElement increaseButton = getInShadowRoot(field, By.cssSelector("[part~='increase-button']"));
        increaseButton.click();

        String value = findElement(By.id("step-message")).getText();
        Assert.assertEquals("Old value: 'null'. New value: '0.5'.", value);
    }
}
