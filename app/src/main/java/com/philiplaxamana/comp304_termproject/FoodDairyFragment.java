package com.philiplaxamana.comp304_termproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDairyFragment extends Fragment {


    public FoodDairyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_dairy, container, false);

        ArrayList<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Apple", 80));
        foodItems.add(new FoodItem("Donut", 260));
        foodItems.add(new FoodItem("New York Cheesecake", 500));
        foodItems.add(new FoodItem("Ramen noodles", 300));

        FoodItemAdapter foodItemAdapter = new FoodItemAdapter((FragmentActivity) getContext(), foodItems);
        ListView listView = view.findViewById(R.id.listView_food);
        listView.setAdapter(foodItemAdapter);

        return view;

    }

}
