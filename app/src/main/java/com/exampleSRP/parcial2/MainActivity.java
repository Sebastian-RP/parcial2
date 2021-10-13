package com.exampleSRP.parcial2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm; //acceso a los sensores
    private Sensor s;
    private SensorEventListener evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibrator = (Vibrator) this.getSystemService((Context.VIBRATOR_SERVICE));


        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);//uso de los sensores
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(s==null){
            finish();
        }

        evento = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if(event.values[0]<-4){ //al elegir el valor cero del arreglo hace enfacis al eje Z
                    System.out.println("gire a la derecha");
                }else{
                    if(event.values[0]>4){
                        System.out.println("gire a la izquierda");
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sm.registerListener(evento, s, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
