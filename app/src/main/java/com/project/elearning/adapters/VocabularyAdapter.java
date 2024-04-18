package com.project.elearning.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.elearning.ItemClickListener;
import com.project.elearning.domains.Vocabulary;
import com.project.elearning.R;
import com.project.elearning.databinding.VocabularyItemLayoutBinding;
import androidx.databinding.DataBindingUtil;
import com.project.elearning.activities.LeaningWordsActivity;

import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private List<Vocabulary> vocabularyList;
    private Context context;

    public VocabularyAdapter(List<Vocabulary> topics) {
        this.vocabularyList = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VocabularyItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.vocabulary_item_layout,
                parent,
                false
        );
        context = parent.getContext();
        return new ViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.layoutBinding.setVocabulary(vocabularyList.get(position));
        Glide.with(context).load(vocabularyList.get(position).getImageUrl()).into(holder.layoutBinding.topicImage);
        holder.setItemClickListener((view, position1) -> {
            Intent intent = new Intent(context, LeaningWordsActivity.class);
            intent.putExtra("topic",vocabularyList.get(position1).getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        VocabularyItemLayoutBinding layoutBinding;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull VocabularyItemLayoutBinding layoutBinding) {
            super(layoutBinding.getRoot());
            this.layoutBinding = layoutBinding;
            layoutBinding.getRoot().setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }

    }
}
