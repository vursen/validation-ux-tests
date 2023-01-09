package com.example.application.components;

import com.example.application.data.OrderItem;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemList extends VerticalLayout {
    public ItemList(OrderItem[] items) {
        for (OrderItem item : items) {
            addItem(item);
        }

        setAlignItems(Alignment.STRETCH);
        setPadding(false);
    }

    private void addItem(OrderItem item) {
        HorizontalLayout layout = new HorizontalLayout();

        Image image = new Image(item.getImage(), item.getTitle());
        image.setMaxWidth("80px");
        image.setMaxHeight("auto");
        layout.add(image);
        layout.setFlexGrow(0, image);

        Div title = new Div(new Text(item.getTitle()));
        title.setWidth("50%");
        layout.add(title);
        layout.setFlexGrow(1, title);

        Div price = new Div(new Text(item.getPrice().toString() + "$"));
        layout.add(price);
        layout.setFlexGrow(1, price);

        Div quantity = new Div(new Text(item.getQuantity().toString() + "kpl"));
        layout.add(quantity);

        add(layout);
    }
}
