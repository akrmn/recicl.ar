package ar.recicl.reciclar.data;

public class FeedItem {

    private int mPictureRes;
    private String mMessage;
    private boolean mChecked;

    public FeedItem(Person person, String base) {
        mPictureRes = person.getPictureRes();
        mMessage = String.format(base, person.getName());
        mChecked = false;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean toggleChecked() {
        return (mChecked ^= true);
    }

    public boolean isChecked() {
        return mChecked;
    }
}
