package xyz.bzennn.vkmusicactualizer.implementations.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import xyz.bzennn.vkmusicactualizer.Application;
import xyz.bzennn.vkmusicactualizer.R;
import xyz.bzennn.vkmusicactualizer.implementations.models.AccountInfoModel;
import xyz.bzennn.vkmusicactualizer.models.AccountInfoInterface;
import xyz.bzennn.vkmusicactualizer.views.MainView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private static SharedPreferences.Editor editor = Application.sharedPreferences.edit();
    private static AccountInfoInterface accountInfo = new AccountInfoModel();
    private static ImageView avatarView;
    private static TextView nameView;
    private static View headerView;

    private static Toast toast;
    private static Intent intent;

    public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainActivity = this;

        intent = new Intent(this, LoginActivity.class);

        headerView = navigationView.getHeaderView(0);
        avatarView = headerView.findViewById(R.id.avatarView);
        nameView = headerView.findViewById(R.id.nameView);

        showAvatar();
        showName();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            AccountInfoInterface accountInfo = new AccountInfoModel();
            Log.d("UserInfo", accountInfo.getUserId());
            Log.d("UserInfo", accountInfo.getUserName());
            Log.d("UserInfo", accountInfo.getUserSurname());
            Log.d("UserInfo", accountInfo.getUserAvatarUrl());

            showAvatar();
            showName();

        } else if (id == R.id.nav_logout) {
            toast = Toast.makeText(getApplicationContext(), R.string.logout_toast, Toast.LENGTH_SHORT);
            toast.show();

            editor.remove(Application.APP_PREFERENCES_TOKEN);
            editor.remove(Application.APP_PREFERENCES_USER_ID);
            editor.remove(Application.APP_PREFERENCES_USER_NAME);
            editor.remove(Application.APP_PREFERENCES_USER_SURNAME);
            editor.remove(Application.APP_PREFERENCES_USER_AVATAR_URL);
            editor.apply();

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showList() {

    }

    @Override
    public void updateList() {

    }

    @Override
    public void showDownloaded() {

    }

    @Override
    public void showAvatar() {
        String url = accountInfo.getUserAvatarUrl();
        //Picasso.get().load(url).into(avatarView);
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(avatarView);
        Log.d("UserAvatar", "Avatar loaded: url=" + url);

    }

    @Override
    public void showName() {
        String name = accountInfo.getUserName() + " " + accountInfo.getUserSurname();

        nameView.setText(name);

    }
}
