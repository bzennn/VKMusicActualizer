package xyz.bzennn.vkmusicactualizer.implementations.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.bzennn.vkmusicactualizer.Application;
import xyz.bzennn.vkmusicactualizer.Presenter;
import xyz.bzennn.vkmusicactualizer.R;
import xyz.bzennn.vkmusicactualizer.implementations.models.AccountInfoModel;
import xyz.bzennn.vkmusicactualizer.models.AccountInfoInterface;
import xyz.bzennn.vkmusicactualizer.utils.Utils;
import xyz.bzennn.vkmusicactualizer.views.LoginView;
import xyz.bzennn.vkmusicactualizer.views.MainView;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private static SharedPreferences.Editor editor = Application.sharedPreferences.edit();
    private static AccountInfoInterface accountInfo = new AccountInfoModel();
    private static ProgressBar progressBar;
    private static Intent intent;
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(R.string.login_activity);

        TextView view = (TextView)findViewById(R.id.info);
        view.setMovementMethod(new ScrollingMovementMethod());
        String formattedText = "<h3>Info about login:</h3>\n" +
                "<ul>\n" +
                "<li>Blablabla 1</li><br/>\n" +
                "<li>Blablabla 2</li><br/>\n" +
                "<li>Blablabla 3</li><br/>\n" +
                "<li>Blablabla 4</li><br/>\n" +
                "<li>Blablabla 5</li><br/>\n" +
                "<li>Blablabla 6</li><br/>\n" +
                "<li>Blablabla 7</li><br/>\n" +
                "<li>Blablabla 8</li><br/>\n" +
                "<li>Blablabla 9</li><br/>\n" +
                "<li>Blablabla 10</li><br/>\n" +
                "<li>Blablabla 11</li><br/>\n" +
                "<li>Blablabla 12</li><br/>\n" +
                "<li>Blablabla 13</li><br/>\n" +
                "<li>Blablabla 14</li><br/>\n" +
                "<li>Blablabla 15</li><br/>\n" +
                "<li>Blablabla 16</li><br/>\n" +
                "<li>Blablabla 17</li><br/>\n" +
                "<li>Blablabla 18</li><br/>\n" +
                "</ul>";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.setText(Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            view.setText(Html.fromHtml(formattedText));
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        intent = new Intent(this, MainActivity.class);
    }

    @Override
    public void showLoginMessage() {

    }

    public void onLoginButtonClick(View view) {
        VKSdk.login(this, getResources().getString(R.string.scope));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                accountInfo.requestUserInfoIntoPreferences();

                toast = Toast.makeText(getApplicationContext(), R.string.login_toast, Toast.LENGTH_SHORT);
                toast.show();

                editor.putString(Application.APP_PREFERENCES_TOKEN, res.accessToken);
                editor.apply();

                progressBar.setVisibility(ProgressBar.VISIBLE);

                Utils.delay(1000);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                progressBar.setVisibility(ProgressBar.INVISIBLE);

                finish();
            }
            @Override
            public void onError(VKError error) {
                toast = Toast.makeText(getApplicationContext(), R.string.login_error_toast, Toast.LENGTH_SHORT);
                toast.show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
