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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.internal.StateNode;
import elemental.json.JsonValue;

/**
 * Utility class for text field mixin web components to disable client
 * side validation.
 *
 * @author Vaadin Ltd
 */
final class fieldValidationUtil {

    fieldValidationUtil() {
        // utility class should not be instantiated
    }

    static void disableClientValidation(Component component) {
        execJS(component,
                "$0.validate = function () {return this.checkValidity();}");
    }

    private static void execJS(Component component, String js) {
        StateNode node = component.getElement().getNode();

        node.runWhenAttached(ui -> ui.getInternals().getStateTree()
                .beforeClientResponse(node, context ->
                        ui.getPage().executeJs(js, component.getElement())
                ));
    }

}
