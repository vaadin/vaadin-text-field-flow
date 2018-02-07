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
package com.vaadin.flow.component.textfield;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.dom.Element;

/**
 * Mixin interface for text-field components that have prefix and suffix slots
 * for inserting components.
 *
 * @author Vaadin Ltd
 */
public interface HasPrefixAndSuffix extends HasElement {

    public static final String SLOT = "slot";
    public static final String PREFIX = "prefix";
    public static final String SUFFIX = "suffix";

    /**
     * Adds the given component into this field before the content, replacing
     * any existing prefix component.
     * <p>
     * This is most commonly used to add a simple icon or static text into the
     * field.
     * 
     * @param component
     *            the component to set, can be {@code null} to remove existing
     *            prefix component
     */
    default void setPrefixComponent(Component component) {
        getElement().getChildren()
                .filter(child -> PREFIX.equals(child.getAttribute(SLOT)))
                .forEach(getElement()::removeChild);

        if (component != null) {
            component.getElement().setAttribute(SLOT, PREFIX);
            getElement().appendChild(component.getElement());
        }
    }

    /**
     * Gets the component in the prefix slot of this field.
     * 
     * @return the prefix component of this field, or {@code null} if no prefix
     *         component has been set
     * @see #setPrefixComponent(Component)
     */
    default Component getPrefixComponent() {
        Optional<Element> element = getElement().getChildren()
                .filter(child -> PREFIX.equals(child.getAttribute(SLOT)))
                .findFirst();
        if (element.isPresent()) {
            return element.get().getComponent().get();
        }
        return null;
    }

    /**
     * Adds the given component into this field after the content, replacing any
     * existing suffix component.
     * <p>
     * This is most commonly used to add a simple icon or static text into the
     * field.
     * 
     * @param component
     *            the component to set, can be {@code null} to remove existing
     *            suffix component
     */
    default void setSuffixComponent(Component component) {
        getElement().getChildren()
                .filter(child -> SUFFIX.equals(child.getAttribute(SLOT)))
                .forEach(getElement()::removeChild);

        if (component != null) {
            component.getElement().setAttribute(SLOT, SUFFIX);
            getElement().appendChild(component.getElement());
        }
    }

    /**
     * Gets the component in the suffix slot of this field.
     * 
     * @return the suffix component of this field, or {@code null} if no suffix
     *         component has been set
     * @see #setPrefixComponent(Component)
     */
    default Component getSuffixComponent() {
        return getElement().getChildren()
                .filter(child -> SUFFIX.equals(child.getAttribute(SLOT)))
                .findFirst().map(Element::getComponent).orElse(null)
                .orElse(null);
    }
}
