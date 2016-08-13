package com.petbook.ido.petbook;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
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

import java.io.File;

public class HandOverPetActivity extends ActionBarActivity {
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private RadioButton radioUnknown;
    private EditText txtAge;
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
        txtAge = (EditText) findViewById(R.id.txtAge);
        locationsSpinner = (Spinner) findViewById(R.id.locationsSpinner);
        locationsAdapter = ArrayAdapter.createFromResource(this, R.array.Areas, R.layout.support_simple_spinner_dropdown_item);
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

        currLocation = 0;
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
        pet.setAge(Integer.parseInt(this.txtAge.getText().toString()));
        pet.setIsVirgin(currVirgin);

        if(imagePath != null && !imagePath.equals("")) {
            File flImg = new File(imagePath);
        }


        String dealsWith = "";
        if(kidsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.CHILDREN.ordinal();
        if(catsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.CATS.ordinal();
        if(dogsCheckBox.isChecked())
            dealsWith += Enums.DEALS_WITH.DOGS.ordinal();
        pet.setDealsWith(dealsWith);


        DbHandler.getInstance(null).insertPet(pet);
        String strPhoneNumber = DbHandler.getInstance(null).CheckForWaiters(pet);

        if(!strPhoneNumber.equals("NOT_FOUND"))
        {
            sendSMS("+972" + strPhoneNumber, "שלום :) החיה שחיפשת באפליקציית PetBook נמצאה. אנא חפש שוב");
        }

        finish();
    }

    protected void sendSMS(String strPhoneNum, String strMessage) {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , strPhoneNum);
        smsIntent.putExtra("sms_body"  ,strMessage );

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HandOverPetActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
