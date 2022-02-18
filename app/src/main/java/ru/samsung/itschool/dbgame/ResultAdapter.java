package ru.samsung.itschool.dbgame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {
    ArrayList<Result> results;
    Context context;

    public ResultAdapter(Context context, ArrayList<Result> results) {
        this.results = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layyaot, null);
        TextView nameTV = view.findViewById(R.id.text1);
        TextView scoreTV = view.findViewById(R.id.text2);
        nameTV.setText(results.get(position).name);
        scoreTV.setText(results.get(position).score + "");
        int a = results.get(position).score;
        if(a >= 500) scoreTV.setTextColor(Color.GREEN);
        else scoreTV.setTextColor(Color.RED);
        if(a%10 == a/100){
            scoreTV.setTextColor(Color.BLUE);
        }
        return view;
    }
}
