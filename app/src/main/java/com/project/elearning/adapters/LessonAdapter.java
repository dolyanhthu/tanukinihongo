package com.project.elearning.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.elearning.domains.Lesson;
import com.project.elearning.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    List<Lesson> lessonList;

    public LessonAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_item_layout, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);

        holder.lessonNumber.setText(lesson.getLessonNumber() + "");
        holder.lessonName.setText(lesson.getEngTitle());
        holder.lessonTitle.setText(lesson.getJapTitle());
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView lessonNumber, lessonName, lessonTitle;
        ImageView lockBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonNumber = itemView.findViewById(R.id.lessonNumber);
            lessonName = itemView.findViewById(R.id.lessonName);
            lessonTitle = itemView.findViewById(R.id.lessonTitle);
            lockBtn = itemView.findViewById(R.id.lockBtn);
        }
    }
}
