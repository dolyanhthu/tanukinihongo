package com.project.elearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.elearning.databinding.ActivityCardFlipBinding;
import com.project.elearning.databinding.ActivityQuizBinding;
import com.project.elearning.databinding.ScoreDialogBinding;
import com.project.elearning.domains.Question;

import java.util.ArrayList;
import java.util.List;

public class CardFlipActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<String> frontList = new ArrayList<>();
    public static List<String> backList = new ArrayList<>();
    ActivityCardFlipBinding cardFlipBinding;
    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardFlipBinding = ActivityCardFlipBinding.inflate(getLayoutInflater());
        setContentView(cardFlipBinding.getRoot());

        cardFlipBinding.btnNext.setOnClickListener(this);
        cardFlipBinding.btnPrevious.setOnClickListener(this);
        cardFlipBinding.btnBack.setOnClickListener(v -> {
            frontList.clear();
            backList.clear();
            onBackPressed();
        });

        cardFlipBinding.redView.setText(frontList.get(currentIndex));

        cardFlipBinding.cardView.setOnClickListener(v -> {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(cardFlipBinding.cardView, "scaleX", 1f, 0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(cardFlipBinding.cardView, "scaleX", 0f, 1f);

                animator1.setInterpolator(new DecelerateInterpolator());
                animator2.setInterpolator(new AccelerateInterpolator());

                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (cardFlipBinding.redView.getText().equals(frontList.get(currentIndex)))
                            cardFlipBinding.redView.setText(backList.get(currentIndex));
                        else cardFlipBinding.redView.setText(frontList.get(currentIndex));
                        animator2.start();

                    }
                });

                animator1.start();
            }
        );
    }

    private void loadQuestions() {
        if (currentIndex == frontList.size()){
            finishQuiz();
        }
        else{
            cardFlipBinding.redView.setText(frontList.get(currentIndex));
        }
    }

    @Override
    public void onClick(View v) {
        ImageView clickedBtn = (ImageView) v;
        if (clickedBtn.getId() == R.id.btnNext){
            if (currentIndex == frontList.size());
            else {
                currentIndex++;
                loadQuestions();
            }
        }
        else if (clickedBtn.getId() == R.id.btnPrevious){
            if (currentIndex == 0);
            else {
                currentIndex--;
                loadQuestions();
            }
        }
    }

    private void finishQuiz() {
    }

    private void firstTransition() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void secondTransition() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}