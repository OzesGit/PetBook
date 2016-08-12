package com.petbook.ido.petbook;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AdoptSearchActivity extends ActionBarActivity {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private String strSelectedArea;
    private String strSelectedAnimal;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private int nAreaCode;
    private int nGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_search);

        GlobalData.getInstance().setAreasIfNotSet();

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        strSelectedAnimal = getIntent().getStringExtra("petType");
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

                // TODO; Search where to put this shit
                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
        // area
        //
        //
        //0
        //
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
    }
}
