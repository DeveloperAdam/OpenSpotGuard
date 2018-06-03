package com.techease.openspotguard;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("abc", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token=sharedPreferences.getString("token","");
        if (token.equals("login"))
        {
            Fragment fragment=new HomeFragment();
            getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).commit();
        }
        else
        {
            Fragment fragment=new Login();
            getFragmentManager().beginTransaction().replace(R.id.mainContainer,fragment).commit();
        }


    }


}
