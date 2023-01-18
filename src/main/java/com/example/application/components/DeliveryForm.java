package com.example.application.components;

import java.time.LocalDateTime;
import java.util.List;

import com.example.application.data.Order;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

public class DeliveryForm extends FormLayout {
    private final Binder<Order> binder;
    private RadioButtonGroup<String> method;
    private ComboBox<String> city;
    private TextField address;
    private DateTimePicker dateTime;
    private TextArea comment;

    public DeliveryForm(Binder<Order> binder) {
        this.binder = binder;

        addMethodField();
        addCityField();
        addAddressField();
        addDateTimeField();
        addCommentField();
    }

    private void addMethodField() {
        method = new RadioButtonGroup<>("Method");
        method.setItems("Posti", "UPS", "Home");
        binder.forField(method)
                .asRequired("The field is required")
                .bind("delivery.method");
        add(method);
    }

    private void addCityField() {
        city = new ComboBox<>("City");
        city.setItems(List.of("Helsinki", "Turku"));
        city.addValueChangeListener(event -> {
            String value = event.getValue();
            LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

            dateTime.setEnabled(true);

            if (value.equals("Helsinki")) {
                dateTime.setMin(now.plusDays(0).withHour(10));
                dateTime.setMax(now.plusDays(10).withHour(22));
            } else if (value.equals("Turku")) {
                dateTime.setMin(now.plusDays(20).withHour(10));
                dateTime.setMax(now.plusDays(30).withHour(22));
            }
        });
        binder.forField(city)
                .asRequired("The field is required")
                .bind("delivery.city");
        add(city);
    }

    private void addAddressField() {
        address = new TextField("Address");
        address.setHelperText("Example: postal code, district, street, apartment");
        binder.forField(address)
                .asRequired("The field is required")
                .bind("delivery.address");
        add(address);
    }

    private void addDateTimeField() {
        dateTime = new DateTimePicker("Suitable date and time");
        dateTime.setEnabled(false);
        binder.forField(dateTime)
                .asRequired("The field is required")
                .bind("delivery.dateTime");
        add(dateTime);
    }

    private void addCommentField() {
        comment = new TextArea("Additional information (optional)");
        comment.setMaxLength(350);
        comment.setValueChangeMode(ValueChangeMode.EAGER);
        comment.setHelperText("0 / " + comment.getMaxLength());
        comment.addValueChangeListener(event -> {
            comment.setHelperText(event.getValue().length() + " / " + comment.getMaxLength());
        });
        binder.forField(comment).bind("delivery.comment");
        add(comment);
    }
}
