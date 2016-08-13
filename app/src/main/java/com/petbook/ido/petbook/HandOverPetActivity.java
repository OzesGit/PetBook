package com.petbook.ido.petbook;

import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class HandOverPetActivity extends ActionBarActivity {
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private RadioButton radioUnknown;
    private Spinner agesSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private RadioButton surgeryYes;
    private RadioButton surgeryNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_pet);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        radioUnknown = (RadioButton) findViewById(R.id.radioUnknown);
        surgeryYes = (RadioButton) findViewById(R.id.surgeryYes);
        surgeryNo = (RadioButton) findViewById(R.id.surgeryNo);
        agesSpinner = (Spinner) findViewById(R.id.agesSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Ages, R.layout.support_simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agesSpinner.setAdapter(adapter);

        agesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HandOverPetActivity.this, "" + id, 1).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickGender(View view) {

        switch (view.getId())
        {
            case(R.id.radioFemale):
            {
                radioMale.setChecked(false);
                radioUnknown.setChecked(false);

                break;
            }
            case(R.id.radioMale):
            {
                radioFemale.setChecked(false);
                radioUnknown.setChecked(false);

                break;
            }
            case(R.id.radioUnknown):
            {
                radioMale.setChecked(false);
                radioFemale.setChecked(false);

                break;
            }
        }

    }

    public void onClickSurgery(View view) {

        switch (view.getId())
        {
            case(R.id.surgeryYes):
            {
                surgeryNo.setChecked(false);

                break;
            }
            case(R.id.surgeryNo):
            {
                surgeryYes.setChecked(false);

                break;
            }
        }

    }
}
