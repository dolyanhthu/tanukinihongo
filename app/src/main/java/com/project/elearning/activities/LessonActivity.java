package com.project.elearning.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.elearning.R;
import com.project.elearning.databinding.ActivityLessonBinding;
import com.project.elearning.domains.Lesson;

public class LessonActivity extends AppCompatActivity {

    public static Lesson lesson = new Lesson();
    ActivityLessonBinding lessonBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lessonBinding = ActivityLessonBinding.inflate(getLayoutInflater());
        setContentView(lessonBinding.getRoot());

        lessonBinding.setLesson(lesson);

        lessonBinding.btnClose.setOnClickListener(v -> onBackPressed());
    }

}