package ar.recicl.reciclar.data;

public class SPItem {

    private int mPictureRes;
    private String mName;
    private String mDescription;
    private int mPrice;
    private int mId;

    public SPItem(ShoppingProduct sp) {
        mPictureRes = sp.getPictureRes();
        mName = sp.getName();
        mDescription = sp.getDescription();
        mPrice = sp.getPrice();
        mId = sp.getId();
    }

    public int getPictureRes() {
        return mPictureRes;
    }
    public String getName() {
        return mName;
    }
    public String getDescription() {
        return mDescription;
    }
    public int getPrice() {return mPrice;}

    public int getId() {
        return mId;
    }
}
