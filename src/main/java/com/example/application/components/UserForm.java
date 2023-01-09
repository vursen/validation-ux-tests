package com.example.application.components;

import java.time.LocalDate;

import com.example.application.data.OrderUser;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class UserForm extends BasicForm {
    public UserForm(OrderUser user) {
        Select<String> gender = new Select<>();
        gender.setLabel("Title");
        gender.setItems("", "Mr", "Mrs");
        fields.add(gender);
        add(gender);

        TextField firstName = new TextField("First name");
        firstName.setRequiredIndicatorVisible(true);
        firstName.setMinLength(2);
        fields.add(firstName);
        add(firstName);

        TextField lastName = new TextField("Last name");
        lastName.setRequiredIndicatorVisible(true);
        lastName.setMinLength(2);
        fields.add(lastName);
        add(lastName);

        DatePicker birthday = new DatePicker("Birthday");
        birthday.setRequiredIndicatorVisible(true);
        birthday.setMax(LocalDate.now());
        fields.add(birthday);
        add(birthday);

        EmailField email = new EmailField("Email");
        email.setRequiredIndicatorVisible(true);
        fields.add(email);
        add(email);

        PhoneField phone = new PhoneField("Phone");
        phone.setRequiredIndicatorVisible(true);
        fields.add(phone);
        add(phone);

        PasswordField password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setPattern("[A-Za-z0-9]+");
        password.setHelperText("Create a password using digits and letters");
        fields.add(password);
        add(password);
    }
}
