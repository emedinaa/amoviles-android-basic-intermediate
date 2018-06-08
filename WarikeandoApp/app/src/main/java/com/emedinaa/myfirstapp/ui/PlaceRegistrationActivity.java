package com.emedinaa.myfirstapp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.emedinaa.myfirstapp.BuildConfig;
import com.emedinaa.myfirstapp.R;
import com.emedinaa.myfirstapp.model.entity.Warike;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PlaceRegistrationActivity extends BaseActivity {

    private @Nullable String currentPhotoPath=null;
    private final int REQUEST_CAMERA = 1;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;
    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    private View relativeLayout;
    private ImageView imageViewPhoto;
    private View textViewMessage,progress;
    private EditText editTextName,editTextDesc;
    private Button buttonRegister;

    private String name,description;
    private double lat=-12.088915, lng=-77.0365457;//academia móviles-12.088915,-77.0365457

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_registration);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        ui();
    }

    private void ui(){
        enabledBack();
        relativeLayout= findViewById(R.id.relativeLayout);
        imageViewPhoto= findViewById(R.id.imageViewPhoto);
        textViewMessage= findViewById(R.id.textViewMessage);
        editTextName= findViewById(R.id.editTextName);
        editTextDesc= findViewById(R.id.editTextDesc);
        buttonRegister= findViewById(R.id.buttonRegister);
        progress= findViewById(R.id.progress);

        //events
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    saveWarike();
                }
            }
        });

        //ui states
        textViewMessage.setVisibility(View.VISIBLE);
    }

    private boolean validateForm(){
        name= editTextName.getText().toString();
        description= editTextDesc.getText().toString();

        return true;
    }

    private void saveWarike(){

        Warike warike= new Warike();
        warike.setName(name);
        warike.setDesc(description);
        warike.setLat(lat);
        warike.setLng(lng);
        warike.setPhoto(currentPhotoPath);
        warike.setRate(5);//Rate.MAX
        warikeRepository.addPlace(warike);

        showSnackbar(R.string.warike_successfully, android.R.string.ok,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //close Activity
                        PlaceRegistrationActivity.this.finish();
                    }
                });
    }

    //camera methods
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("CONSOLE", "requestCode "+requestCode+ " resultCode "+resultCode+
        " data "+data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                textViewMessage.setVisibility(View.GONE);
                if (data == null) return;
               Bundle bundle= data.getExtras();
                if (bundle != null) {
                    currentPhotoPath = bundle.getString("PATH");
                    renderPhoto();
                }

            }else{
                textViewMessage.setVisibility(View.VISIBLE);
            }
        }
    }

    private void takePhoto(){
        startActivityForResult(new Intent(this,CustomCameraActivity.class),
                REQUEST_CAMERA);
    }

    private void renderPhoto(){
        if(currentPhotoPath!=null){
            Picasso.with(imageViewPhoto.getContext()).load(new File(currentPhotoPath)).into(imageViewPhoto);
        }
    }

    //location
    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     * <p>
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        progress.setVisibility(View.VISIBLE);
        //buttonRegister.setEnabled(false);
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();

                            lat= mLastLocation.getLatitude();
                            lng=mLastLocation.getLongitude();
                            locationFoundSuccessfully();
                        } else {
                            Log.v("CONSOLE", "getLastLocation:exception", task.getException());
                            showSnackbar(getString(R.string.no_location_detected));
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void locationFoundSuccessfully(){

        Log.v("CONSOLE", "lat "+lat+ " lng "+lng);

        progress.setVisibility(View.GONE);
        //buttonRegister.setEnabled(true);

    }
    /**
     * Shows a {@link Snackbar} using {@code text}.
     *
     * @param text The Snackbar text.
     */
    private void showSnackbar(final String text) {
        View container = findViewById(R.id.main_activity_container);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }


    /**
     * Shows a {@link Snackbar}.
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i("CONSOLE", "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i("CONSOLE", "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i("CONSOLE", "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i("CONSOLE", "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }
    }
}
