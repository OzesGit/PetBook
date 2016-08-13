package com.petbook.ido.petbook;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.petbook.ido.petbook.BL.DbHandler;
import com.petbook.ido.petbook.BL.Pet;

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
    private String imagePath = null;

    private EditText nameEditText;
    private EditText contactEditText;
    private EditText mailEditText;
    private EditText commentsEditText;

    private CheckBox kidsCheckBox;
    private CheckBox catsCheckBox;
    private CheckBox dogsCheckBox;

    int currGender;
    int currConditions;
    boolean currVirgin;

    int currAge;
    int currLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_pet);
        mailTextView = (TextView) findViewById(R.id.mailTextView);

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

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        contactEditText = (EditText) findViewById(R.id.contactEditText);
        mailEditText = (EditText) findViewById(R.id.mailEditText);
        commentsEditText = (EditText) findViewById(R.id.commentsEditText);

        kidsCheckBox = (CheckBox) findViewById(R.id.kidsCheckBox);
        catsCheckBox = (CheckBox) findViewById(R.id.catsCheckBox);
        dogsCheckBox = (CheckBox) findViewById(R.id.dogsCheckBox);

        currGender = Enums.Gender.MALE.ordinal();
        currConditions = Enums.CONDITIONS.CLOSEDAPPARTMENT.ordinal();
        currVirgin = true;

        currAge = 0;
        currLocation = 0;

        agesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currAge = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        locationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currLocation = position;
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
                currGender = Enums.Gender.FEMALE.ordinal();
                radioMale.setChecked(false);
                radioUnknown.setChecked(false);

                break;
            }
            case(R.id.radioMale):
            {
                currGender = Enums.Gender.MALE.ordinal();
                radioFemale.setChecked(false);
                radioUnknown.setChecked(false);

                break;
            }
            case(R.id.radioUnknown):
            {
                currGender = Enums.Gender.UNKNOWN.ordinal();
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
                currVirgin = true;
                surgeryNo.setChecked(false);

                break;
            }
            case(R.id.surgeryNo):
            {
                currVirgin = false;
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
                currConditions = Enums.CONDITIONS.CLOSEDAPPARTMENT.ordinal();
                openApartmentRadio.setChecked(false);
                roomRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.openApartmentRadio):
            {
                currConditions = Enums.CONDITIONS.OPENAPPARTMENT.ordinal();
                closedApartmentRadio.setChecked(false);
                roomRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.roomRadio):
            {
                currConditions = Enums.CONDITIONS.YARD.ordinal();
                closedApartmentRadio.setChecked(false);
                openApartmentRadio.setChecked(false);
                notMatterRadio.setChecked(false);

                break;
            }
            case(R.id.notMatterRadio):
            {
                currConditions = Enums.CONDITIONS.NO_MATTER.ordinal();
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
            imagePath = cursor.getString(columnIndex);
            cursor.close();

            pickImageButton.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setImageURI(selectedImage);
        }
    }


    public void submit(View view) {
        Pet pet = new Pet();
        pet.setName(nameEditText.getText().toString());
        pet.setId(DbHandler.getInstance(null).GetNextSeq("id"));
        pet.setAndroidId(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        pet.setGender(currGender);
        pet.setType(GlobalData.getInstance().getTypeID(getIntent().getStringExtra("petType")));
        pet.setCondition(currConditions);
        pet.setPhoneNumber(contactEditText.getText().toString());
        pet.setLocation(currLocation);
        pet.setEmail(mailEditText.getText().toString());
        pet.setNotes(commentsEditText.getText().toString());
        pet.setPicture(BitmapFactory.decodeFile(imagePath));
        pet.setAge(currAge);
        pet.setIsVirgin(currVirgin);

        String dealsWith = "";
        if(kidsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.CHILDREN;
        if(catsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.CATS;
        if(dogsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.DOGS;
        pet.setDealsWith(dealsWith);


        DbHandler.getInstance(null).insertPet(pet);
        String strPhoneNumber = DbHandler.getInstance(null).CheckForWaiters(pet);

        if(!strPhoneNumber.equals("NOT_FOUND"))
        {
            sendSMS(strPhoneNumber, "שלום :) החיה שחיפשת באפליקציית PetBook נמצאה. אנא חפש שוב");
        }

        finish();
    }

    private void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

}
