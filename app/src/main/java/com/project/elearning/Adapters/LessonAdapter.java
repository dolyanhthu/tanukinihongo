package com.project.elearning.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.elearning.Domains.Lesson;
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
                .inflate(R.layout.lesson_viewholder, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);

        holder.lessonNumber.setText(lesson.getLessonNumber() + "");
        holder.lessonName.setText(lesson.getLessonName());
        holder.lessonTitle.setText(lesson.getLessonTitle());
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
