package com.petbook.ido.petbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

import java.util.ArrayList;
import java.util.List;

public class ResultListActivity extends Activity {
    private List<PetItemControl> petsControls;
    ScrollView scrlScrol;
    LinearLayout llLayout;
    LinearLayout llLayoutBtn;
    Boolean blPerarea = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btnSearch = null;
        blPerarea = getIntent().getBooleanExtra("perArea",false);

        this.llLayoutBtn = new LinearLayout(this);
        this.llLayout = new LinearLayout(this);
        this.scrlScrol = new ScrollView(this);
        llLayoutBtn.setOrientation(LinearLayout.VERTICAL);

        if(!blPerarea)
        {
            btnSearch = new Button(this);
            btnSearch.setBackgroundColor(Color.argb(255,19, 7, 58));
            btnSearch.setText("חיפוש");
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(GlobalData.getInstance().getFromWhere().equals("search"))
                    {
                        finish();
                    }
                    else
                    {
                        Intent t = new Intent(getApplicationContext(),AdoptSearchActivity.class);
                        t.putExtra("petType",getIntent().getStringExtra("petType"));
                        startActivity(t);
                    }
                }
            });
            llLayoutBtn.addView(btnSearch);
        }

        scrlScrol.addView(llLayout);
        llLayoutBtn.addView(scrlScrol);
        setContentView(llLayoutBtn);

        if (getIntent().getBooleanExtra("Filter",false)||
            blPerarea){
            SetPetsView(GlobalData.getInstance().getLstChosenPets());
        }
        else {
            // By default - get all pets
            SetPetsView(DbHandler.getInstance(this.getApplicationContext()).GetPetsByTypes(GlobalData.getInstance().getTypeID(getIntent().getStringExtra("petType"))));
        }
    }

    private void SetPetsView(List<Pet> pets){
        petsControls = new ArrayList<PetItemControl>();

        Display display = getWindowManager().getDefaultDisplay();
        boolean zebra = false;

        // Create all the pets controls`
        for(Pet ptPet : pets)
        {
            PetItemControl cont = new PetItemControl(this,ptPet);
            cont.SetSize(display.getWidth(),display.getHeight());
            cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),PetDetailsActivity.class);
                        i.putExtra("petId",1);
                        startActivity(i);
                    }
                }
            );

            cont.requestLayout();
            if(zebra)
            {
                cont.setBackgroundColor(Color.argb(255,20, 115, 20));
                zebra = false;
            }
            else{
                cont.setBackgroundColor(Color.argb(255,0, 60, 0));
                zebra = true;
            }

            this.petsControls.add(cont);
            llLayout.addView(cont);
        }

        this.llLayout.setOrientation(LinearLayout.VERTICAL);
    }
}
