package com.example.user.finalproject_mobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;


public class SensorInfo extends AppCompatActivity implements OnMapReadyCallback {
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private int shake_threshold = 10;
    private int index = 0;
    private boolean showDialog = false;
    private Restaurant restaurant;
    private Predict pre = new Predict();

    private String name;
    boolean aBoodialog = false;
    private double la, lo;
    //    private DataSnapshot dataSnapshot;
    private AnimationDrawable mAnimation = new AnimationDrawable();

    public void setSensor(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void showDialog(final AnimationDrawable mAnimation, final Context context) {
        if ((Math.abs(x) > shake_threshold) || (Math.abs(y) > shake_threshold) || (Math.abs(z) > shake_threshold)) {
            if (!showDialog) {
                showDialog = true;
                FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<Restaurant>> genericTypeIndicator = new GenericTypeIndicator<List<Restaurant>>() {
                        };
                        List<Restaurant> restaurantList = dataSnapshot.getValue(genericTypeIndicator);
                        index = new Predict().getIndex();
                        if (restaurantList != null) {
                            dialogRestaurant(mAnimation, context, restaurantList.get(index));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                mAnimation.stop();
            }

        }
    }

    public void dialogRestaurant(final AnimationDrawable mAnimaton, Context context, Restaurant restaurant) {
        name = restaurant.getName();
        la = restaurant.getLatitude();
        lo = restaurant.getLongitude();
        Dialog dialog = new Dialog(context);
        if (!dialog.isShowing()) {
            dialog.cancel();
            dialog.setContentView(R.layout.restaurant_dialog);
            TextView textView = (TextView) dialog.findViewById(R.id.textView);
            textView.setText(name);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    showDialog = false;
                    mAnimaton.start();

                }
            });
            setMap(dialog, context);
            dialog.show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(la, lo)).title(name);
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(la, lo), 17f));

    }

    private void setMap(Dialog dialog, Context context) {
        MapView mMapView = dialog.findViewById(R.id.map);
        MapsInitializer.initialize(context);
        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    public boolean isBoolean() {
        if (showDialog == true) {
            aBoodialog = false;
        } else aBoodialog = true;

        return aBoodialog;
    }
}
