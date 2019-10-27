package com.example.englishvocabularyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Vocabulary mVocabulary = new Vocabulary();

    private TextView tvQuestion;
    private Button[] btnAnswers = new Button[4];
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            refresh();
        }
    };

    private String readRawResource() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.vocabulary);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);

        btnAnswers[0] = findViewById(R.id.btnAnswer1);
        btnAnswers[1] = findViewById(R.id.btnAnswer2);
        btnAnswers[2] = findViewById(R.id.btnAnswer3);
        btnAnswers[3] = findViewById(R.id.btnAnswer4);

        for (int i = 0; i < 4; i++) {
            btnAnswers[i].setOnClickListener(btnListener);
        }

        try {
            mVocabulary.init(readRawResource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    // Return random permutation of four predefined colors
    private int[] shuffleColors() {

        int[] colors = {0xffbffcc6, 0xffc4faf8, 0xfff3ffe3, 0xfff2e4ff};

        Random rnd = new Random(System.currentTimeMillis());
        int a, b, c, n = rnd.nextInt(10);
        for (int i = 0; i < n; i++) {
            a = rnd.nextInt(4);
            do {
                b = rnd.nextInt(4);
            } while (a == b);

            c = colors[a];
            colors[a] = colors[b];
            colors[b] = c;
        }

        return colors;
    }

    // Return random permutation of [0..3]
    private int[] shuffleAnswers() {

        int[] numbers = {0, 1, 2, 3};

        Random rnd = new Random(System.currentTimeMillis());
        int a, b, c, n = rnd.nextInt(10);
        for (int i = 0; i < n; i++) {
            a = rnd.nextInt(4);
            do {
                b = rnd.nextInt(4);
            } while (a == b);

            c = numbers[a];
            numbers[a] = numbers[b];
            numbers[b] = c;
        }

        return numbers;
    }

    private void refresh() {
        int[] colors = shuffleColors();
        int[] answers = shuffleAnswers();

        Task t = mVocabulary.getTask();

        tvQuestion.setText(t.getQuestion());

        for (int i = 0; i < 4; i++) {
            btnAnswers[answers[i]].setText(t.getAnswer(i));
        }

        for (int i = 0; i < 4; i++) {
            btnAnswers[i].setBackgroundColor(colors[i]);
            btnAnswers[i].invalidate();
        }
    }
}
