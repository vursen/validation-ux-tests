package com.example.application.components;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.example.application.data.Phone;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.shared.HasClientValidation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.HasValidator;

public class PhoneField extends CustomField<Phone> implements HasValidator<String>, HasClientValidation {
    private Select<String> codeField;
    private TextField numberField;

    private List<String> COUNTRY_CODES = List.of("+358", "+49", "+1");

    public PhoneField(String label) {
        this();
        setLabel(label);
    }

    public PhoneField() {
        HorizontalLayout layout = new HorizontalLayout();

        codeField = new Select<>();
        codeField.setItems(COUNTRY_CODES);
        codeField.setWidth("25%");
        codeField.setPlaceholder("Code");
        layout.add(codeField);

        numberField = new TextField();
        numberField.setWidth("75%");
        layout.add(numberField);

        add(layout);
    }

    @Override
    protected Phone generateModelValue() {
        String code = codeField.getValue();
        String number = numberField.getValue();

        if (code == null && number.isEmpty()) {
            return null;
        }

        return new Phone(code, number.isEmpty() ? null : number);
    }

    @Override
    protected void setPresentationValue(Phone phone) {
        if (phone != null) {
            codeField.setValue(phone.getCode());
            numberField.setValue(phone.getNumber());
        }
    }
}
