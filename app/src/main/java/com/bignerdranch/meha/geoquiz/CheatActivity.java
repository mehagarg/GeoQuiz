package com.bignerdranch.meha.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
    Button showAnswer;
    TextView textView;
    boolean answer;

    private static final String EXTRA_ANSWER_SHOWN = "anser_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        textView = (TextView) findViewById(R.id.textDumy);

        Intent intent = getIntent();
        if (intent != null) {
            answer = intent.getBooleanExtra("answer", false);
        }

        showAnswer = (Button) findViewById(R.id.showAnser);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer) {
                    textView.setText("true");
                } else {
                    textView.setText("false");
                }
                setAnswerShownForResult(true);
            }
        });
    }

    private void setAnswerShownForResult(boolean b) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, b);
        setResult(RESULT_OK, data);
    }

}
