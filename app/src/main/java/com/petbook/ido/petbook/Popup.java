package com.petbook.ido.petbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.petbook.ido.petbook.BL.DbHandler;

/**
 * Created by Omri on 13/08/2016.
 */
public class Popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int nWidth = dm.widthPixels;
        int nHeight = dm.heightPixels;

        getWindow().setLayout((int)(nWidth * .9 ), (int)(nHeight * .8));
    }

    public void onClickSignToNotification(View view) {
        EditText edPhonenum = (EditText) findViewById(R.id.etPhone);

        GlobalData.getInstance().getSdCurrSearch().setStrPhonenum(edPhonenum.getText().toString());

        DbHandler.getInstance(getApplicationContext()).addSearchSaved(GlobalData.getInstance().getSdCurrSearch());

        Toast.makeText(Popup.this, "נכנסת לרשימת המתנה בהצלחה ! :)", Toast.LENGTH_SHORT).show();
    }

    public void onClickBack(View view) {

        Intent intent = new Intent(getApplicationContext(), AdoptSearchActivity.class);

        startActivity(intent);

    }
}
