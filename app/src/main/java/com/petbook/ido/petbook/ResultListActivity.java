package com.petbook.ido.petbook;

import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        this.petsControls = new ArrayList<PetItemControl>();
        //List<Pet> pets = GlobalData.getInstance().getLstChosenPets();
        this.llLayout = new LinearLayout(this);
        this.scrlScrol = new ScrollView(this);

        // Create all the pets controls
        for(Pet ptPet : pets)
        {
            this.petsControls.add(new PetItemControl(this,ptPet));
            llLayout.addView(new PetItemControl(this,ptPet));
        }

        this.llLayout.setOrientation(LinearLayout.VERTICAL);
        scrlScrol.addView(llLayout);

        setContentView(scrlScrol);
    }
}
