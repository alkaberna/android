package com.example.englishvocabularyapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class Vocabulary {

    private static final Type VOCABULARY_TYPE = new TypeToken<List<VocabularyItem>>() {}.getType();

    private List<VocabularyItem> mItems;

    public void init(String json) {

        Gson gson = new Gson();
        mItems = gson.fromJson(json, VOCABULARY_TYPE);
    }

    public Task getTask() {

        Random rnd = new Random(System.currentTimeMillis());
        int questionNumber = rnd.nextInt(mItems.size());
        Task result = new Task(mItems.get(questionNumber).getEnglishWord(),
                mItems.get(questionNumber).getRussianWord());

        int[] incorrectIndex = {-1, -1, -1};

        for (int i = 0; i < 3; i++) {
            int answerNumber = rnd.nextInt(mItems.size());
            if (answerNumber == questionNumber) {
                i--;
                continue;
            }

            boolean isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (answerNumber == incorrectIndex[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                i--;
                continue;
            }

            result.addIncorrectAnswer(mItems.get(answerNumber).getRussianWord());
            incorrectIndex[i] = answerNumber;
        }

        return result;
    }
}
