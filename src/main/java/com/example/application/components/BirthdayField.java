package com.example.application.components;

import java.time.LocalDate;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;

public class BirthdayField extends CustomField<LocalDate> {
    private Select<Integer> dayField;
    private Select<Integer> monthField;
    private Select<Integer> yearField;

    public BirthdayField(String label) {
        this();
        setLabel(label);
    }

    public BirthdayField() {
        HorizontalLayout layout = new HorizontalLayout();

        dayField = new Select<>();
        dayField.setPlaceholder("Day");
        dayField.setItems(1, 2);
        layout.add(dayField);

        monthField = new Select<>();
        monthField.setPlaceholder("Month");
        monthField.setItems(1, 2);
        layout.add(monthField);

        yearField = new Select<>();
        yearField.setPlaceholder("Year");
        yearField.setItems(1, 2);
        layout.add(yearField);

        add(layout);
    }

    @Override
    protected LocalDate generateModelValue() {
        return LocalDate.of(yearField.getValue(), monthField.getValue(), dayField.getValue());
    }

    @Override
    protected void setPresentationValue(LocalDate birthday) {
        yearField.setValue(birthday.getYear());
        monthField.setValue(birthday.getMonthValue());
        dayField.setValue(birthday.getDayOfMonth());
    }
}
