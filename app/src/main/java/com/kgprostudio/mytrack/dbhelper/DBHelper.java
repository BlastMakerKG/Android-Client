package com.kgprostudio.mytrack.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static android.widget.Toast.makeText;

public class DBHelper extends SQLiteOpenHelper {
    public  static final int DATABASE_VERSION = 1;
    public  static final String DATABASE_NAME = "TruckLocation1.db";
    public  static final String TABLE_CONTACTS = "TruckLocation";

    public  static final String KEY_ID = "loc_id";
    public  static final String DATE_TIME = "_dtime";
    public  static final String LATITUDE = "_latitude";
    public  static final String LONGITUDE = "_longitude";
    public  static final String ALTITUDE = "_altitude";
    public  static final String DISTANCE = "_distance";
    public  static final String VELOCITY = "_veloc";

    public  String Output= "";

    public  String getMsg()
    {
        return Output;
    }

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_CONTACTS + "("
                +  KEY_ID + " INTEGER  PRIMARY KEY NOT NULL , "
                +  DATE_TIME + " datetime ,"
                +  LATITUDE + " REAL,"
                +  LONGITUDE+ " REAL,"
                +  ALTITUDE+  " REAL,"
                + DISTANCE +  " REAL,"
                + VELOCITY + " REAL"
                +  " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);

        onCreate(db);

    }

    public static void exportDB() {
        // TODO Auto-generated method stub

        File direct = new File(Environment.getExternalStorageDirectory() + "/Exam Creator");

        if (!direct.exists()) {
            if (direct.mkdir()) {
                try {
                    File sd = Environment.getExternalStorageDirectory();
                    File data = Environment.getDataDirectory();

                    if (sd.canWrite()) {
                        String  currentDBPath= "//data//" + "com.kgprostudio.androidgps"
                                + "//databases//" + DATABASE_NAME;
                        String backupDBPath  = "/BackupFolder/DatabaseName";
                        File currentDB = new File(data, currentDBPath);
                        File backupDB = new File(sd, backupDBPath);

                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();


                    }
                } catch (Exception e) {


                }


            }

        }

    }


    public void exportDB1()
    {

        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.kgprostudio.androidgps" +"/databases/"+DATABASE_NAME ;
        String backupDBPath = "/data/"+ "com.kgprostudio.androidgps" +"/cache";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        Output = "Успешно экспортирована ";
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Output = "Успешно экспортирована ";
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
