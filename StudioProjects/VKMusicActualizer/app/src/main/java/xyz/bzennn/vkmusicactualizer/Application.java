package xyz.bzennn.vkmusicactualizer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import xyz.bzennn.vkmusicactualizer.implementations.views.LoginActivity;

public class Application extends android.app.Application {
    public static final String APP_PREFERENCES = "preferences";
    public static final String APP_PREFERENCES_TOKEN = "Token";
    public static final String APP_PREFERENCES_USER_ID = "UserID";
    public static final String APP_PREFERENCES_USER_NAME = "UserName";
    public static final String APP_PREFERENCES_USER_SURNAME = "UserSurname";

    private static Intent loginIntentCp;
    public static SharedPreferences sharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        vkAccessTokenTracker.startTracking();

        sharedPreferences = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        loginIntentCp = loginIntent;

        if (!tokenExists()) {
            this.startActivity(loginIntent);
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

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
