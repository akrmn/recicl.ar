package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.Question;
import butterknife.Bind;
import butterknife.OnClick;

public class Helper extends Base {
    private static final String TAG = Helper.class.getSimpleName();
    private static final int MAX_QUESTIONS = 7;

    LinkedList<Question> mQuestionnaire;
    LinkedList<Question> mAnswered;
    LinkedList<HashMap<Character, Integer>> mStates;

    int mTotalAnswered;

    String mTitleString;

    Question mCurrentQuestion;

    @Bind(R.id.label_question_statement) TextView mQuestionStatementTextView;

    public Helper() {
        super(R.layout.activity_helper, R.menu.helper, R.string.title_activity_helper, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleString = getResources().getString(R.string.title_activity_helper_question);

        mQuestionnaire = Question.getQuestionnaire();
        mAnswered = new LinkedList<>();
        mStates = new LinkedList<>();

        prepareNextQuestion();
    }

    @Override
    public void onBackPressed() {
        if (mStates.isEmpty())
            super.onBackPressed();
        else
            restorePreviousQuestion();
    }

    private void updateTitle() {
        setTitle(String.format(mTitleString, mStates.size() + 1));
    }

    private void restorePreviousQuestion() {
        mStates.pop();
        mQuestionnaire.push(mCurrentQuestion);
        mCurrentQuestion = mAnswered.pop();
        mQuestionStatementTextView.setText(mCurrentQuestion.getStatementResId());

        updateTitle();
    }

    private void prepareNextQuestion() {
        if (mCurrentQuestion != null)
            mAnswered.push(mCurrentQuestion);
        mCurrentQuestion = mQuestionnaire.pop();
        mQuestionStatementTextView.setText(mCurrentQuestion.getStatementResId());

        updateTitle();
    }

    private void evalAnswer(int modifier) {
        if (mCurrentQuestion == null) return;

        HashMap<Character, Integer> oldState;
        if (mStates.isEmpty()) {
            oldState = new HashMap<>();
            for (Character c : "gpco".toCharArray())
                oldState.put(c, 0);
        }
        else
            oldState = mStates.peek();

        HashMap<Character, Integer> newState = new HashMap<>();
        for (Character c : "gpco".toCharArray()) {
            newState.put(c, oldState.get(c) + modifier * mCurrentQuestion.getValues().get(c));
        }

        mStates.push(newState);

        if (mStates.size() == MAX_QUESTIONS) {
            presentResults();
        } else {
            prepareNextQuestion();
        }
    }

    private void presentResults() {
        // THIS FUNCTION IS FUCKING AWFUL AND I KNOW IT
        class Item {
            int value;
            char letter;
        }

        Comparator<Item> comp_ = new Comparator<Item>(){
            @Override
            public int compare(Item lhs, Item rhs) {
                if (lhs.value > rhs.value) {
                    return -1;
                } else if (lhs.value < rhs.value) {
                    return +1;
                } else {
                    if (lhs.letter > rhs.letter) {
                        return -1;
                    } else if (lhs.letter < rhs.letter) {
                        return +1;
                    } else {
                        return 0;
                    }
                }
            }
        };

        ArrayList<Item> results = new ArrayList<>();
        for (Character c : "gpco".toCharArray()) {
            Item result = new Item();
            result.letter = c;
            result.value = mStates.peek().get(c);
            results.add(result);
        }

        Collections.sort(results, comp_);

        if (results.get(0).value == 0 || results.get(0).value == results.get(1).value) {
            Intent intent = new Intent(this, HelperFailure.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, HelperSuccess.class);
            intent.putExtra("type", results.get(0).letter);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.button_yes)
    void onButtonYesClick() {
        evalAnswer(+1);
    }

    @OnClick(R.id.button_no)
    void onButtonNoClick() {
        evalAnswer(-1);
    }

    @OnClick(R.id.button_dont_know)
    void onButtonDontKnowClick() {
        evalAnswer(0);
    }
}
