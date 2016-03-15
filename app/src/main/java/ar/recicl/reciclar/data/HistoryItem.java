package ar.recicl.reciclar.data;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class HistoryItem {
    private int mPoints;
    private int mMessageResId;

    public static int[] messageResArray = {
            R.string.history_item_message_1,
            R.string.history_item_message_2,

            R.string.history_item_message_10,
            R.string.history_item_message_11,
            R.string.history_item_message_12
    };

    public HistoryItem () {
        mMessageResId = messageResArray[Application.sRandom.nextInt(messageResArray.length)];
        mPoints = (Application.sRandom.nextInt(30) + 1) * 5;
    }

    public int getMessageRes() {
        return mMessageResId;
    }

    public void setMessageRes(int messageRes) {
        mMessageResId = messageRes;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        mPoints = points;
    }
}
