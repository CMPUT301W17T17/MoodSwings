package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by erasseli on 3/4/17.
 */

public class MoodListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_list);

        Button popButt = (Button) findViewById(R.id.popbutt);
        popButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MoodListActivity.this, MoodPopupActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
