package com.petbook.ido.petbook;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.petbook.ido.petbook.BL.DataLoader;
import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

public class PetDetailsActivity extends Activity {
    private Pet CurrPet;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        int nId = this.getIntent().getIntExtra("petId",-1);

        this.InitFields(nId);
    }

    private void InitFields(int nId){
        this.CurrPet = DbHandler.getInstance(null).getPetById(nId);

        ((TextView) findViewById(R.id.txtName)).setText(this.CurrPet.getName());
        ((TextView) findViewById(R.id.txtAge)).setText((((TextView) findViewById(R.id.txtAge))).getText() +" "+ this.CurrPet.getAge());
        ((TextView) findViewById(R.id.txtType)).setText((((TextView) findViewById(R.id.txtType))).getText() +" "+ GlobalData.getInstance().getTypeName(this.CurrPet.getType()));
        ((TextView) findViewById(R.id.txtLocation)).setText((((TextView) findViewById(R.id.txtLocation))).getText() +" " + GlobalData.getInstance().getAreaString(this.CurrPet.getLocation()));
        ((TextView) findViewById(R.id.txtGender)).setText((((TextView) findViewById(R.id.txtGender))).getText() +" "+ GlobalData.getInstance().getGender(this.CurrPet.getGender()));
    }

}
