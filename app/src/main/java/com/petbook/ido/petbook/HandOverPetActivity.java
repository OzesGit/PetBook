package com.petbook.ido.petbook;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HandOverPetActivity extends ActionBarActivity {
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private RadioButton radioUnknown;
    private Spinner agesSpinner;
    private ArrayAdapter<CharSequence> agesAdapter;
    private RadioButton surgeryYes;
    private RadioButton surgeryNo;
    private RadioButton closedApartmentRadio;
    private RadioButton openApartmentRadio;
    private RadioButton roomRadio;
    private RadioButton notMatterRadio;
    private Spinner locationsSpinner;
    private ArrayAdapter<CharSequence> locationsAdapter;
    private TextView mailTextView;
    private Button pickImageButton;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_pet);
        mailTextView = (TextView) findViewById(R.id.mailTextView);
        if (!getIntent().getStringExtra("userType").equals("Amuta"))
            mailTextView.setVisibility(View.GONE);

        pickImageButton = (Button) findViewById(R.id.pickImagebutton);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        radioUnknown = (RadioButton) findViewById(R.id.radioUnknown);
        surgeryYes = (RadioButton) findViewById(R.id.surgeryYes);
        surgeryNo = (RadioButton) findViewById(R.id.surgeryNo);
        closedApartmentRadio = (RadioButton) findViewById(R.id.closedApartmentRadio);
        openApartmentRadio = (RadioButton) findViewById(R.id.openApartmentRadio);
        roomRadio = (RadioButton) findViewById(R.id.roomRadio);
        notMatterRadio = (RadioButton) findViewById(R.id.notMatterRadio);
        agesSpinner = (Spinner) findViewById(R.id.agesSpinner);
        locationsSpinner = (Spinner) findViewById(R.id.locationsSpinner);
        agesAdapter = ArrayAdapter.createFromResource(this, R.array.Ages, R.layout.support_simple_spinner_dropdown_item);
        locationsAdapter = ArrayAdapter.createFromResource(this, R.array.Areas, R.layout.support_simple_spinner_dropdown_item);
        agesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agesSpinner.setAdapter(agesAdapter);
        locationsSpinner.setAdapter(locationsAdapter);

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

    public void onClickConditions(View view) {

        switch (view.getId())
        {
            case(R.id.closedApartmentRadio):
            {
                openApartmentRadio.setChecked(false);
                roomRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.openApartmentRadio):
            {
                closedApartmentRadio.setChecked(false);
                roomRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.roomRadio):
            {
                closedApartmentRadio.setChecked(false);
                openApartmentRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.notMatterRadio):
            {
                closedApartmentRadio.setChecked(false);
                openApartmentRadio.setChecked(false);
                roomRadio.setChecked(false);

                break;
            }
        }

    }

    private static final int RESULT_LOAD_IMAGE = 1;

    public void pickImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            pickImageButton.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setImageURI(selectedImage);

        }
    }

}
