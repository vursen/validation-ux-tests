package com.example.application.views;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.application.components.DeliveryForm;
import com.example.application.components.ItemList;
import com.example.application.components.UserForm;
import com.example.application.data.Order;
import com.example.application.data.OrderDelivery;
import com.example.application.data.OrderItem;
import com.example.application.data.OrderUser;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "create-order")
@PageTitle(value = "Create order")
public class CreateOrderView extends Div {
    // TODO: Consider using the builder pattern.
    private Order order = new Order(
            new OrderUser(),
            new OrderDelivery(),
            new OrderItem(
                    "https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/now/now00373/y/26.jpg",
                    "NOW Foods, Vitamin D-3, 125 mcg (5,000 IU), 240 Softgels", Double.valueOf(15), 2),
            new OrderItem(
                    "https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/now/now00680/y/25.jpg",
                    "NOW Foods, C-1000, 100 Tablets", Double.valueOf(10), 5));

    private Binder<Order> binder;

    private Button submit;

    private UserForm userForm;

    private DeliveryForm deliveryForm;

    public CreateOrderView() {
        binder = new Binder<>();

        H1 h1 = new H1("Create Order");
        h1.addClassNames(LumoUtility.Margin.Top.NONE);
        add(h1);

        addUserForm();

        addItemList();

        addDeliveryForm();

        addSubmit();

        addClassNames(LumoUtility.Padding.XLARGE, LumoUtility.MaxWidth.SCREEN_MEDIUM);
    }

    private void addSubmit() {
        submit = new Button("Create");
        submit.addClassNames(LumoUtility.Margin.Top.LARGE);
        submit.addClickListener((event) -> onSubmit());
        add(submit);
    }

    private void addUserForm() {
        userForm = new UserForm(order.getUser());
        add(new H2("User"), userForm);
    }

    private void addItemList() {
        add(new H2("Items"), new ItemList(order.getItems()));
    }

    private void addDeliveryForm() {
        deliveryForm = new DeliveryForm(order.getDelivery());
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
                    String.format("The \"%s\" field is required.",
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
