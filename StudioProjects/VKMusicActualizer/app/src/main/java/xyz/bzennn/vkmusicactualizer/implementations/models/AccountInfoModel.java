package xyz.bzennn.vkmusicactualizer.implementations.models;

import android.content.SharedPreferences;
import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.bzennn.vkmusicactualizer.Application;
import xyz.bzennn.vkmusicactualizer.models.AccountInfoInterface;

public class AccountInfoModel implements AccountInfoInterface {
    private static SharedPreferences sharedPreferences = Application.sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor = Application.sharedPreferences.edit();

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
        String token = "";
        token = sharedPreferences.getString(Application.APP_PREFERENCES_TOKEN, "");

        return token;
    }

    @Override
    public String getUserAvatarUrl() {
        String avatarUrl = "";
        avatarUrl = sharedPreferences.getString(Application.APP_PREFERENCES_USER_AVATAR_URL, "");

        return avatarUrl;
    }

    // Иначе не работает
    @Override
    public void requestUserInfoIntoPreferences () {
        VKRequest requestUserInfo = VKApi.users().get();
        VKRequest requestUserPhoto = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_100"));
        requestUserInfo.attempts = 5;
        requestUserPhoto.attempts = 5;
        requestUserInfo.executeWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    JSONArray resp = response.json.getJSONArray("response");
                    JSONObject user = resp.getJSONObject(0);

                    sharedPreferencesEditor.putString(Application.APP_PREFERENCES_USER_ID, user.getString("id"));
                    sharedPreferencesEditor.putString(Application.APP_PREFERENCES_USER_NAME, user.getString("first_name"));
                    sharedPreferencesEditor.putString(Application.APP_PREFERENCES_USER_SURNAME, user.getString("last_name"));
                    sharedPreferencesEditor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        requestUserPhoto.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    JSONArray resp = response.json.getJSONArray("response");
                    JSONObject avatar = resp.getJSONObject(0);

                    sharedPreferencesEditor.putString(Application.APP_PREFERENCES_USER_AVATAR_URL, avatar.getString("photo_100"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
