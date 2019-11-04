window.Vaadin.Flow.fieldConnector = {
    patchValidation: function(textField) {
        textField.validate = function () {
            return this.checkValidity();
        }
    }
};