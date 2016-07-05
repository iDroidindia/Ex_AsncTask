package com.example.manmohan.example_asnctask;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by manmohan on 15/11/15.
 */
public class Sensor_Proximity extends Activity implements SensorEventListener {

    TextView TV;
    SensorManager SM;
    Sensor S_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_pr_layout);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        S_Type = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        TV = (TextView) findViewById(R.id.tv_status);

        SM.registerListener(this, S_Type, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent SE) {
        if (SE.values[0] == 0) {

                 //for (int i=0; i< SE.values.length; i++)

            //    Log.e("Values" + i, "" + SE.values[i]);

            TV.setText("You are very near to the Sensor.");
        }
        else {

            for (int i=0; i< SE.values.length; i++)

                Log.e("Values Else "+i, ""+SE.values[i]);

            TV.setText("You are very far from the Sensor.");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

}
