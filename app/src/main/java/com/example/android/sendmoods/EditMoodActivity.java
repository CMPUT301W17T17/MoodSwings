package com.example.android.sendmoods;

        import android.app.Activity;
        import android.os.Bundle;
        import android.text.InputFilter;
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

}
