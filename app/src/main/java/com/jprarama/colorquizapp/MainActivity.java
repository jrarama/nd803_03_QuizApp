package com.jprarama.colorquizapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

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
    private static final int NUM_QUESTIONS = 9;
    private static final String QUESTION_KEY = "question";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.w(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setQuestions();
        } else {
            getFragments(savedInstanceState);
        }

        final Button btnGrade = (Button) findViewById(R.id.btnGrade);
        btnGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grade();
            }
        });

        final Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void getFragments(Bundle bundle) {
        if (bundle != null) {
            fragments = new ArrayList<>();
            FragmentManager manager = getSupportFragmentManager();
            for (int i = 1; i <= NUM_QUESTIONS; i++) {
                fragments.add((BaseQuestionFragment) manager.findFragmentByTag(QUESTION_KEY + i));
            }
        }
    }

    private void grade() {
        int score = 0;
        for (BaseQuestionFragment fragment: fragments) {
            if (fragment.isAnswerCorrect()) {
                score++;
            }
            fragment.setAnswerVisible(true);
        }

        String msg = String.format(getString(R.string.grade_message), score, NUM_QUESTIONS);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private void reset() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (BaseQuestionFragment fragment: fragments) {
            transaction.remove(fragment);
        }
        transaction.commit();

        setQuestions();
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViews);
        scrollView.smoothScrollTo(0, 0);
    }

    private void addFragments(List<Question> questions) {
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

            fragments.add(fragment);

            manager.beginTransaction()
                    .add(R.id.questionContainer, fragment, QUESTION_KEY + i)
                    .commit();
        }

    }

    private void setQuestions() {
        QuizGenerator generator = new QuizGenerator(this);
        questions = generator.randomQuestions();
        addFragments(questions);

        int i = 0;
        for(Question question: questions) {
            BaseQuestionFragment fragment = fragments.get(i);
            fragment.init(i + 1, question);
            i++;
        }
    }
}
