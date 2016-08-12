package com.petbook.ido.petbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

import java.util.List;

public class ResultListActivity extends ActionBarActivity {
    private List<PetItemControl> petsControls;
    ScrollView scrlScrol;
    LinearLayout llLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Pet> pets = DbHandler.getInstance(this).getAllPets();
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
