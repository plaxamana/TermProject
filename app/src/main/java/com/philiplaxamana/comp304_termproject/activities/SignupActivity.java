package com.philiplaxamana.comp304_termproject.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.philiplaxamana.comp304_termproject.helper.DatabaseManager;
import com.philiplaxamana.comp304_termproject.R;

public class SignupActivity extends AppCompatActivity {

    //////////////////////////////
    // Database Management stuff//
    //////////////////////////////

    // Tables
    private static final String[] tables = {"tbl_users"};

    // Creator string
    private static final String[] tableCreatorString = {
            "CREATE TABLE tbl_users (userID INTEGER PRIMARY KEY AUTOINCREMENT, fullName TEXT, sex TEXT, " +
                    "DOB TEXT, activityLevel TEXT, height NUMBER, weight NUMBER, goal TEXT, weeklyGoal TEXT, username TEXT, password TEXT)"
    };

    // Widgets
    Spinner spinner_activityLevel, spinner_weeklyGoal, spinner_goal;
    Button btnSubmit;
    EditText _fullName, _DOB, _height, _weight, _username, _password;
    String _activityLevel, _goal, _weeklyGoal, sex;
    RadioGroup radioSexGroup;
    RadioButton male, female;

    Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Please register below");

        spinner_activityLevel = findViewById(R.id.spinner_activityLevel);
        spinner_goal = findViewById(R.id.spinner_goal);
        spinner_weeklyGoal = findViewById(R.id.spinner_weeklyGoal);

        // Database management
        final DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.createDatabase(this);
        dbManager.dbInitialize(tables, tableCreatorString);
        final String[] fields = {"userID", "fullName", "sex", "DOB", "activityLevel", "height", "weight", "goal", "weeklyGoal", "username", "password"};
        final String[] record = new String[11];

        // Handle submit button (save)
        btnSubmit = findViewById(R.id.btnSubmit);
        _fullName = findViewById(R.id.et_fullName);
        _DOB = findViewById(R.id.et_dob);
        _height = findViewById(R.id.et_height);
        _weight = findViewById(R.id.et_currentWeight);
        _username = findViewById(R.id.et_username);
        _password = findViewById(R.id.et_password);
        _activityLevel = spinner_activityLevel.getSelectedItem().toString();
        _goal = spinner_goal.getSelectedItem().toString();
        _weeklyGoal = spinner_weeklyGoal.getSelectedItem().toString();

        radioSexGroup = findViewById(R.id.rg_sex);
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_male:
                        sex = "male";
                        break;
                    case R.id.rb_female:
                        sex = "female";
                        break;
                    default:
                        break;
                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record[1] = _fullName.getText().toString();
                record[2] = sex;
                record[3] = _DOB.getText().toString();
                record[4] = _activityLevel;
                record[5] = _height.getText().toString();
                record[6] = _weight.getText().toString();
                record[7] = _goal;
                record[8] = _weeklyGoal;
                record[9] = _username.getText().toString();
                record[10] = _password.getText().toString();

                ContentValues values = new ContentValues();
                for(int i=1; i<record.length; i++){
                    values.put(fields[i], record[i]);
                }

                    dbManager.addRecord(values, "tbl_users", fields, record);
                    Toast.makeText(getApplicationContext(), "User successfully created!  Please login.", Toast.LENGTH_SHORT).show();
                    mIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(mIntent);
            }
        });


    }

}

