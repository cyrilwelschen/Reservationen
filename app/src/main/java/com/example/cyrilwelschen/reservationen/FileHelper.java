package com.example.cyrilwelschen.reservationen;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * Created by cyril on 07.07.18.
 */

public class FileHelper {
    private final static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final static String TAG = FileHelper.class.getName();

    public static  String ReadFile(String fileName){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        } catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }
}
