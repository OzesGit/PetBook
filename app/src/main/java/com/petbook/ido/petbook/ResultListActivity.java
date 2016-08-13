package com.petbook.ido.petbook;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

import java.util.ArrayList;
import java.util.List;

public class ResultListActivity extends ActionBarActivity {
    private List<PetItemControl> petsControls;
    ScrollView scrlScrol;
    LinearLayout llLayout;
    LinearLayout llLayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btnSearch = new Button(this);

        this.llLayoutBtn = new LinearLayout(this);
        this.llLayout = new LinearLayout(this);
        this.scrlScrol = new ScrollView(this);
        llLayoutBtn.setOrientation(LinearLayout.VERTICAL);
        
        btnSearch.setText("חיפוש");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getApplicationContext(),AdoptSearchActivity.class);
                startActivity(t);
            }
        });
        scrlScrol.addView(llLayout);
        llLayoutBtn.addView(btnSearch);
        llLayoutBtn.addView(scrlScrol);
        setContentView(llLayoutBtn);
        // By default - get all pets
        SetPetsView(DbHandler.getInstance(this.getApplicationContext()).GetPetsByTypes(GlobalData.getInstance().getTypeID(getIntent().getStringExtra("Type"))));


    }

    private void SetPetsView(List<Pet> pets){
        //List<Pet> pets = GlobalData.getInstance().getLstChosenPets();
        petsControls = new ArrayList<PetItemControl>();

        Display display = getWindowManager().getDefaultDisplay();
        boolean zebra = false;

        // Create all the pets controls`
        for(Pet ptPet : pets)
        {
            PetItemControl cont = new PetItemControl(this,ptPet);
            cont.SetSize(display.getWidth(),display.getHeight());
            cont.requestLayout();

            if(zebra)
            {
                cont.setBackgroundColor(Color.LTGRAY);
                zebra = false;
            }
            else{
                cont.setBackgroundColor(Color.DKGRAY);
                zebra = true;
            }

            this.petsControls.add(cont);
            llLayout.addView(cont);
        }

        this.llLayout.setOrientation(LinearLayout.VERTICAL);
    }
}
