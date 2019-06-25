package com.vaadin.flow.component.textfield.tests;

import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.demo.ComponentDemoTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Integration tests for the {@link NumberField}.
 */
public class NumberFieldIT extends ComponentDemoTest {

    @Override
    protected String getTestPath() {
        return "/vaadin-text-field/prefix-and-suffix";
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
}
