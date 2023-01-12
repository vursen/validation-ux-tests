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
import com.vaadin.flow.component.html.Hr;
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

    public CreateOrderView() {
        binder = new Binder<>(Order.class);
        binder.setBean(order);

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
        Button submit = new Button("Create");
        submit.addClassNames(LumoUtility.Margin.Top.LARGE);
        submit.addClickListener((event) -> onSubmit());
        binder.addStatusChangeListener(event -> {
            submit.setEnabled(!event.hasValidationErrors());
        });
        add(submit);
    }

    private void addUserForm() {
        add(new H2("User"), new UserForm(binder));
    }

    private void addItemList() {
        add(new H2("Items"), new ItemList(binder));
    }

    private void addDeliveryForm() {
        add(new H2("Delivery"), new DeliveryForm(binder));
    }

    // private Stream<HasValueAndElement<?, ?>> getEmptyRequiredFields() {
    //     return Stream.concat(
    //             deliveryForm.getEmptyRequiredFields(),
    //             userForm.getEmptyRequiredFields());
    // }

    // private Stream<HasValueAndElement<?, ?>> getInvalidFields() {
    //     return Stream.concat(
    //             deliveryForm.getInvalidFields(),
    //             userForm.getInvalidFields());
    // }

    private void onSubmit() {
        // Optional<HasValueAndElement<?, ?>> emptyRequiredField = getEmptyRequiredFields().findFirst();
        // if (emptyRequiredField.isPresent()) {
        //     showNotification(
        //             String.format("The \"%s\" field is required.",
        //                     ((HasLabel) emptyRequiredField.get()).getLabel()),
        //             NotificationVariant.LUMO_ERROR);
        //     return;
        // }

        // Optional<HasValueAndElement<?, ?>> invalidField = getInvalidFields().findFirst();
        // if (invalidField.isPresent()) {
        //     showNotification(
        //             String.format("The \"%s\" field has an invalid value.",
        //                     ((HasLabel) invalidField.get()).getLabel()),
        //             NotificationVariant.LUMO_ERROR);
        //     return;
        // }

        if (binder.writeBeanIfValid(order)) {
            showNotification("The order #1234 is successfully created.", NotificationVariant.LUMO_SUCCESS);
        } else {
            showNotification("Please, fill out the required fields before submission.", NotificationVariant.LUMO_ERROR);
        }
    }

    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = new Notification(message, 3000, Notification.Position.TOP_START);
        notification.addThemeVariants(variant);
        notification.open();
    }
}
