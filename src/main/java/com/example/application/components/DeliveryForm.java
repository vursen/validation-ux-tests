package com.example.application.components;

import java.util.List;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class DeliveryForm extends BasicForm {
    public DeliveryForm() {
        RadioButtonGroup<String> type = new RadioButtonGroup<>("Type");
        type.setItems("Posti", "UPS");
        type.setRequiredIndicatorVisible(true);
        fields.add(type);
        add(type);

        ComboBox<String> city = new ComboBox<>("City");
        city.setItems(List.of("Helsinki", "Turku", "Tampere"));
        city.setRequiredIndicatorVisible(true);
        fields.add(city);
        add(city);

        TextField address = new TextField("Address");
        address.setRequiredIndicatorVisible(true);
        fields.add(address);
        add(address);

        DateTimePicker dateTime = new DateTimePicker("Date and time");
        dateTime.setRequiredIndicatorVisible(true);
        fields.add(dateTime);
        add(dateTime);

        TextArea comment = new TextArea("Additional information (optional)");
        fields.add(comment);
        add(comment);
    }
}
