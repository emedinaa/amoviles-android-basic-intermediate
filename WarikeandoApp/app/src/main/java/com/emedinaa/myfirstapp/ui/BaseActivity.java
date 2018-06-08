package com.emedinaa.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.emedinaa.myfirstapp.WarikeApplication;
import com.emedinaa.myfirstapp.model.storage.WarikeRepository;

public abstract class BaseActivity extends AppCompatActivity {

    protected WarikeRepository warikeRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        warikeRepository= ((WarikeApplication)getApplication()).getWarikeRepository();
    }

    protected void enabledBack(){
        if(getSupportActionBar()==null)return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected void next(Class<?> activityClass, Bundle bundle, boolean destroy){
        Intent intent= new Intent(this, activityClass);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (destroy) {
            finish();
        }
    }
}
