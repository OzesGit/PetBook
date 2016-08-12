package com.petbook.ido.petbook.BL;

import android.content.res.AssetManager;

import org.xml.sax.XMLReader;

import java.io.BufferedReader;
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
        dicAnimalTypes.put("1","חתול");
        dicAnimalTypes.put("2","כלב");
        dicAnimalTypes.put("3","חתול");
        dicAnimalTypes.put("4","כלב");
        dicAnimalTypes.put("5","חתול");
        dicAnimalTypes.put("6","חתול");

        return dicAnimalTypes;
    }
}
