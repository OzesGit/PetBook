package com.petbook.ido.petbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class PetDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        this.InitFields();
    }

    private void InitFields(){

    }

}
