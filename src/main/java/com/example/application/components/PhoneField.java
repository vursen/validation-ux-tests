package com.example.application.components;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneField extends CustomField<String> {
    private Select<String> codeField;
    private TextField numberField;

    private List<String> COUNTRY_CODES = List.of("+358", "+1");

    public PhoneField(String label) {
        this();
        setLabel(label);
    }

    public PhoneField() {
        HorizontalLayout layout = new HorizontalLayout();

        codeField = new Select<>();
        codeField.setItems(COUNTRY_CODES);
        codeField.setWidth("25%");
        layout.add(codeField);

        numberField = new TextField();
        numberField.setWidth("75%");
        layout.add(numberField);

        add(layout);
    }

    @Override
    protected String generateModelValue() {
        String code = codeField.getValue();
        String number = numberField.getValue();

        if (code == null || number.isEmpty()) {
            return null;
        }

        return code + number;
    }

    @Override
    protected void setPresentationValue(String phone) {
        Pattern phonePattern = Pattern.compile("^(" + String.join("|", COUNTRY_CODES) + ")(.+)$");
        Matcher phoneMatcher = phonePattern.matcher(phone);

        if (phoneMatcher.find()) {
            codeField.setValue(phoneMatcher.group(1));
            numberField.setValue(phoneMatcher.group(2));
        }
    }
}
