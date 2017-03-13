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

        EditText ReasonEdit = (EditText) findViewById(R.id.reasonText);
        ReasonEdit.setFilters(new InputFilter[]{new TextInputFilter()});
    }


    /**
     * Implement save Button
     */
    public void saveMood(View view) {
        /*The variables that don't have edit fields will be entered by the users*/
        String date = "Today";
        String time = "Now";
        String reason = ((EditText)findViewById(R.id.reasonText)).getText().toString();
        String username = "Username";
        String address = "Here";
        String emotion = Constants.HAPPY_WORD;
        int popupshape = Constants.HAPPY_POPUP_BOX;
        String color = Constants.HAPPY_COLOR;

        Intent intent = new Intent();
        Bundle extras = new Bundle();

        extras.putString("EXTRA_DATE", date);
        extras.putString("EXTRA_TIME", time);
        extras.putString("EXTRA_REASON", reason);
        extras.putString("EXTRA_USER", username);
        extras.putString("EXTRA_ADDRESS", address);
        extras.putString("EXTRA_EMOTION", emotion);
        extras.putInt("EXTRA_POPUP", popupshape);
        extras.putString("EXTRA_COLOR", color);

        intent.putExtras(extras);

        setResult(Constants.INTENT_RESULT_CODE, intent);
        finish();

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
