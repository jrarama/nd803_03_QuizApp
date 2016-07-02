package com.jprarama.colorquizapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
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
public class SingleAnswerFragment extends BaseQuestionFragment {

    private List<RadioButton> choices;
    private RadioGroup radioGroup;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.fragment_single_answer, container, false);
        setCommonViews(rootView);
        choices = Arrays.asList(
                (RadioButton) rootView.findViewById(R.id.rdChoice1),
                (RadioButton) rootView.findViewById(R.id.rdChoice2),
                (RadioButton) rootView.findViewById(R.id.rdChoice3),
                (RadioButton) rootView.findViewById(R.id.rdChoice4)
        );

        radioGroup = (RadioGroup) rootView.findViewById(R.id.rdGroup);

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
        int selected = radioGroup.getCheckedRadioButtonId();

        if (selected == -1) {
            return false;
        }
        String answer = ((RadioButton) rootView.findViewById(selected)).getText().toString();
        return validAnswers.size() == 1 && answer.equals(validAnswers.get(0));
    }
}
