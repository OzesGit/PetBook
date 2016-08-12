package com.petbook.ido.petbook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MenuActivity extends Activity {

    RadioButton rbGive;
    RadioButton rbAdopt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rbGive = (RadioButton) findViewById(R.id.rbGive);
        rbAdopt = (RadioButton) findViewById(R.id.rbAdopt);
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

        if (rbAdopt.isShown() &&
            rbGive.isShown())
        {
            if (!rbAdopt.isChecked() == false &&
                !rbGive.isChecked() == false )
            {
                Toast.makeText(MenuActivity.this, "עליך לבחור אחת מהאופציות", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // TODO: Redirect to amuta screen
            }
        }
        else
        {
            rbAdopt.setVisibility(RadioButton.VISIBLE);
            rbGive.setVisibility (RadioButton.VISIBLE);
        }
    }

    public void onClickGuest(View view) {

        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);

    }

    public void onClickVet(View view) {

        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);

    }

    public void onClickStore(View view) {
        rbAdopt.setVisibility(RadioButton.INVISIBLE);
        rbGive.setVisibility (RadioButton.INVISIBLE);
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
