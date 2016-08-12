package com.petbook.ido.petbook;

import android.provider.CalendarContract;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Ido on 8/12/2016.
 */
public class GlobalData {

    public static Map<Integer,String> mpAreas = new HashMap<Integer, String>();
    private static GlobalData gldInstance;


    public GlobalData(){

    }

    public static GlobalData getInstance(){
        if(gldInstance == null){
            gldInstance = new GlobalData();
        }

        return gldInstance;
    }

    public void setAreasIfNotSet()
    {
        if(mpAreas.size() == 0)
        {
            mpAreas.put(Enums.LOCATIONS.NORTH.ordinal(), "צפון");
            mpAreas.put(Enums.LOCATIONS.HADERA_NORTH_AMAKIM.ordinal(), "חדרה זכרון ועמקים");
            mpAreas.put(Enums.LOCATIONS.HASHARON.ordinal(), "השרון");
            mpAreas.put(Enums.LOCATIONS.MERKAZ.ordinal(), "מרכז");
            mpAreas.put(Enums.LOCATIONS.JERUSALEM.ordinal(), "ירושלים");
            mpAreas.put(Enums.LOCATIONS.YEHUDA_AND_SHOMRON.ordinal(), "יהודה ושומרון");
            mpAreas.put(Enums.LOCATIONS.SHFELA_AND_MISHOR_HOF_SOUTH.ordinal(), "שפלה ומישור חוף דרומי");
            mpAreas.put(Enums.LOCATIONS.SOUTH.ordinal(), "דרום");
        }
    }

}
