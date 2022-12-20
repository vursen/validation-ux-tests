package com.example.application.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "checkout")
@PageTitle(value = "Checkout")
public class CheckoutView extends Div {
    Button submit;

    ArrayList<HasValueAndElement<?, ?>> fields = new ArrayList<>();

    public CheckoutView() {
        addClassNames(LumoUtility.Padding.XLARGE, LumoUtility.MaxWidth.SCREEN_MEDIUM);

        H1 h1 = new H1("Checkout");
        h1.addClassNames(LumoUtility.Margin.Top.NONE);
        add(h1);

        addUserForm();

        addItemList();

        addDeliveryForm();

        addSubmit();
    }

    private void addSubmit() {
        submit = new Button("Order");
        submit.addClassNames(LumoUtility.Margin.Top.LARGE);
        submit.addClickListener((event) -> onSubmit());
        add(submit);
    }

    private void addUserForm() {
        FormLayout layout = new FormLayout();

        TextField firstName = new TextField("First name");
        firstName.setRequiredIndicatorVisible(true);
        firstName.setMinLength(2);
        fields.add(firstName);
        layout.add(firstName);

        TextField lastName = new TextField("Last name");
        lastName.setRequiredIndicatorVisible(true);
        lastName.setMinLength(2);
        fields.add(lastName);
        layout.add(lastName);

        EmailField email = new EmailField("Email");
        email.setRequiredIndicatorVisible(true);
        fields.add(email);
        layout.add(email);

        DatePicker birthday = new DatePicker("Birthday");
        birthday.setRequiredIndicatorVisible(true);
        birthday.setMax(LocalDate.now());
        fields.add(birthday);
        layout.add(birthday);

        PasswordField password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setPattern("[A-Za-z0-9]+");
        password.setHelperText("Must only consist of digits and letters");
        fields.add(password);
        layout.add(password);

        add(new H2("User"), layout);
    }

    private void addItemList() {
        add(new H2("Items"), new VerticalLayout());
    }

    private void addDeliveryForm() {
        FormLayout layout = new FormLayout();

        RadioButtonGroup<String> type = new RadioButtonGroup<>("Type");
        type.setItems("Posti", "UPS");
        type.setRequiredIndicatorVisible(true);
        fields.add(type);
        layout.add(type);

        ComboBox<String> city = new ComboBox<>("City");
        city.setItems(List.of("Helsinki", "Turku", "Tampere"));
        city.setRequiredIndicatorVisible(true);
        fields.add(city);
        layout.add(city);

        TextField address = new TextField("Address");
        address.setRequiredIndicatorVisible(true);
        fields.add(address);
        layout.add(address);

        DateTimePicker dateTime = new DateTimePicker("Date and time");
        dateTime.setRequiredIndicatorVisible(true);
        fields.add(dateTime);
        layout.add(dateTime);

        add(new H2("Delivery"), layout);
    }

    private List<HasValueAndElement<?, ?>> getInvalidFields() {
        return fields.stream().filter((field) -> ((HasValidation) field).isInvalid()).toList();
    }

    private List<HasValueAndElement<?, ?>> getEmptyRequiredFields() {
        return fields.stream()
                .filter((field) -> field.isRequiredIndicatorVisible())
                .filter((field) -> Objects.equals(field.getValue(), field.getEmptyValue()))
                .toList();
    }

    private void onSubmit() {
        List<HasValueAndElement<?, ?>> emptyRequiredFields = getEmptyRequiredFields();
        if (emptyRequiredFields.size() > 0) {
            showNotification(
                    String.format("The \"%s\" field must be filled.",
                            ((HasLabel) emptyRequiredFields.get(0)).getLabel()),
                    NotificationVariant.LUMO_ERROR);
            return;
        }

        List<HasValueAndElement<?, ?>> invalidFields = getInvalidFields();
        if (invalidFields.size() > 0) {
            showNotification(
                    String.format("The \"%s\" field has an invalid value.",
                            ((HasLabel) invalidFields.get(0)).getLabel()),
                    NotificationVariant.LUMO_ERROR);
            return;
        }

        showNotification("The order #1234 is successfully created.", NotificationVariant.LUMO_SUCCESS);
    }

    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = new Notification(message, 3000, Notification.Position.BOTTOM_END);
        notification.addThemeVariants(variant);
        notification.open();
    }
}
