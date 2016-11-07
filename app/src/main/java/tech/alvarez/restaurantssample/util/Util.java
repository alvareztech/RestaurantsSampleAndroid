package tech.alvarez.restaurantssample.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class Util {

    public static void setToken(Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences("prefe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("prefe", Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }

    public static boolean isEmailValid(String correo) {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }

    public static boolean validateLogin(String email, String pass) {
        if (isEmailValid(email) && pass.length() > 0) {
            return true;
        }
        return false;
    }
}
