package com.tanvir.tanvirmahmudkhan.parser;

import android.util.Log;

import com.tanvir.tanvirmahmudkhan.model.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlowerJSONParser {

    public static List<Flower> parseFeed(String content){

        try {
            JSONArray array = new JSONArray(content);

            List<Flower> flowers = new ArrayList<>();

            for (int i=0; i<array.length(); i++){
                JSONObject object =  array.getJSONObject(i);

                Flower flower = new Flower();

                flower.setProductId(object.getInt("productId"));
                flower.setName(object.getString("name"));
                flower.setCategory(object.getString("category"));
                flower.setInstructions(object.getString("instructions"));
                flower.setPhoto(object.getString("photo"));
                flower.setPrice(object.getDouble("price"));

                flowers.add(flower);

            }

            return  flowers;




        }  catch (JSONException e) {
            Log.d("Error",e.getMessage());
            return null;
        }



    }

}
