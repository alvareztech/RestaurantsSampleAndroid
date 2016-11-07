package tech.alvarez.restaurantssample.models;


import java.util.ArrayList;

public class Restaurant {

    private int id;
    private String name;
    private String phone;
    private String street_name;
    private String latitude;
    private String longitude;
    private String preparation;
    private String rate;
    private double estimate_time;
    private ArrayList<Product> products;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public double getEstimate_time() {
        return estimate_time;
    }

    public void setEstimate_time(double estimate_time) {
        this.estimate_time = estimate_time;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
