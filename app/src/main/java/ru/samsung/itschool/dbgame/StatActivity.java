package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatActivity extends Activity {

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        TextView min = findViewById(R.id.textView4);
        TextView max = findViewById(R.id.textView5);
        TextView allGames = findViewById(R.id.textView6);
        TextView averege = findViewById(R.id.textView11);
        dbManager = DBManager.getInstance(this);

        ArrayList<Result> results = dbManager.getAllResults();

        ArrayList<Result> results1 = dbManager.getMaxUserResults();

        ArrayList<Result> results2 = dbManager.getMinUserResults();

        int s = results.size();
        allGames.setText(s + "");

        String[] resultsString = new String[results1.size()];
        ListView listView = this.findViewById(R.id.listView1);
        listView.setAdapter(new ResultAdapter(this, results1));


        String[] resultsString1 = new String[results2.size()];
        ListView listView1 = this.findViewById(R.id.listView2);
        listView1.setAdapter(new ResultAdapter(this, results2));


        int mx = -1, mn = 1001, sum = 0;
        String x = "max", n = "min";
        for(Result d: results){
            sum += d.getScore();
            if(d.getScore() > mx){
                mx = d.getScore();
                x = d.getName();
            }
            if(d.getScore() < mn){
                mn = d.getScore();
                n = d.getName();
            }
        }
        int avg = (sum)/results.size();
        averege.setText(avg + "");

        String ss = Integer.toString(mx) + " " + x;
        String sss = Integer.toString(mn) + " " + n;
        max.setText(ss);
        min.setText(sss);
        /*String[] resultsString = new String[results.size()];
        ListView listView = this.findViewById(R.id.listWiew);
        listView.setAdapter(new ResultAdapter(this, results));*/

    }
}
