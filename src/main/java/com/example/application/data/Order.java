package com.example.application.data;

public class Order {
    private OrderUser user;
    private OrderItem[] items;
    private OrderDelivery delivery;

    public Order(OrderUser user, OrderDelivery delivery, OrderItem... items) {
        this.user = user;
        this.items = items;
        this.delivery = delivery;
    }

    public OrderUser getUser() {
        return this.user;
    }

    public void setUser(OrderUser user) {
        this.user = user;
    }

    public OrderItem[] getItems() {
        return this.items;
    }

    public void setItems(OrderItem... items) {
        this.items = items;
    }

    public OrderDelivery getDelivery() {
        return this.delivery;
    }

    public void setDelivery(OrderDelivery delivery) {
        this.delivery = delivery;
    }
}
