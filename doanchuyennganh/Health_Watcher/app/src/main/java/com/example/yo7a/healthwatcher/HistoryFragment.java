package com.example.yo7a.healthwatcher;

import java.sql.Timestamp;
import java.util.Date;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yo7a.healthwatcher.Adapters.ItemHistoryAdapter;
import com.example.yo7a.healthwatcher.Models.ItemHistory;
import com.example.yo7a.healthwatcher.Models.SubItemHistory;
import com.example.yo7a.healthwatcher.databinding.FragmentHistoryBinding;
import com.example.yo7a.healthwatcher.utilities.Constants;
import com.example.yo7a.healthwatcher.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private PreferenceManager preferenceManager;
    private LinearLayoutManager layoutManager;
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);

        // render data
        preferenceManager = new PreferenceManager(getContext());
        this.getHistoryItem(preferenceManager.getString(Constants.KEY_USER_ID));
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void getHistoryItem(String idUser) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        List<SubItemHistory> listSub = new ArrayList<>();

        database.collection(Constants.KEY_COLLECTION_HISTORY)
                .whereEqualTo("idUser", idUser)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size() > 0) {
                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                            SubItemHistory subItemHistory = new SubItemHistory(documentSnapshot.getId(),
                                    documentSnapshot.getString("idUser"),
                                    documentSnapshot.getString("number"),
                                    documentSnapshot.getString("type"),
                                    getReadableDateTime(documentSnapshot.getDate(Constants.KEY_TIMESTAMP))
                                    );
                            listSub.add(subItemHistory);
                        }
                        List<ItemHistory> list = this.getItem(listSub);

                        layoutManager = new LinearLayoutManager(getContext());
                        ItemHistoryAdapter itemAdapter = new ItemHistoryAdapter(list);
                        binding.historyRecyclerview.setAdapter(itemAdapter);
                        binding.historyRecyclerview.setLayoutManager(layoutManager);
                        binding.historyRecyclerview.setVisibility(View.VISIBLE);
                    }
                });
    }

    private String getReadableDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    private List<ItemHistory> getItem(List<SubItemHistory> list) {
        List<ItemHistory> itemHistories = new ArrayList<>();
        List<String> times = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (times.size() == 0) {
                times.add(list.get(i).getTime());
            }else {
                if (!times.contains(list.get(i).getTime())) {
                    times.add(list.get(i).getTime());
                }
            }
        }

        for (int i = 0; i < times.size(); i++) {
            ItemHistory itemHistory;
            List<SubItemHistory> subItemHistories = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getTime().equals(times.get(i))) {
                    System.out.println("sss");
                    subItemHistories.add(list.get(j));
                }
            }
            itemHistory = new ItemHistory(times.get(i), subItemHistories);
            itemHistories.add(itemHistory);
        }

        return itemHistories;
    }
}