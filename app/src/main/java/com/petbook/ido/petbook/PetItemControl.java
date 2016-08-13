package com.petbook.ido.petbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petbook.ido.petbook.BL.Pet;

import java.io.ByteArrayInputStream;

/**
 * Created by Ido on 8/13/2016.
 */
public class PetItemControl extends LinearLayout{
    private Pet currPet;
    private ImageView imgPhoto;
    private TextView txtName;
    private int textSize = 25;

    public PetItemControl(Context context) {
        super(context);

        this.imgPhoto = new ImageView(context);
        this.txtName = new TextView(context);
        this.imgPhoto.setLeft(0);
        this.txtName.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.txtName.setTextSize(textSize);

        this.addView(this.imgPhoto);
        this.addView(this.txtName);

    }

    public PetItemControl(Context context,String name,int type,byte[] imageBytes)
    {
        this(context);
        String txt = "שם:" + name;
        int radius = 45;
        this.txtName.setText(txt);

        Drawable drw = null;
        if (imageBytes != null) {
            ByteArrayInputStream stream = new ByteArrayInputStream(imageBytes);
            drw = Drawable.createFromStream(stream, "articleImage");
        }

        if(drw != null) {
            this.imgPhoto.setBackground(drw);// setImageBitmap(img);
        }
        else {
            Drawable myIcon = GlobalData.getInstance().getImageByAnimalName(getResources(),type);
            this.imgPhoto.setImageDrawable(myIcon);
        }
    }

    public PetItemControl(Context context,Pet ptPet)
    {
        this(context,ptPet.getName(),ptPet.getType(),ptPet.getPicture());
    }

    public void SetSize(int width,int height)
    {
        this.imgPhoto.setLayoutParams(new LinearLayout.LayoutParams((width*40)/100,height/6));
        this.imgPhoto.requestLayout();
        this.txtName.setLayoutParams(new LinearLayout.LayoutParams((width*60)/100,height/6));
        this.txtName.setLeft((width*40)/100);
        this.txtName.requestLayout();
    }
    // Returns the current pet
    public Pet getCurrPet()
    {
        return  (this.currPet);
    }
}
