package com.example.projectsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectsqlite.database.Employee;

import java.util.List;

public class AdapterListView extends ArrayAdapter<Employee> {
    private Context context;
    private List<Employee> list;
    private LayoutInflater layoutInflater;
    public AdapterListView(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = objects;
    }


    @Override
    public int getCount() {
        return list.size();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_employee, parent, false);
            viewHolder.txtAge = convertView.findViewById(R.id.txtAge);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Employee employee = list.get(position);
        viewHolder.txtName.setText(employee.getName());
        viewHolder.txtAge.setText(String.valueOf(employee.getAge()));
        return convertView;
    }

    private class ViewHolder{
        private TextView txtName;
        private TextView txtAge;
    }
}
