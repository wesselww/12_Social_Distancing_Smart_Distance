package de.wirvsvirus.smart_distance_control;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    SharedPreferences settings;

    EditText MinDistance;
    EditText OptDistance;
    EditText MaxRSSI;
    EditText MinRSSI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = getSharedPreferences(MainActivity.APP_TAG, MODE_PRIVATE);

        initFormValues();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void initFormValues() {
        int tvMinDistance = settings.getInt("tvMinDistance", 150);
        int tvOptDistance = settings.getInt("tvOptDistance", 200);
        int tvMaxRSSI = settings.getInt("tvMaxRSSI", -40);
        int tvMinRSSI = settings.getInt("tvMinRSSI", -80);

        MinDistance = findViewById(R.id.tvMinDistance);
        OptDistance = findViewById(R.id.tvOptDistance);
        MaxRSSI = findViewById(R.id.tvMaxRSSI);
        MinRSSI = findViewById(R.id.tvMinRSSI);

        MinDistance.setText(String.valueOf(tvMinDistance));
        OptDistance.setText(String.valueOf(tvOptDistance));
        MaxRSSI.setText(String.valueOf(tvMaxRSSI));
        MinRSSI.setText(String.valueOf(tvMinRSSI));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.tbSettings);
        item.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public void save(View v) {

        SharedPreferences.Editor editor = settings.edit();

        int tvMinDistance = 0;
        int tvOptDistance = 0;
        int tvMaxRSSI = 0;
        int tvMinRSSI = 0;

        try {
            tvMinDistance = Integer.parseInt(MinDistance.getText().toString());
        } catch (Exception ex) {}

        try {
            tvOptDistance = Integer.parseInt(OptDistance.getText().toString());
        } catch (Exception ex) {}

        try {
            tvMaxRSSI = Integer.parseInt(MaxRSSI.getText().toString());
        } catch (Exception ex) {}

        try {
            tvMinRSSI = Integer.parseInt(MinRSSI.getText().toString());
        } catch (Exception ex) {}

        if (tvMinDistance != 0 ){
            editor.putInt("tvMinDistance", tvMinDistance);
        }

        if (tvOptDistance != 0 ){
            editor.putInt("tvOptDistance", tvOptDistance);
        }

        if (tvMaxRSSI != 0 ){
            editor.putInt("tvMaxRSSI", tvMaxRSSI);
        }

        if (tvMinRSSI != 0 ){
            editor.putInt("tvMinRSSI", tvMinRSSI);
        }

        editor.commit();

        finish();

    }
}
