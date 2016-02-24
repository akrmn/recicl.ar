package ar.recicl.reciclar.data;

public class RPItem {

    private int mPictureRes;
    private String mMessage;

    public RPItem(Person person, String base) {
        mPictureRes = person.getPictureRes();
        mMessage = String.format(base, person.getName());
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public String getMessage() {
        return mMessage;
    }
}
