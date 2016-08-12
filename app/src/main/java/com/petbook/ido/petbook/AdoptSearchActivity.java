package com.petbook.ido.petbook;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AdoptSearchActivity extends ActionBarActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String strSelectedArea;
    String strSelectedAnimal;
    RadioButton rbMale;
    RadioButton rbFemale;


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
    }
}
