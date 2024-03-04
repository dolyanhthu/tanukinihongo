package com.project.elearning.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.elearning.adapters.LessonAdapter;
import com.project.elearning.domains.Lesson;
import com.project.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class LessonFragment extends Fragment {

    private LessonAdapter adapter;
    private List<Lesson> lessonList;
    private RecyclerView recyclerView;

    public LessonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        initView(view);
        setVariables();

        return view;
    }

    private void setVariables() {
        Lesson lesson1 = new Lesson("Nice to meet you ", "はじめまして", false);
        Lesson lesson2 = new Lesson("It's just a feeling", " ほんの気持ちです", false);
        Lesson lesson3 = new Lesson("Please give me this", "これをください", false);
        Lesson lesson5 = new Lesson("Are you going to Koshien?", "甲子園へ行きますか", false);
        Lesson lesson6 = new Lesson("Would you like to go with me?", "いっしょにいきませんか", false);
        Lesson lesson7 = new Lesson("Sorry", "ごめんください", false);
        Lesson lesson8 = new Lesson("Excuse me soon", "そろそろ失礼します", false);
        Lesson lesson9 = new Lesson("Sorry", "残念です", false);
        Lesson lesson10 = new Lesson( "Do you have any chili sauce?", "チリソースがありませんか", false);

        lessonList.add(lesson1);
        lessonList.add(lesson2);
        lessonList.add(lesson3);
        lessonList.add(lesson5);
        lessonList.add(lesson6);
        lessonList.add(lesson7);
        lessonList.add(lesson8);
        lessonList.add(lesson9);
        lessonList.add(lesson10);

        adapter = new LessonAdapter(lessonList);

        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        lessonList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.lessonRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }
}