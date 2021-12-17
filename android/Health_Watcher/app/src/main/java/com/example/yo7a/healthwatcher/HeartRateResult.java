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


public class HeartRateResult extends AppCompatActivity implements SaveDatabase{

    private String user, Date;
    int HR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_result);

        preferenceManager = new PreferenceManager(getApplicationContext());

        Date = df.format(today);
        TextView RHR = this.findViewById(R.id.HRR);
        ImageButton SHR = this.findViewById(R.id.SendHR);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            HR = bundle.getInt("bpm");
            user = bundle.getString("Usr");
            RHR.setText(String.valueOf(HR));
            this.saveDataHistory(preferenceManager.getString(Constants.KEY_USER_ID), Constants.BPM, String.valueOf(HR));
        }

        SHR.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
            i.putExtra(Intent.EXTRA_TEXT, user + "'s Heart Rate " + "\n" + " at " + Date + " is :    " + HR);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(HeartRateResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(HeartRateResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
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
