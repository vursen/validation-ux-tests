package com.example.application.components;

import java.time.LocalDate;

import com.example.application.data.Order;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends BasicForm {
    public UserForm(Binder<Order> binder) {
        Select<String> gender = new Select<>();
        gender.setLabel("Gender");
        gender.setItems("", "Mr", "Mrs");
        binder.forField(gender).asRequired("The field is required").bind("user.gender");
        add(gender);

        TextField firstName = new TextField("First name");
        firstName.setMinLength(2);
        binder.forField(firstName).asRequired("The field is required").bind("user.firstName");
        add(firstName);

        TextField lastName = new TextField("Last name");
        lastName.setMinLength(2);
        binder.forField(lastName).asRequired("The field is required").bind("user.lastName");
        add(lastName);

        DatePicker birthday = new DatePicker("Birthday");
        birthday.setMax(LocalDate.now());
        binder.forField(birthday).asRequired("The field is required").bind("user.birthday");
        add(birthday);

        // BirthdayField birthday = new BirthdayField("Birthday");
        // binder.forField(birthday).asRequired("The field is required").bind("user.birthday");
        // add(birthday);

        EmailField email = new EmailField("Email");
        binder
                .forField(email)
                .asRequired("The field is required")
                .withValidator(value -> !value.equals("vinogradov@vaadin.com"),
                        "An account with this email already exists.")
                .bind("user.birthday");
        add(email);

        PhoneField phone = new PhoneField("Phone");
        phone.setHelperText("Select a country code and enter your number using digits");
        binder.forField(phone)
                .asRequired("The field is required")
                .bind("user.phone");
        add(phone);

        PasswordField password = new PasswordField("Password");
        password.setPattern("[A-Za-z0-9]+");
        password.setHelperText("Create a password using digits and letters");
        binder.forField(password).asRequired("The field is required").bind("user.password");
        add(password);
    }
}
