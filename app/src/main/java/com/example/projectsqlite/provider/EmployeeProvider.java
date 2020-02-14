package com.example.projectsqlite.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectsqlite.database.DatabaseNhanVien;

public class EmployeeProvider extends ContentProvider {

    private String AUTHOR = "com.example.projectsqlite.provider";
    private String TB = "Ten";
    private Cursor cursor;
    private DatabaseNhanVien databaseNhanVien;
    UriMatcher uriMatcher;
    private String TAG = "TAG";
    private String URI_TB = "content://" + AUTHOR + "/" + TB;
    @Override
    public boolean onCreate() {
         uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHOR, TB, 1);
        uriMatcher.addURI(AUTHOR, TB +"/*",2);
        databaseNhanVien = new DatabaseNhanVien(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int matcher = uriMatcher.match(uri);
        Log.e(TAG, "query: " + matcher);
        switch (matcher){
            case 1 : {
                cursor = databaseNhanVien.getEmployee();
            }break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        databaseNhanVien.delete(uri, selection, selectionArgs);
        Log.e(TAG, "delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
