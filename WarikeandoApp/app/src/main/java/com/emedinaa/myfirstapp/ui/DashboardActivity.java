package com.emedinaa.myfirstapp.ui;

import android.os.Bundle;
import android.view.View;

import com.emedinaa.myfirstapp.R;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ui();
    }

    private void ui() {

        findViewById(R.id.imageViewMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(PlacesMapActivity.class,null,false);
            }
        });

        findViewById(R.id.imageViewCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(PlaceRegistrationActivity.class,null,false);
            }
        });
    }
}
