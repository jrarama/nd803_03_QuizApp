package com.jprarama.colorquizapp.entity;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshua on 1/7/16.
 */
public enum  MyColor {
    RED,
    BLUE,
    GREEN,
    CYAN,
    YELLOW,
    MAGENTA,
    ORANGE,
    VIOLET;

    public String getName() {
        return this.name().toLowerCase();
    }

    public static List<String> toStringList(List<MyColor> colors) {
        List<String> items = new ArrayList<>(colors.size());
        for (MyColor color: colors) {
            items.add(color.toString());
        }
        return items;
    }

    public static String listToString(List<MyColor> colors) {
        return TextUtils.join(", ", toStringList(colors));
    }

    public static List<MyColor> fromStringList(List<String> items) {
        List<MyColor> colors = new ArrayList<>(items.size());
        for (String item: items) {
            colors.add(MyColor.valueOf(item));
        }
        return colors;
    }
}
