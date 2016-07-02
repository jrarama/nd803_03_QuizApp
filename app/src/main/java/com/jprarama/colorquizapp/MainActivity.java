package com.jprarama.colorquizapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jprarama.colorquizapp.entity.Question;
import com.jprarama.colorquizapp.fragment.BaseQuestionFragment;
import com.jprarama.colorquizapp.fragment.IdentificationFragment;
import com.jprarama.colorquizapp.fragment.MultipleAnswersFragment;
import com.jprarama.colorquizapp.fragment.SingleAnswerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private ArrayList<Question> questions;
    private List<BaseQuestionFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setQuestions();
        }
    }

    private void setQuestions() {
        QuizGenerator generator = new QuizGenerator(this);
        questions = generator.randomQuestions();
        fragments = new ArrayList<>();

        int i = 0;
        FragmentManager manager = getSupportFragmentManager();
        for(Question question: questions) {
            ++i;
            BaseQuestionFragment fragment;

            switch (question.getQuestionType()) {
                case SINGLE_ANSWER:
                    fragment = new SingleAnswerFragment();
                    break;
                case IDENTIFICATION:
                    fragment = new IdentificationFragment();
                    break;
                default:
                    fragment = new MultipleAnswersFragment();
                    break;
            }

            fragment.init(i, question);
            fragments.add(fragment);

            manager.beginTransaction()
                .add(R.id.questionContainer, fragment, "question" + i)
                .commit();
        }
    }
}
