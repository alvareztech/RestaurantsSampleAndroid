package tech.alvarez.restaurantssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.restaurantssample.adapters.OnItemClickListener;
import tech.alvarez.restaurantssample.adapters.RestaurantsAdapter;
import tech.alvarez.restaurantssample.models.Restaurant;
import tech.alvarez.restaurantssample.models.RestaurantsResponse;
import tech.alvarez.restaurantssample.services.RetrofitClient;
import tech.alvarez.restaurantssample.util.Constants;
import tech.alvarez.restaurantssample.util.Util;
import tech.alvarez.restaurantssample.services.RestaurantsService;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private RecyclerView recyclerView;
    private RestaurantsAdapter restaurantsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantsAdapter = new RestaurantsAdapter(this);
        recyclerView.setAdapter(restaurantsAdapter);

        RestaurantsService service = RetrofitClient.getClient().create(RestaurantsService.class);
        Call<RestaurantsResponse> call = service.getRestaurants("1000", "-17.188242", "-66.034142", Util.getToken(this));

        System.out.println("url: " + call.request().url());

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    RestaurantsResponse restaurantsResponse = response.body();
                    handleResponse(restaurantsResponse);
                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println(t.getMessage());
            }
        });
    }

    private void handleResponse(RestaurantsResponse restaurantsResponse) {
        restaurantsAdapter.setDataset(restaurantsResponse.getRestaurants());
    }

    @Override
    public void onItemClick(Restaurant restaurant) {
        Intent intent = new Intent(this, RestaurantActivity.class);
        intent.putExtra("id", restaurant.getId());
        startActivity(intent);
    }
}
