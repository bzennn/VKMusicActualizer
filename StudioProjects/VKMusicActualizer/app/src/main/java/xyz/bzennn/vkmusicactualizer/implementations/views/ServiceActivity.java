package xyz.bzennn.vkmusicactualizer.implementations.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xyz.bzennn.vkmusicactualizer.R;
import xyz.bzennn.vkmusicactualizer.views.ServiceView;

public class ServiceActivity extends AppCompatActivity implements ServiceView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }
}
