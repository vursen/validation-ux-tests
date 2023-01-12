package com.example.application.components;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.shared.HasClientValidation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationStatusChangeEvent;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;
import com.vaadin.flow.shared.Registration;

public class PhoneField extends CustomField<String> implements HasValidator<String>, HasClientValidation {
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
    public Registration addValidationStatusChangeListener(
            ValidationStatusChangeListener<String> listener) {
        return addClientValidatedEventListener(
                event -> listener.validationStatusChanged(
                        new ValidationStatusChangeEvent<String>(this,
                                !isInvalid())));
    }

    // public Validator<String> getDefaultValidator() {
    //     return (value, context) -> {
    //         if (
    //             Objects.equals(codeField.getValue(), codeField.getEmptyValue()) &&
    //             Objects.equals(numberField.getValue(), numberField.getEmptyValue())
    //         ) {
    //             return ValidationResult.ok();
    //         }

    //         if (Objects.equals(codeField.getValue(), codeField.getEmptyValue())) {
    //             return ValidationResult.error("The country code is required");
    //         }

    //         Pattern numberPattern = Pattern.compile("$\\d+^");
    //         Matcher numberMatcher = numberPattern.matcher(numberField.getValue());
    //         if (!numberMatcher.matches()) {
    //             return ValidationResult.error("The number must consist of digits");
    //         }

    //         return ValidationResult.ok();
    //     };
    // }

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
