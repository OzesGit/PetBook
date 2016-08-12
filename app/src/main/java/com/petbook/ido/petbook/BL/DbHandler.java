package com.petbook.ido.petbook.BL;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.petbook.ido.petbook.Enums;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omri on 12/08/2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    private static DbHandler Instance;
    private static String DATABASE_PATH = "/data/data/petbook/databases/";
    private static String DATABASE_NAME = "pets1.db";
    private static SQLiteDatabase myDataBase;
    private static Context mContext;
    private static String DROP_TABLES = "DROP TABLE Pets";
    private static String CREATE_TABLES = "CREATE TABLE `Pets` (\n" +
            "\t`name`\tTEXT,\n" +
            "\t`id`\tINTEGER NOT NULL,\n" +
            "\t`androidid`\tTEXT,\n" +
            "\t`gender`\tINTEGER,\n" +
            "\t`type`\tINTEGER,\n" +
            "\t`conditions`\tSTRING,\n" +
            "\t`phonenumber`\tTEXT,\n" +
            "\t`location`\tINTEGER,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`notes`\tTEXT,\n" +
            "\t`picture`\tBLOB,\n" +
            "\t`age`\tINTEGER\n" +
            ");";
    private static String INSERT_PET = "INSERT INTO Pets Values ";
    private static String GET_ALL_PETS = "SELECT * FROM Pets";
    //, SQLiteDatabase.CursorFactory factory, int version
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
        SQLiteDatabase db = getWritableDatabase();
        this.onCreate(db);
    }

    public static DbHandler getInstance(Context context)
    {
        if( Instance == null){
            Instance = new DbHandler(context);
        }
        return (Instance);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(this.DROP_TABLES);
            db.execSQL(this.CREATE_TABLES);
            commit(db);
        }
        catch (Exception ex){

        }
        insertMUCHAnimals();
        getAllPets();
    }

    private void insertMUCHAnimals(){
        SQLiteDatabase db = getWritableDatabase();
        String newPetInsertCommand;

        for(int i = 0; i < 100; i++){
            newPetInsertCommand = this.INSERT_PET;
            String Values = "(";

            Values += "'OzOmDi" + i + "',";
            Values += GetNextSeq("id") + ",";
            Values += 0 + ",";
            Values += (int)(Math.random() * 3) + ",";
            Values += (int)(Math.random() * 3) + ",";
            Values += (int)(Math.random() * 4) + ",";
            Values += "'05284932" + (int)(Math.random() * 10) + "',";
            Values += (int)(Math.random() * 8) + ",";
            Values += "'ozomdi" + i + "/@gmail.com'" + ",";
            Values +=  i + ",";
            Values +=  i + ",";
            Values += (int)(Math.random() * 10);

            Values += ")";
            newPetInsertCommand += Values;
            db.execSQL(newPetInsertCommand);
        }
    }

    public List<Pet> getAllPets(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor Res = db.rawQuery(this.GET_ALL_PETS,null);
        List<Pet> lstRet = new ArrayList<Pet>();
        if(Res.getCount() != 0) {
            Res.moveToFirst();
            for (int nIndex = 0; nIndex < Res.getCount(); nIndex++){
                Pet p = new Pet();
                p.setName(Res.getString((0)));
                p.setId(Res.getInt((1)));
                p.setAndroidId(Res.getString((2)));
                p.setGender(Res.getInt((3)));
                p.setType(Res.getInt((4)));
                p.setCondition(Res.getString((5)));
                p.setPhoneNumber(Res.getString((6)));
                p.setLocation(Res.getInt((7)));
                p.setEmail(Res.getString((8)));
                p.setNotes(Res.getString((9)));
                p.setAge(Res.getInt((11)));

                // FOR OZ !!!
                if(nIndex == 0 || nIndex == 7){
                    p.setAndroidId("28cdf996ff8a378f");
                }

                lstRet.add(p);

                Res.moveToNext();
            }
        }

        return lstRet;
    }

    public List<Pet> getSearchedPets(String strAndroidID,
                                     int nGender,
                                     int nType,
                                     String strCond,
                                     int nAreaID,
                                     int nMinAge,
                                     int nMaxAge)
    {
        String strQuery = GET_ALL_PETS;

        strQuery += " WHERE androidid = "+ strAndroidID + " AND " +
                    "type = "+ nType + " AND " +
                    "location = "+ nAreaID + " AND ";

        if (nGender != Enums.Gender.UNKNOWN.ordinal())
        {
            strQuery += "gender = "+ nGender + " AND ";
        }

        if (nMinAge != 999) {
            strQuery += "age >= " + nMinAge + " AND ";
        }

        if (nMaxAge != 999){
            strQuery += "age <= " + nMaxAge + " AND ";
        }


        if(strCond.length() == 0)
        {
            strQuery += "( conditions = '012' OR conditions = '01' OR " +
                        "conditions = '02' OR conditions = '12' OR " +
                        "conditions = '1' OR conditions = '2' OR conditions = '0' )";
        }
        else if(strCond.length() == 1)
        {
            if(strCond == "0"){
                strQuery += "( conditions = '012' OR conditions = '01' OR condition = '02' OR condition = '0' )";
            }
            else if(strCond == "1"){
                strQuery += "( conditions = '1' OR conditions = '012' OR conditions = '01' OR condition = '12' )";
            }
            else if(strCond == "2"){
                strQuery += "( conditions = '2' OR conditions = '12' OR conditions = '02' OR conditions = '012' )";
            }
        }
        else if(strCond.length() == 2)
        {
            if(strCond == "01"){
                strQuery += "( conditions = '01' OR conditions = '012 )";
            }
            else if(strCond == "02"){
                strQuery += "( conditions = '02' OR conditions = '012' )";
            }
            else if(strCond == "12"){
                strQuery += "( conditions = '12' OR conditions = '012' )";
            }
        }
        else if(strCond.length() == 3)
        {
            strQuery += "( conditions = '012' )";
        }


        return null;
    }


    public int GetNextSeq(String strColName){
        SQLiteDatabase db = getWritableDatabase();
        String strCommand = "SELECT MAX(" + strColName + ") FROM Pets";
        Cursor Res = db.rawQuery(strCommand,null);

        Res.moveToFirst();
        return Res.getInt(0) + 1;
    }

    public void commit(SQLiteDatabase db)
    {
        db.execSQL("commit;");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    /** This method close database connection and released occupied memory **/
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }
}
