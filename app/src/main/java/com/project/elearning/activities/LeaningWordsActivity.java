package com.project.elearning.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.elearning.R;
import com.project.elearning.adapters.WordAdapter;
import com.project.elearning.databinding.ActivityLeaningWordsBinding;
import com.project.elearning.domains.Word;
import com.project.elearning.fragments.VocabularyFragment;

import java.util.ArrayList;
import java.util.List;

public class LeaningWordsActivity extends AppCompatActivity {

    private List<Word> wordList = new ArrayList<>();
    private WordAdapter adapter;
    private ActivityLeaningWordsBinding wordsBinding;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private TextView textView;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaning_words);
        wordsBinding = DataBindingUtil.setContentView(this, R.layout.activity_leaning_words);

        initView();
        setVariables();



        imageView.setOnClickListener((v) -> {
                onBackPressed();
        });


    }

    private void setVariables() {
        adapter = new WordAdapter(wordList);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("topic"));

        String path = "Vocabulary/" + intent.getStringExtra("topic") +"/words";
        database = FirebaseDatabase.getInstance().getReference(path);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Word word = dataSnapshot.getValue(Word.class);
                    wordList.add(word);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        textView = wordsBinding.textView;
        imageView = wordsBinding.imageView;
        recyclerView = wordsBinding.wordRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}