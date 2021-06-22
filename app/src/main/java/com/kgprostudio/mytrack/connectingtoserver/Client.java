package com.kgprostudio.mytrack.connectingtoserver;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.kgprostudio.mytrack.MapsActivity;
import com.kgprostudio.mytrack.locationpackage.LocationClass;

import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Client extends MapsActivity{

    static Socket socket;

    public static  void ConnectToServer(String ip , int port)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new Socket(ip, port);
                    Log.d("Connect", "К серверу подключены");
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }


    public static  void MultiConnectToServer(String ip , int port)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new Socket(ip, port);
                    Log.d("Connect", "К серверу подключены");

                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    public static void TransferToServer(LocationClass loc)
    {
        if(socket != null)
        {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(loc.ToString());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        Log.d("msg" , "Не удается подключиться");
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}

