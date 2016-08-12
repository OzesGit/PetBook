package com.petbook.ido.petbook;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.petbook.ido.petbook.BL.DbHandler;

import javax.microedition.khronos.opengles.GL;

public class AdoptSearchActivity extends ActionBarActivity {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private String strSelectedArea;
    private String strSelectedAnimal;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private int nAreaCode;
    private int nGender;
    private int nAge;
    private String android_id;
    private int nAnimalType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_search);

        GlobalData.getInstance().setAreasIfNotSet();

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        strSelectedAnimal = getIntent().getStringExtra("petType");
        nAnimalType = GlobalData.getInstance().getTypeID(strSelectedAnimal);
        tvTitle.setText("חפש " + strSelectedAnimal + " לאימוץ");

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Areas, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSelectedArea = parent.getItemAtPosition(position).toString();

                nAreaCode = GlobalData.getInstance().getAreaID(strSelectedArea);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickGender(View view) {

        switch (view.getId())
        {
            case(R.id.rbFemale):
            {
                if(rbMale.isChecked())
                {
                    rbMale.setChecked(false);
                }

                break;
            }
            case(R.id.rbMale):
            {
                if(rbFemale.isChecked())
                {
                    rbFemale.setChecked(false);
                }

                break;
            }
        }

    }

    public void onClickSearch(View view) {

        String strCondition = "";

        Boolean isKids = ((CheckBox)findViewById(R.id.cbKids)).isChecked();
        Boolean isDogs = ((CheckBox)findViewById(R.id.cbDogs)).isChecked();
        Boolean isCats = ((CheckBox)findViewById(R.id.cbCats)).isChecked();

        EditText edMinAge = (EditText) findViewById(R.id.etAge);
        EditText edMaxAge = (EditText) findViewById(R.id.etMaxAge);

        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        int nMinAge = 999;
        int nMaxAge = 999;

        if(!edMinAge.getText().toString().equals("")){
            nMinAge = Integer.parseInt(edMinAge.getText().toString());
        }

        if(!edMaxAge.getText().toString().equals("")){
            nMaxAge = Integer.parseInt(edMaxAge.getText().toString());
        }

        if (!rbMale.isChecked() &&
            !rbFemale.isChecked()) {
            nGender = Enums.Gender.UNKNOWN.ordinal();
        }
        else if(rbMale.isChecked()) {
            nGender = Enums.Gender.MALE.ordinal();
        }
        else {
            nGender = Enums.Gender.FEMALE.ordinal();
        }

        if(isKids){
            strCondition = "0";
        }

        if(isDogs){
            strCondition += "1";
        }

        if(isCats){
            strCondition += "2";
        }

        DbHandler.getInstance(this.getApplicationContext()).getSearchedPets(android_id,nGender,nAnimalType,strCondition,nAreaCode,nMinAge,nMaxAge);

    }
}