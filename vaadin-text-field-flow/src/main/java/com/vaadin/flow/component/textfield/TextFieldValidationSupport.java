package com.vaadin.flow.component.textfield;

import com.vaadin.flow.component.HasValue;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Utility class for performing server-side validation of string values in text fields. This is needed because it is
 * possible to circumvent the client side validation constraints using browser development tools.
 *
 * @author Vaadin Ltd
 */
final class TextFieldValidationSupport implements Serializable {

    private final HasValue<?, String> field;
    private boolean required;
    private Integer minLength;
    private Integer maxLength;
    private Pattern pattern;

    TextFieldValidationSupport(HasValue<?, String> field) {
        this.field = field;
    }

    /**
     * @see GeneratedVaadinTextField#setRequired
     */
    void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @see GeneratedVaadinTextField#setMinlength(double)
     */
    void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    /**
     * @see GeneratedVaadinTextField#setMaxlength(double)
     */
    void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * @see GeneratedVaadinTextField#setPattern(String)
     */
    void setPattern(String pattern) {
        this.pattern = pattern == null || pattern.isEmpty() ? null : Pattern.compile(pattern);
    }

    /**
     * Test if value is invalid for the field.
     * @param value value to be tested.
     * @return <code>true</code> if the value is invalid.
     */
    boolean isInvalid(String value) {
        if (required && Objects.equals(field.getEmptyValue(), value)) {
            return true;
        }
        if (value != null && maxLength != null && value.length() > maxLength) {
            return true;
        }
        if (value != null && minLength != null && value.length() < minLength) {
            return true;
        }
        if (value != null && pattern != null && !pattern.matcher(value).matches()) {
            return true;
        }
        return false;
    }
}
