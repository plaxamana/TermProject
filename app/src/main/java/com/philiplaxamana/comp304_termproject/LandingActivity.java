package com.philiplaxamana.comp304_termproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    ActionBar mActionBar;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mActionBar = getSupportActionBar();
        mActionBar.hide();
    }

    public void logIn(View v){
        mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
    }

    public void signUp(View v){
        mIntent = new Intent(this, SignupActivity.class);
        startActivity(mIntent);
    }
}
