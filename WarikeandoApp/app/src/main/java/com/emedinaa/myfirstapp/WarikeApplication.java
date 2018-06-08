package com.emedinaa.myfirstapp;

import android.app.Application;

import com.emedinaa.myfirstapp.model.storage.WarikeRepository;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 6/8/18
 */
public class WarikeApplication extends Application {

    private WarikeRepository warikeRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        warikeRepository= new WarikeRepository(this);
    }

    public WarikeRepository getWarikeRepository() {
        return warikeRepository;
    }
}
