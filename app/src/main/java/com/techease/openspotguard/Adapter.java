package com.techease.openspotguard;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adamnoor on 5/28/2018.
 */

public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<Model> models;
    public static  ArrayList<Integer> timeIdArray = new ArrayList<Integer>();
    MyViewHolder viewHolder=null;
    String userName;
    private LayoutInflater layoutInflater;
    public Adapter(Context context, ArrayList<Model> models) {
        this.context=context;
        this.models=models;
        if (context!=null)
        {
            this.layoutInflater=LayoutInflater.from(context);
        }

    }

    @Override
    public int getCount() {
        if (models!=null) return models.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(models != null && models.size() > i) return  models.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        final Model model=models.get(i);
        if(models != null && models.size() > i) return  models.size();
        Toast.makeText(context, String.valueOf(models.size()), Toast.LENGTH_SHORT).show();
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final Model model=models.get(i);
        viewHolder=new MyViewHolder() ;
        view=layoutInflater.inflate(R.layout.custom,parent,false);
        viewHolder.tvBOx= view.findViewById(R.id.tvBox);
        viewHolder.tvTime= view.findViewById(R.id.tvTame);
        viewHolder.tvTime.setText(model.getTimeTo());
        userName=model.getUserName();
        if (!userName.equals(""))
        {
            viewHolder.tvBOx.setText("Booked by\n"+model.getUserName());
            viewHolder.tvBOx.setBackgroundResource(R.color.colorAccent);
        }
        else
        {

        }

        timeIdArray.clear();
        viewHolder.tvBOx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeIdArray.add(Integer.valueOf(model.getTimeId()));
            }
        });


        view.setTag(viewHolder);
        return view;
    }


    private class MyViewHolder  {
        TextView tvBOx,tvTime;
    }
}
