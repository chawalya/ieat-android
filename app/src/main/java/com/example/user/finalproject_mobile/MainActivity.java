package com.example.user.finalproject_mobile;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    Context context;
    ImageView img;
    static final int POLL_INTERVAL = 3000;
    SensorInfo sensorInfo = new SensorInfo();
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    private DatabaseManager data;
    private Predict p = new Predict();
    Handler hdr = new Handler();
    private SensorInfo sensorinfo = new SensorInfo();
    public  AnimationDrawable mAnimation;
    private Runnable pollTask = new Runnable() {
        @Override
        public void run() {
            sensorInfo.showDialog(mAnimation,context);
            hdr.postDelayed(pollTask, POLL_INTERVAL);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.unnamed);
        BitmapDrawable[] frame = new BitmapDrawable[208];
        int i=0;
        for (i=1;i<=207;i++){
            frame[i] =(BitmapDrawable) getResources().getDrawable(getResources().getIdentifier(
                    (String) "food"+i,"drawable",this.getPackageName()));
        }
        int reasonableDuration = 30;
        mAnimation = new AnimationDrawable();
        for(i=1;i<=207;i++){
            mAnimation.addFrame(frame[i],reasonableDuration);
        }
        img.setImageDrawable(mAnimation);
        mAnimation.start();

//        Restaurant restaurant = new Restaurant();
//        restaurant.setLongitude(15.324234);
//        restaurant.setLatitude(10.3243234);
//        restaurant.setName("ร้านทดสอบ");
        data = new DatabaseManager();
        data.createRestaurant(p.getRestaurantArrayList());
    }
    @Override

    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        hdr.postDelayed(pollTask, POLL_INTERVAL);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if(type == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            sensorInfo.setSensor(x, y, z);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}
