package com.petbook.ido.petbook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.List;

public class ResultListActivity extends ActionBarActivity {
    private List<PetItemControl> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
    }
}
