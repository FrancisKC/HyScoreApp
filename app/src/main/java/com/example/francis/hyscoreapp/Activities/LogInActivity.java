package com.example.francis.hyscoreapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Selection;
import android.view.View;

import com.example.francis.hyscoreapp.DataBaseHelper.DataBaseHelper;
import com.example.francis.hyscoreapp.Helper.InputValidation;
import com.example.francis.hyscoreapp.R;

/**
 * Created by Francis on 09-Jun-18.
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
//creating the instance of the AppCompativity class and initializing it to LogInActivity.
private final AppCompatActivity activity = LogInActivity.this;
        //
        private NestedScrollView nestedScrollView;
        private TextInputLayout textInputLayoutEmail;
        private TextInputLayout textInputLayoutPassword;

        private TextInputEditText textInputEditTextEmail;
        private TextInputEditText textInputEditTextPassword;

        private AppCompatButton appCompatButtonLogin;

        private AppCompatTextView textViewLinkRegister;
        //Instance of my InputValidation class
        private InputValidation inputValidation;
        private DataBaseHelper dataBaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.login);
                getSupportActionBar().hide();

                initViews();
                initListeners();
                initObjects();
        }

        //this method will intialize the views
        private void initViews() {

                nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

                textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
                textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

                textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
                textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

                appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

                textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

        }

        /**
         * This method is to initialize listeners
         */
        private void initListeners() {
                appCompatButtonLogin.setOnClickListener(this);
                textViewLinkRegister.setOnClickListener(this);
                appCompatButtonLogin.setOnClickListener(this);
        }

        /**
         * This method is to initialize objects to be used
         */
        private void initObjects() {
                dataBaseHelper = new DataBaseHelper(activity);
                inputValidation = new InputValidation(activity);

        }

        /**
         * This implemented method is to listen the click on view
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.appCompatButtonLogin:
                                //created a method for this
                                verifyFromSQLite();
                                break;
                        case R.id.textViewLinkRegister:
                                // Go to RegisterActivity
                                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                                startActivity(intentRegister);
                                break;
                }
        }

        /**
         * This method is to validate the input text fields and verify login credentials from SQLite
         */
        private void verifyFromSQLite() {
                if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                        return;
                }
                if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                        return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
                        return;
                }

                if (dataBaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())) {


                        Intent accountsIntent = new Intent(activity, Select.class);
                        accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);


                } else {
                        // Snack Bar to show success message that record is wrong
                        Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
                }
        }

        /**
         * This method is to empty all input edit text
         */
        private void emptyInputEditText() {
                textInputEditTextEmail.setText(null);
                textInputEditTextPassword.setText(null);
        }
}
