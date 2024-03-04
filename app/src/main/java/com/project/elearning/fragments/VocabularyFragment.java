package com.project.elearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.elearning.adapters.VocabularyAdapter;
import com.project.elearning.domains.Vocabulary;
import com.project.elearning.databinding.FragmentVocabularyBinding;

import java.util.ArrayList;
import java.util.List;

public class VocabularyFragment extends Fragment {

    private List<Vocabulary> topics;
    private RecyclerView recyclerView;
    private VocabularyAdapter adapter;
    private FragmentVocabularyBinding vocabularyBinding;
    final private DatabaseReference database = FirebaseDatabase.getInstance().getReference("Vocabulary");

    public VocabularyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vocabularyBinding = FragmentVocabularyBinding.inflate(inflater, container, false);
        initView();
        setVariables();
        return vocabularyBinding.getRoot();
    }

    private void setVariables() {
        adapter = new VocabularyAdapter(topics);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Vocabulary vocabulary = dataSnapshot.getValue(Vocabulary.class);
                    topics.add(vocabulary);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        recyclerView = vocabularyBinding.topicRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(vocabularyBinding.getRoot().getContext(), 2));
        topics = new ArrayList<>();

    }
}