package com.example.application.components;

import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Pattern;

import com.example.application.data.Order;
import com.example.application.data.Phone;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Binder.Binding;

public class UserForm extends FormLayout {
    private final Binder<Order> binder;

    public UserForm(Binder<Order> binder) {
        this.binder = binder;

        addGenderField();
        addFirstNameField();
        addLastNameField();
        addBirthdayField();
        addEmailField();
        addPhoneField();
        addPasswordField();
    }

    private void addGenderField() {
        Select<String> gender = new Select<>();
        gender.setLabel("Gender");
        gender.setItems("", "Mr", "Mrs");
        binder.forField(gender).asRequired((value, context) -> {
            if (value == null || "".equals(value)) {
                return ValidationResult.error("The field is required");
            }

            return ValidationResult.ok();
        }).bind("user.gender");
        add(gender);
    }

    private void addFirstNameField() {
        TextField firstName = new TextField("First name");
        firstName.setMinLength(2);
        binder.forField(firstName).asRequired("The field is required").bind("user.firstName");
        add(firstName);
    }

    private void addLastNameField() {
        TextField lastName = new TextField("Last name");
        binder.forField(lastName).asRequired("The field is required").bind("user.lastName");
        add(lastName);
    }

    private void addBirthdayField() {
        DatePicker birthday = new DatePicker("Birthday");
        birthday.setMin(LocalDate.now().minusYears(100));
        birthday.setMax(LocalDate.now());
        binder.forField(birthday).asRequired("The field is required").bind("user.birthday");
        add(birthday);
    }

    private void addEmailField() {
        EmailField email = new EmailField("Email");
        binder
                .forField(email)
                .asRequired("The field is required")
                .withValidator(value -> !"vinogradov@vaadin.com".equals(value),
                        "An account with this email already exists.")
                .bind("user.email");
        add(email);
    }

    @SuppressWarnings("unchecked")
    private void addPhoneField() {
        PhoneField phone = new PhoneField("Phone");
        phone.setHelperText("Select a country code and enter a number using digits");

        Binding<Order, Phone> phoneBinding = binder.forField(phone)
                .asRequired("The field is required")
                .withValidator(
                        value -> value == null || value.getNumber() == null
                                || value.getCode() != null,
                        "The country code is required")
                .withValidator(
                        value -> value == null || value.getNumber() == null
                                || Pattern.matches("^\\d+$", value.getNumber()),
                        "The number should consist of digits")
                .bind("user.phone");
        phoneBinding.setAsRequiredEnabled(false);

        binder.addValueChangeListener(event -> {
            binder.getBinding("confirmation.methods")
                    .map(binding -> binding.getField())
                    .ifPresent(field -> {
                        boolean isPhoneRequired = ((Set<String>) field.getValue()).contains("By phone");
                        phoneBinding.setAsRequiredEnabled(isPhoneRequired);
                    });
        });

        add(phone);
    }

    private void addPasswordField() {
        PasswordField password = new PasswordField("Password");
        password.setPattern("[A-Za-z0-9]+");
        password.setMinLength(6);
        password.setHelperText("Must have at least 6 characters. Allowed: digits, letters");
        binder.forField(password).asRequired("The field is required").bind("user.password");
        add(password);
    }
}
