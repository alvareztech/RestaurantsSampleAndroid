package tech.alvarez.restaurantssample.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.restaurantssample.util.Constants;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
