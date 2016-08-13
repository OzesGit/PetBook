package com.petbook.ido.petbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MenuActivity extends Activity {

    Button btnAdopt;
    Button btnDeliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnAdopt = (Button) findViewById(R.id.btnAdopt);
        btnDeliver = (Button) findViewById(R.id.btnDeliver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickAmuta(View view) {

        btnAdopt.setVisibility(RadioButton.INVISIBLE);
        btnDeliver.setVisibility (RadioButton.INVISIBLE);

        String strType = "Amuta";
        GoToSelection(strType, false);

    }

    public void onClickGuest(View view) {

        if (!btnAdopt.isShown())
        {
            btnAdopt.setVisibility(RadioButton.VISIBLE);
            btnDeliver.setVisibility (RadioButton.VISIBLE);
        }
    }

    public void onClickVet(View view) {

        btnAdopt.setVisibility(RadioButton.INVISIBLE);
        btnDeliver.setVisibility (RadioButton.INVISIBLE);

        String strType = "Vet";
        GoToSelection(strType, false);

    }

    public void onClickStore(View view) {
        btnAdopt.setVisibility(RadioButton.INVISIBLE);
        btnDeliver.setVisibility(RadioButton.INVISIBLE);

        String strType = "Store";
        GoToSelection(strType, false);
    }

    private void GoToSelection(String strType, Boolean isAdopt) {

        Intent intent = new Intent(getApplicationContext(), PetSelectionActivity.class);
        intent.putExtra("Type", strType);
        intent.putExtra("isAdopt", isAdopt);

        this.startActivity(intent);

    }

    public void onClickSelectionType(View view) {
        boolean bIsAdopt = false;
        if(view.getId() == R.id.btnAdopt)
        {
            bIsAdopt = true;
        }
        String strType = "Guest";
        GoToSelection(strType, bIsAdopt);
    }

    public void onClickPersonalArea(View view) {

    }
}
