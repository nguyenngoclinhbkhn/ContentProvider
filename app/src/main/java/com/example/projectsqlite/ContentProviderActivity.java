package com.example.projectsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projectsqlite.database.Employee;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterListView adapterListView;
    private List<Employee> list;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        listView = findViewById(R.id.listViewProvider);
        list = new ArrayList<>();
        final ContentResolver contentResolver = getContentResolver();
        final Uri uri = Uri.parse("content://com.example.projectsqlite.provider/Ten");
        CursorLoader cursorLoader = new CursorLoader(this, uri, null, null, null, null);
        cursor = cursorLoader.loadInBackground();
//        cursor = contentResolver.query(uri, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Employee employee = new Employee(cursor.getString(1), cursor.getInt(2));
            employee.setId(cursor.getInt(0));
            list.add(employee);
        }
        cursor.close();
        adapterListView = new AdapterListView(this, R.layout.item_employee, list);
        listView.setAdapter(adapterListView);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String where = "_id = " + list.get(position).getId();
                contentResolver.delete(uri, where, null);
                for (Employee e : getList()){
                    Log.e("tAG", "name " + e.getName() + " : " + e.getId());

                }
                adapterListView.notifyDataSetChanged();
            }
        });




    }

    private List<Employee> getList(){
        List<Employee> list = new ArrayList<>();
        final Uri uri = Uri.parse("content://com.example.projectsqlite.provider/Ten");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Employee employee = new Employee(cursor.getString(1), cursor.getInt(2));
            employee.setId(cursor.getInt(0));
            list.add(employee);
        }
        cursor.close();
        return list;
    }
}
