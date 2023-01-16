package com.example.application.views;

import com.example.application.components.ConfirmationForm;
import com.example.application.components.DeliveryForm;
import com.example.application.components.ItemList;
import com.example.application.components.UserForm;
import com.example.application.data.Order;
import com.example.application.data.OrderConfirmation;
import com.example.application.data.OrderDelivery;
import com.example.application.data.OrderItem;
import com.example.application.data.OrderUser;
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
    private Order order = new Order(
            new OrderUser(),
            new OrderDelivery(),
            new OrderConfirmation(),
            new OrderItem(
                    "https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/now/now00373/y/26.jpg",
                    "NOW Foods, Vitamin D-3, 125 mcg (5,000 IU), 240 Softgels", Double.valueOf(9.99), Double.valueOf(15), 2),
            new OrderItem(
                    "https://cloudinary.images-iherb.com/image/upload/f_auto,q_auto:eco/images/now/now00680/y/25.jpg",
                    "NOW Foods, C-1000, 100 Tablets", Double.valueOf(49.99), Double.valueOf(10), 5));

    private Binder<Order> binder;

    public CreateOrderView() {
        binder = new Binder<>(Order.class);
        binder.setBean(order);

        H1 h1 = new H1("Create Order");
        h1.addClassNames(LumoUtility.Margin.Top.NONE);
        add(h1);

        add(new H2("User"));
        add(new UserForm(binder));

        add(new H2("Items"));
        add(new ItemList(binder));

        add(new H2("Delivery"));
        add(new DeliveryForm(binder));

        add(new H2("Order Confirmation"));
        add(new ConfirmationForm(binder));

        addSubmit();

        addClassNames(LumoUtility.Padding.XLARGE, LumoUtility.MaxWidth.SCREEN_MEDIUM);
    }

    private void addSubmit() {
        Button submit = new Button("Create");
        submit.addClassNames(LumoUtility.Margin.Top.LARGE);
        submit.addClickListener((event) -> onSubmit());
        add(submit);
    }

    private void onSubmit() {
        if (binder.writeBeanIfValid(order)) {
            showNotification("The order #1234 was successfully created.", NotificationVariant.LUMO_SUCCESS);
        } else {
            showNotification("Please correct the errors highlighted in the form and try again.", NotificationVariant.LUMO_ERROR);
        }
    }

    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = new Notification(message, 3000, Notification.Position.TOP_START);
        notification.addThemeVariants(variant);
        notification.open();
    }
}
