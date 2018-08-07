package xyz.bzennn.vkmusicactualizer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;


import xyz.bzennn.vkmusicactualizer.implementations.models.AccountInfoModel;
import xyz.bzennn.vkmusicactualizer.implementations.views.LoginActivity;
import xyz.bzennn.vkmusicactualizer.implementations.views.MainActivity;
import xyz.bzennn.vkmusicactualizer.models.AccountInfoInterface;
import xyz.bzennn.vkmusicactualizer.utils.Utils;

public class Application extends android.app.Application {
    public static final String APP_PREFERENCES = "preferences";
    public static final String APP_PREFERENCES_TOKEN = "Token";
    public static final String APP_PREFERENCES_USER_ID = "UserID";
    public static final String APP_PREFERENCES_USER_NAME = "UserName";
    public static final String APP_PREFERENCES_USER_SURNAME = "UserSurname";
    public static final String APP_PREFERENCES_USER_AVATAR_URL = "UserAvatarUrl";

    private static Intent loginIntent;
    private static Intent loginIntentCp;
    private static Intent mainIntent;
    public static SharedPreferences sharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        vkAccessTokenTracker.startTracking();

        sharedPreferences = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        loginIntentCp = loginIntent;

        mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (!tokenExists()) {
            this.startActivity(loginIntent);
        } else {
            this.startActivity(mainIntent);
        }
    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                getApplicationContext().startActivity(loginIntentCp);
            }
        }
    };

    private boolean tokenExists() {
        if (sharedPreferences.contains(APP_PREFERENCES_TOKEN)) {
            return true;
        }
        return false;
    }
}
