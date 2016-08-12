package com.petbook.ido.petbook.BL;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Omri on 12/08/2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    private static String DATABASE_PATH = "/data/data/petbook/databases/";
    private static String DATABASE_NAME = "pets1.db";
    private static SQLiteDatabase myDataBase;
    private static Context mContext;
    private static String CREATE_TABLES = "CREATE TABLE `Pets` (\n" +
            "\t`name`\tTEXT,\n" +
            "\t`id`\tINTEGER NOT NULL,\n" +
            "\t`androidid`\tINTEGER,\n" +
            "\t`gender`\tINTEGER,\n" +
            "\t`type`\tINTEGER,\n" +
            "\t`conditions`\tINTEGER,\n" +
            "\t`phonenumber`\tINTEGER,\n" +
            "\t`location`\tINTEGER,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`notes`\tTEXT,\n" +
            "\t`picture`\tBLOB\n" +
            ");";
    //, SQLiteDatabase.CursorFactory factory, int version
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
        SQLiteDatabase db = getWritableDatabase();
        this.onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(this.CREATE_TABLES);
            commit(db);
        }
        catch (Exception ex){

        }
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
