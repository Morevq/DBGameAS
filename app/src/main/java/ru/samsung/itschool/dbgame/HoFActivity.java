package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class HoFActivity extends Activity {

	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ho_f);
		dbManager = DBManager.getInstance(this);
		ArrayList<Result> results = dbManager.getAllResults(); //ааа, вот тут
		String[] resultsString = new String[results.size()];
		ListView listView = this.findViewById(R.id.listWiew5);
		listView.setAdapter(new ResultAdapter(this, results));
	}
}
