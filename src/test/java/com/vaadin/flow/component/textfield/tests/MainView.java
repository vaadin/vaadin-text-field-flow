package com.vaadin.flow.component.textfield.tests;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;

@Route("xxx")
public class MainView extends Div {

    public static class Entity {

        @NotNull
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Entity, name: " + name;
        }

    }

    final TextField name = new TextField();

    public MainView() {

        final Div layout = new Div();
        name.setLabel("Type your name here:");

        Entity entity = new Entity();
        BeanValidationBinder<Entity> b = new BeanValidationBinder<>(
                Entity.class);
        b.setRequiredConfigurator(null);
        // Binder<Entity> b = new Binder<>(Entity.class);
        b.bindInstanceFields(this);
        // ValidatorFactory fac = Validation.buildDefaultValidatorFactory();
        // Validator val = fac.getValidator();
        // val.va
        // b.forField(name)
        // .withValidator(a -> a != null && !a.isEmpty(), "eeeeeee")
        // .bind("name");

        b.setBean(entity);

        NativeButton button = new NativeButton("Check validity");
        button.addClickListener(e -> {
            ValidatorFactory factory = Validation
                    .buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Entity>> validate = validator
                    .validate(entity);
            String msg = String.format("Binder.isValid: %s, JSR 303 valid: %s",
                    b.validate().isOk() + " ; " + entity.getName(),
                    validate.isEmpty());
            System.out.println(msg);
        });

        add(name, button);

    }

}