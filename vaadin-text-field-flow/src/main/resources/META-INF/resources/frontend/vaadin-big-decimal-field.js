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
(function() {
  customElements.whenDefined('vaadin-text-field').then(() => {

    class BigDecimalFieldElement extends Vaadin.TextFieldElement {

      static get is() {
        return 'vaadin-big-decimal-field';
      }

      ready() {
        super.ready();
        this.inputElement.setAttribute('inputmode', 'numeric');
        this._enabledCharPattern = "[\\d-+.eE]";
      }

    }

    customElements.define(BigDecimalFieldElement.is, BigDecimalFieldElement);

    /**
     * @namespace Vaadin
     */
    window.Vaadin = window.Vaadin || {};
    Vaadin.BigDecimalFieldElement = BigDecimalFieldElement;

  });
})();