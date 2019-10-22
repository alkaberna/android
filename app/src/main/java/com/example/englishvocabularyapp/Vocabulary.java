package com.example.englishvocabularyapp;

public class Vocabulary {

    public void init() {

    }

    public Task getTask() {

        String[] incorrect = {"бежевый", "чёрный", "синий"};

        Task result = new Task("green", "зелёный");

        for (int i = 0; i < 3; i++)
            result.addIncorrectAnswer(incorrect[i]);

        return result;
    }
}
