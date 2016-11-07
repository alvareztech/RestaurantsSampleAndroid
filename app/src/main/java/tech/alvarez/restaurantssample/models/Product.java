package tech.alvarez.restaurantssample.models;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class Product {

    private String name;
    private String description;
    private float price;
    private String type_money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType_money() {
        return type_money;
    }

    public void setType_money(String type_money) {
        this.type_money = type_money;
    }
}
