package com.techease.openspotguard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainFragment extends Fragment implements View.OnClickListener {

    GridView gridView;
    ArrayList<Model> models;
    Adapter adapter;
    CalendarView calendarView;
    Button btnAddBooking;
    ImageView btnRefresh;
    int strDuration=0;
    long getDate;
    Model model;
    android.support.v7.app.AlertDialog alertDialog;
    String groundId,date,userId,strTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sharedPreferences = getActivity().getSharedPreferences("abc", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        calendarView= view.findViewById(R.id.calendarView);
        btnAddBooking= view.findViewById(R.id.btnAddBooking);
        btnRefresh= view.findViewById(R.id.ivRefresh);
        groundId=sharedPreferences.getString("groundid","");
        userId=sharedPreferences.getString("id","");
        gridView=view.findViewById(R.id.gv);
        models=new ArrayList<>();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.format(c);
        apicall();
        adapter = new Adapter(getActivity(), models);
        gridView.setAdapter(adapter);


        btnRefresh.setOnClickListener(this);
        btnAddBooking.setOnClickListener(this);
        getDate=calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //GregorianCalendar calendar= new GregorianCalendar( year, month, dayOfMonth );
                int myasht=month+1;
                date=String.valueOf(year+"-"+myasht+"-"+dayOfMonth);
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                if (alertDialog==null)
                {
                    alertDialog= AlertsUtils.createProgressDialog(getActivity());
                    alertDialog.show();
                }
                apicall();
            }
        });
        return view;
    }

    private void apicall() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://openspot.qa/restapi/bookings", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zmaReg",response);
                if (alertDialog!=null)
                    alertDialog.dismiss();
                models.clear();
                try {
                    JSONObject object=new JSONObject(response);
                    JSONObject jsonObject=object.getJSONObject("data");
                    JSONArray jsonArray=jsonObject.getJSONArray("Morning");
                    JSONArray jsonArrayNoon=jsonObject.getJSONArray("Noon");
                    JSONArray jsonArrayEvening=jsonObject.getJSONArray("Evening");
                    model=new Model();
                    for(int i=0; i< jsonArray.length();i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        model.setTimeFrom(obj.getString("time_from"));
                        model.setTimeTo(obj.getString("time_to"));
                        model.setUserName( obj.getString("user"));
                        model.setUserId(obj.getString("userid"));
                        model.setIsChecked(obj.getString("booked"));
                        model.setTimeId(obj.getString("time_id"));
                        model.setPrice(obj.getString("price"));
                       // models.add(model);
                    }
                    for(int i=0; i< jsonArrayNoon.length();i++) {
                        JSONObject obj = jsonArrayNoon.getJSONObject(i);
                        model.setTimeFrom(obj.getString("time_from"));
                        model.setTimeTo(obj.getString("time_to"));
                        model.setUserName( obj.getString("user"));
                        model.setUserId(obj.getString("userid"));
                        model.setIsChecked(obj.getString("booked"));
                        model.setTimeId(obj.getString("time_id"));
                        model.setPrice(obj.getString("price"));
                       // models.add(model);
                    }
                    for(int i=0; i< jsonArrayEvening.length();i++) {
                        JSONObject obj = jsonArrayEvening.getJSONObject(i);
                        model.setTimeFrom(obj.getString("time_from"));
                        model.setTimeTo(obj.getString("time_to"));
                        model.setUserName( obj.getString("user"));
                        model.setUserId(obj.getString("userid"));
                        model.setIsChecked(obj.getString("booked"));
                        model.setTimeId(obj.getString("time_id"));
                        model.setPrice(obj.getString("price"));

                    }
                    models.add(model);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (alertDialog!=null)
                    alertDialog.dismiss();
                AlertsUtils.showErrorDialog(getActivity(),error.getMessage().toString());
                Log.d("zma error", String.valueOf(error.getCause()));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ground_id",groundId);
                params.put("date",date);
                return params;
            }
        };

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        String check;
        switch (v.getId()) {
            case R.id.btnAddBooking:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_addbooking, null);
                dialogBuilder.setView(dialogView);

                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etPhoneNo = dialogView.findViewById(R.id.etPhoneNo);
                TextView btnBook = dialogView.findViewById(R.id.btnBook);
                TextView btnCancel = dialogView.findViewById(R.id.btnCancel);
                TextView tvDate = dialogView.findViewById(R.id.tvDate);
                TextView tvTime = dialogView.findViewById(R.id.tvTime);
                TextView tvDuration = dialogView.findViewById(R.id.tvDuration);

                tvDate.setText(date);
                tvTime.setText(strTime);
                tvDuration.setText(String.valueOf(strDuration));

                btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=0; i<Adapter.timeIdArray.size(); i++)
                        {
                            Adapter.timeIdArray.get(i).toString().replace("[","").replace("]","").replace(",", " ");
                            apicallForBooking(Adapter.timeIdArray.get(i));
                        }

                    }
                });

                date = "";
                strTime = "";
                strDuration = 0;

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                break;
        }
    }

    private void apicallForBooking(final Integer integer) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://openspot.qa/openspot/bookground", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zmaReg",response);
                if (alertDialog!=null)
                    alertDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String message=jsonObject.getString("message");
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (alertDialog!=null)
                    alertDialog.dismiss();
                AlertsUtils.showErrorDialog(getActivity(),error.getMessage().toString());
                Log.d("zma error", String.valueOf(error.getCause()));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ground_id",groundId);
                params.put("user_id",userId);
                params.put("time_id",String.valueOf(integer));
                params.put("price","60");
                return params;
            }
        };

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);
    }
}
