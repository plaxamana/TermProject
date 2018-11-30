package com.philiplaxamana.comp304_termproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ActionBar mActionBar;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBar = getSupportActionBar();
        mActionBar.hide();
    }

    public void logIn(){
        mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
    }

    public void signUp(){
        mIntent = new Intent(this, SignupActivity.class);
        startActivity(mIntent);
    }
}
