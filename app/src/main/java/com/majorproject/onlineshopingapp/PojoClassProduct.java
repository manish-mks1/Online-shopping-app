package com.majorproject.onlineshopingapp;

public class PojoClassProduct {
    int image;
    String head;
    String desc;
    int price;
    Boolean isAddedToCart=false;

    public PojoClassProduct(int image, String head, String desc, int price) {
        this.image = image;
        this.head = head;
        this.desc = desc;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }
    public boolean isAddedToCart() {
        return isAddedToCart;
    }

    // Setter for the 'isAddedToCart' field
    public void setAddedToCart(boolean addedToCart) {
        isAddedToCart = addedToCart;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
