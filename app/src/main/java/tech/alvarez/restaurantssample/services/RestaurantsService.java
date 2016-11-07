package tech.alvarez.restaurantssample.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.alvarez.restaurantssample.models.LoginRequest;
import tech.alvarez.restaurantssample.models.LoginResponse;
import tech.alvarez.restaurantssample.models.RestaurantsResponse;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public interface RestaurantsService {

    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest user);

    @GET("restaurants?limit=10")
    Call<RestaurantsResponse> getRestaurants(@Query("radius") String radius, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("token") String token);

    @GET("restaurants/{id}")
    Call<RestaurantResponse> getRestaurant(@Path("id") int id, @Query("token") String token);


}
