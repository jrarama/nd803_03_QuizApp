package com.jprarama.colorquizapp;

import android.content.Context;

import com.jprarama.colorquizapp.entity.ColorCombination;
import com.jprarama.colorquizapp.entity.MyColor;
import com.jprarama.colorquizapp.entity.Question;
import com.jprarama.colorquizapp.entity.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jprarama.colorquizapp.entity.ColorCombination.CombinationType.ADDITIVE;
import static com.jprarama.colorquizapp.entity.ColorCombination.CombinationType.PRIMARY;
import static com.jprarama.colorquizapp.entity.ColorCombination.CombinationType.SUBTRACTIVE;

/**
 * Created by joshua on 1/7/16.
 */
public class QuizGenerator {

    private static final List<ColorCombination> COMBINATION_LIST = Arrays.asList(
            // Primary Colors
            new ColorCombination(PRIMARY, MyColor.ORANGE, MyColor.RED, MyColor.YELLOW),
            new ColorCombination(PRIMARY, MyColor.GREEN, MyColor.BLUE, MyColor.YELLOW),
            new ColorCombination(PRIMARY, MyColor.VIOLET, MyColor.RED, MyColor.BLUE),
            // Additive Colors
            new ColorCombination(ADDITIVE, MyColor.MAGENTA, MyColor.RED, MyColor.BLUE),
            new ColorCombination(ADDITIVE, MyColor.CYAN, MyColor.BLUE, MyColor.GREEN),
            new ColorCombination(ADDITIVE, MyColor.YELLOW, MyColor.RED, MyColor.GREEN),
            // Subtractive Colors
            new ColorCombination(SUBTRACTIVE, MyColor.GREEN, MyColor.CYAN, MyColor.YELLOW),
            new ColorCombination(SUBTRACTIVE, MyColor.RED, MyColor.BLUE, MyColor.GREEN),
            new ColorCombination(SUBTRACTIVE, MyColor.YELLOW, MyColor.RED, MyColor.GREEN)
    );

    private static final QuestionType[] QUESTION_TYPES = QuestionType.values();
    private static final int NUM_CHOICES = 4;

    private Context context;

    public QuizGenerator(Context context) {
        this.context = context;
    }

    private String getQuestionFormat(QuestionType type) {
        if (type == QuestionType.IDENTIFICATION || type == QuestionType.SINGLE_ANSWER) {
            return context.getString(R.string.question_format_single);
        }
        return context.getString(R.string.question_format_multiple);
    }

    private Question createQuestion(QuestionType type, ColorCombination combination) {
        Question question = new Question(type);

        String format = getQuestionFormat(type);
        List<MyColor> colors = combination.getCombination();

        if (type == QuestionType.IDENTIFICATION || type == QuestionType.SINGLE_ANSWER) {
            question.setDescription(String.format(format, combination.getType().getName(),
                    colors.get(0), colors.get(1)));
            question.setAnswers(Arrays.asList(combination.getOutput()));
        } else {
            question.setDescription(String.format(format, combination.getType().getName(),
                    combination.getOutput()));
            question.setAnswers(combination.getCombination());
        }

        question.setChoices(createChoices(question.getAnswers()));
        return question;
    }

    private List<MyColor> createChoices(List<MyColor> answers) {
        List<MyColor> allChoices = new ArrayList<>(Arrays.asList(MyColor.values()));
        List<MyColor> choices = new ArrayList<>(answers);

        allChoices.removeAll(choices);
        Collections.shuffle(allChoices);

        while (choices.size() < NUM_CHOICES) {
            choices.add(allChoices.remove(0));
        }

        Collections.shuffle(choices);
        return choices;
    }

    public ArrayList<Question> randomQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        List<ColorCombination> shuffled = new ArrayList<>(COMBINATION_LIST);
        Collections.shuffle(shuffled);

        int i = 0;
        final int len = QUESTION_TYPES.length;
        for (ColorCombination combination: shuffled) {
            QuestionType type = QUESTION_TYPES[i % len];
            Question question = createQuestion(type, combination);
            questions.add(question);
            ++i;
        }

        Collections.shuffle(questions);
        return questions;
    }

}
