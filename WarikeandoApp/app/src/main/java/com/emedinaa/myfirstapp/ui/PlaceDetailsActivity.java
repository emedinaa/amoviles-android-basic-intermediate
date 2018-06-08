package com.emedinaa.myfirstapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emedinaa.myfirstapp.R;
import com.emedinaa.myfirstapp.model.entity.Warike;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PlaceDetailsActivity extends BaseActivity {

    private View relativeLayout;
    private ImageView imageViewPhoto;
    private TextView textViewName,textViewDesc;
    private Button buttonMap;

    private Warike warike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        extras();
        ui();

        populate();
    }

    private void ui() {
        enabledBack();
        imageViewPhoto= findViewById(R.id.imageViewPhoto);
        textViewName= findViewById(R.id.textViewName);
        textViewDesc= findViewById(R.id.textViewDesc);
        buttonMap= findViewById(R.id.buttonMap);

        //events
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (warike != null) { goToMap();}
            }
        });
    }

    private void populate(){
        if(warike==null)return;

        textViewName.setText(warike.getName());
        textViewDesc.setText(warike.getDesc());
        Picasso.with(imageViewPhoto.getContext()).load(
                new File(warike.getPhoto())).into(imageViewPhoto);
    }

    private void goToMap(){
        //https://developers.google.com/maps/documentation/urls/android-intents

        // Creates an Intent that will load a map of San Francisco
        //Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Uri gmmIntentUri = Uri.parse("geo:"+warike.getLat()+","+warike.getLng()+"");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }

    }

    private void extras(){
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle= getIntent().getExtras();
            warike=(Warike) bundle.getSerializable("WARIKE");
        }

    }
}
