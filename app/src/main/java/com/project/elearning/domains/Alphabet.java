package com.project.elearning.domains;

public class Alphabet {
    private String alphabet;
    private int number;


    // 0 - hiragana
    // 1 - katakana


    public Alphabet() {
    }

    public Alphabet(String alphabet, int number) {
        this.number = number;
        this.alphabet = alphabet;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }
}
