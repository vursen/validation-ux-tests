package com.example.application.components;

import java.util.List;

import com.example.application.data.Order.OrderItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemList extends VerticalLayout {
    public ItemList(List<OrderItem> items) {
        add(new Item());
        add(new Item());
    }

    private void createItem() {

    }
}
