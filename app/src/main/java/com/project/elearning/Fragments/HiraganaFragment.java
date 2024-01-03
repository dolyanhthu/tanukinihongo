package com.project.elearning.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.elearning.Adapters.AlphabetAdapter;
import com.project.elearning.Domains.Alphabet;
import com.project.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class HiraganaFragment extends Fragment {

    private AlphabetAdapter adapter;
    private RecyclerView recyclerView;
    private List<Alphabet> list;

    public HiraganaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hiragana, container, false);

        initView(view);
        setVariables();
        return view;
    }

    private void initView(View view) {
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.alphabetRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setVariables() {
        Alphabet hiragana1 = new Alphabet(1, "あ い う え お");
        Alphabet hiragana2 = new Alphabet(2, "か き く け こ");
        Alphabet hiragana3 = new Alphabet(3, "さ し す せ そ");
        Alphabet hiragana4 = new Alphabet(4, "た ち つ て と");
        Alphabet hiragana5 = new Alphabet(5, "な に ぬ ね の");
        Alphabet hiragana6 = new Alphabet(6, "は ひ ふ へ ほ");
        Alphabet hiragana7 = new Alphabet(7, "ま み む め も");
        Alphabet hiragana8 = new Alphabet(8, "ら り る れ ろ");
        Alphabet hiragana9 = new Alphabet(9, "や ゆ よ わ ゐ を ん");
        Alphabet hiragana10 = new Alphabet(10, "が ぎ ぐ げ ご");
        Alphabet hiragana11 = new Alphabet(11, "ざ じ ず ぜ ぞ");
        Alphabet hiragana12 = new Alphabet(12, "だ ぢ づ で ど");
        Alphabet hiragana13 = new Alphabet(13, "ば び ぶ べ ぼ");
        Alphabet hiragana14 = new Alphabet(14, "ぱ ぴ ぷ ぺ ぽ");

        list.add(hiragana1);
        list.add(hiragana2);
        list.add(hiragana3);
        list.add(hiragana4);
        list.add(hiragana5);
        list.add(hiragana6);
        list.add(hiragana7);
        list.add(hiragana8);
        list.add(hiragana9);
        list.add(hiragana10);
        list.add(hiragana11);
        list.add(hiragana12);
        list.add(hiragana13);
        list.add(hiragana14);

        adapter = new AlphabetAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}