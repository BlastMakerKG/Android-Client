package com.kgprostudio.mytrack.connectingtoserver;

import android.util.Log;

import com.kgprostudio.mytrack.MainActivity;
import com.kgprostudio.mytrack.locationpackage.LocationClass;

import java.io.PrintWriter;
import java.net.Socket;

public class Client extends MainActivity {

    private Socket socket;


    // Подключение к серверу
    public void ConnectToServer(String ip , int port)
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


  // public  void MultiConnectToServer(String ip , int port)
  // {
  //     Runnable runnable = new Runnable() {
  //         @Override
  //         public void run() {
  //             try{
  //                 socket = new Socket(ip, port);
  //                 Log.d("Connect", "К серверу подключены");

  //             }
  //             catch (Exception exception)
  //             {
  //                 exception.printStackTrace();
  //             }
  //         }
  //     };
  //     Thread thread = new Thread(runnable);
  //     thread.start();

  // }


    // Передача данных на сервер
    public  void TransferToServer(LocationClass loc, int id)
    {
        if(socket != null)
        {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(id+","+loc.ToString());
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

