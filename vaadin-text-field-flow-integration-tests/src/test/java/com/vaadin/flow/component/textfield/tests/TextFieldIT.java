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

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.demo.ComponentDemoTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Integration tests for the {@link TextField}.
 */
public class TextFieldIT extends ComponentDemoTest {

    @Override
    protected String getTestPath() {
        return "/vaadin-text-field/text-field";
    }

    @Test
    public void textFieldHasPlaceholder() {
        WebElement textField = layout
                .findElement(By.id("text-field-placeholder-id"));
        Assert.assertEquals(textField.getAttribute("placeholder"),
                "Placeholder");
    }

    @Test
    public void assertFocusShortcut() {
        WebElement shortcutField = layout.findElement(By.id("shortcut-field"));
        Assert.assertNull("TextField should not be focused before the shortcut event is triggered.",
                shortcutField.getAttribute("focused"));

        SendKeysHelper.sendKeys(driver, Keys.ALT, "1");
        Assert.assertTrue("TextField should be focused after the shortcut event is triggered.",
                shortcutField.getAttribute("focused").equals("true")
                        || shortcutField.getAttribute("focused").equals(""));
    }
}
