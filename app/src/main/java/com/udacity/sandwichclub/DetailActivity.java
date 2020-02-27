package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView mSandwichView;
    TextView mOrigin, mAlsoKnownAs, mIngredients, mDescription;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       mSandwichView = findViewById(R.id.image_iv);
       mOrigin = findViewById(R.id.origin_tv);
       mAlsoKnownAs = findViewById(R.id.also_known_tv);
       mIngredients = findViewById(R.id.ingredients_tv);
       mDescription = findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
               setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
if(alsoKnownAs!=null) {
    for (int i = 0; i < alsoKnownAs.size(); i++) {
        if (i > 0) mAlsoKnownAs.append(", ");
        mAlsoKnownAs.append(alsoKnownAs.get(i));
    }
}
        List<String> ingredients = sandwich.getIngredients();
        for(int j=0; j<ingredients.size(); j++){
            mIngredients.append(ingredients.get(j));
        }
        mOrigin.setText(sandwich.getPlaceOfOrigin());
        mDescription.setText(sandwich.getDescription());
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(mSandwichView);
    }
}
