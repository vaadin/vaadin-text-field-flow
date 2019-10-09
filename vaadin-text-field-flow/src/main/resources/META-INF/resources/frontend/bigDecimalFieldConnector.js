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
window.Vaadin.Flow.bigDecimalFieldConnector = {

  preventInvalidInput: function(bigDecimalField) {
    if (bigDecimalField.$connector) {
      return;
    }
    bigDecimalField.$connector = true;

    // Based on <vaadin-integer-field> https://github.com/vaadin/vaadin-text-field/pull/415
    bigDecimalField.addEventListener('keydown', e => {

      const shouldAcceptKey = (event.metaKey || event.ctrlKey)
        || !event.key // allow typing anything if event.key is not supported
        || event.key.length !== 1 // allow "Backspace", "ArrowLeft" etc.
        || /^[\d-+.eE]$/.test(event.key);

      if (!shouldAcceptKey) {
        e.preventDefault();
      }
    });
  }
};
