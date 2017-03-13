package com.example.android.sendmoods;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.InputFilter;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

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
        moodText= (TextView) findViewById(R.id.editText);
        reasonText = (TextView) findViewById(R.id.editText2);
    }

    @Override
    protected void onStart(){
        super.onStart();

        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        moodText.setText(moodEvent.getEmotion());
        dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        reasonText.setText(moodEvent.getReason());
    }

    public void saveEdit(View view) {
        String temp;

        temp = reasonText.getText().toString();
        moodEvent.setReason(temp);

        temp = moodText.getText().toString();
        moodEvent.setEmotion(temp);

        temp = dateText.getText().toString();
        moodEvent.setDate(temp);


        Intent intent = new Intent(this, MoodListActivity.class);

        intent.putExtra("MoodEvent",moodEvent);
        startActivity(intent);
    }
}
