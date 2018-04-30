package com.iglesias.c.mercuriomovil.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Ciglesias on 25/04/2018.
 */

public class UtilsFiles {


    public static String readFile(String path) {
        String result = "";
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int i = 0;
            String line = "";
            while (line != null) {
                result += line;
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
