package com.techease.openspotguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends Fragment implements View.OnClickListener {


    EditText etEmail,etPassword;
    Button btnLogin;
    String strEmail,strPass,uId,userName,Email,bookedGroundId;
    android.support.v7.app.AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sharedPreferences = getActivity().getSharedPreferences("abc", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etEmail=(EditText)view.findViewById(R.id.etEmail);
        etPassword=(EditText)view.findViewById(R.id.etPassword);
        btnLogin=(Button) view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);


        return view;
    }
    private void check() {
        strEmail=etEmail.getText().toString();
        strPass=etPassword.getText().toString();
        if (strEmail.equals("") && strEmail.contains("@") && strEmail.contains(".com"))
        {
            etEmail.setError("Please enter accurate email");
        }else
        if (strPass.equals(""))
        {
            etPassword.setError("Please fill this field");
        }
        else
        {
            if (alertDialog==null)
            {
                alertDialog= AlertsUtils.createProgressDialog(getActivity());
                alertDialog.show();
            }
            apicall();
        }
    }

    private void apicall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://openspot.qa/restapi/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zmaReg",response);
                if (alertDialog!=null)
                    alertDialog.dismiss();
                try {

                    JSONObject object=new JSONObject(response);
                    String message=object.getString("message");
                    String responseMessage=object.getString("success");
                    if (responseMessage.equals("true"))
                    {
                        AlertsUtils.Toast(getActivity(),message);
                        JSONObject jsonObject=object.getJSONObject("user");
                        uId=jsonObject.getString("id");
                        userName=jsonObject.getString("name");
                        Email=jsonObject.getString("email");
                        bookedGroundId=jsonObject.getString("groundid");
                        editor.putString("id",uId).commit();
                        editor.putString("email",Email).commit();
                        editor.putString("groundid",bookedGroundId).commit();
                        editor.putString("name",userName).commit();
                        editor.putString("token","login").commit();
                        Fragment fragment=new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).commit();

                    }
                    else
                    {
                        AlertsUtils.Toast(getActivity(),message);
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
                params.put("email",strEmail);
                params.put("password",strPass);
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
        switch (v.getId())
        {
            case R.id.btnLogin:
                check();
                break;

        }
    }
}
