package com.jprarama.colorquizapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jprarama.colorquizapp.R;

/**
 * Created by joshua on 2/7/16.
 */
public class IdentificationFragment extends BaseQuestionFragment {

    private EditText txtAnswer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_identification, container, false);
        setCommonViews(rootView);
        txtAnswer = (EditText) rootView.findViewById(R.id.etAnswer);

        setQuestion();
        return rootView;
    }

    @Override
    public boolean isAnswerCorrect() {
        String answer = String.valueOf(txtAnswer.getText());
        return answer.equalsIgnoreCase(question.getAnswers().get(0).toString());
    }
}
