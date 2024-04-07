package com.project.elearning.domains;

public class Lesson {
    private int lessonNumber;
    private String engTitle;
    private String japTitle;
    private boolean isOpen;
    private static int totalLesson = 0;

    public Lesson() {
        totalLesson++;
        this.lessonNumber = totalLesson;
    }

    public Lesson(int lessonNumber, String engTitle, String japTitle, boolean isOpen) {
        this.lessonNumber = lessonNumber;
        this.engTitle = engTitle;
        this.japTitle = japTitle;
        this.isOpen = isOpen;
    }

    public Lesson(String engTitle, String japTitle) {
        this.engTitle = engTitle;
        this.japTitle = japTitle;
        this.isOpen = false;
        totalLesson++;
        this.lessonNumber = totalLesson;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public String getJapTitle() {
        return japTitle;
    }

    public void setJapTitle(String japTitle) {
        this.japTitle = japTitle;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
