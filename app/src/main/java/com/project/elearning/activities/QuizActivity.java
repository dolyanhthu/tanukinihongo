package com.project.elearning.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.elearning.R;
import com.project.elearning.databinding.ActivityQuizBinding;
import com.project.elearning.databinding.ScoreDialogBinding;
import com.project.elearning.domains.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Question> questionList = new ArrayList<>();
    ActivityQuizBinding quizBinding;
    int currentQuestionIndex = 0;
    String selectedAns = "";
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizBinding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(quizBinding.getRoot());

        quizBinding.btn0.setOnClickListener(this);
        quizBinding.btn1.setOnClickListener(this);
        quizBinding.btn2.setOnClickListener(this);
        quizBinding.btn3.setOnClickListener(this);
        quizBinding.nextBtn.setOnClickListener(this);
        quizBinding.btnClose.setOnClickListener(v -> {
            onBackPressed();
        });

        loadQuestions();
    }

    private void loadQuestions() {
        if (currentQuestionIndex == questionList.size()){
            finishQuiz();
        }
        else {
            quizBinding.questionIndicatorTextview.setText("Question " + (currentQuestionIndex + 1) + "/" + questionList.size());
            quizBinding.questionProgressIndicator.setProgress(
                    (int) (((float) currentQuestionIndex / questionList.size()) * 100)
            );
            quizBinding.questionTextview.setText(questionList.get(currentQuestionIndex).getQuestion());
            quizBinding.btn0.setText(questionList.get(currentQuestionIndex).getOptions().get(0));
            quizBinding.btn1.setText(questionList.get(currentQuestionIndex).getOptions().get(1));
            quizBinding.btn2.setText(questionList.get(currentQuestionIndex).getOptions().get(2));
            quizBinding.btn3.setText(questionList.get(currentQuestionIndex).getOptions().get(3));
        }
    }

    @Override
    public void onClick(View v) {
        quizBinding.btn0.setBackgroundColor(getColor(R.color.gray));
        quizBinding.btn1.setBackgroundColor(getColor(R.color.gray));
        quizBinding.btn2.setBackgroundColor(getColor(R.color.gray));
        quizBinding.btn3.setBackgroundColor(getColor(R.color.gray));

        Button clickedBtn = (Button) v;
        if (clickedBtn.getId() == R.id.next_btn){
            if (selectedAns.equals(questionList.get(currentQuestionIndex).getCorrect())){
                score++;
            }
            currentQuestionIndex++;
            loadQuestions();
        }
        else {
            selectedAns = clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(getColor(R.color.orange));
        }
    }

    private void finishQuiz() {
        int totalQuestions = questionList.size();
        int percentage = (int)(((float) score / totalQuestions) * 100);

        ScoreDialogBinding dialogBinding = ScoreDialogBinding.inflate(getLayoutInflater());
        dialogBinding.scoreProgressIndicator.setProgress(percentage);
        dialogBinding.scoreProgressText.setText(percentage + "%");
        dialogBinding.scoreSubtitle.setText(score + " out of " + totalQuestions + " are correct");
        dialogBinding.finishBtn.setOnClickListener(v -> finish());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogBinding.getRoot())
                .setCancelable(false)
                .show();
    }
}