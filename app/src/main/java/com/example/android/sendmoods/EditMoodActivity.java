package com.example.android.sendmoods;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.InputFilter;
        import android.view.View;
        import android.widget.EditText;

/**
 *
 */

public class EditMoodActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        EditText ReasonEdit = (EditText) findViewById(R.id.editText2);
        ReasonEdit.setFilters(new InputFilter[]{new TextInputFilter()});
    }

    /**
     * Implement save Button
     */
    public void saveMood(View view) {
        Intent intent = new Intent(this, MoodListActivity.class);
        startActivity(intent);
    }

    /**
     * Implement delete Button
     */
    public void deleteMood(View view) {

    }

    public void shareOne(View view) {

    }

    public void shareTwo(View view) {

    }

    public void shareThree(View view) {

    }
}
