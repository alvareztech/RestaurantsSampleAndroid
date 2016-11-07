package tech.alvarez.restaurantssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.alvarez.restaurantssample.models.LoginRequest;
import tech.alvarez.restaurantssample.models.LoginResponse;
import tech.alvarez.restaurantssample.models.User;
import tech.alvarez.restaurantssample.services.RestaurantsService;
import tech.alvarez.restaurantssample.services.RetrofitClient;
import tech.alvarez.restaurantssample.util.Constants;
import tech.alvarez.restaurantssample.util.Util;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passEditText;
    private Button loginButton;
    private ProgressBar progressBar;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passTextInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        passTextInputLayout = (TextInputLayout) findViewById(R.id.passTextInputLayout);
        loginButton = (Button) findViewById(R.id.loginButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        emailTextInputLayout.setError(null);
        passTextInputLayout.setError(null);

        String email = emailEditText.getText().toString().trim();
        String pass = passEditText.getText().toString().trim();

        if (Util.validateLogin(email, pass)) {

            User user = new User(email, pass);

            RestaurantsService service = RetrofitClient.getClient().create(RestaurantsService.class);

            Call<LoginResponse> call = service.login(new LoginRequest(user));

            progressBar.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        handleReponse(loginResponse);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);
                }
            });
        } else {
            emailTextInputLayout.setError(getString(R.string.enter_valid_data));
            passTextInputLayout.setError(getString(R.string.enter_valid_data));
        }
    }

    private void handleReponse(LoginResponse loginResponse) {
        if (loginResponse.getStatus().equals(Constants.STATUS_OK)) {
            Util.setToken(this, loginResponse.getToken());
            goMainScreen();
        } else {
            Snackbar.make(progressBar, loginResponse.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
