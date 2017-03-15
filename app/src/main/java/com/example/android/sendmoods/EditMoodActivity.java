package com.example.android.sendmoods;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.InputFilter;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

        import static com.example.android.sendmoods.Constants.HAPPY_COLOR;
        import static com.example.android.sendmoods.Constants.HAPPY_POPUP_BOX;
        import static com.example.android.sendmoods.Constants.REQ_CODE_EXISTING;
        import static com.example.android.sendmoods.Constants.REQ_CODE_NEW;

/**
 *
 */

public class EditMoodActivity extends Activity {

    private MoodEvent moodEvent;
    private TextView moodText;
    private TextView dateText;
    private TextView reasonText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        EditText ReasonEdit = (EditText) findViewById(R.id.editText2);
        ReasonEdit.setFilters(new InputFilter[]{new TextInputFilter()});


        dateText = (TextView) findViewById(R.id.datetime);
        moodText = (TextView) findViewById(R.id.editText);
        reasonText = (TextView) findViewById(R.id.editText2);
    }

    @Override
    protected void onStart(){
        super.onStart();
        String nullString = "a";
        String nullDate = "February 02, 2017";

        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        try {
            moodText.setText(moodEvent.getEmotion());
        } catch (Exception e) {
            moodText.setText(nullString);
        }

        try {
            dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        } catch (Exception e) {
            dateText.setText(nullDate);
        }

        try {
            reasonText.setText(moodEvent.getReason());
        } catch (Exception e) {
            reasonText.setText(nullString);
        }
    }

    public void saveEdit(View view) {
        String temp;
        int resultCode = 1;

        moodEvent.setUsername("machung");

        //temp = reasonText.getText().toString();
        moodEvent.setReason(reasonText.getText().toString());

        //temp = moodText.getText().toString();
        moodEvent.setEmotion(moodText.getText().toString());

        //temp = dateText.getText().toString();

        moodEvent.setDate("February 02, 2017");//http://stackoverflow.com/questions/9945072/convert-string-to-date-in-java This will help sort by date
        moodEvent.setTime("11:11");
        moodEvent.setAddress("123 Fakestreet, WA");
        moodEvent.setColor(HAPPY_COLOR);
        moodEvent.setPopupShape(HAPPY_POPUP_BOX);


        Intent intent = new Intent(this, MoodListActivity.class);

        intent.putExtra("MoodEvent",moodEvent);

        setResult(resultCode, intent);
        finish();
        //startActivity(intent);
    }

    public void deleteMood(View view) {
        int resultCode = 0;

        Intent intent = new Intent(this, MoodListActivity.class);

        intent.putExtra("MoodEvent",moodEvent);

        //startActivityForResult(intent, resultCode);
        setResult(resultCode, intent);
        finish();
    }
}
