/*
 * Copyright 2000-2018 Vaadin Ltd.
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
package com.vaadin.flow.component.textfield.testbench;

import com.vaadin.flow.component.common.testbench.HasLabel;
import com.vaadin.flow.component.common.testbench.HasPlaceholder;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

import java.util.Collections;
import java.util.Map;

/**
 * A TestBench element representing a <code>&lt;vaadin-text-field&gt;</code>
 * element.
 */
@Element("vaadin-text-field")
public class TextFieldElement extends TestBenchElement
        implements HasStringValueProperty, HasLabel, HasPlaceholder {

    @Override
    public void setValue(String string) {
        HasStringValueProperty.super.setValue(string);
        EventsUtil.dispatchEvent(this, "change",
                Collections.singletonMap("bubbles", true));
        dispatchEvent("blur");
    }

    private static class EventsUtil {

        private EventsUtil() {
            // Util methods only
        }

       /**
        * Dispatches (fires) a custom event of the given type on the element with
        * the given properties
        *
        * @param element
        *            element that will dispatch the event
        * @param eventType
        *            the type of custom event to dispatch
        * @param customEventInit
        *            map with properties and values that will be used to initialize
        *            the event
        */
        private static void dispatchEvent(TestBenchElement element, String eventType,
                Map<String, Object> customEventInit) {
            element.executeScript(
                "arguments[0].dispatchEvent(new CustomEvent(arguments[1], arguments[2]));",
                element, eventType, customEventInit);
        }
    }
}
