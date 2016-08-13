package com.petbook.ido.petbook;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.petbook.ido.petbook.BL.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ido on 8/12/2016.
 */
public class GlobalData {

    public static Map<Integer,String> mpAreas = new HashMap<Integer, String>();
    public static Map<Integer,String> mpTypes = new HashMap<Integer, String>();
    private static GlobalData gldInstance;
    public static List<Pet> lstChosenPets = new ArrayList<Pet>();

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

    public void setLstChosenPets(List<Pet> lstPet){
        lstChosenPets = lstPet;
    }

    public List<Pet> getLstChosenPets()
    {
        return lstChosenPets;
    }

    public Drawable getImageByAnimalName(Resources src, String strName)
    {
        Drawable d = null;
        switch (strName) {
            case ("dog"): { d = src.getDrawable(R.drawable.dog); break;}
            case ("cat"):{ d = src.getDrawable(R.drawable.cat); break;}
            case ("turkey"):{ d = src.getDrawable(R.drawable.turkey); break;}
//            case ("turtle"):{ d = src.getDrawable(R.drawable.turtle); break;}
            case ("donkey"):{ d = src.getDrawable(R.drawable.donkey); break;}
            case ("horse"):{ d = src.getDrawable(R.drawable.horse); break;}
            case ("peacock"):{ d = src.getDrawable(R.drawable.peacock); break;}
            case ("humus"):{ d = src.getDrawable(R.drawable.humus); break;}
            default: { d = src.getDrawable(R.drawable.shadow); break; }
        }
        return  d;
    }
}
