package com.example.quickmathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, problemTextView, pointTextView, answerTextView;
    Button qtn1, qtn2, qtn3, qtn4, startButton;
    androidx.gridlayout.widget.GridLayout mygridLayout;

    Double answer;
    String question;
    int answerPosition;
    int numberOfCorrectAnswers = 0;
    int numberOfQuestionsGenerated = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qtn1 = (Button) findViewById(R.id.button);
        qtn2 = (Button) findViewById(R.id.button2);
        qtn3 = (Button) findViewById(R.id.button3);
        qtn4 = (Button) findViewById(R.id.button4);
        startButton = (Button) findViewById(R.id.button5);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        problemTextView = (TextView) findViewById(R.id.problemTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        mygridLayout = findViewById(R.id.gridLayout);
        mygridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < mygridLayout.getChildCount(); i++) {
            mygridLayout.getChildAt(i).setEnabled(false);
        }
        pointTextView.setText("0/0");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mygridLayout.setEnabled(true);
                answerTextView.setText("");
                generateRandomNumber();
                for (int i = 0; i < mygridLayout.getChildCount(); i++) {
                    mygridLayout.getChildAt(i).setEnabled(true);
                }
                startTimer();
            }
        });


    }

    public void checkAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(answerPosition))) {
            numberOfCorrectAnswers++;
            answerTextView.setText("Correct");
            answerTextView.setBackgroundColor(Color.GREEN);
        } else {
            answerTextView.setText("Wrong");
            answerTextView.setBackgroundColor(Color.RED);
        }
        pointTextView.setText(Integer.toString(numberOfCorrectAnswers) + "/" + Integer.toString(numberOfQuestionsGenerated));
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        numberOfQuestionsGenerated++;
        Random random = new Random();
        double i = random.nextInt(15);
        double j = random.nextInt(15);
        double k = random.nextInt(15);
        double z = 2;
        int position = random.nextInt(4);
        int type = random.nextInt(4);
        position++;

        answerPosition = position;

        if(type== 0) {
            answer = i + j;
            question = "(" + Integer.toString((int) i) + "+"+Integer.toString((int) j) + ")";
        }
        if(type== 1) {
            answer = i - j;
            question = "(" + Integer.toString((int) i) + "-"+ Integer.toString((int) j) + ")";
        }
        if(type== 2) {
            answer = i * j;
            question = "(" + Integer.toString((int) i) + "*"+ Integer.toString((int) j) + ")";
        }
        if(type== 3) {
            answer = i + j;
            question = "(" + Integer.toString((int) i) + "+"+ Integer.toString((int) j) + ")";
        }
        if(type== 4) {
            answer = i - j;
            question = "(" + Integer.toString((int) i) + "-"+ Integer.toString((int) j) + ")";
        }
        String q1 ="", q2 ="", q3 = "", q4 = "";
        double ai=0,aj=0,ak=0;
        ai = random.nextInt(90);
        aj = random.nextInt(90);
        ak = random.nextInt(90);
        if (ai==answer||aj==answer||ak==answer) {
            ai = random.nextInt(90);
            aj = random.nextInt(90);
            ak = random.nextInt(90);
        }
        if (position == 1) {
            q1 = Double.toString(answer);
            q2 = Double.toString(ai);
            q3 = Double.toString(aj);
            q4 = Double.toString(ak);
        } else if (position == 2) {
            q1 = Double.toString(ai);
            q2 = Double.toString(answer);
            q3 = Double.toString(aj);
            q4 = Double.toString(ak);
        } else if (position == 3) {
            q1 = Double.toString(ai);
            q2 = Double.toString(aj);
            q3 = Double.toString(answer);
            q4 = Double.toString(ak);
        } else if (position == 4) {
            q1 = Double.toString(ai);
            q2 = Double.toString(aj);
            q3 = Double.toString(ak);
            q4 = Double.toString(answer);
        }
        qtn1.setText(q1);
        qtn2.setText(q2);
        qtn3.setText(q3);
        qtn4.setText(q4);
        problemTextView.setText(question);
    }

    private void startTimer() {
        new CountDownTimer(45000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                 timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
                 startButton.setEnabled(false);
            }

            @Override
            public void onFinish() {
                 timerTextView.setText("45s");
                 answerTextView.setText("Your Score : " + String.valueOf(numberOfCorrectAnswers));
                 problemTextView.setText("0");
                 pointTextView.setText("0");
                 startButton.setEnabled(true);
                 numberOfCorrectAnswers = 0;
                 numberOfQuestionsGenerated = 0;
                for (int i = 0; i < mygridLayout.getChildCount(); i++) {
                    mygridLayout.getChildAt(i).setEnabled(false);
                }
            }
        }.start();
    }


}
