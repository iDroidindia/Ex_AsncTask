package com.example.manmohan.example_asnctask;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by manmohan on 14/11/15.
 */
public class SS_Act extends Activity implements SensorEventListener {

    private SensorManager SM;
    private TextView TV;
    private boolean color=false;
    private long lastupdate;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sensors);

        TV=(TextView)findViewById(R.id.tv);
        TV.setBackgroundColor(Color.GREEN);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        SM.registerListener(this, SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),3);
        lastupdate=System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent Event)
    {
        if(Event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {

            float values[]=Event.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];

            //use the following formula
            //use gravity according to your place if you are on moon than use moon gravity

            float asr=((x*x)+(y*y)+(z*z))/((SensorManager.GRAVITY_EARTH)*(SensorManager.GRAVITY_EARTH));
            TV.setText("X = "+x+"\n"+" Y = "+y+"\n"+"Z = "+z);

            long actual=System.currentTimeMillis();
            if(asr>=2) {

                //					Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                if(lastupdate-actual>=200)
                {
                    //tv.setBackgroundColor(Color.RED);
                    return;
                }
                lastupdate=actual;
                if(color) {

                    TV.setBackgroundColor(Color.GREEN);
                }
                else {

                    TV.setBackgroundColor(Color.RED);
                }
                color=!color;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
}