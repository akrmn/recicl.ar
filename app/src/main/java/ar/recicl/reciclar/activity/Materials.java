package ar.recicl.reciclar.activity;

import android.content.Intent;

import ar.recicl.reciclar.R;
import butterknife.OnClick;

public class Materials extends Base {

    public Materials() {
        super(R.layout.activity_materials, R.menu.materials, R.string.title_activity_materials, true);
    }

    @OnClick(R.id.button_dont_know)
    void onClickDontKnow() {
        Intent intent = new Intent(this, Helper.class);
        startActivity(intent);
    }
    @OnClick(R.id.button_glass)
    void onClickGlass() {
        loadRecyclingCenters(RecyclingCenters.TYPE_GLASS);
    }
    @OnClick(R.id.button_plastic)
    void onClickPlastic() {
        loadRecyclingCenters(RecyclingCenters.TYPE_PLASTIC);
    }
    @OnClick(R.id.button_cardboard)
    void onClickCardboard() {
        loadRecyclingCenters(RecyclingCenters.TYPE_CARDBOARD);
    }
    @OnClick(R.id.button_organic_waste)
    void onClickOrganicWaste() {
        loadRecyclingCenters(RecyclingCenters.TYPE_ORGANIC);
    }

    void loadRecyclingCenters(int type) {
        Intent intent = new Intent(this, RecyclingCenters.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @OnClick(R.id.button_info_dont_know)
    void onClickDontKnowInfo() {
        showAlert(R.string.label_help_me, R.string.content_dont_know);
    }
    @OnClick(R.id.button_info_glass)
    void onClickGlassInfo() {
        showAlert(R.string.label_glass, R.string.content_glass);
    }
    @OnClick(R.id.button_info_plastic)
    void onClickPlasticInfo() {
        showAlert(R.string.label_plastic, R.string.content_plastic);
    }
    @OnClick(R.id.button_info_cardboard)
    void onClickCardboardInfo() {
        showAlert(R.string.label_cardboard, R.string.content_cardboard);
    }
    @OnClick(R.id.button_info_organic_waste)
    void onClickOrganicWasteInfo() {
        showAlert(R.string.label_organic_waste, R.string.content_organic_waste);
    }
}
