package com.example.francis.hyscoreapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.francis.hyscoreapp.DataBaseHelper.DataBaseHelper;
import com.example.francis.hyscoreapp.R;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Francis on 09-Jun-18.
 */

public class Select extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"Computer Engineering", "Mechanical Engineering", "Chemical Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_select);

        spinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Select.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


