package com.example.application.components;

import java.time.LocalDate;

import com.example.application.data.Order;
import com.example.application.data.OrderUser;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends BasicForm {
    public UserForm(Binder<Order> binder, OrderUser user) {
        Select<String> gender = new Select<>();
        gender.setLabel("Gender");
        gender.setItems("", "Mr", "Mrs");
        binder.forField(gender).bind(user::getGender, user::setGender);
        add(gender);

        TextField firstName = new TextField("First name");
        firstName.setMinLength(2);
        fields.add(firstName);
        binder.forField(firstName).asRequired().bind("user.firstName");
        add(firstName);

        TextField lastName = new TextField("Last name");
        lastName.setMinLength(2);
        fields.add(lastName);
        binder.forField(lastName).asRequired().bind("user.lastName");
        add(lastName);

        DatePicker birthday = new DatePicker("Birthday");
        birthday.setMax(LocalDate.now());
        fields.add(birthday);
        binder.forField(birthday).asRequired().bind("user.birthday");
        add(birthday);

        EmailField email = new EmailField("Email");
        fields.add(email);
        binder.forField(email).asRequired().bind("user.birthday");
        add(email);

        PhoneField phone = new PhoneField("Phone");
        fields.add(phone);
        binder.forField(phone).asRequired().bind("user.phone");
        add(phone);

        PasswordField password = new PasswordField("Password");
        password.setPattern("[A-Za-z0-9]+");
        password.setHelperText("Create a password using digits and letters");
        fields.add(password);
        binder.forField(password).asRequired().bind("user.password");
        add(password);
    }
}
