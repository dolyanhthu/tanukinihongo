package com.project.elearning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.project.elearning.R;
import com.project.elearning.databinding.WordItemLayoutBinding;
import com.project.elearning.domains.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{

    private List<Word> wordList;

    public WordAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.word_item_layout,
                parent,
                false
        );
        return new ViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.layoutBinding.setWord(wordList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        WordItemLayoutBinding layoutBinding;
        public ViewHolder(@NonNull WordItemLayoutBinding layoutBinding) {
            super(layoutBinding.getRoot());
            this.layoutBinding = layoutBinding;
        }
    }
}
