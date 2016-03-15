package ar.recicl.reciclar.data;

public class FeedItem {

    private int mPictureRes;
    private String mMessage;
    private boolean mChecked;
    private String mName;
    private String mEmail;

    public FeedItem(Person person, String base) {
        mPictureRes = person.getPictureRes();
        mName = person.getName();
        mMessage = String.format(base, person.getName());
        mChecked = false;
        mEmail = person.getEmail();
    }

    public String getName() {
        return mName;
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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
