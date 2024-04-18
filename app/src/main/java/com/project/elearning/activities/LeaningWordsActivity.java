package com.project.elearning.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.elearning.CardFlipActivity;
import com.project.elearning.NetworkChangeReceiver;
import com.project.elearning.R;
import com.project.elearning.adapters.WordAdapter;
import com.project.elearning.databinding.ActivityLeaningWordsBinding;
import com.project.elearning.domains.Question;
import com.project.elearning.domains.Word;
import com.project.elearning.fragments.VocabularyFragment;

import java.util.ArrayList;
import java.util.List;

public class LeaningWordsActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkChangeListener{

    private List<Word> wordList = new ArrayList<>();
    private WordAdapter adapter;
    private ActivityLeaningWordsBinding wordsBinding;
    private ImageView imageView;
    private Button testBtn;
    private Button learnBtn;
    private RecyclerView recyclerView;
    private TextView textView;
    private DatabaseReference database;
    private String path = "";

    Dialog dialog;
    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordsBinding = ActivityLeaningWordsBinding.inflate(getLayoutInflater());
        setContentView(wordsBinding.getRoot());

        initView();
        setVariables();

    }

    private void setVariables() {
        adapter = new WordAdapter(wordList);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("topic"));

        path = "Vocabulary/" + intent.getStringExtra("topic") +"/words";
        database = FirebaseDatabase.getInstance().getReference(path);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Word word = dataSnapshot.getValue(Word.class);
                    wordList.add(word);
                    CardFlipActivity.frontList.add(word.getWord());
                    CardFlipActivity.backList.add(word.getMeaning());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageView.setOnClickListener((v) -> {
            onBackPressed();
        });

        testBtn.setOnClickListener(v -> {
            List<Question> questionList = new ArrayList<>();
            path = "Vocabulary/" + intent.getStringExtra("topic") + "/test/questionList";
            database = FirebaseDatabase.getInstance().getReference(path);
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        Question question = dataSnapshot.getValue(Question.class);
                        questionList.add(question);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            QuizActivity.questionList = questionList;
            startActivity(new Intent(this, QuizActivity.class));
        });

        learnBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, CardFlipActivity.class));
        });
    }

    private void initView() {
        textView = wordsBinding.textView;
        imageView = wordsBinding.imageView;
        recyclerView = wordsBinding.wordRecyclerView;
        testBtn = wordsBinding.testBtn;
        learnBtn = wordsBinding.learnBtn;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (isConnected) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } else {
            if (dialog == null || !dialog.isShowing()) {
                ShowDialog();
            }
        }
    }

    private void ShowDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_no_internet);
        dialog.setCancelable(false);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.transparent));
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeReceiver);
    }
}