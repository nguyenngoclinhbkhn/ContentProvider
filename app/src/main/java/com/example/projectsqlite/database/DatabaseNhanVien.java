package com.example.projectsqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

public class DatabaseNhanVien {
    private SQLiteDatabase sqLiteDatabase;
    private String DB_NAME = "NhanVien";
    private String TB_NAME = "Ten";
    private int DB_VERSION = 1;
    private String COLOUMN_NAME = "name";
    private String COLUMN_AGE = "age";


    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createDatabase = "CREATE TABLE IF NOT EXISTS " + TB_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)";
            db.execSQL(createDatabase);
            insert(db ,new Employee("Strom", 22));
            insert(db, new Employee("NVM", 22));
            insert(db,new Employee("NVM2", 22));
            insert(db,new Employee("NVM3", 22));
            insert(db,new Employee("NVM4", 22));
            insert(db,new Employee("NVM5", 22));


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropTable = "DROP TABLE IF EXISTS " + TB_NAME;
            db.execSQL(dropTable);
            onCreate(db);
        }
    }

    private void insert(SQLiteDatabase database, Employee employee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLOUMN_NAME, employee.getName());
        contentValues.put(COLUMN_AGE, employee.getAge());
        database.insertOrThrow(TB_NAME, null, contentValues);
    }
    public void delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs){
        sqLiteDatabase.delete(TB_NAME, selection, null);
    }

    public Cursor getEmployee() {
        return sqLiteDatabase.query(TB_NAME, null, null,
                null, null, null, null);
    }

    public DatabaseNhanVien(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
}
