package de.wirvsvirus.smart_distance_control;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;


    private BluetoothAdapter BTAdapter;
    public static int REQUEST_BLUETOOTH = 1;
    private CountDownTimer timerBTDiscovery;
    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                RSSI2DISTANCE rssi2distance = new RSSI2DISTANCE(1.5, 2.5);
                alarmManager.checkDistance((int) (rssi2distance.getDistance(rssi)));
                Toast.makeText(getApplicationContext(), "Device Name:" + device.getName() + "  RSSI: " + rssi + "dBm " + "Abstand: " + rssi2distance.getDistance(rssi) + "cm", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        this.BTAdapter = BluetoothAdapter.getDefaultAdapter();


        alarmManager = new AlarmManager(getApplicationContext(), (ImageView) findViewById(R.id.alarm_icon));


        BTAdapter = BluetoothAdapter.getDefaultAdapter();

        if (BTAdapter == null) {
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
        this.timerBTDiscovery = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                BTAdapter.startDiscovery();
                timerBTDiscovery.start();
            }
        }.start();
    }

    protected void enableBluetooth() {
        if (!this.BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }
    }

    protected double getDistance(int rssi) {
        double dDistance = 0.0;

        return dDistance;
    }


    public void alarmbutton(View v) {
        alarmManager.alarmbutton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tbSettings:
                startActivity(new Intent(MainActivity.this, Settings.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
