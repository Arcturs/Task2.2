package ru.vsu.csf.Sashina;

import javax.swing.*;
import java.io.*;

public class Files {

    public static String [] getNumbers (String nameFile) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(nameFile));
            String s = bf.readLine();
            String [] str = new String[2];
            str[0] = s;
            s = bf.readLine();
            str[1] = s;
            bf.close();
            return str;
        } catch (Exception exp) {
            return null;
        }
    }

    public static void fillFile (String nameFile, ListModel<String> list) {
        try {
            File file1 = new File(nameFile);
            if (!file1.exists()){
                file1.createNewFile();
            }
            PrintWriter pov = new PrintWriter(new FileWriter(file1, true));
            int i = 0;
            pov.println(list.getElementAt(0));
            pov.println(list.getElementAt(1));
            pov.println("--------------------------------------------------------");
            String s = list.getElementAt(2);
            if (s.isEmpty()) {
                pov.println("Ошибка в обработке листа");
            } else {
                pov.println(s);
            }
            pov.close();
        } catch (Exception ep) {
            System.out.println("Ошибка: " + ep);
        }
    }
}
