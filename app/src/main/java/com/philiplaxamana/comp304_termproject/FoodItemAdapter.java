package com.philiplaxamana.comp304_termproject;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.philiplaxamana.comp304_termproject.models.FoodItem;

import java.util.ArrayList;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

    private static final String LOG_TAG = FoodItemAdapter.class.getSimpleName();


    public FoodItemAdapter(FragmentActivity context, ArrayList<FoodItem> foodItems) {
        super(context, 0, foodItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_food_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        FoodItem currentFoodItem = getItem(position);

        // Find the TextView in the list_food_item_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.foodName);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentFoodItem.getName());

        // Find the TextView in the list_food_item_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.calories);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(String.valueOf(currentFoodItem.getCalories()));


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
