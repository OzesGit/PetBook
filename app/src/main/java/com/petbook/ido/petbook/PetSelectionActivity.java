package com.petbook.ido.petbook;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.petbook.ido.petbook.BL.DataLoader;

import java.io.InputStream;
import java.util.Map;

public class PetSelectionActivity extends ActionBarActivity {
    private TableLayout tblLayout;
    private ScrollView scrlScroll;
    private double btnHegihtPercent = 0.25;
    private Boolean isAdopt;
    private String userType;
    private int itemsInRow = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scrlScroll = new ScrollView(this);
        tblLayout = new TableLayout(this);
        LoadAnimalTypeList();
        scrlScroll.addView(tblLayout);
        setContentView(scrlScroll);
        isAdopt = getIntent().getBooleanExtra("isAdopt", false);
        strType = getIntent().getStringExtra("Type");
    }

    private void LoadAnimalTypeList() {
        Map<String, String> mpAnimalList = DataLoader.GetAnimalTypeList();
        TableRow row = null;
        int colCount = 0;
        for (String strKey : mpAnimalList.keySet()) {
            if(colCount%3==0)
            {
                row = new TableRow(this);
                tblLayout.addView(row);
            }

            row.addView(CreateAnimalTypeButton(mpAnimalList.get(strKey),strKey));
            colCount++;
        }
    }

    private Button CreateAnimalTypeButton(final String strText, final String strKey){
        Display display = getWindowManager().getDefaultDisplay();
        Button btn = new Button(this);
        btn.setText(strText);
        btn.setHeight((int) (display.getHeight() * btnHegihtPercent));
        btn.setWidth(display.getWidth() / 3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedBtn = (Button)v;

                String strType = clickedBtn.getText().toString();
                Intent intent;

                if (isAdopt)
                {
                    intent = new Intent(getApplicationContext(), ResultListActivity.class);
                    intent.putExtra("petType", strType);
                }
                else
                {
                    intent = new Intent(getApplicationContext(), HandOverPetActivity.class);
                    intent.putExtra("petType", strType);
                    intent.putExtra("petEnum", strKey);
                }

                startActivity(intent);
            }
        });

        try {
            AssetManager asmMng = getAssets();
            InputStream f = asmMng.open(strKey);
            Drawable d = BitmapDrawable.createFromStream(f, strKey);

            btn.setBackground(d);
        }
        catch (Exception ex)
        {

        }
        return (btn);
    }

};