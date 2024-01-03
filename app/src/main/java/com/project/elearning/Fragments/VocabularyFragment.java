package com.project.elearning.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.elearning.Adapters.VocabularyAdapter;
import com.project.elearning.Domains.VocabularyTopic;
import com.project.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class VocabularyFragment extends Fragment {

    private List<VocabularyTopic> topics;
    private RecyclerView recyclerView;
    private VocabularyAdapter adapter;

    public VocabularyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
    
        initView(view);
        setVariables();
        
        return view;
    }

    private void setVariables() {
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Greeting"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Family"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Food and Drink"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Fruit and Vegetable"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Vehicle"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Country and City"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "School"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Weather"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Information Technology"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Financial"));
        topics.add(new VocabularyTopic(R.drawable.ic_launcher_foreground, "Entertainment"));

        adapter = new VocabularyAdapter(topics);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.topicRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        topics = new ArrayList<>();


    }
}