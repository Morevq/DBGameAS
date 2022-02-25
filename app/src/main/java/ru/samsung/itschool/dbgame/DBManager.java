package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addResult(String username, int score) {
		//db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score + ");");
		ContentValues contentValues = new ContentValues();
		contentValues.put("USERNAME", username);
		contentValues.put("SCORE", score);
		db.insert("RESULTS", null, contentValues);
	}



	ArrayList<Result> getAllResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = cursor.getInt(1);//Integer.parseInt(cursor.getInt(cursor.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	ArrayList<Result> getAllUserResults(String username, int minScore) {

		ArrayList<Result> data = new ArrayList<Result>();

		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS WHERE USERNAME=? AND SCORE >= ?", new String[]{username, minScore + ""});
		//Cursor cursor1 = db.rawQuery("SELECT USERNAME || 'abc', SCORE*SCORE FROM RESULTS WHERE USERNAME=? AND SCORE >= ?", new String[]{username, minScore + ""});
		//создает два столбца юзернеймabc и скоре умноженное на скоре

		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}
	ArrayList<Result> getMaxUserResults(String username, int minScore) {
		Cursor cursor = db.query("RESULTS",
				new String[]{"USERNAME", "MAX(SCORE) AS MS"},
				null, null, "USERNAME",
				"MS > 500" , "MS DESC");
		ArrayList<Result> data = new ArrayList<Result>();
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

	private boolean dbExist() {
		File dbFile = context.getDatabasePath(DB_NAME);
		return dbFile.exists();
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
	public void clearTable(){
		db.execSQL("DELETE FROM RESULTS;");
	}
}