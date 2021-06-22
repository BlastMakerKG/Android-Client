package com.kgprostudio.mytrack.dbhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.kgprostudio.mytrack.MapsActivity;
import com.kgprostudio.mytrack.connectingtoserver.Client;
import com.kgprostudio.mytrack.locationpackage.LocationClass;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.opencsv.CSVWriter;

public class DateBaseTools {

    public static void RecordToDB(SQLiteDatabase database, ContentValues contentValues, LocationClass locationClass, Button customBtn_stop) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(customBtn_stop.isEnabled())
                {
                    try {
                           contentValues.put(DBHelper.DATE_TIME, locationClass.getDate());
                           contentValues.put(DBHelper.LATITUDE, locationClass.getLat());
                           contentValues.put(DBHelper.LONGITUDE, locationClass.getLon());
                           contentValues.put(DBHelper.ALTITUDE, locationClass.getAlt());
                           //contentValues.put(DBHelper.VELOCITY, lastLocation.getSpeed()+1);
                           contentValues.put(DBHelper.DISTANCE, locationClass.getDistance());
                           contentValues.put(DBHelper.VELOCITY, locationClass.getVelocity());
                           database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public  static void ShowDB(SQLiteDatabase database, DBHelper dbHelper)
    {
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int latIndex = cursor.getColumnIndex(DBHelper.LATITUDE);
            int lonIndex = cursor.getColumnIndex(DBHelper.LONGITUDE);
            int altIndex = cursor.getColumnIndex(DBHelper.ALTITUDE);

            do
            {
                Log.d("coor", "ID = " + cursor.getInt(idIndex) + " , lat = " + cursor.getFloat(latIndex) +
                        " , lon = " + cursor.getFloat(lonIndex) + " , alt  = " + cursor.getFloat(altIndex) + "      \t");
            }
            while (cursor.moveToNext());
        }
        else
        {
            Log.d("Exception", "0 rows");
        }
        cursor.close();

       // dbHelper.exportDB1();
        // Log.d("Msg save db", DBHelper.Output.toString());
      //  Log.d("Msg", "msg!!!!!!!!!!!!!!!!!!!!!!!!!! " + dbHelper.getMsg());
    }

    //public static void ExportDBtoCSV()
   // {
   //     File dbFile = getDatabasePath(DBHelper.DATABASE_NAME);
   //     DBHelper dbhelper = new DBHelper(getApplicationContext());
   //     File exportDir = new File(Environment.getExternalStorageDirectory(), "");
   //     if (!exportDir.exists()) {
   //         exportDir.mkdirs();
   //     }
//
   //     File file = new File(exportDir, "csvname1.csv");
   //     try {
   //         Log.d("PATH", exportDir.toString() +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   //         Toast.makeText(MapsActivity.this, "Путь " +exportDir.toString(), Toast.LENGTH_SHORT ).show();
   //         file.createNewFile();
   //         CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
   //         SQLiteDatabase db = dbhelper.getReadableDatabase();
   //         Cursor curCSV = db.rawQuery("SELECT * FROM TruckLocation", null);
   //         csvWrite.writeNext(curCSV.getColumnNames());
   //         while (curCSV.moveToNext()) {
   //             //Which column you want to exprort
   //             String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2)};
   //             csvWrite.writeNext(arrStr);
   //         }
   //         csvWrite.close();
   //         curCSV.close();
   //     } catch (Exception sqlEx) {
   //         status_msg.setText("MainActivity"+ sqlEx.getMessage() + "  "+sqlEx);
   //         Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
   //     }
   // }
}
