package com.jprarama.colorquizapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshua on 2/7/16.
 */
public class Question implements Parcelable {

    private QuestionType questionType;

    private String description;

    private List<MyColor> answers;

    private List<MyColor> choices;

    public Question(QuestionType questionType) {
        this.questionType = questionType;
    }

    protected Question(Parcel in) {
        questionType = QuestionType.valueOf(in.readString());
        description = in.readString();
        List<String> strAnswers = new ArrayList<>();
        List<String> strChoices = new ArrayList<>();
        in.readStringList(strAnswers);
        in.readStringList(strChoices);

        answers = MyColor.fromStringList(strAnswers);
        choices = MyColor.fromStringList(strChoices);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MyColor> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MyColor> answers) {
        this.answers = answers;
    }

    public List<MyColor> getChoices() {
        return choices;
    }

    public void setChoices(List<MyColor> choices) {
        this.choices = choices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionType.toString());
        parcel.writeString(description);
        parcel.writeStringList(MyColor.toStringList(answers));
        parcel.writeStringList(MyColor.toStringList(choices));
    }
}
