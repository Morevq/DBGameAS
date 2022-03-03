package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatActivity extends Activity {

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        TextView max = findViewById(R.id.textView4);
        TextView min = findViewById(R.id.textView5);
        TextView allGames = findViewById(R.id.textView6);
        dbManager = DBManager.getInstance(this);
        ArrayList<Result> results = dbManager.getAllResults();
        int s = results.size();
        allGames.setText(s + "");
        max.setText("dffd");
        min.setText("gfgtbf");
        /*String[] resultsString = new String[results.size()];
        ListView listView = this.findViewById(R.id.listWiew);
        listView.setAdapter(new ResultAdapter(this, results));*/

    }
}
