package com.example.myapplication.model;

public class ProductListModel {

    String item_id;
    String item_name;
    String item_image;
    String item_quantity;
    int item_marked_price;
    int item_selling_price;
    int item_type_veg_nonveg;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public int getItem_marked_price() {
        return item_marked_price;
    }

    public void setItem_marked_price(int item_marked_price) {
        this.item_marked_price = item_marked_price;
    }

    public int getItem_selling_price() {
        return item_selling_price;
    }

    public void setItem_selling_price(int item_selling_price) {
        this.item_selling_price = item_selling_price;
    }

    public int getItem_type_veg_nonveg() {
        return item_type_veg_nonveg;
    }

    public void setItem_type_veg_nonveg(int item_type_veg_nonveg) {
        this.item_type_veg_nonveg = item_type_veg_nonveg;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

}
