package com.petbook.ido.petbook;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.petbook.ido.petbook.BL.DataLoader;

import java.util.Map;

public class PetSelectionActivity extends ActionBarActivity {
    private ScrollView scrlScroll;
    private LinearLayout llLayout;
    private double btnHegihtPercent = 0.15;
    private OnClickListener lsnrButtonOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), AdoptSearchActivity.class);
            intent.putExtra("Name", "Dog");

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        llLayout = new LinearLayout(this);
        scrlScroll = new ScrollView(this);

        LoadAnimalTypeList();
        llLayout.setOrientation(LinearLayout.VERTICAL);

        Intent intent = this.getIntent();
        String strType = intent.getStringExtra("Type");
        Boolean isAdopt = intent.getBooleanExtra("isAdopt", false);
        scrlScroll.addView(llLayout);

        setContentView(scrlScroll);
    }

    private void LoadAnimalTypeList() {
        Map<String,String> mpAnimalList = DataLoader.GetAnimalTypeList();

        for (String strKey : mpAnimalList.keySet()) {
            CreateAnimalTypeButton(mpAnimalList .get(strKey),strKey);
        }
    }

    private void CreateAnimalTypeButton(String strText,String strKey){
        Display display = getWindowManager().getDefaultDisplay();
        Button btn = new Button(this);
        btn.setOnClickListener(lsnrButtonOnClick);
        btn.setText(strText);
        btn.setHeight((int) (display.getHeight() * btnHegihtPercent));
        btn.setWidth(display.getWidth());
        llLayout.addView(btn);
    }
}
