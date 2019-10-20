package com.example.englishvocabularyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] btnAnswers = new Button[4];
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT).show();
            refresh();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnswers[0] = findViewById(R.id.btnAnswer1);
        btnAnswers[1] = findViewById(R.id.btnAnswer2);
        btnAnswers[2] = findViewById(R.id.btnAnswer3);
        btnAnswers[3] = findViewById(R.id.btnAnswer4);

        for (int i = 0; i < 4; i++) {
            btnAnswers[i].setOnClickListener(btnListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    // Return random permutation of four predefined colors
    private int[] shuffleColors() {

        int[] colors = {0xffbffcc6, 0xffc4faf8,  0xfff3ffe3, 0xfff2e4ff};

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

    private void refresh() {
        int[] colors = shuffleColors();

        for (int i = 0; i < 4; i++) {
            btnAnswers[i].setBackgroundColor(colors[i]);
            btnAnswers[i].invalidate();
        }
    }
}
