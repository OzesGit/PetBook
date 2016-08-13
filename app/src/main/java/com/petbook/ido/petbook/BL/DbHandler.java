package com.petbook.ido.petbook.BL;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.petbook.ido.petbook.Enums;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omri on 12/08/2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    private static DbHandler Instance;
    private static String DATABASE_PATH = "/data/data/petbook/databases/";
    private static String DATABASE_NAME = "petss.db";
    private static SQLiteDatabase myDataBase;
    private static Context mContext;
    private SQLiteDatabase db;
    private static String DROP_TABLES = "DROP TABLE `Pets`;";
    private static String DROP_SEARCHES = "DROP TABLE 'SavedSearches'";
    private static String CREATE_PETS = "CREATE TABLE IF NOT EXISTS `Pets` (\n" +
            "\t`name`\tTEXT,\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`androidid`\tTEXT,\n" +
            "\t`gender`\tINTEGER,\n" +
            "\t`type`\tINTEGER,\n" +
            "\t`conditions`\tTEXT,\n" +
            "\t`phonenumber`\tTEXT,\n" +
            "\t`location`\tINTEGER,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`notes`\tTEXT,\n" +
            "\t`picture`\tBLOB,\n" +
            "\t`age`\tINTEGER,\n" +
            "\t`isvirgin`\tINTEGER,\n" +
            "\t`dealswith`\tTEXT,\n" +
            "\tPRIMARY KEY(id)\n" +
            ") ";
            //"\t`isvirgin`\tINTEGER\n" +;
    private static String CREATE_SAVED_SEARCHES = "CREATE TABLE IF NOT EXISTS `SavedSearches` (\n" +
            "\t`phonenum`\tTEXT,\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`minage`\tINTEGER,\n" +
            "\t`maxage`\tINTEGER,\n" +
            "\t`gender`\tINTEGER,\n" +
            "\t`areacode`\tINTEGER,\n" +
            "\t`animaltype`\tINTEGER,\n" +
            "\t`condition`\tTEXT,\n" +
            "\tPRIMARY KEY(id)\n" +
            ");";

    private static String INSERT_PET = "INSERT INTO Pets Values ";
    private static String GET_ALL_PETS = "SELECT * FROM Pets";
    //, SQLiteDatabase.CursorFactory factory, int version
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
        this.db = this.getWritableDatabase();
        this.CreateTables();
        insertMUCHAnimals();
    }

    public static DbHandler getInstance(Context context)
    {
        if( Instance == null){
            Instance = new DbHandler(context);
        }
        return (Instance);
    }

    private  void CreateTables(){
        try {
            //db.execSQL(this.DROP_TABLES);
            db.execSQL(this.DROP_SEARCHES);
        }
        catch (Exception ex){

        }
        try {
            db.execSQL(this.CREATE_PETS);
            db.execSQL(this.CREATE_SAVED_SEARCHES);
        }
        catch (Exception ex){
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    private List<Pet> ExecutePetQuery(String command){
        Cursor Res = db.rawQuery(command,null);
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
                p.setCondition(Res.getInt((5)));
                p.setPhoneNumber(Res.getString((6)));
                p.setLocation(Res.getInt((7)));
                p.setEmail(Res.getString((8)));
                p.setNotes(Res.getString((9)));
                p.setAge(Res.getInt((11)));

                boolean bToSet = Res.getInt(12) == 0 ? false : true;
                p.setIsVirgin(bToSet);
                p.setDealsWith(Res.getString((13)));

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

    public List<Pet> GetPetsByTypes(int type){
        String command = "SELECT * FROM Pets WHERE type = " + type;

        return ExecutePetQuery(command);

    }

    public void insertPet(Pet pet){
        int nToSet = pet.getIsVirgin() ? 1 : 0;
        String values = "('" +
                pet.getName()+"',"+
                pet.getId()+",'"+
                pet.getAndroidId()+"',"+
                pet.getGender()+","+
                pet.getType()+","+
                pet.getCondition()+",'"+
                pet.getPhoneNumber()+"',"+
                pet.getLocation()+",'"+
                pet.getEmail()+"','"+
                pet.getNotes()+"',"+
                DataLoader.getBytes(pet.getPicture())+","+
                pet.getAge()+","+
                nToSet+",'"+
                pet.getDealsWith() + "');";

        db.execSQL(this.INSERT_PET + values);
    }

    public Pet getPetById(int nId){
        String command = "SELECT * FROM Pets WHERE id = " + nId;
        Cursor Res = db.rawQuery(command, null);
        Pet p = null;

        if(Res.getCount() != 0) {
            Res.moveToFirst();
            p = new Pet();
            p.setName(Res.getString((0)));
            p.setId(Res.getInt((1)));
            p.setAndroidId(Res.getString((2)));
            p.setGender(Res.getInt((3)));
            p.setType(Res.getInt((4)));
            p.setCondition(Res.getInt((5)));
            p.setPhoneNumber(Res.getString((6)));
            p.setLocation(Res.getInt((7)));
            p.setEmail(Res.getString((8)));
            p.setNotes(Res.getString((9)));
            p.setAge(Res.getInt((11)));

            boolean bToSet = Res.getInt(12) == 0 ? false : true;
            p.setIsVirgin(bToSet);
            p.setDealsWith(Res.getString((13)));
        }

        return p;
    }

    private void insertMUCHAnimals(){
        String newPetInsertCommand;

        for(int i = 0; i < 100; i++){
            newPetInsertCommand = this.INSERT_PET;
            String Values = "(";

            Values += "'OzOmDi" + i + "',";
            Values += GetNextSeq("id") + ",";
            Values += 0 + ",";
            Values += (int)(Math.random() * 3) + ",";
            Values += (int)(Math.random() * 8) + ",";
            Values += (int)(Math.random() * 4) + ",";
            Values += "'05284932" + (int)(Math.random() * 10) + "',";
            Values += (int)(Math.random() * 8) + ",";
            Values += "'ozomdi" + i + "/@gmail.com'" + ",";
            Values +=  i + ",";
            Values +=  i + ",";
            Values += (int)(Math.random() * 10) + ",";
            Values += (int)(Math.random() * 2) + ",";
            Values += (int)(Math.random() * 4);

            Values += ")";

            if(i == 2 ){
                Values = "(";

                Values += "'Idogi" + i + "',";
                Values += GetNextSeq("id") + ",";
                Values += "'28cdf996ff8a378f'" + ",";
                Values += 0 + ",";
                Values += 0 + ",";
                Values += "'01'" + ",";
                Values += "'05284932" + (int)(Math.random() * 10) + "',";
                Values += 0 + ",";
                Values += "'ozomdi" + i + "/@gmail.com'" + ",";
                Values +=  i + ",";
                Values +=  i + ",";
                Values += 10 + ",";
                Values += (int)(Math.random() * 2) + ",";
                Values += (int)(Math.random() * 4);

                Values += ")";
            }
            else if (i == 5){
                Values = "(";

                Values += "'Idogi" + i + "',";
                Values += GetNextSeq("id") + ",";
                Values += "'28cdf996ff8a378f'" + ",";
                Values += 0 + ",";
                Values += 0 + ",";
                Values += "'012'" + ",";
                Values += "'05284932" + (int)(Math.random() * 10) + "',";
                Values += 0 + ",";
                Values += "'ozomdi" + i + "/@gmail.com'" + ",";
                Values +=  i + ",";
                Values +=  i + ",";
                Values += 10 + ",";
                Values += (int)(Math.random() * 2) +",";
                Values += (int)(Math.random() * 4);

                Values += ")";
            }
            newPetInsertCommand += Values;
            db.execSQL(newPetInsertCommand);
        }
    }

    public List<Pet> getAllPets(){
        return ExecutePetQuery(this.GET_ALL_PETS);
    }

    public List<Pet> getSearchedPets(int nGender,
                                     int nType,
                                     String strDealsWith,
                                     int nAreaID,
                                     int nMinAge,
                                     int nMaxAge)
    {
        List<Pet> lstRet = new ArrayList<Pet>();
        String strQuery = GET_ALL_PETS;

        strQuery += " WHERE type = "+ nType + " AND " +
                    "location = "+ nAreaID;

        if (nGender != Enums.Gender.UNKNOWN.ordinal())
        {
            strQuery += " AND gender = "+ nGender;
        }

        if (nMinAge != 999) {
            strQuery += " AND age >= " + nMinAge;
        }

        if (nMaxAge != 999){
            strQuery += " AND age <= " + nMaxAge;
        }

        /*if(strCond.length() == 0)
        {
            strQuery += "( conditions = '012' OR conditions = '01' OR " +
                        "conditions = '02' OR conditions = '12' OR " +
                        "conditions = '1' OR conditions = '2' OR conditions = '0' )";
        }
        else*/ if(strDealsWith.length() == 1)
        {
            if(strDealsWith.equals("0")){
                strQuery += " AND ( dealswith = '012' OR dealswith = '01' OR dealswith = '02' OR dealswith = '0' )";
            }
            else if(strDealsWith.equals("1")){
                strQuery += " AND ( dealswith = '1' OR dealswith = '012' OR dealswith = '01' OR dealswith = '12' )";
            }
            else if(strDealsWith.equals("2")){
                strQuery += " AND ( dealswith = '2' OR dealswith = '12' OR dealswith = '02' OR dealswith = '012' )";
            }
        }
        else if(strDealsWith.length() == 2)
        {
            if(strDealsWith.equals("01")){
                strQuery += " AND ( dealswith = '01' OR dealswith = '012' )";
            }
            else if(strDealsWith.equals("02")){
                strQuery += " AND ( dealswith = '02' OR dealswith = '012' )";
            }
            else if(strDealsWith.equals("12")){
                strQuery += " AND ( dealswith = '12' OR dealswith = '012' )";
            }
        }
        else if(strDealsWith.length() == 3)
        {
            strQuery += " AND ( dealswith = '012' )";
        }

        Cursor Res = db.rawQuery(strQuery, null);

        if(Res.getCount() != 0) {
            Res.moveToFirst();

            for (int nIndex = 0; nIndex < Res.getCount(); nIndex++)
            {
                Pet p = new Pet();
                p.setName(Res.getString((0)));
                p.setId(Res.getInt((1)));
                p.setAndroidId(Res.getString((2)));
                p.setGender(Res.getInt((3)));
                p.setType(Res.getInt((4)));
                p.setCondition(Res.getInt((5)));
                p.setPhoneNumber(Res.getString((6)));
                p.setLocation(Res.getInt((7)));
                p.setEmail(Res.getString((8)));
                p.setNotes(Res.getString((9)));
                p.setAge(Res.getInt((11)));

                boolean bToSet = Res.getInt(12) == 0 ? false : true;
                p.setIsVirgin(bToSet);
                p.setDealsWith(Res.getString((13)));

                lstRet.add(p);
            }

        }


        return lstRet;
    }

    public void deletePetByID(int nPetID)
    {
        String strQuery = "DELETE FROM Pets " +
                "WHERE id = " + nPetID;

        db.execSQL(strQuery);
    }

    public void updatePetByPetObject(Pet updatedPet)
    {
        Boolean bool = updatedPet.getIsVirgin();
        int nIsVirgin = 0;

        if(bool)
        {
            nIsVirgin = 1;
        }

        String strQuery = "UPDATE Pets " +
                          "SET name = '" + updatedPet.getName() +
                            "', gender = " + updatedPet.getGender() +
                             ", type = " + updatedPet.getType() +
                             ", dealswith = " + updatedPet.getDealsWith() +
                             ", conditions = " + updatedPet.getCondition() +
                             ", phonenumber = " + updatedPet.getPhoneNumber() +
                             ", location = " + updatedPet.getLocation() +
                             ", email = " + updatedPet.getEmail() +
                             ", notes = " + updatedPet.getNotes() +
                             ", picture = " + updatedPet.getPicture() +
                             ", age = " + updatedPet.getAge() +
                             ", isvirgin = " + nIsVirgin +
                          "WHERE id = " + updatedPet.getId();

        db.execSQL(strQuery, null);
    }

    public String CheckForWaiters(Pet pet)
    {
        String strDealsWith = pet.getDealsWith();
        int nAge = pet.getAge();
        int nMinAge = 0;
        int nMaxAge = 0;

        switch (nAge)
        {
            case(1):
            {
                nMinAge = 0;
                nMaxAge = 0;

                break;
            }
            case(2):
            {
                nMinAge = 0;
                nMaxAge = 0;

                break;
            }
            case(3):
            {
                nMinAge = 1;
                nMaxAge = 5;

                break;
            }
            case(4):
            {
                nMinAge = 6;
                nMaxAge = 10;

                break;
            }
            case(5):
            {
                nMaxAge = 999999;
                nMinAge = 10;

                break;
            }

        }

        String strQuery = "SELECT * FROM SavedSearches "+
                          "WHERE minage >= "+ nMinAge +
                          " AND maxage <= " + nMaxAge +
                          " AND gender = " +  pet.getGender() +
                          " AND animaltype = " + pet.getType();

        if(strDealsWith.length() == 1)
        {
            if(strDealsWith.equals("0")){
                strQuery += " AND ( condition = '012' OR condition = '01' OR condition = '02' OR condition = '0' )";
            }
            else if(strDealsWith.equals("1")){
                strQuery += " AND ( condition = '1' OR condition = '012' OR condition = '01' OR condition = '12' )";
            }
            else if(strDealsWith.equals("2")){
                strQuery += " AND ( condition = '2' OR condition = '12' OR condition = '02' OR condition = '012' )";
            }
        }
        else if(strDealsWith.length() == 2)
        {
            if(strDealsWith.equals("01")){
                strQuery += " AND ( condition = '01' OR condition = '012' )";
            }
            else if(strDealsWith.equals("02")){
                strQuery += " AND ( condition = '02' OR condition = '012' )";
            }
            else if(strDealsWith.equals("12")){
                strQuery += " AND ( condition = '12' OR condition = '012' )";
            }
        }
        else if(strDealsWith.length() == 3)
        {
            strQuery += " AND ( condition = '012' )";
        }

        Cursor cursor = db.rawQuery(strQuery, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0)
        {
            String string = cursor.getString(0).toString();

            return  string;
        }
        else
        {
            return "NOT_FOUND";
        }
    }


    public List<Pet> getOwnedPet(String strAndroidID)
    {
        List<Pet> lstRet = new ArrayList<Pet>();
        String strQuery = "SELECT * FROM Pets WHERE androidid = '" + strAndroidID + "'";

        Cursor Res = db.rawQuery(strQuery, null);
        Res.moveToFirst();

        for(int nIndex = 0; nIndex < Res.getCount(); nIndex++)
        {
            Pet p = new Pet();
            p.setName(Res.getString((0)));
            p.setId(Res.getInt((1)));
            p.setAndroidId(Res.getString((2)));
            p.setGender(Res.getInt((3)));
            p.setType(Res.getInt((4)));
            p.setCondition(Res.getInt((5)));
            p.setPhoneNumber(Res.getString((6)));
            p.setLocation(Res.getInt((7)));
            p.setEmail(Res.getString((8)));
            p.setNotes(Res.getString((9)));
            p.setAge(Res.getInt((11)));

            lstRet.add(p);
        }

        return lstRet;
    }

    public void addSearchSaved(SearchData searchData)
    {
        int nId = this.GetNextSeqForOz("id");

        String strQuery = "INSERT INTO SavedSearches " +
                          "VALUES( " + nId + ", " +
                                 "'" + searchData.getStrPhonenum() + "', " +
                                     + searchData.getnMinAge() + ", "
                                     + searchData.getnMaxAge() + ", "
                                     + searchData.getnGender() + ", "
                                     + searchData.getnAreaCode() + ", "
                                     + searchData.getnAnimalType() + ", '"
                                     + searchData.getStrCondition() + "' );";

        db.execSQL(strQuery);
    }

    public void LoadDbFromDropBox()
    {
        String url="https://dl.dropboxusercontent.com/u/73386806/Prune%20Juice/Prune%20Juice.exe";
        String filename="PruneJuice.exe";

        try{
            URL download=new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(download.openStream());
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
            fileOut.flush();
            fileOut.close();
            rbc.close();
        }catch(Exception e){ e.printStackTrace(); }
    }
    public int GetNextSeq(String strColName){
        String strCommand = "SELECT MAX(" + strColName + ") FROM Pets";
        Cursor Res = db.rawQuery(strCommand,null);

        Res.moveToFirst();
        return Res.getInt(0) + 1;
    }

    public int GetNextSeqForOz(String strColName){
        String strCommand = "SELECT MAX(" + strColName + ") FROM SavedSearches";
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
