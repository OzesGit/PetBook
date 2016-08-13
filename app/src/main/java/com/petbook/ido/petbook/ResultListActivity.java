package com.petbook.ido.petbook;

import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // By default - get all pets
        SetPetsView(DbHandler.getInstance(this.getApplicationContext()).GetPetsByTypes(GlobalData.getInstance().getTypeID(getIntent().getStringExtra("Type"))));
    }

    private void SetPetsView(List<Pet> pets){
        //List<Pet> pets = GlobalData.getInstance().getLstChosenPets();
        petsControls = new ArrayList<PetItemControl>();

        Display display = getWindowManager().getDefaultDisplay();
        this.llLayout = new LinearLayout(this);
        this.scrlScrol = new ScrollView(this);
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
        scrlScrol.addView(llLayout);
        setContentView(scrlScrol);
    }
}
