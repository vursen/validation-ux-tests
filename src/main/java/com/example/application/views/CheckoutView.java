package com.example.application.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.example.application.components.DeliveryForm;
import com.example.application.components.PhoneField;
import com.example.application.components.UserForm;
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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "checkout")
@PageTitle(value = "Checkout")
public class CheckoutView extends Div {
    private Button submit;

    private UserForm userForm;

    private DeliveryForm deliveryForm;

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
        userForm = new UserForm();
        add(new H2("User"), userForm);
    }

    private void addItemList() {
        add(new H2("Items"), new VerticalLayout());
    }

    private void addDeliveryForm() {
        deliveryForm = new DeliveryForm();
        add(new H2("Delivery"), deliveryForm);
    }

    private Stream<HasValueAndElement<?, ?>> getEmptyRequiredFields() {
        return Stream.concat(
                deliveryForm.getEmptyRequiredFields(),
                userForm.getEmptyRequiredFields());
    }

    private Stream<HasValueAndElement<?, ?>> getInvalidFields() {
        return Stream.concat(
                deliveryForm.getInvalidFields(),
                userForm.getInvalidFields());
    }

    private void onSubmit() {
        Optional<HasValueAndElement<?, ?>> emptyRequiredField = getEmptyRequiredFields().findFirst();
        if (emptyRequiredField.isPresent()) {
            showNotification(
                    String.format("The \"%s\" field has an invalid value.",
                            ((HasLabel) emptyRequiredField.get()).getLabel()),
                    NotificationVariant.LUMO_ERROR);
            return;
        }

        Optional<HasValueAndElement<?, ?>> invalidField = getInvalidFields().findFirst();
        if (invalidField.isPresent()) {
            showNotification(
                    String.format("The \"%s\" field has an invalid value.",
                            ((HasLabel) invalidField.get()).getLabel()),
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
