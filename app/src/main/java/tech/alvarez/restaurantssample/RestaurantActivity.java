package tech.alvarez.restaurantssample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.restaurantssample.adapters.ProductsAdapter;
import tech.alvarez.restaurantssample.models.Restaurant;
import tech.alvarez.restaurantssample.services.RetrofitClient;
import tech.alvarez.restaurantssample.util.Constants;
import tech.alvarez.restaurantssample.util.Util;
import tech.alvarez.restaurantssample.services.RestaurantResponse;
import tech.alvarez.restaurantssample.services.RestaurantsService;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class RestaurantActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView addressTextView;
    private RatingBar ratingBar;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;

    private int idRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        idRestaurant = getIntent().getIntExtra("id", 0);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsAdapter = new ProductsAdapter();
        recyclerView.setAdapter(productsAdapter);

        RestaurantsService service = RetrofitClient.getClient().create(RestaurantsService.class);
        Call<RestaurantResponse> call = service.getRestaurant(idRestaurant, Util.getToken(this));

        System.out.println("url: " + call.request().url());

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    RestaurantResponse restaurantResponse = response.body();
                    handleResponse(restaurantResponse);
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void handleResponse(RestaurantResponse restaurantResponse) {
        Restaurant r = restaurantResponse.getRestaurant();
        productsAdapter.setDataset(r.getProducts());
        updateViews(r);
    }

    private void updateViews(Restaurant r) {
        nameTextView.setText(r.getName());
        addressTextView.setText(r.getStreet_name());
        ratingBar.setRating(Float.parseFloat(r.getRate()));
    }
}
