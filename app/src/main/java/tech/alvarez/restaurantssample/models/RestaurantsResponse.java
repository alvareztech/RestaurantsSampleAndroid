package tech.alvarez.restaurantssample.models;


import java.util.ArrayList;

public class RestaurantsResponse {

    private ArrayList<Restaurant> restaurants;
    private Pagination pagination;

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
