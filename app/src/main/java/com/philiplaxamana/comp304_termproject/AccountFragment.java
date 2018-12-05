package com.philiplaxamana.comp304_termproject;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    DatabaseManager dbManager;
    TextView[] fields;
    String [] strArray;

    private Button btnUpdate;
    private EditText mFullName,mSex, mDOB, mUsername, mHeight, mWeight, mActivityLevel, mGoal, mWeeklyGoal;

    final String tbl_user = "tbl_users";


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        dbManager = new DatabaseManager(getContext());
        SQLiteDatabase db = dbManager.getWritableDatabase();

        btnUpdate = view.findViewById(R.id.btnUpdate);
        mFullName = view.findViewById(R.id.et_fullName);
        mUsername = view.findViewById(R.id.et_username);
        mSex = view.findViewById(R.id.et_sex);
        mDOB = view.findViewById(R.id.et_dob);
        mHeight = view.findViewById(R.id.et_height);
        mWeight = view.findViewById(R.id.et_weight);
        mActivityLevel = view.findViewById(R.id.et_activityLevel);
        mGoal = view.findViewById(R.id.et_goal);
        mWeeklyGoal = view.findViewById(R.id.et_weeklyGoal);

        fields = new TextView[] {mFullName, mUsername, mSex, mDOB, mHeight, mWeight, mActivityLevel, mGoal, mWeeklyGoal};
        SharedPreferences sharedPref = this.getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "");

        Cursor c = dbManager.getUserData(tbl_user, username);

        c.moveToFirst();

        final String _id = c.getString(0);
        String _fullName = c.getString(1);
        String _sex = c.getString(2);
        String _dob = c.getString(3);
        String _activityLevel = c.getString(4);
        String _height = c.getString(5);
        String _weight = c.getString(6);
        String _goal = c.getString(7);
        String _weeklyGoal = c.getString(8);
        String _username = c.getString(9);

        mFullName.setText(_fullName);
        mSex.setText(_sex);
        mDOB.setText(_dob);
        mActivityLevel.setText(_activityLevel);
        mHeight.setText(_height);
        mWeight.setText(_weight);
        mGoal.setText(_goal);
        mWeeklyGoal.setText(_weeklyGoal);
        mUsername.setText(_username);

        final String[] record = new String[10];

        final String[] fields = {
                "userID",
                "fullName",
                "sex",
                "dob",
                "activityLevel",
                "height",
                "weight",
                "goal",
                "weeklyGoal",
                "username"
        };

        db.close();
        c.close();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record[0] = _id;
                record[1] = mFullName.getText().toString();
                record[2] = mSex.getText().toString();
                record[3] = mDOB.getText().toString();
                record[4] = mActivityLevel.getText().toString();
                record[5] = mHeight.getText().toString();
                record[6] = mWeight.getText().toString();
                record[7] = mGoal.getText().toString();
                record[8] = mWeeklyGoal.getText().toString();
                record[9] = mUsername.getText().toString();

                ContentValues values = new ContentValues();
                for (int i=1;i<record.length;i++)
                    values.put(fields[i],record[i]);

                dbManager.updateRecord(values, tbl_user, fields, record);
                DisplayToast("Record updated.");
            }
        });


        return view;
    }

    private void DisplayToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

}
