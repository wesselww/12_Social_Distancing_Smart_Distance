package de.wirvsvirus.smart_distance_control;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BTAdapter;
    public static int REQUEST_BLUETOOTH = 1;
    private CountDownTimer timerBTDiscovery;
    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                RSSI2DISTANCE rssi2distance = new RSSI2DISTANCE(1.5,2.5);
                Toast.makeText(getApplicationContext(),"Device Name:" + device.getName() +"  RSSI: " + rssi + "dBm "+"Abstand: "+ rssi2distance.getDistance(rssi) + "m", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.BTAdapter = BluetoothAdapter .getDefaultAdapter();
        if (BTAdapter == null){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                     })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        this.enableBluetooth();
        registerReceiver(bReciever, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            this.timerBTDiscovery = new CountDownTimer(10000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    BTAdapter.startDiscovery() ;
                    timerBTDiscovery.start();
                }
            }.start();
    }
    protected void enableBluetooth(){
        if (!this.BTAdapter.isEnabled()){
            Intent enableBT = new Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }
    }
    protected double getDistance(int rssi){
        double dDistance = 0.0;

        return dDistance;
    }
}
