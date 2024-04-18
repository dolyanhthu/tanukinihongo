package com.project.elearning.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.project.elearning.ItemClickListener;
import com.project.elearning.activities.LessonActivity;
import com.project.elearning.databinding.LessonItemLayoutBinding;
import com.project.elearning.domains.Lesson;
import com.project.elearning.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    List<Lesson> lessonList;
    Context context;

    public LessonAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LessonItemLayoutBinding layoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.lesson_item_layout,
                parent,
                false
        );
        this.context = parent.getContext();
        return new ViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);

        holder.layoutBinding.setLesson(lesson);
        holder.setItemClickListener((view, position1) -> {
            LessonActivity.lesson = lesson;
            context.startActivity(new Intent(context, LessonActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LessonItemLayoutBinding layoutBinding;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull LessonItemLayoutBinding layoutBinding) {
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
