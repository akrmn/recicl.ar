package ar.recicl.reciclar.activity;

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

    }
    @OnClick(R.id.button_plastic)
    void onClickPlastic() {

    }
    @OnClick(R.id.button_cardboard)
    void onClickCardboard() {

    }
    @OnClick(R.id.button_organic_waste)
    void onClickOrganicWaste() {

    }

    @OnClick(R.id.button_info_glass)
    void onClickGlassInfo() {

    }
    @OnClick(R.id.button_info_plastic)
    void onClickPlasticInfo() {

    }
    @OnClick(R.id.button_info_cardboard)
    void onClickCardboardInfo() {

    }
    @OnClick(R.id.button_info_organic_waste)
    void onClickOrganicWasteInfo() {

    }
}
