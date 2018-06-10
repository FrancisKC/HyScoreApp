package com.example.francis.hyscoreapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.francis.hyscoreapp.R;

/**
 * Created by Francis on 09-Jun-18.
 */

public class FirstActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }
    public void initLogIn(View view){
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
    public void initSignUp(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
