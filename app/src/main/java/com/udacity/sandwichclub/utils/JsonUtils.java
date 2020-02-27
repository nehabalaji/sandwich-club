package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichJson;
        Sandwich sandwiches = new Sandwich();
        try{
            sandwichJson= new JSONObject(json);
            JSONObject name = sandwichJson.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownas = new ArrayList<>();
            for(int i=0; i<alsoKnownas.size(); i++){
                String s = alsoKnownAs.getString(i);
                alsoKnownas.add(s);
            }
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            JSONArray ingredients = sandwichJson.getJSONArray("ingredients");
            List<String> ingredient = new ArrayList<>();
            for(int j=0; j<ingredient.size(); j++){
                String i = ingredients.getString(j);
                ingredient.add(i);
            }
            String imageResID = name.getString("image");

            sandwiches.setMainName(mainName);
            sandwiches.setAlsoKnownAs(alsoKnownas);
            sandwiches.setPlaceOfOrigin(placeOfOrigin);
            sandwiches.setDescription(description);
            sandwiches.setIngredients(ingredient);
            sandwiches.setImage(imageResID);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwiches;
    }
}
