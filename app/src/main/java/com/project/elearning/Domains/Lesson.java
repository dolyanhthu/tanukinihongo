package com.project.elearning.Domains;

public class Lesson {
    private int lessonNumber;
    private String lessonName;
    private String lessonTitle;
    private boolean isOpen;
    private static int totalLesson = 0;

    public Lesson(String lessonName, String lessonTitle, boolean isOpen) {
        this.lessonNumber = totalLesson + 1;
        this.lessonName = lessonName;
        this.lessonTitle = lessonTitle;
        this.isOpen = isOpen;
        totalLesson++;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
