package com.petbook.ido.petbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.petbook.ido.petbook.BL.DbHandler;

import java.io.IOException;

public class MenuActivity extends Activity {

    RadioButton rbGive;
    RadioButton rbAdopt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rbGive = (RadioButton) findViewById(R.id.rbGive);
        rbAdopt = (RadioButton) findViewById(R.id.rbAdopt);
        //DbHandler hnd = new DbHandler(this);
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

        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);

        String strType = "Amuta";
        GoToSelection(strType, false);

    }

    public void onClickGuest(View view) {

        if (rbAdopt.isShown() &&
                rbGive.isShown())
        {
            if (!rbAdopt.isChecked() && !rbGive.isChecked())
            {
                Toast.makeText(MenuActivity.this, "עליך לבחור אחת מהאופציות", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // TODO: Redirect to amuta screen
                String strType = "Guest";
                GoToSelection(strType, rbAdopt.isChecked());
            }
        }
        else
        {
            rbAdopt.setVisibility(RadioButton.VISIBLE);
            rbGive.setVisibility (RadioButton.VISIBLE);
        }

    }

    public void onClickVet(View view) {

        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);

        String strType = "Vet";
        GoToSelection(strType, false);

    }

    public void onClickStore(View view) {
        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);

        String strType = "Store";
        GoToSelection(strType, false);
    }

    private void GoToSelection(String strType, Boolean isAdopt) {

        Intent intent = new Intent(getApplicationContext(), PetSelectionActivity.class);
        intent.putExtra("Type", strType);
        intent.putExtra("isAdopt", isAdopt);

        this.startActivity(intent);

    }

    public void onClickRadio(View view) {

        switch (view.getId())
        {
            case(R.id.rbAdopt):
            {
                if (rbGive.isChecked())
                {
                    rbGive.setChecked(false);
                }

                break;
            }
            case(R.id.rbGive):
            {
                if (rbAdopt.isChecked())
                {
                    rbAdopt.setChecked(false);
                }

                break;
            }
        }
    }
}
