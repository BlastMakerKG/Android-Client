package com.kgprostudio.mytrack.file_system;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.kgprostudio.mytrack.MainActivity;
import com.kgprostudio.mytrack.R;
import com.kgprostudio.mytrack.locationpackage.LocationClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class XMLWorker {

    private static final String fileName = "hello.xml";

    public void writeFile(LocationClass loc) {
        try {
            /*
             * Создается объект файла, при этом путь к файлу находиться методом класcа Environment
             * Обращение идёт, как и было сказано выше к внешнему накопителю
             */
            File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);
            myFile.createNewFile();                                         // Создается файл, если он не был создан
            FileOutputStream outputStream = new FileOutputStream(myFile);   // После чего создаем поток для записи
            outputStream.write(loc.ToString().getBytes());                            // и производим непосредственно запись
            outputStream.close();

            Log.d("File","Файл успешно создан");
            /*
             * Вызов сообщения Toast не относится к теме.
             * Просто для удобства визуального контроля исполнения метода в приложении
             */


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("File","Не удалось создать файл");
        }
    }

    private void readFile() {
        /*
         * Аналогично создается объект файла
         */
        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);
        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            /*
             * Буфферезируем данные из выходного потока файла
             */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            /*
             * Класс для создания строк из последовательностей символов
             */
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                /*
                 * Производим построчное считывание данных из файла в конструктор строки,
                 * Псоле того, как данные закончились, производим вывод текста в TextView
                 */
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                Log.d("readFile",stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
