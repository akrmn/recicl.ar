package ar.recicl.reciclar.data;

import java.util.ArrayList;

import ar.recicl.reciclar.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Question {
    private int mStatementResId;
    private HashMap<Character, Integer> mValues;

    public static LinkedList<Question> getQuestionnaire() {
        LinkedList<Question> questionnaire = new LinkedList<>();

        HashMap<Character, Integer> values_bad_smell = new HashMap<>();
        values_bad_smell.put('g', -1);
        values_bad_smell.put('p', -1);
        values_bad_smell.put('c',  0);
        values_bad_smell.put('o', +1);
        questionnaire.add(new Question(R.string.q_bad_smell, values_bad_smell ));

        HashMap<Character, Integer> values_flies = new HashMap<>();
        values_flies.put('g', -1);
        values_flies.put('p', -1);
        values_flies.put('c', -1);
        values_flies.put('o', +1);
        questionnaire.add(new Question(R.string.q_flies, values_flies ));

        HashMap<Character, Integer> values_transparent = new HashMap<>();
        values_transparent.put('g', +2);
        values_transparent.put('p', +1);
        values_transparent.put('c', -1);
        values_transparent.put('o', -1);
        questionnaire.add(new Question(R.string.q_transparent, values_transparent));

        HashMap<Character, Integer> values_dense = new HashMap<>();
        values_dense.put('g', +1);
        values_dense.put('p', -1);
        values_dense.put('c', -1);
        values_dense.put('o',  0);
        questionnaire.add(new Question(R.string.q_dense, values_dense ));

        HashMap<Character, Integer> values_fragile = new HashMap<>();
        values_fragile.put('g', +1);
        values_fragile.put('p', -1);
        values_fragile.put('c', -1);
        values_fragile.put('o', -1);
        questionnaire.add(new Question(R.string.q_fragile, values_fragile ));

        HashMap<Character, Integer> values_malleable = new HashMap<>();
        values_malleable.put('g', -1);
        values_malleable.put('p', +1);
        values_malleable.put('c', +1);
        values_malleable.put('o',  0);
        questionnaire.add(new Question(R.string.q_malleable, values_malleable ));

        HashMap<Character, Integer> values_brown_color = new HashMap<>();
        values_brown_color.put('g', -1);
        values_brown_color.put('p', -1);
        values_brown_color.put('c', +2);
        values_brown_color.put('o', +1);
        questionnaire.add(new Question(R.string.q_brown_color, values_brown_color));

        HashMap<Character, Integer> values_has_cap = new HashMap<>();
        values_has_cap.put('g', +1);
        values_has_cap.put('p', +1);
        values_has_cap.put('c', -1);
        values_has_cap.put('o', -1);
        questionnaire.add(new Question(R.string.q_has_cap, values_has_cap ));

        HashMap<Character, Integer> values_serve_food = new HashMap<>();
        values_serve_food.put('g', +1);
        values_serve_food.put('p', +1);
        values_serve_food.put('c', +1);
        values_serve_food.put('o', -1);
        questionnaire.add(new Question(R.string.q_serve_food, values_serve_food ));

        HashMap<Character, Integer> values_bad_taste = new HashMap<>();
        values_bad_taste.put('g', -1);
        values_bad_taste.put('p', -1);
        values_bad_taste.put('c', +1);
        values_bad_taste.put('o', +1);
        questionnaire.add(new Question(R.string.q_bad_taste, values_bad_taste ));

        Collections.shuffle(questionnaire);

        return questionnaire;
    }

    public Question(int statementResId, HashMap<Character, Integer> values) {
        mStatementResId = statementResId;
        mValues = values;
    }

    public int getStatementResId() {
        return mStatementResId;
    }

    public HashMap<Character, Integer> getValues() {
        return mValues;
    }
}
