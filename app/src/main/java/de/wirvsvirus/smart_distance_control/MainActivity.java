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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BTAdapter;
    public static int REQUEST_BLUETOOTH = 1;
    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                Toast.makeText(getApplicationContext(),"  RSSI: " + rssi + "dBm", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTAdapter = BluetoothAdapter .getDefaultAdapter();
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
    }
    protected void enableBluetooth(){
        if (!this.BTAdapter.isEnabled()){
            Intent enableBT = new Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }
    }
    protected void discoverBluetoothDevices(){

    }
}
