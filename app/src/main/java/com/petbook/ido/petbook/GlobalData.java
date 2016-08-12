package com.petbook.ido.petbook;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Ido on 8/12/2016.
 */
public class GlobalData {

    public static List<String> strAreas = new ArrayList<>();

    public static void setAreasIfNotSet()
    {
        if(strAreas.size() == 0)
        {
            strAreas.add("צפון");
            strAreas.add("חדרה זכרון ועמקים");
            strAreas.add("השרון");
            strAreas.add("מרכז");
            strAreas.add("ירושלים");
            strAreas.add("יהודה ושומרון");
            strAreas.add("שפלה ומישור חוף דרומי");
            strAreas.add("דרום");
        }
    }

}
