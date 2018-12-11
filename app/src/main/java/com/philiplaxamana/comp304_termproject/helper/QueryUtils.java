package com.philiplaxamana.comp304_termproject.helper;

import android.util.Log;

import com.philiplaxamana.comp304_termproject.models.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE = "{" +
            "    \"foodItem\" : [" +
            "        {" +
            "            \"text\" : \"red apple\"," +
            "            \"parsed\" : [ {" +
            "              \"food\" : {" +
            "                \"foodId\" : \"food_bnbh4ycaqj9as0a9z7h9xb2wmgat\"," +
            "                \"uri\" : \"http://www.edamam.com/ontologies/edamam.owl#Food_09003\"," +
            "                \"label\" : \"Apples, raw, with skin\"," +
            "                \"nutrients\" : {" +
            "                  \"ENERC_KCAL\" : 52.0," +
            "                  \"PROCNT\" : 0.26," +
            "                  \"FAT\" : 0.17," +
            "                  \"CHOCDF\" : 13.81," +
            "                  \"FIBTG\" : 2.4" +
            "                }," +
            "                \"category\" : \"Generic foods\"," +
            "                \"categoryLabel\" : \"food\"" +
            "              }" +
            "            } ]" +
            "          },\n" +
            "          {" +
            "            \"text\" : \"banana\"," +
            "            \"parsed\" : [ {" +
            "              \"food\" : {" +
            "                \"foodId\" : \"food_b0bn6w4ab49t55b1o8jsnbq6nm2g\"," +
            "                \"uri\" : \"http://www.edamam.com/ontologies/edamam.owl#Food_09040\"," +
            "                \"label\" : \"Bananas, raw\"," +
            "                \"nutrients\" : {" +
            "                  \"ENERC_KCAL\" : 89.0," +
            "                  \"PROCNT\" : 1.09," +
            "                  \"FAT\" : 0.33," +
            "                  \"CHOCDF\" : 22.84," +
            "                  \"FIBTG\" : 2.6" +
            "                }," +
            "                \"category\" : \"Generic foods\"," +
            "                \"categoryLabel\" : \"food\"" +
            "              }" +
            "            } ]" +
            "          },\n" +
            "          {" +
            "            \"text\" : \"chocolate donut\"," +
            "            \"parsed\" : [ {" +
            "              \"food\" : {" +
            "                \"foodId\" : \"food_a767edbajekim1a0jef89begt3ap\"," +
            "                \"uri\" : \"http://www.edamam.com/ontologies/edamam.owl#Food_18249\"," +
            "                \"label\" : \"Doughnuts, cake-type, plain, chocolate-coated or frosted\"," +
            "                \"nutrients\" : {" +
            "                  \"ENERC_KCAL\" : 452.0," +
            "                  \"PROCNT\" : 4.93," +
            "                  \"FAT\" : 25.25," +
            "                  \"CHOCDF\" : 51.33," +
            "                  \"FIBTG\" : 1.9" +
            "                }," +
            "                \"category\" : \"Generic foods\"," +
            "                \"categoryLabel\" : \"food\"" +
            "              }" +
            "            } ]" +
            "          }" +
            "    ]" +
            "}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<FoodItem> extractFood() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<FoodItem> foods = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonRootObj = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray foodArray = jsonRootObj.getJSONArray("foodItem");
            for(int i=0; i<foodArray.length(); i++){
                JSONObject currentFoodItem = foodArray.getJSONObject(i);
                String foodText = currentFoodItem.getString("text");
                JSONArray parsed = currentFoodItem.getJSONArray("parsed");
                JSONObject aFood = parsed.getJSONObject(0);
                JSONObject food = aFood.getJSONObject("food");
                JSONObject nutrients = food.getJSONObject("nutrients");
                double cal = Double.parseDouble(nutrients.optString("ENERC_KCAL"));

                FoodItem foodItem = new FoodItem(foodText, cal);
                foods.add(foodItem);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the foodItems JSON results", e);
        }

        // Return the list of earthquakes
        return foods;
    }

}