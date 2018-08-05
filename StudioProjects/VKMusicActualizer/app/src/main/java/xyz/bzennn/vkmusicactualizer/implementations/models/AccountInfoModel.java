package xyz.bzennn.vkmusicactualizer.implementations.models;

import android.content.SharedPreferences;
import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.bzennn.vkmusicactualizer.Application;
import xyz.bzennn.vkmusicactualizer.models.AccountInfoInterface;

public class AccountInfoModel implements AccountInfoInterface {
    private static SharedPreferences sharedPreferences = Application.sharedPreferences;

    @Override
    public String getUserId() {
        String id = "";
        id = sharedPreferences.getString(Application.APP_PREFERENCES_USER_ID, "");

        return id;
    }

    @Override
    public String getUserName() {
        String name = "";
        name = sharedPreferences.getString(Application.APP_PREFERENCES_USER_NAME, "");

        return name;
    }

    @Override
    public String getUserSurname() {
        String surname = "";
        surname = sharedPreferences.getString(Application.APP_PREFERENCES_USER_SURNAME, "");

        return surname;
    }

    @Override
    public String getUserToken() {
        return Application.sharedPreferences.getString(Application.APP_PREFERENCES_TOKEN, "");
    }

    @Override
    public String getUserAvatar() {
        return null;
    }
}
