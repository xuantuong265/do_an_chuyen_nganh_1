package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Primary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

//        ImageButton HeartRate = this.findViewById(R.id.HR);
//        ImageButton BloodPressure = this.findViewById(R.id.BP);
//        ImageButton Ox2 = this.findViewById(R.id.O2);
//        ImageButton RRate = this.findViewById(R.id.RR);
//        ImageButton VitalSigns = this.findViewById(R.id.VS);
//        ImageButton Abt = this.findViewById(R.id.About);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }

//        Abt.setOnClickListener(v -> {
//            Intent i = new Intent(v.getContext(), AboutApp.class);
//            startActivity(i);
//            finish();
//        });


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
//        HeartRate.setOnClickListener(v -> {
//            p = 1;
//            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
//            i.putExtra("Usr", user);
//            i.putExtra("Page", p);
//            startActivity(i);
//            finish();
//        });

//        BloodPressure.setOnClickListener(v -> {
//            p = 2;
//            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
//            i.putExtra("Usr", user);
//            i.putExtra("Page", p);
//            startActivity(i);
//            finish();
//        });
//
//        RRate.setOnClickListener(v -> {
//            p = 3;
//            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
//            i.putExtra("Usr", user);
//            i.putExtra("Page", p);
//            startActivity(i);
//            finish();
//        });

//        Ox2.setOnClickListener(v -> {
//            p = 2;
//            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
//            i.putExtra("Usr", user);
//            i.putExtra("Page", p);
//            startActivity(i);
//            finish();
//
//        });

//        VitalSigns.setOnClickListener(v -> {
//            p = 5;
//            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
//            i.putExtra("Usr", user);
//            i.putExtra("Page", p);
//            startActivity(i);
//            finish();
//        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.heart_rate:
                Intent i = new Intent(Primary.this, StartVitalSigns.class);
                i.putExtra("Usr", user);
                startActivity(i);
                break;
            case R.id.respiration_rate:
                Intent i1 = new Intent(Primary.this, StartVitalSigns.class);
                i1.putExtra("Usr", user);
                startActivity(i1);
                break;
            case R.id.blood_pressure:
                Intent i2 = new Intent(Primary.this, StartVitalSigns.class);
                i2.putExtra("Usr", user);
                startActivity(i2);
                break;
            case R.id.oxygen_saturation:
                Intent i3 = new Intent(Primary.this, StartVitalSigns.class);
                i3.putExtra("Usr", user);
                startActivity(i3);
                break;
        }
        return true;
    }
}

