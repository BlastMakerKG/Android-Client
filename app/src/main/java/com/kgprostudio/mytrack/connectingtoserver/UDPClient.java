package com.kgprostudio.mytrack.connectingtoserver;

import android.util.Log;

import com.kgprostudio.mytrack.MainActivity;
import com.kgprostudio.mytrack.locationpackage.LocationClass;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class UDPClient extends MainActivity {

    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;
    private int port_n;

    public UDPClient() throws SocketException {
        socket = new DatagramSocket();
    }


    // Подключение к серверу
    public void ConnectToServer(String ip , int port)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    address = InetAddress.getByName(ip);
                    port_n = port;
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


    // Передача данных на сервер
    public  void TransferToServer(LocationClass loc, int id)
    {
        if(socket != null)
        {
            Runnable runnable = () -> {
                try {
//                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                        out.println(id+","+loc.ToString());
                    buf = (id+","+loc.ToString()).getBytes();
                    System.out.println(buf.length);
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port_n);
                    socket.send(packet);
//                        packet = new DatagramPacket(buf, buf.length);
//                        socket.receive(packet);
//                        String received = new String(
//                                packet.getData(), 0, packet.getLength());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    Log.d("msg" , "Не удается подключиться");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
