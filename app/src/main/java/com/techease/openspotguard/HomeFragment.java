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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment implements View.OnClickListener {

    CalendarView calendarView;
    Button btnAddBooking;
    ImageView btnRefresh;
    int strDuration=0;
    String strName,strPhoneNo;
    long getDate;
    int length;
    int totalPrice=0,temp;
    int check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12,check13,check14,
            check15,check16,check17,check18,check19,check20,check21,check22,check23,check24,check25,check26,check27,check28,check29,check30;
    String bookedBy;
    String date,strUseriD,strGroundId,timeFrom,timeTo,username,bookedByPhone="Booked by phone",strTime,strTimeTo,strTimeFrom,strDate;
    TextView tv930_10,tv10_1030,tv1030_11,tv11_1130,tv1130_12,tv12_1230,tv13_1330,tv1330_14,tv14_1430,tv1430_15,tv15_1530,
    tv1530_16,tv20_2030,tv2030_21,tv21_2130,tv2130_22,tv22_2230,tv2230_23,tv2330_24,tv24_130,tv130_2,tv2_230,
    tv230_3,tv3_330,tv16_1630,tv1630_17,tv17_1730,tv1730_18,tv18_1830,tv1830_19;
    int timeId1=0,timeId2=0,timeId3=0,timeId4=0,timeId5=0,timeId6=0,timeId7=0,timeId8=0,timeId9=0,timeId10=0,timeId11=0,timeId12=0,timeId13=0,timeId14=0
            ,timeId15=0,timeId16=0,timeId17=0,timeId18=0,timeId19=0,timeId20=0,timeId21=0,timeId22=0,timeId23=0,timeId24=0,timeId111=0,timeId222=0,
            timeId333=0,timeId444=0,timeId555=0,timeId666=0;
    String price1,price2,price3,price4,price5,price6,price7,price8,price9,price10,price11,price12,price13,price14,
            price15,price16,price17,price18,price19,price20,price21,price22,price23,price24,price111,price222,price333,price444,price555,price666;
    String timeTo1="time",timeTo2="time",timeTo3="time",timeTo4="time",timeTo5="time",timeTo6="time",timeTo7="time",
            timeTo8="time",timeTo9="time",timeTo10="time",timeTo11="time",timeTo12="time",timeTo13="time",
            timeTo14="time",timeTo15="time",timeTo16="time",timeTo17="time",timeTo18="time",timeTo19="time",timeTo20="time",
            timeTo21="time",timeTo22="time",timeTo23="time",timeTo24="time",timeTo25="time",
            timeTo26="time",timeTo27="time",timeTo28="time",timeTo29="time",timeTo30="time";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    android.support.v7.app.AlertDialog alertDialog;
    public static ArrayList<Integer> timeIds=new ArrayList<Integer>();
    public static ArrayList<String> priceArray=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sharedPreferences = getActivity().getSharedPreferences("abc", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        strGroundId=sharedPreferences.getString("groundid","");
        strUseriD=sharedPreferences.getString("id","");
        calendarView= view.findViewById(R.id.calendarView);
        btnAddBooking= view.findViewById(R.id.btnAddBooking);
        btnRefresh= view.findViewById(R.id.ivRefresh);
        tv930_10= view.findViewById(R.id.tv930_10);
        tv10_1030= view.findViewById(R.id.tv10_1030);
        tv1030_11= view.findViewById(R.id.tv1030_11);
        tv11_1130= view.findViewById(R.id.tv11_1130);
        tv1130_12= view.findViewById(R.id.tv1130_12);
        tv12_1230= view.findViewById(R.id.tv12_1230);
        tv13_1330= view.findViewById(R.id.tv13_1330);
        tv1330_14= view.findViewById(R.id.tv1330_14);
        tv14_1430= view.findViewById(R.id.tv14_1430);
        tv1430_15= view.findViewById(R.id.tv1430_15);
        tv15_1530= view.findViewById(R.id.tv15_1530);
        tv1530_16= view.findViewById(R.id.tv1530_16);
        tv16_1630= view.findViewById(R.id.tv16_1630);
        tv1630_17= view.findViewById(R.id.tv1630_17);
        tv17_1730= view.findViewById(R.id.tv17_1730);
        tv1730_18= view.findViewById(R.id.tv1730_18);
        tv18_1830= view.findViewById(R.id.tv18_1830);
        tv1830_19= view.findViewById(R.id.tv1830_19);
        tv20_2030= view.findViewById(R.id.tv20_2030);
        tv2030_21= view.findViewById(R.id.tv2030_21);
        tv21_2130= view.findViewById(R.id.tv21_2130);
        tv2130_22= view.findViewById(R.id.tv2130_22);
        tv22_2230= view.findViewById(R.id.tv22_2230);
        tv2230_23= view.findViewById(R.id.tv2230_23);
        tv2330_24= view.findViewById(R.id.tv2330_24);
        tv24_130= view.findViewById(R.id.tv24_130);
        tv130_2= view.findViewById(R.id.tv130_2);
        tv2_230= view.findViewById(R.id.tv2_230);
        tv230_3= view.findViewById(R.id.tv230_3);
        tv3_330= view.findViewById(R.id.tv3_330);

        tv930_10.setOnClickListener(this);
        tv10_1030.setOnClickListener(this);
        tv1030_11.setOnClickListener(this);
        tv11_1130.setOnClickListener(this);
        tv1130_12.setOnClickListener(this);
        tv12_1230.setOnClickListener(this);
        tv13_1330.setOnClickListener(this);
        tv1330_14.setOnClickListener(this);
        tv14_1430.setOnClickListener(this);
        tv1430_15.setOnClickListener(this);
        tv15_1530.setOnClickListener(this);
        tv1530_16.setOnClickListener(this);
        tv16_1630.setOnClickListener(this);
        tv1630_17.setOnClickListener(this);
        tv17_1730.setOnClickListener(this);
        tv1730_18.setOnClickListener(this);
        tv18_1830.setOnClickListener(this);
        tv1830_19.setOnClickListener(this);
        tv20_2030.setOnClickListener(this);
        tv2030_21.setOnClickListener(this);
        tv21_2130.setOnClickListener(this);
        tv2130_22.setOnClickListener(this);
        tv22_2230.setOnClickListener(this);
        tv2230_23.setOnClickListener(this);
        tv2330_24.setOnClickListener(this);
        tv24_130.setOnClickListener(this);
        tv130_2.setOnClickListener(this);
        tv2_230.setOnClickListener(this);
        tv230_3.setOnClickListener(this);
        tv3_330.setOnClickListener(this);




        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.format(c);
        apicall();

        if (length<1)
        {

            temp=123;
            tv16_1630.setBackgroundResource(R.drawable.zigzag);
            tv1630_17.setBackgroundResource(R.drawable.zigzag);
            tv17_1730.setBackgroundResource(R.drawable.zigzag);
            tv1730_18.setBackgroundResource(R.drawable.zigzag);
            tv18_1830.setBackgroundResource(R.drawable.zigzag);
            tv1830_19.setBackgroundResource(R.drawable.zigzag);
            tv20_2030.setBackgroundResource(R.drawable.zigzag);
            tv2030_21.setBackgroundResource(R.drawable.zigzag);
            tv21_2130.setBackgroundResource(R.drawable.zigzag);
            tv2130_22.setBackgroundResource(R.drawable.zigzag);
            tv22_2230.setBackgroundResource(R.drawable.zigzag);
            tv2230_23.setBackgroundResource(R.drawable.zigzag);
            tv2330_24.setBackgroundResource(R.drawable.zigzag);
            tv24_130.setBackgroundResource(R.drawable.zigzag);
            tv130_2.setBackgroundResource(R.drawable.zigzag);
            tv2_230.setBackgroundResource(R.drawable.zigzag);
            tv230_3.setBackgroundResource(R.drawable.zigzag);
            tv3_330.setBackgroundResource(R.drawable.zigzag);

        }

        btnRefresh.setOnClickListener(this);
        btnAddBooking.setOnClickListener(this);
        getDate=calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int myasht=month+1;
                date=String.valueOf(year+"-"+myasht+"-"+dayOfMonth);

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

        tv930_10.setText("");
        tv10_1030.setText("");
        tv1030_11.setText("");
        tv11_1130.setText("");
        tv1130_12.setText("");
        tv12_1230.setText("");
        tv13_1330.setText("");
        tv1330_14.setText("");
        tv14_1430.setText("");
        tv1430_15.setText("");
        tv15_1530.setText("");
        tv1530_16.setText("");
        tv16_1630.setText("");
        tv1630_17.setText("");
        tv17_1730.setText("");
        tv1730_18.setText("");
        tv18_1830.setText("");
        tv1830_19.setText("");
        tv20_2030.setText("");
        tv2030_21.setText("");
        tv21_2130.setText("");
        tv2130_22.setText("");
        tv22_2230.setText("");
        tv2230_23.setText("");
        tv2330_24.setText("");
        tv24_130.setText("");
        tv130_2.setText("");
        tv2_230.setText("");
        tv230_3.setText("");
        tv3_330.setText("");

        tv930_10.setBackgroundResource(R.drawable.boundry);
        tv10_1030.setBackgroundResource(R.drawable.boundry);
        tv1030_11.setBackgroundResource(R.drawable.boundry);
        tv11_1130.setBackgroundResource(R.drawable.boundry);
        tv1130_12.setBackgroundResource(R.drawable.boundry);
        tv12_1230.setBackgroundResource(R.drawable.boundry);
        tv13_1330.setBackgroundResource(R.drawable.boundry);
        tv1330_14.setBackgroundResource(R.drawable.boundry);
        tv14_1430.setBackgroundResource(R.drawable.boundry);
        tv1430_15.setBackgroundResource(R.drawable.boundry);
        tv15_1530.setBackgroundResource(R.drawable.boundry);
        tv1530_16.setBackgroundResource(R.drawable.boundry);
        tv16_1630.setBackgroundResource(R.drawable.zigzag);
        tv1630_17.setBackgroundResource(R.drawable.zigzag);
        tv17_1730.setBackgroundResource(R.drawable.zigzag);
        tv1730_18.setBackgroundResource(R.drawable.zigzag);
        tv18_1830.setBackgroundResource(R.drawable.zigzag);
        tv1830_19.setBackgroundResource(R.drawable.zigzag);
        tv20_2030.setBackgroundResource(R.drawable.zigzag);
        tv2030_21.setBackgroundResource(R.drawable.zigzag);
        tv21_2130.setBackgroundResource(R.drawable.zigzag);
        tv2130_22.setBackgroundResource(R.drawable.zigzag);
        tv22_2230.setBackgroundResource(R.drawable.zigzag);
        tv2230_23.setBackgroundResource(R.drawable.zigzag);
        tv2330_24.setBackgroundResource(R.drawable.zigzag);
        tv24_130.setBackgroundResource(R.drawable.zigzag);
        tv130_2.setBackgroundResource(R.drawable.zigzag);
        tv2_230.setBackgroundResource(R.drawable.zigzag);
        tv230_3.setBackgroundResource(R.drawable.zigzag);
        tv3_330.setBackgroundResource(R.drawable.zigzag);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://openspot.qa/restapi/bookings", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("bookingResponse",response);
                if (alertDialog!=null)
                    alertDialog.dismiss();
                try {
                    JSONObject object=new JSONObject(response);
                    JSONObject jsonObject=object.getJSONObject("data");
                    JSONArray jsonArray=jsonObject.getJSONArray("Morning");
                    for(int i=0; i< jsonArray.length();i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        timeFrom = obj.getString("time_from");
                        timeTo = obj.getString("time_to");
                        username = obj.getString("user");
                        bookedBy=obj.getString("booked_by");

                        if (timeTo.equals("09:30"))
                        {
                            timeTo1=timeTo;
                            timeId1=obj.getInt("time_id");
                            price1=obj.getString("price") ;
                            if (!username.equals("")) {
                                tv930_10.setText("Booked by "+username);
                                if (bookedBy.equals("admin"))
                                {
                                    tv930_10.setBackgroundResource(R.color.colorOrange);
                                }
                                else {
                                    tv930_10.setBackgroundResource(R.color.colorAccent);
                                }


                            }

                        }
                        else
                            if (timeTo.equals("10:30")) {
                                timeTo2=timeTo;
                            timeId2=obj.getInt("time_id");
                            price2=obj.getString("price") ;
                            bookedBy=obj.getString("booked_by");
                            if (!username.equals("")) {
                                tv10_1030.setText("Booked by "+username);
                                if (bookedBy.equals("admin"))
                                {
                                    tv10_1030.setBackgroundResource(R.color.colorOrange);
                                }
                                else {
                                    tv10_1030.setBackgroundResource(R.color.colorAccent);
                                }
                             }

                        }
                        else
                            if (timeTo.equals("11:00")) {
                                timeTo3=timeTo;
                                timeId3 = obj.getInt("time_id");
                                price3 = obj.getString("price");
                                bookedBy=obj.getString("booked_by");
                                if (!username.equals("")) {
                                    tv1030_11.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv1030_11.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv1030_11.setBackgroundResource(R.color.colorAccent);
                                    }
                                }

                            }
                        else
                            if (timeTo.equals("11:30")) {
                                timeTo4=timeTo;
                            timeId4=obj.getInt("time_id");
                            price4=obj.getString("price") ;
                                bookedBy=obj.getString("booked_by");
                            if (!username.equals("")) {
                                tv11_1130.setText("Booked by "+username);
                                if (bookedBy.equals("admin"))
                                {
                                    tv11_1130.setBackgroundResource(R.color.colorOrange);
                                }
                                else {
                                    tv11_1130.setBackgroundResource(R.color.colorAccent);
                                }
                            }
                        }
                        else
                  if (timeTo.equals("12:00")) {
                            timeId5=obj.getInt("time_id");
                            price5=obj.getString("price") ;
                      bookedBy=obj.getString("booked_by");
                            if (!username.equals("")) {
                                tv1130_12.setText("Booked by "+username);
                                if (bookedBy.equals("admin"))
                                {
                                    tv1130_12.setBackgroundResource(R.color.colorOrange);
                                }
                                else {
                                    tv1130_12.setBackgroundResource(R.color.colorAccent);
                                }
                            }
                        }
else
                        if (timeTo.equals("12:30")) {
                            timeId6=obj.getInt("time_id");
                            price6=obj.getString("price") ;


                            if (!username.equals("")) {
                                tv12_1230.setText("Booked by "+username);
                                if (bookedBy.equals("admin"))
                                {
                                    tv12_1230.setBackgroundResource(R.color.colorOrange);
                                }
                                else {
                                    tv12_1230.setBackgroundResource(R.color.colorAccent);
                                }
                            }
                        }
                    }
                    JSONArray jsonArrayNoon=jsonObject.getJSONArray("Noon");
                        for(int i=0; i<=jsonArrayNoon.length();i++) {
                            JSONObject objectNoon = jsonArrayNoon.getJSONObject(i);
                            timeFrom = objectNoon.getString("time_from");
                            timeTo = objectNoon.getString("time_to");
                            username = objectNoon.getString("user");
                            bookedBy=objectNoon.getString("booked_by");
                            if (timeTo.equals("13:30")) {
                                timeTo7=timeTo;
                                timeId7=objectNoon.getInt("time_id");
                                price7=objectNoon.getString("price") ;
                                if (!username.equals("")) {
                                    tv13_1330.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv13_1330.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv13_1330.setBackgroundResource(R.color.colorAccent);
                                    }
                                }

                            }

                            if (timeTo.equals("14:00")) {
                                timeId8=objectNoon.getInt("time_id");
                                price8=objectNoon.getString("price") ;
                                bookedBy=objectNoon.getString("booked_by");
                                if (!username.equals("")) {
                                    tv1330_14.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv1330_14.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv1330_14.setBackgroundResource(R.color.colorAccent);
                                    }
                                }

                            }

                            if (timeTo.equals("14:30")) {
                                timeId9=objectNoon.getInt("time_id");
                                price9=objectNoon.getString("price") ;
                                bookedBy=objectNoon.getString("booked_by");
                                if (!username.equals("")) {
                                    tv14_1430.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv14_1430.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv14_1430.setBackgroundResource(R.color.colorAccent);
                                    }
                                }
                            }

                            if (timeTo.equals("15:00")) {
                                timeId10=objectNoon.getInt("time_id");
                                price10=objectNoon.getString("price") ;
                                bookedBy=objectNoon.getString("booked_by");
                                if (!username.equals("")) {
                                    tv1430_15.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv1430_15.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv1430_15.setBackgroundResource(R.color.colorAccent);
                                    }
                                }
                            }
                            if (timeTo.equals("15:30")) {
                                timeId11=objectNoon.getInt("time_id");
                                price11=objectNoon.getString("price") ;
                                bookedBy=objectNoon.getString("booked_by");
                                if (!username.equals("")) {
                                    tv15_1530.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv15_1530.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv15_1530.setBackgroundResource(R.color.colorAccent);
                                    }
                                }
                            }

                            if (timeTo.equals("16:00")) {
                                timeId12=objectNoon.getInt("time_id");
                                price12=objectNoon.getString("price") ;
                                bookedBy=objectNoon.getString("booked_by");
                                if (!username.equals("")) {
                                    tv1530_16.setText("Booked by "+username);
                                    if (bookedBy.equals("admin"))
                                    {
                                        tv1530_16.setBackgroundResource(R.color.colorOrange);
                                    }
                                    else {
                                        tv1530_16.setBackgroundResource(R.color.colorAccent);
                                    }
                                }
                            }
                        }

                      JSONArray  jsonArrayEvening=jsonObject.getJSONArray("Evening");
                       length= jsonArrayEvening.length();
                            for(int i=0; i<=jsonArrayEvening.length();i++)
                            {
                                JSONObject objectEvening=jsonArrayEvening.getJSONObject(i);
                                timeFrom = objectEvening.getString("time_from");
                                timeTo = objectEvening.getString("time_to");
                                username = objectEvening.getString("user");
                                bookedBy=objectEvening.getString("booked_by");
                                if (timeTo.equals("16:30"))
                                {
                                    timeId111=objectEvening.getInt("time_id");
                                    price111=objectEvening.getString("price") ;
                                    if (!username.equals(""))
                                    {
                                        tv16_1630.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv16_1630.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv16_1630.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("17:00"))
                                {
                                    timeId222=objectEvening.getInt("time_id");
                                    price222=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv1630_17.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv1630_17.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv1630_17.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }
                                if (timeTo.equals("17:30"))
                                {
                                    timeId333=objectEvening.getInt("time_id");
                                    price333=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv17_1730.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv17_1730.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv17_1730.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("18:00"))
                                {
                                    timeId444=objectEvening.getInt("time_id");
                                    price444=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv1730_18.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv1730_18.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv1730_18.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("18:30"))
                                {
                                    timeId555=objectEvening.getInt("time_id");
                                    price555=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv18_1830.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv18_1830.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv18_1830.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("19:00"))
                                {
                                    timeId666=objectEvening.getInt("time_id");
                                    price666=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv1830_19.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv1830_19.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv1830_19.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("20:30"))
                                {
                                    timeId13=objectEvening.getInt("time_id");
                                    price13=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv20_2030.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv20_2030.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv20_2030.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }
                                }


                                if (timeTo.equals("21:00"))
                                {
                                    timeId14=objectEvening.getInt("time_id");
                                    price14=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv2030_21.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv2030_21.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv2030_21.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }
                                }

                                if (timeTo.equals("21:30"))
                                {
                                    timeId15=objectEvening.getInt("time_id");
                                    price15=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv21_2130.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv21_2130.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv21_2130.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }


                                if (timeTo.equals("22:00"))
                                {
                                    timeId16=objectEvening.getInt("time_id");
                                    price16=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv2130_22.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv2130_22.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv2130_22.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }
                                if (timeTo.equals("22:30"))
                                {
                                    timeId17=objectEvening.getInt("time_id");
                                    price17=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv22_2230.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv22_2230.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv22_2230.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }
                                }

                                if (timeTo.equals("23:00"))
                                {
                                    timeId18=objectEvening.getInt("time_id");
                                    price18=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv2230_23.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv2230_23.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv2230_23.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("24:00"))
                                {
                                    timeId19=objectEvening.getInt("time_id");
                                    price19=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv2330_24.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv2330_24.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv2330_24.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }
                                if (timeTo.equals("00:30"))
                                {
                                    timeId20=objectEvening.getInt("time_id");
                                    bookedBy=objectEvening.getString("booked_by");
                                    price20=objectEvening.getString("price") ;
                                    if (!username.equals(""))
                                    {
                                        tv24_130.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv24_130.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv24_130.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("01:00"))
                                {
                                    timeId21=objectEvening.getInt("time_id");
                                    price21=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv130_2.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv130_2.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv130_2.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("01:30"))
                                {
                                    timeId22=objectEvening.getInt("time_id");
                                    bookedBy=objectEvening.getString("booked_by");
                                    price22=objectEvening.getString("price") ;
                                    if (!username.equals(""))
                                    {
                                        tv2_230.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv2_230.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv2_230.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("02:00"))
                                {
                                    timeId23=objectEvening.getInt("time_id");
                                    price23=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv230_3.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv230_3.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv230_3.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }

                                }

                                if (timeTo.equals("02:30"))
                                {
                                    timeId24=objectEvening.getInt("time_id");
                                    price24=objectEvening.getString("price") ;
                                    bookedBy=objectEvening.getString("booked_by");
                                    if (!username.equals(""))
                                    {
                                        tv3_330.setText("Booked by "+username);
                                        if (bookedBy.equals("admin"))
                                        {
                                            tv3_330.setBackgroundResource(R.color.colorOrange);
                                        }
                                        else {
                                            tv3_330.setBackgroundResource(R.color.colorAccent);
                                        }
                                    }
                                }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (alertDialog!=null)
                    alertDialog.dismiss();
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
                params.put("ground_id",strGroundId);
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
        final EditText etName,etPhoneNo;
        switch (v.getId())
        {
            case R.id.btnAddBooking:
                if (strTime==null)
                {
                    Toast.makeText(getActivity(), "No booking selected yet", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.custom_addbooking, null);
                    dialogBuilder.setView(dialogView);

                    etName =  dialogView.findViewById(R.id.etName);
                    etPhoneNo =  dialogView.findViewById(R.id.etPhoneNo);
                    final TextView btnBook =dialogView.findViewById(R.id.btnBook);
                    final TextView btnCancel =dialogView.findViewById(R.id.btnCancel);
                    TextView tvDate =dialogView.findViewById(R.id.tvDate);
                    TextView tvTime =dialogView.findViewById(R.id.tvTime);
                    TextView tvPrice=dialogView.findViewById(R.id.tvPrice);
                    TextView tvDuration =dialogView.findViewById(R.id.tvDuration);

                    tvDate.setText(date);
                    tvTime.setText(strTime);
                    tvPrice.setText(String.valueOf(totalPrice));
                    tvDuration.setText(String.valueOf(strDuration));
                    final AlertDialog alert = dialogBuilder.create();
                    btnBook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            strName=etName.getText().toString();
                            strPhoneNo=etPhoneNo.getText().toString();
                            if (strName.equals(""))
                            {
                                etName.setError("please fill this field");
                            }
                            else
                            if (strPhoneNo.equals(""))
                            {
                                etPhoneNo.setError("please fill this field");
                            }
                            else
                            {
                                for(int i=0; i<timeIds.size(); i++)
                                {
                                    timeIds.get(i).toString().replace("[","").replace("]","").replace(",", " ");
                                    priceArray.get(i).replace("[","").replace("]","").replace(",", " ");
                                    if (alertDialog==null)
                                    {
                                        alertDialog= AlertsUtils.createProgressDialog(getActivity());
                                        alertDialog.show();
                                    }
                                    apicallForBooking(timeIds.get(i),priceArray.get(i));
                                }
                                alert.dismiss();
                            }

                        }
                    });

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.dismiss();
                        }
                    });

                    date="";
                    strTime="";
                    strDuration=0;
                    totalPrice=0;


                    alert.show();
                }

                break;
            case R.id.tv930_10:
                check=tv930_10.getText().toString();
                check1++;

                if (check1>1)
                {
                    Toast.makeText(getActivity(), "after"+strTime, Toast.LENGTH_SHORT).show();
                    tv930_10.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check1=0;
                }
                else
                {
                    if (check.equals(""))
                    {

                        tv930_10.setBackgroundResource(R.color.colorSelect);
                        strTimeFrom="09:30";
                        strTimeTo="10:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        Toast.makeText(getActivity(), strTime, Toast.LENGTH_SHORT).show();
                        strDate=date;
                        strDuration =strDuration+30;
//                        totalPrice=Integer.valueOf(price1);
//                        timeIds.add(timeId1);
//                        priceArray.add(price1);

                        try {
                            totalPrice = Integer.parseInt(price1);
                            timeIds.add(timeId1);
                            priceArray.add(price1);
                        } catch(NumberFormatException nfe) {
                        }
                    }
                }


                break;
            case R.id.tv10_1030:
                check=tv10_1030.getText().toString();
                check2++;
                if(check2>1)
                {
                    tv10_1030.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check2=0;
                }
                else
                {
                    if (check.equals(""))
                    {
                        tv10_1030.setBackgroundResource(R.color.colorSelect);
                        if (strTimeFrom!=null)
                        {
                            strTimeTo="10:30";
                            strTime=strTimeFrom+"-"+strTimeTo;
                            strDate=date;
                            strDuration =strDuration+30;
                        }
                        else
                        {
                            strTimeFrom="10:00";
                            strTimeTo="10:30";
                            strTime=strTimeFrom+"-"+strTimeTo;
                            strDate=date;
                            strDuration =strDuration+30;

                        }
                        try {
                            totalPrice = Integer.parseInt(price2);
                            timeIds.add(timeId2);
                            priceArray.add(price2);
                        } catch(NumberFormatException nfe) {
                        }
                }


                }
                break;
            case R.id.tv1030_11:
                check=tv1030_11.getText().toString();
                check3++;
                if(check3>1)
                {
                    tv1030_11.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check3=0;
                }
                else
                {
                if (check.equals("")) {
                    tv1030_11.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "11:00";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "10:30";
                        strTimeTo = "11:00";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price3);
                        timeIds.add(timeId3);
                        priceArray.add(price3);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv11_1130:
                check=tv11_1130.getText().toString();
                check4++;
                if(check4>1)
                {
                    tv11_1130.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check4=0;
                }
                else
                {
                if (check.equals("")) {
                    tv11_1130.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "11:00";
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price4);
                        timeIds.add(timeId4);
                        priceArray.add(price4);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv1130_12:
                check=tv1130_12.getText().toString();
                check5++;
                if(check5>1)
                {
                    tv1130_12.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check5=0;
                }
                else
                {
                if (check.equals("")) {
                    tv1130_12.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "11:00";
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price5);
                        timeIds.add(timeId5);
                        priceArray.add(price5);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv12_1230:
                check=tv12_1230.getText().toString();
                check6++;
                if(check6>1)
                {
                    tv12_1230.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check6=0;
                }
                else
                {
                if (check.equals("")) {
                    tv12_1230.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "11:00";
                        strTimeTo = "11:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price6);
                        timeIds.add(timeId6);
                        priceArray.add(price6);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv13_1330:
                check=tv13_1330.getText().toString();
                check7++;
                if(check7>1)
                {
                    tv13_1330.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check7=0;
                }
                else
                {
                if (check.equals("")) {
                    tv13_1330.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "13:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "13:00";
                        strTimeTo = "13:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price7);
                        timeIds.add(timeId7);
                        priceArray.add(price7);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv1330_14:
                check=tv1330_14.getText().toString();
                check8++;
                if(check8>1)
                {
                    tv1330_14.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check8=0;
                }
                else
                {
                if (check.equals("")) {
                    tv1330_14.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "14:00";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "13:30";
                        strTimeTo = "14:00";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price8);
                        timeIds.add(timeId8);
                        priceArray.add(price8);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
            case R.id.tv14_1430:
                check=tv14_1430.getText().toString();
                check9++;
                if(check9>1)
                {
                    tv14_1430.setBackgroundResource(R.drawable.boundry);
                    strTimeFrom="";
                    strTimeTo="";
                    strTime=null;
                    strDuration=0;
                    totalPrice=0;
                    check9=0;
                }
                else
                {
                if (check.equals("")) {
                    tv14_1430.setBackgroundResource(R.color.colorSelect);
                    if (strTimeFrom != null) {
                        strTimeTo = "14:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    } else {
                        strTimeFrom = "14:00";
                        strTimeTo = "14:30";
                        strTime = strTimeFrom + "-" + strTimeTo;
                        strDate = date;
                        strDuration = strDuration + 30;
                    }
                    try {
                        totalPrice = totalPrice + Integer.parseInt(price9);
                        timeIds.add(timeId9);
                        priceArray.add(price9);
                    } catch (NumberFormatException nfe) {
                    }
                }
                }
                break;
                case R.id.tv1430_15:
            check=tv1430_15.getText().toString();
                    check10++;
                    if(check10>1)
                    {
                        tv1430_15.setBackgroundResource(R.drawable.boundry);
                        strTimeFrom="";
                        strTimeTo="";
                        strTime=null;
                        strDuration=0;
                        totalPrice=0;
                        check10=0;
                    }
                    else
                    {
            if (check.equals("")) {
                tv1430_15.setBackgroundResource(R.color.colorSelect);
                if (strTimeFrom != null) {
                    strTimeTo = "15:00";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                } else {
                    strTimeFrom = "14:30";
                    strTimeTo = "15:00";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                }
                try {
                    totalPrice = totalPrice + Integer.parseInt(price10);
                    timeIds.add(timeId10);
                    priceArray.add(price10);
                } catch (NumberFormatException nfe) {
                }
            }
            }
            break;  case R.id.tv15_1530:
            check=tv15_1530.getText().toString();
            check11++;
            if(check11>1)
            {
                tv15_1530.setBackgroundResource(R.drawable.boundry);
                strTimeFrom="";
                strTimeTo="";
                strTime=null;
                strDuration=0;
                totalPrice=0;
                check11=0;
            }
            else
            {
            if (check.equals("")) {
                tv15_1530.setBackgroundResource(R.color.colorSelect);
                if (strTimeFrom != null) {
                    strTimeTo = "15:30";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                } else {
                    strTimeFrom = "15:00";
                    strTimeTo = "15:30";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                }
                try {
                    totalPrice = Integer.parseInt(price11);
                    timeIds.add(timeId11);
                    priceArray.add(price11);
                } catch (NumberFormatException nfe) {
                }

            }
            }
            break;  case R.id.tv1530_16:
            check=tv1530_16.getText().toString();
            check12++;
            if(check12>1)
            {
                tv1530_16.setBackgroundResource(R.drawable.boundry);
                strTimeFrom="";
                strTimeTo="";
                strTime=null;
                strDuration=0;
                totalPrice=0;
                check12=0;
            }
            else
            {
            if (check.equals("")) {
                tv1530_16.setBackgroundResource(R.color.colorSelect);
                if (strTimeFrom != null) {
                    strTimeTo = "16:00";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                } else {
                    strTimeFrom = "15:30";
                    strTimeTo = "16:00";
                    strTime = strTimeFrom + "-" + strTimeTo;
                    strDate = date;
                    strDuration = strDuration + 30;
                }
                try {
                    totalPrice = totalPrice + Integer.parseInt(price12);
                    timeIds.add(timeId12);
                    priceArray.add(price12);
                } catch (NumberFormatException nfe) {
                }
            }
            }
            break;
            case R.id.tv16_1630:
                check=tv16_1630.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="16:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="16:00";
                        strTimeTo="16:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv16_1630.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price111);
                        timeIds.add(timeId111);
                        priceArray.add(price111);
                    }

                }
                break;
            case R.id.tv1630_17:
                check=tv1630_17.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="17:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="16:30";
                        strTimeTo="17:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv1630_17.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price222);
                        timeIds.add(timeId222);
                        priceArray.add(price222);
                    }

                }
                break;
            case R.id.tv17_1730:
                check=tv17_1730.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="17:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="17:00";
                        strTimeTo="17:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv17_1730.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price333);
                        timeIds.add(timeId333);
                        priceArray.add(price333);
                    }

                }
                break;
            case R.id.tv1730_18:
                check=tv1630_17.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="18:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="17:30";
                        strTimeTo="18:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv1630_17.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price444);
                        timeIds.add(timeId444);
                        priceArray.add(price444);
                    }

                }
                break;
            case R.id.tv18_1830:
                check=tv18_1830.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="17:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="18:00";
                        strTimeTo="18:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv18_1830.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price555);
                        timeIds.add(timeId555);
                        priceArray.add(price555);
                    }

                }
                break;
            case R.id.tv1830_19:
                check=tv1830_19.getText().toString();
                if (check.equals(""))
                {

                    if (strTimeFrom!=null)
                    {
                        strTimeTo="17:30";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    else
                    {
                        strTimeFrom="18:30";
                        strTimeTo="19:00";
                        strTime=strTimeFrom+"-"+strTimeTo;
                        strDate=date;
                        strDuration =strDuration+30;
                    }
                    if (temp==123)
                    {
                        Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        tv1830_19.setBackgroundResource(R.color.colorSelect);
                        temp=temp+Integer.valueOf(price666);
                        timeIds.add(timeId666);
                        priceArray.add(price666);
                    }

                }
                break;
            case R.id.tv20_2030:
            check=tv20_2030.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="20:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="20:00";
                    strTimeTo="20:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv20_2030.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price13);
                    timeIds.add(timeId13);
                    priceArray.add(price13);
                }

            }
            break;
            case R.id.tv2030_21:
            check=tv2030_21.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="21:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="20:30";
                    strTimeTo="21:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv2030_21.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price14);
                    timeIds.add(timeId14);
                    priceArray.add(price14);
                }

            }
            break;
            case R.id.tv21_2130:
            check=tv21_2130.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="21:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="21:00";
                    strTimeTo="21:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv21_2130.setBackgroundResource(R.color.colorSelect);
                    timeIds.add(timeId15);
                    priceArray.add(price15);
                    temp=temp+Integer.valueOf(price15);
                }

            }
            break;  case R.id.tv2130_22:
            check=tv2130_22.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="22:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="21:30";
                    strTimeTo="22:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv2130_22.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price16);
                    timeIds.add(timeId16);
                    priceArray.add(price16);
                }

            }
            break;  case R.id.tv22_2230:
            check=tv22_2230.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="22:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="22:00";
                    strTimeTo="22:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv22_2230.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price17);
                    timeIds.add(timeId17);
                    priceArray.add(price17);
                }

            }
            break;  case R.id.tv2230_23:
            check=tv2230_23.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="23:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="22:30";
                    strTimeTo="23:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv2230_23.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price18);
                    timeIds.add(timeId18);
                    priceArray.add(price18);
                }

            }
            break;  case R.id.tv2330_24:
            check=tv2330_24.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="24:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="23:30";
                    strTimeTo="24:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv2330_24.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price19);
                    timeIds.add(timeId19);
                    priceArray.add(price19);
                }

            }
            break;  case R.id.tv24_130:
            check=tv24_130.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="00:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="24:00";
                    strTimeTo="00:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv24_130.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price20);
                    timeIds.add(timeId20);
                    priceArray.add(price20);
                }

            }
            break;  case R.id.tv130_2:
            check=tv130_2.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="02:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="01:30";
                    strTimeTo="02:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv130_2.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price21);
                    timeIds.add(timeId21);
                    priceArray.add(price21);
                }

            }
            break;  case R.id.tv2_230:
            check=tv2_230.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="02:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="02:00";
                    strTimeTo="02:30";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv2_230.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price22);
                    timeIds.add(timeId22);
                    priceArray.add(price22);
                }

            }
            break;  case R.id.tv230_3:
            check=tv230_3.getText().toString();
            if (check.equals(""))
            {

                if (strTimeFrom!=null)
                {
                    strTimeTo="03:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                else
                {
                    strTimeFrom="02:30";
                    strTimeTo="03:00";
                    strTime=strTimeFrom+"-"+strTimeTo;
                    strDate=date;
                    strDuration =strDuration+30;
                }
                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv230_3.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price23);
                    timeIds.add(timeId23);
                    priceArray.add(price23);
                }

            }
            break;  case R.id.tv3_330:
            check=tv3_330.getText().toString();
            if (check.equals(""))
            {

                strTimeFrom="03:00";
                strTimeTo="03:30";
                strTime=strTimeFrom+"-"+strTimeTo;
                strDate=date;
                strDuration =strDuration+30;

                if (temp==123)
                {
                    Toast.makeText(getActivity(), "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tv3_330.setBackgroundResource(R.color.colorSelect);
                    temp=temp+Integer.valueOf(price24);
                    timeIds.add(timeId24);
                    priceArray.add(price24);
                }

            }
            break;
            case R.id.ivRefresh:
               Fragment fragment=new HomeFragment();
               getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).commit();
                break;

        }
    }

    private void apicallForBooking(final Integer integer, final String price) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://openspot.qa/restapi/bookground", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zmaBooking",response);
                if (alertDialog!=null)
                    alertDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String message=jsonObject.getString("message");
//                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    Fragment fragment=new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).commit();

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
                params.put("ground_id",strGroundId);
                params.put("name",strName);
                params.put("phone_no",strPhoneNo);
                params.put("time_id",String.valueOf(integer));
                params.put("price",price);
                params.put("date",strDate);
                Log.d("myParams",params.toString());
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
