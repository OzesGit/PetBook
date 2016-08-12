package com.petbook.ido.petbook;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.petbook.ido.petbook.BL.DataLoader;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class PetSelectionActivity extends ActionBarActivity {
    private LinearLayout llLayout;
    private double btnHegihtPercent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        llLayout = new LinearLayout(this);
        LoadAnimalTypeList();
        llLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(llLayout);

        Intent intent = this.getIntent();
        String strType = intent.getStringExtra("Type");
        Boolean isAdopt = intent.getBooleanExtra("isAdopt", false);

//        setContentView(R.layout.activity_pet_selection);
    }

    private void LoadAnimalTypeList() {
        Map<String,String> mpAnimalList = DataLoader.GetAnimalTypeList();

        for (String strKey : mpAnimalList .keySet()) {
            CreateAnimalTypeButton(mpAnimalList .get(strKey));
        }
    }

    private void CreateAnimalTypeButton(String strText){
        Display display = getWindowManager().getDefaultDisplay();
        Button btn = new Button(this);
        btn.setText(strText);
        btn.setHeight((int)(display.getHeight() * btnHegihtPercent));

        llLayout.addView(btn);
    }

}
