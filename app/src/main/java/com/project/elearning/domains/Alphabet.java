package com.project.elearning.domains;

public class Alphabet {
    private String alphabet;
    private String audio;
    private String gif;

    public Alphabet() {
    }

    public Alphabet(String alphabet, String audio, String gif) {
        this.alphabet = alphabet;
        this.audio = audio;
        this.gif = gif;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }
}
