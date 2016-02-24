package ar.recicl.reciclar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ar.recicl.reciclar.R;
import butterknife.OnClick;

public class Materials extends Base {



    public Materials() {
        super(R.layout.activity_materials, R.menu.materials, R.string.title_activity_materials, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_glass)
    void onClickGlass() {
        loadRecyclingPoints(RecyclingPoints.TYPE_GLASS);
    }
    @OnClick(R.id.button_plastic)
    void onClickPlastic() {
        loadRecyclingPoints(RecyclingPoints.TYPE_PLASTIC);
    }
    @OnClick(R.id.button_cardboard)
    void onClickCardboard() {
        loadRecyclingPoints(RecyclingPoints.TYPE_CARDBOARD);
    }
    @OnClick(R.id.button_organic_waste)
    void onClickOrganicWaste() {
        loadRecyclingPoints(RecyclingPoints.TYPE_ORGANIC);
    }

    void loadRecyclingPoints(int type) {
        Intent intent = new Intent(this, RecyclingPoints.class);
        intent.putExtra("type", type);
        startActivity(intent);
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
