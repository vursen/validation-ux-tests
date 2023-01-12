package com.example.application.components;

import java.time.LocalDateTime;
import java.util.List;

import com.example.application.data.Order;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

public class DeliveryForm extends BasicForm {
    private RadioButtonGroup<String> method;
    private ComboBox<String> city;
    private TextField address;
    private DateTimePicker dateTime;
    private TextArea comment;

    public DeliveryForm(Binder<Order> binder) {
        method = new RadioButtonGroup<>("Method");
        method.setItems("Posti", "UPS", "Home");
        binder.forField(method)
                .asRequired("The field is required")
                .bind("delivery.method");
        add(method);

        city = new ComboBox<>("City");
        city.setItems(List.of("Helsinki", "Turku", "Tampere"));
        city.addValueChangeListener(event -> {
            // dateTime.setMin();
        });
        binder.forField(city)
                .asRequired("The field is required")
                .bind("delivery.city");
        add(city);

        address = new TextField("Address");
        address.setHelperText("Example: postal code, district, street, apartment");
        binder.forField(address)
                .asRequired("The field is required")
                .bind("delivery.address");
        add(address);

        dateTime = new DateTimePicker("Suitable date and time");
        dateTime.setMin(LocalDateTime.now());
        binder.forField(dateTime)
                .asRequired("The field is required")
                .bind("delivery.dateTime");
        add(dateTime);

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
