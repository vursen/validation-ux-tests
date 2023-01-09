package com.example.application.data;

public class OrderItem {
    private String image;
    private String title;
    private Double price;
    private Integer quantity;

    public OrderItem(String image, String title, Double price, Integer quantity) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setTitle(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
