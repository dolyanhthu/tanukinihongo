package com.project.elearning.Domains;

public class Alphabet {
    private int setNumber;
    private String setAlphabet;

    // 0 - hiragana
    // 1 - katakana
    public Alphabet(int setNumber, String setAlphabet) {
        this.setNumber = setNumber;
        this.setAlphabet = setAlphabet;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetAlphabet() {
        return setAlphabet;
    }

    public void setSetAlphabet(String setAlphabet) {
        this.setAlphabet = setAlphabet;
    }
}
