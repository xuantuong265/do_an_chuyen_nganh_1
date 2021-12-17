package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yo7a.healthwatcher.utilities.Constants;
import com.example.yo7a.healthwatcher.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class O2Result extends AppCompatActivity implements SaveDatabase{

    private String user, Date;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    int O2;

    private PreferenceManager preferenceManager;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o2_result);

        preferenceManager = new PreferenceManager(getApplicationContext());

        Date = df.format(today);
        TextView RO2 = this.findViewById(R.id.O2R);
        ImageButton SO2 = this.findViewById(R.id.SendO2);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            O2 = bundle.getInt("O2R");
            user = bundle.getString("Usr");
            RO2.setText(String.valueOf(O2));

            saveDataHistory(preferenceManager.getString(Constants.KEY_USER_ID), Constants.MEASURE_OXYGEN, String.valueOf(O2));
        }

        SO2.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
            i.putExtra(Intent.EXTRA_TEXT, user + "'s Oxygen Saturation Level " + "\n" + " at " + Date + " is :   " + O2);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(O2Result.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(O2Result.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }

    @Override
    public void saveDataHistory(String id, String type, String number) {
        HashMap<String, Object> history = new HashMap<>();
        history.put("idUser", id);
        history.put("number", number);
        history.put("type", type);
        history.put(Constants.KEY_TIMESTAMP, new Date());

        database.collection(Constants.KEY_COLLECTION_HISTORY)
                .add(history)
                .addOnSuccessListener(documentReference -> {
                    Log.d("thanh cong", "okkkk");
                })
                .addOnFailureListener(exception -> {
                    Log.d("that bai", "sfsdfjskjf");
                });
    }
}
