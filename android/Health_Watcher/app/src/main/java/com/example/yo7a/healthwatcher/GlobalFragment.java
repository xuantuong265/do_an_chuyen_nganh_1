package com.example.yo7a.healthwatcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yo7a.healthwatcher.utilities.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class GlobalFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private TextView tvCases, tvDeaths, tvActive, tvRecovered, tvCritical,
            tvTodayCases, tvTodayRecovered, tvTodayDeaths;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GlobalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlobalFragment newInstance(String param1, String param2) {
        GlobalFragment fragment = new GlobalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_global, container, false);
        initView();
        fetchData();
        // Inflate the layout for this fragment
        return view;
    }

    private void initView() {
        tvCases = (TextView) view.findViewById(R.id.cases);
        tvDeaths = (TextView) view.findViewById(R.id.deaths);
        tvActive = (TextView) view.findViewById(R.id.active);
        tvCritical = (TextView) view.findViewById(R.id.critical);
        tvRecovered = (TextView) view.findViewById(R.id.recovered);
        tvTodayCases = (TextView) view.findViewById(R.id.today_cases);
        tvTodayRecovered = (TextView) view.findViewById(R.id.today_recovered);
        tvTodayDeaths = (TextView) view.findViewById(R.id.today_deaths);
    }

    private void fetchData() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = Constants.URL_API_COVID + "all";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            tvCases.setText(Constants.formatNumber(jsonObject.getDouble("cases")));
                            tvDeaths.setText(Constants.formatNumber(jsonObject.getDouble("deaths")));
                            tvActive.setText(Constants.formatNumber(jsonObject.getDouble("active")));
                            tvRecovered.setText(Constants.formatNumber(jsonObject.getDouble("recovered")));
                            tvCritical.setText(Constants.formatNumber(jsonObject.getDouble("critical")));
                            tvTodayCases.setText(Constants.formatNumber(jsonObject.getDouble("todayCases")));
                            tvTodayDeaths.setText(Constants.formatNumber(jsonObject.getDouble("todayDeaths")));
                            tvTodayRecovered.setText(Constants.formatNumber(jsonObject.getDouble("todayRecovered")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error: " + error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
