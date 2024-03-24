package com.project.elearning.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.elearning.ItemClickListener;
import com.project.elearning.databinding.HiraganaViewholderBinding;
import com.project.elearning.domains.Alphabet;
import com.project.elearning.R;

import java.io.IOException;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {

    List<Alphabet> list;
    Context context;

    public AlphabetAdapter(List<Alphabet> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HiraganaViewholderBinding viewholderBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.hiragana_viewholder,
                parent,
                false
        );
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiragana_viewholder, parent, false);
        context = parent.getContext();
        return new ViewHolder(viewholderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setAlphabet(list.get(position));

        holder.setItemClickListener((view, position1, isLongClick) -> {
            if (!list.get(position1).getAlphabet().equals("")) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_layout, null);
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;

                PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
                view.post(() -> popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0));

                ImageView sound = popupView.findViewById(R.id.sound);
                ImageView closeBtn = popupView.findViewById(R.id.closeBtn);
                GifImageView imageView = popupView.findViewById(R.id.gifImageView);
                Glide.with(popupView.getContext()).load(list.get(position1).getGif()).into(imageView);

                sound.setOnClickListener(v -> {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    try {
                        mediaPlayer.setDataSource(list.get(position1).getAudio());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                closeBtn.setOnClickListener(v -> popupWindow.dismiss());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView setAlphabet;
        HiraganaViewholderBinding binding;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull HiraganaViewholderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}

