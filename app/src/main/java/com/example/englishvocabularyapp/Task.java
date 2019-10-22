package com.example.englishvocabularyapp;

public class Task {

    private String question;
    private String correctAnswer;
    private String[] incorrectAnswers;
    private int nAnswers;

    public Task(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        incorrectAnswers = new String[3];
        nAnswers = 0;
    }

    // Add answer to array of incorrect answers
    public void addIncorrectAnswer(String answer) {

        if (nAnswers < 3) {
            incorrectAnswers[nAnswers] = answer;
            nAnswers++;
        }
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer(int index) {

        if (index == 0)
            return correctAnswer;

        if (index < 4)
            return incorrectAnswers[index - 1];

        // stub
        return "";
    }
}
