package com.jprarama.colorquizapp.entity;

import com.jprarama.colorquizapp.QuizGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joshua on 1/7/16.
 */
public class ColorCombination {

    public enum CombinationType {
        PRIMARY,
        ADDITIVE,
        SUBTRACTIVE;

        public String getName() {
            return this.name().toLowerCase();
        }
    }

    private CombinationType type;

    private List<MyColor> combination;

    private MyColor output;

    public ColorCombination(CombinationType type, MyColor output, MyColor... combination) {
        this.type = type;
        this.combination = Arrays.asList(combination);
        this.output = output;
    }

    public CombinationType getType() {
        return type;
    }

    public void setType(CombinationType type) {
        this.type = type;
    }

    public List<MyColor> getCombination() {
        return combination;
    }

    public void setCombination(List<MyColor> combination) {
        this.combination = combination;
    }

    public MyColor getOutput() {
        return output;
    }

    public void setOutput(MyColor output) {
        this.output = output;
    }
}
