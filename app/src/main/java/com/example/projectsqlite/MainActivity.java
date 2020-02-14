package com.example.projectsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.projectsqlite.database.DatabaseNhanVien;
import com.example.projectsqlite.database.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterListView adapterListView;
    private Button btnShow;
    private DatabaseNhanVien databaseNhanVien;
    private List<Employee> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btShow);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        databaseNhanVien = new DatabaseNhanVien(this);
        Cursor cursor = databaseNhanVien.getEmployee();
        cursor.moveToFirst();
        list.add(new Employee(cursor.getString(1), cursor.getInt(2)));
        while (cursor.moveToNext()){
            list.add(new Employee(cursor.getString(1), cursor.getInt(2)));
        }
        cursor.close();
        adapterListView = new AdapterListView(this, R.layout.item_employee, list);
        listView.setAdapter(adapterListView);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });
    }
}
