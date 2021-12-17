package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yo7a.healthwatcher.databinding.FragmentHomeBinding;
import com.example.yo7a.healthwatcher.utilities.Constants;
import com.example.yo7a.healthwatcher.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeFragment extends Fragment {

    private int p;
    private PreferenceManager preferenceManager;
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        preferenceManager = new PreferenceManager(getActivity());
        loadUserDetail();
        getToken();

        binding.HR.setOnClickListener(v -> {
            p = 1;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Page", p);
            startActivity(i);
        });

        binding.O2.setOnClickListener(v -> {
            p = 4;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Page", p);
            startActivity(i);
        });

        return  binding.getRoot();
    }

    private void loadUserDetail() {
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.person.setImageBitmap(bitmap);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
                .addOnFailureListener(e -> showToast("Unable to update token"));
    }
}