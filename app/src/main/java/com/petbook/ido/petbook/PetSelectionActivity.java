package com.petbook.ido.petbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.petbook.ido.petbook.BL.DataLoader;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class PetSelectionActivity extends ActionBarActivity {
    private TableLayout tblLayout;
    private double btnHegihtPercent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadAnimalTypeList();
        setContentView(tblLayout);
    }

    private void LoadAnimalTypeList() {
        Map<String, String> mpAnimalList = DataLoader.GetAnimalTypeList();
        TableRow row = new TableRow(this);
        int colCount = 0;
        for (String strKey : mpAnimalList.keySet()) {
            row.addView(CreateAnimalTypeButton(mpAnimalList.get(strKey)));

            if(colCount%3==0)
            {
                tblLayout.addView(row);
            }
        }
    }

    private Button CreateAnimalTypeButton(String strText) {
        Display display = getWindowManager().getDefaultDisplay();
        Button btn = new Button(this);
        btn.setText(strText);
        btn.setHeight((int) (display.getHeight() * btnHegihtPercent));

        return btn;
    }

}