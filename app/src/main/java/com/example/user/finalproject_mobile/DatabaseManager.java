package com.example.user.finalproject_mobile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private DatabaseReference databaseReference;


    public DatabaseManager(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void createRestaurant(ArrayList<Restaurant> restaurantArrayList){
        databaseReference.setValue(restaurantArrayList);
    }

    public void getRestaurant(int index){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Restaurant>> genericTypeIndicator =new GenericTypeIndicator<List<Restaurant>>(){};
                List<Restaurant> restaurantList = dataSnapshot.getValue(genericTypeIndicator);

                Log.d("onDataChange", "onDataChange: " + restaurantList);


            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
