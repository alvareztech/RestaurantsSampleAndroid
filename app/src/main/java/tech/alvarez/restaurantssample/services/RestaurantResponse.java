package tech.alvarez.restaurantssample.services;

import tech.alvarez.restaurantssample.models.Restaurant;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */
public class RestaurantResponse {

    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
