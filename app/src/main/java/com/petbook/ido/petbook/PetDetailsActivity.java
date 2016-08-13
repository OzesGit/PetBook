package com.petbook.ido.petbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

public class PetDetailsActivity extends ActionBarActivity {

    Pet CurrPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        int nId = this.getIntent().getIntExtra("petId",-1);

        this.InitFields(nId);
    }

    private void InitFields(int nId){

        this.CurrPet = DbHandler.getInstance(null).getPetById(nId);
    }

}
