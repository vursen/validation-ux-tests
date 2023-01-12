package com.example.application.data;

public class Order {
    private OrderUser user;
    private OrderItem[] items;
    private OrderDelivery delivery;
    private OrderConfirmation confirmation;

    public Order(OrderUser user, OrderDelivery delivery, OrderConfirmation confirmation, OrderItem... items) {
        this.user = user;
        this.items = items;
        this.delivery = delivery;
        this.confirmation = confirmation;
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

    public OrderConfirmation getConfirmation() {
        return this.confirmation;
    }

    public void setConfirmation(OrderConfirmation confirmation) {
        this.confirmation = confirmation;
    }
}
