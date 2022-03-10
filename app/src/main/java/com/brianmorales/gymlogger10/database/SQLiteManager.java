package com.brianmorales.gymlogger10.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.brianmorales.gymlogger10.models.GymLog;

import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "GymLogDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Exercise";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "ID";
    private static final String TITLE_FIELD = "WorkOutTitle";
    private static final String DESCRIPTION_FIELD = "Description";


    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sqlStatement = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DESCRIPTION_FIELD)
                .append(" TEXT)");
        sqLiteDatabase.execSQL(sqlStatement.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void addLoggerToDB(GymLog logger){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_FIELD, logger.getID());
        values.put(TITLE_FIELD, logger.getWorkoutTitle());
        values.put(DESCRIPTION_FIELD, logger.getSetsToString());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public void populateLoggerList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {

            if(result!=null){//result.getCount() != 0){
                while(result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String desc = result.getString(3);
                    GymLog logger = new GymLog(title, desc, id);
                    GymLogDatabase.addGymLog(logger);
                }
            }
        }
    }



    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        sqLiteDatabase.execSQL("delete from " + TABLE_NAME);

    }
}
