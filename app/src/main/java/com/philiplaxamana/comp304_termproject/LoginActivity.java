package com.philiplaxamana.comp304_termproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Constants
    private final String TBL_USERS = "tbl_users";
    private Intent intent;

    EditText username, password;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Please Login");

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }

    // Handles the login method for user
    public void onLoginClicked(View view){
        final String loginUser = username.getText().toString();
        final String loginPass = password.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Login(loginUser, loginPass, TBL_USERS);

        // retrieves username to store in sharedpref
        editor.putString("username", username.getText().toString());
        editor.apply();

    }

    //  Manages login functionality
    public void Login(String username, String password, String TABLE_NAME){
        dbManager = new DatabaseManager(this);
        SQLiteDatabase db = dbManager.getWritableDatabase();

        // Retrieves table name, username and password from user
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE userName=? AND password=?";
        String selectionArgs [] = {username, password};

        // selection args are passed into query
        Cursor c = db.rawQuery(selectQuery,selectionArgs);

        // checks if such record exists
        boolean isObject;
        if(c.getCount()>0){
            isObject=true;
        } else {
            isObject=false;
        }

        // if record exists
        if (isObject==true && TABLE_NAME == "tbl_users"){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            DisplayToast("Successfully logged in.");
        } else {
            DisplayToast("User does not exist!");
        }

        db.close();
        c.close();
    }

    // Method for Toast Message
    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }
}
