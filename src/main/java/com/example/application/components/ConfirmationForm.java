package com.example.application.components;

import java.time.LocalTime;

import com.example.application.data.Order;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Binder.Binding;

public class ConfirmationForm extends FormLayout {
    private final Binder<Order> binder;
    private CheckboxGroup<String> methods;
    private TimePicker time;
    private Binding<Order, LocalTime> timeBinding;

    public ConfirmationForm(Binder<Order> binder) {
        this.binder = binder;

        addMethodsField();
        addTimeField();
    }

    private void addMethodsField() {
        methods = new CheckboxGroup<>("Method");
        methods.setItems("By SMS", "By phone");
        methods.addValueChangeListener(event -> {
            boolean isTimeVisible = event.getValue().contains("By phone");
            time.setVisible(isTimeVisible);
            timeBinding.setValidatorsDisabled(!isTimeVisible);
        });
        binder.forField(methods).asRequired("The field is required").bind("confirmation.methods");
        add(methods);
    }

    private void addTimeField() {
        time = new TimePicker("When would you prefer us to call you?");
        time.setMin(LocalTime.of(9, 00));
        time.setMax(LocalTime.of(16, 00));
        time.setVisible(false);
        timeBinding = binder.forField(time).asRequired("The field is required").bind("confirmation.time");
        timeBinding.setValidatorsDisabled(true);
        add(time);
    }
}
