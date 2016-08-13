package com.petbook.ido.petbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petbook.ido.petbook.BL.Pet;

/**
 * Created by Ido on 8/13/2016.
 */
public class PetItemControl extends LinearLayout{
    private Pet currPet;
    private ImageView imgPhoto;
    private TextView txtName;

    public PetItemControl(Context context) {
        super(context);

        this.imgPhoto = new ImageView(context);
        this.txtName = new TextView(context);

        this.addView(this.imgPhoto);
        this.addView(this.txtName);
    }

    public PetItemControl(Context context,String name,Bitmap btmpImage)
    {
        this(context);

        this.txtName.setText(name);
        if(btmpImage != null) {
            this.imgPhoto.setImageBitmap(btmpImage);
        }
        else {
            Drawable myIcon = getResources().getDrawable( R.drawable.shadow );
            this.imgPhoto.setImageDrawable(myIcon);
        }
    }

    public PetItemControl(Context context,Pet ptPet)
    {
        this(context,ptPet.getName(),ptPet.getPicture());
    }

}
