package com.example.englishvocabularyapp;

import com.google.gson.annotations.SerializedName;

public class VocabularyItem {

    @SerializedName("eng")
    private String mEnglishWord;

    @SerializedName("rus")
    private String mRussianWord;

    @SerializedName("pos")
    private int mPartOfSpeech;

    public VocabularyItem(String eng, String rus, int pos) {
        mEnglishWord = eng;
        mRussianWord = rus;
        mPartOfSpeech = pos;
    }

    public String getEnglishWord() {
        return mEnglishWord;
    }

    public String getRussianWord() {
        return mRussianWord;
    }
}
