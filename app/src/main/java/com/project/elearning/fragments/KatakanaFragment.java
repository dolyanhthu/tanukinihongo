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
import com.project.elearning.R;
import com.project.elearning.adapters.AlphabetAdapter;
import com.project.elearning.domains.Alphabet;

import java.util.ArrayList;
import java.util.List;

public class KatakanaFragment extends Fragment {

    private AlphabetAdapter adapter;
    private RecyclerView recyclerView;
    private List<Alphabet> list;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("Alphabet/Katakana");

    public KatakanaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_katakana, container, false);
        initView(view);
        setVariables();
        return view;
    }

    private void initView(View view) {
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.katakanaRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
    }

    private void setVariables() {

        adapter = new AlphabetAdapter(list);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Alphabet alphabet = dataSnapshot.getValue(Alphabet.class);
                    list.add(alphabet);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}