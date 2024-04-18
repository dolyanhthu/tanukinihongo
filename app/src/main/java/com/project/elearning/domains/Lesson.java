package com.project.elearning.domains;

public class Lesson {
    private String number;
    private String grammar;
    private String meaning;
    private String example;

    public Lesson(String number, String grammar, String meaning, String example) {
        this.number = number;
        this.grammar = grammar;
        this.meaning = meaning;
        this.example = example;
    }

    public Lesson() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
