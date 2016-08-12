package com.petbook.ido.petbook;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ido on 8/12/2016.
 */
public class GlobalData {

    public static Map<Integer,String> mpAreas = new HashMap<Integer, String>();
    public static Map<Integer,String> mpTypes = new HashMap<Integer, String>();
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

    public void setAnimalTypes()
    {
        if(mpTypes.size() == 0)
        {
            mpTypes.put(Enums.Type.DOG.ordinal(), "כלב");
            mpTypes.put(Enums.Type.CAT.ordinal(), "חתול");
            mpTypes.put(Enums.Type.TURTLE.ordinal(), "צב");
        }
    }

    public int getAreaID(String strName)
    {
        for ( Integer nKey : mpAreas.keySet() ) {
            if (mpAreas.get(nKey).equals(strName))
            {
                return nKey.intValue();
            }
        }

        return 0;
    }

    public int getTypeID(String strName)
    {
        for ( Integer nKey : mpTypes.keySet() ) {
            if (mpTypes.get(nKey).equals(strName))
            {
                return nKey.intValue();
            }
        }

        return 0;
    }

}
