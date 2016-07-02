package com.jprarama.colorquizapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jprarama.colorquizapp.R;
import com.jprarama.colorquizapp.entity.MyColor;
import com.jprarama.colorquizapp.entity.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joshua on 2/7/16.
 */
public class MultipleAnswersFragment extends BaseQuestionFragment {

    private List<CheckBox> choices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_multiple_answer, container, false);
        setCommonViews(rootView);
        choices = Arrays.asList(
                (CheckBox) rootView.findViewById(R.id.chkChoice1),
                (CheckBox) rootView.findViewById(R.id.chkChoice2),
                (CheckBox) rootView.findViewById(R.id.chkChoice3),
                (CheckBox) rootView.findViewById(R.id.chkChoice4)
        );
        setQuestion();
        return rootView;
    }

    @Override
    public void setQuestion() {
        if (question == null) {
            return;
        }
        super.setQuestion();

        int i = 0;
        for (MyColor color: question.getChoices()) {
            choices.get(i++).setText(color.toString());
        }
    }

    @Override
    public boolean isAnswerCorrect() {
        List<String> answers = new ArrayList<>();
        for (CheckBox checkBox: choices) {
            if (checkBox.isChecked()) {
                String selected = checkBox.getText().toString();
                answers.add(selected);
                if (!validAnswers.contains(selected)) {
                    return false;
                }
            }
        }
        return answers.size() == validAnswers.size();
    }
}
