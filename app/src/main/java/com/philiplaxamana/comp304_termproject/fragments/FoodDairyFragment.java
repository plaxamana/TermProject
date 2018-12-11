package com.philiplaxamana.comp304_termproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.philiplaxamana.comp304_termproject.helper.QueryUtils;
import com.philiplaxamana.comp304_termproject.models.FoodItem;
import com.philiplaxamana.comp304_termproject.FoodItemAdapter;
import com.philiplaxamana.comp304_termproject.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDairyFragment extends Fragment {


    public FoodDairyFragment() {
        // Required empty public constructor
    }

    public void onAddFoodBtnClicked(View view){
        Intent i = new Intent(getContext(), FoodFragment.class);
        startActivity(i);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_dairy, container, false);

        ArrayList<FoodItem> foodItems = QueryUtils.extractFood();
//        foodItems.add(new FoodItem("Apple", 80));
//        foodItems.add(new FoodItem("Donut", 260));
//        foodItems.add(new FoodItem("New York Cheesecake", 500));
//        foodItems.add(new FoodItem("Ramen noodles", 300));

        FoodItemAdapter foodItemAdapter = new FoodItemAdapter((FragmentActivity) getContext(), foodItems);
        ListView listView = view.findViewById(R.id.listView_food);
        listView.setAdapter(foodItemAdapter);

        return view;

    }

}
