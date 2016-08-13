package com.petbook.ido.petbook.BL;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Omri on 12/08/2016.
 */
public class DataLoader {

    public static Map<String,String> GetAnimalTypeList() {
        // In the future - get the animal types from a DB/XML...
        // IN the midtime - GET IT FROM A STATIC LIST !!! MUAHAHA
        Map<String,String> dicAnimalTypes = new HashMap<String,String>();
        dicAnimalTypes.put("dog","כלב");
        dicAnimalTypes.put("cat","חתול");
        dicAnimalTypes.put("turkey","תרנגול");
        dicAnimalTypes.put("turtle","צב");
        dicAnimalTypes.put("donkey","חמור");
        dicAnimalTypes.put("horse","סוס");
        dicAnimalTypes.put("peacock","טווס");
        dicAnimalTypes.put("humus","חמוס");
        return dicAnimalTypes;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        if(bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }
            return null;
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
