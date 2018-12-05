package com.philiplaxamana.comp304_termproject.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.philiplaxamana.comp304_termproject.R;
import com.philiplaxamana.comp304_termproject.fragments.AccountFragment;
import com.philiplaxamana.comp304_termproject.fragments.FoodDairyFragment;
import com.philiplaxamana.comp304_termproject.fragments.SmsFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment FragSMS = new SmsFragment();
    final Fragment FragDiary = new FoodDairyFragment();
    final Fragment FragAccount = new AccountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = FragSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.mainTitle);

        fm.beginTransaction().add(R.id.main_frame, FragAccount, "3").hide(FragAccount).commit();
        fm.beginTransaction().add(R.id.main_frame, FragDiary, "2").hide(FragDiary).commit();
        fm.beginTransaction().add(R.id.main_frame, FragSMS, "1").commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_SMS:
                        fm.beginTransaction().hide(active).show(FragSMS).commit();
                        active = FragSMS;
                        return true;
                    case R.id.nav_diary:
                        fm.beginTransaction().hide(active).show(FragDiary).commit();
                        active = FragDiary;
                        return true;
                    case R.id.nav_account:
                        fm.beginTransaction().hide(active).show(FragAccount).commit();
                        active = FragAccount;
                        return true;
                }

                return false;
            }
        });


    }
}
