package ar.recicl.reciclar.data;

public class RPItem {

    private int mPictureRes;
    private String mMessage;
    private String mId;

    public RPItem(RecyclingCenter rc, String distance) {
        mPictureRes = rc.getPictureRes();
        mMessage = "" + distance + "km. " + rc.getName();
        mId = rc.getId();
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getId() {
        return mId;
    }
}
