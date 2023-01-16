package com.example.application.components;

import java.util.Objects;

import com.example.application.data.Order;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;

public class ConfirmationForm extends FormLayout {
    private CheckboxGroup<String> methods;
    private TimePicker time;

    public ConfirmationForm(Binder<Order> binder) {
        methods = new CheckboxGroup<>("Method");
        methods.setItems("By SMS", "By phone");
        methods.addValueChangeListener(event -> {
            time.setVisible(event.getValue().contains("By phone"));
        });
        binder.forField(methods).asRequired("The field is required").bind("confirmation.methods");
        add(methods);

        time = new TimePicker("When would you prefer us to call you?");
        time.setVisible(false);
        binder.forField(time).asRequired((value, context) -> {
            if (methods.getValue().contains("By phone") && Objects.equals(value, time.getEmptyValue())) {
                return ValidationResult.error("The field is required");
            }

            return ValidationResult.ok();
        }).bind("confirmation.time");
        add(time);
    }
}
