package com.jprarama.colorquizapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jprarama.colorquizapp.R;
import com.jprarama.colorquizapp.entity.MyColor;
import com.jprarama.colorquizapp.entity.Question;

import java.util.List;

/**
 * Created by joshua on 2/7/16.
 */
public class BaseQuestionFragment extends Fragment {

    private static final String TAG = BaseQuestionFragment.class.getName();
    protected TextView tvQuestionNumber;
    protected TextView tvQuestion;
    protected TextView tvCorrectAnswers;
    protected Question question;
    protected int questionNumber;
    protected List<String> validAnswers;

    protected String QUESTION_KEY = "question";
    protected String NUMBER_KEY = "number";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(QUESTION_KEY, question);
        outState.putInt(NUMBER_KEY, questionNumber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            question = savedInstanceState.getParcelable(QUESTION_KEY);
            questionNumber = savedInstanceState.getInt(NUMBER_KEY);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setCommonViews(View view) {
        Log.w(TAG, "setCommonViews");

        tvQuestionNumber = (TextView) view.findViewById(R.id.tvQuestionNumber);
        tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
        tvCorrectAnswers= (TextView) view.findViewById(R.id.tvCorrectAnswer);
    }

    public void init(int number, Question question) {
        this.questionNumber = number;
        this.question = question;
    }

    public void setQuestion() {
        if (question == null) {
            return;
        }
        this.validAnswers = MyColor.toStringList(question.getAnswers());

        tvQuestionNumber.setText(String.valueOf(questionNumber));
        tvQuestion.setText(question.getDescription());
        tvCorrectAnswers.setText(MyColor.listToString(question.getAnswers()));
    }

    public boolean isAnswerCorrect() {
        return false;
    }
}
