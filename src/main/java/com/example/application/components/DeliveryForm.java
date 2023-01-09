package com.example.application.components;

import java.util.List;

import com.example.application.data.Order;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class DeliveryForm extends BasicForm {
    public DeliveryForm(Binder<Order> binder) {
        RadioButtonGroup<String> method = new RadioButtonGroup<>("Method");
        method.setItems("Posti", "UPS", "Home");
        fields.add(method);
        binder.forField(method).asRequired().bind("delivery.method");
        add(method);

        ComboBox<String> city = new ComboBox<>("City");
        city.setItems(List.of("Helsinki", "Turku", "Tampere"));
        fields.add(city);
        binder.forField(city).asRequired().bind("delivery.city");
        add(city);

        TextField address = new TextField("Address");
        address.setHelperText("Example: postal code, district, street, apartment");
        fields.add(address);
        binder.forField(address).asRequired().bind("delivery.address");
        add(address);

        DateTimePicker dateTime = new DateTimePicker("Suitable date and time");
        fields.add(dateTime);
        binder.forField(dateTime).asRequired().bind("delivery.dateTime");
        add(dateTime);

        TextArea comment = new TextArea("Additional information (optional)");
        fields.add(comment);
        binder.forField(comment).bind("delivery.comment");
        add(comment);
    }
}
