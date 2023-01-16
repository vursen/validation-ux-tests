package com.example.application.components;

import com.example.application.data.Order;
import com.example.application.data.OrderItem;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ItemList extends VerticalLayout {
    private Binder<Order> binder;

    public ItemList(Binder<Order> binder) {
        this.binder = binder;

        for (OrderItem item : binder.getBean().getItems()) {
            addItem(item);
            add(new Hr());
        }

        setPadding(false);
        setAlignItems(Alignment.STRETCH);
    }

    private void addItem(OrderItem item) {
        HorizontalLayout layout = new HorizontalLayout();

        Image image = new Image(item.getImage(), item.getTitle());
        image.setMaxWidth("80px");
        image.setMaxHeight("auto");
        layout.add(image);
        layout.setFlexGrow(0, image);

        Div title = new Div(new Text(item.getTitle()));
        title.setWidth("40%");
        layout.add(title);
        layout.setFlexGrow(1, title);

        Div price = new Div(new Text(item.getPrice() + " €"));
        price.addClassNames(LumoUtility.FontWeight.BOLD);
        price.setWidth("10%");
        layout.add(price);
        layout.setFlexGrow(1, price);

        NumberField discount = new NumberField("Discount");
        discount.setValue(item.getDiscount());
        discount.setMin(0);
        discount.setMax(100);
        discount.setSuffixComponent(new Div(new Text("%")));
        discount.addClassNames(LumoUtility.Padding.Top.NONE);
        discount.setWidth("10%");
        binder.forField(discount)
                .withValidator((value) -> value <= 30, "Max: 30%")
                .bind(order -> item.getDiscount(), (order, value) -> item.setDiscount(value));
        layout.add(discount);

        IntegerField quantity = new IntegerField("Quantity");
        quantity.setValue(item.getQuantity());
        quantity.setStepButtonsVisible(true);
        quantity.setMin(1);
        quantity.setWidth("112px");
        quantity.addClassNames(LumoUtility.Padding.Top.NONE);
        binder.forField(quantity)
                .asRequired()
                .withValidator(value -> value <= 10, "Max: 10 klp")
                .bind(order -> item.getQuantity(), (order, value) -> item.setQuantity(value));
        layout.add(quantity);

        add(layout);
    }
}
