package com.bignerdranch.meha.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUESTCODE =0;
    private static final String EXTRA_ANSWER_SHOWN = "anser_shown";
    private boolean isCheater;

    TextView questionTV;
    Button trueBtn;
    Button falseBtn;
    Button cheatBtn;
    ImageButton prevBtn;
    ImageButton nextBtn;

    int currentIndex = 0;


    private QuestionModel[] mQuestionBank = new QuestionModel[]{
            new QuestionModel(R.string.q1, true),
            new QuestionModel(R.string.q2, true),
            new QuestionModel(R.string.q3, false),
            new QuestionModel(R.string.q4, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        questionTV = (TextView) findViewById(R.id.question);
        int question = mQuestionBank[currentIndex].getQuestion();
        questionTV.setText(question);
        questionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        trueBtn = (Button) findViewById(R.id.trueButton);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseBtn = (Button) findViewById(R.id.falseButton);
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        prevBtn = (ImageButton) findViewById(R.id.previousButton);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex >= 0) {
                    currentIndex = (currentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });


        nextBtn = (ImageButton) findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % mQuestionBank.length;
                isCheater = false;
                updateQuestion();
            }
        });

        cheatBtn = (Button) findViewById(R.id.cheatButton);
        cheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(this, "cheating is wrong", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra("answer", showAnser());
                startActivityForResult(intent, REQUESTCODE);
            }
        });
    }

    private boolean showAnser(){
        boolean answer = mQuestionBank[currentIndex].isAnswer();
        return answer;
    }

    private void checkAnswer(boolean userAnswer) {
        boolean checkAnser = mQuestionBank[currentIndex].isAnswer();
        int messageId = 0;

        if (isCheater) {
            messageId = R.string.judgement_toast;
        } else{
            if (checkAnser == userAnswer) {
                messageId = R.string.correct_toast;
            } else {
                messageId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    private void updateQuestion() {
        int question = mQuestionBank[currentIndex].getQuestion();
        questionTV.setText(question);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUESTCODE) {
            if (data == null) {
                return;
            }
            isCheater = wasAnswerShown(data);        }

    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
