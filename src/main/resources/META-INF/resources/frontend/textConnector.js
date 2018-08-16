window.Vaadin.Flow.textConnector = {
        
        disableClientValidation: function (textComponent){
            if ( typeof textComponent.$validation == 'undefined'){
                textComponent.$validation = textComponent.checkValidity;
                textComponent.checkValidity = function() { return !textComponent.invalid; };
            }
        },

        enableClientValidation: function (textComponent){
            if ( textComponent.$validation ){
                textComponent.checkValidity = textComponent.$validation;
                delete textComponent.$validation;
            }
        }

}
