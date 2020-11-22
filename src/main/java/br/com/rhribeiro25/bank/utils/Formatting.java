package br.com.rhribeiro25.bank.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

public class Formatting {

    public static Date stringToDate_yyyy_MM_dd__HH_mm_ss(String date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date formatted = null;
            try {
                formatted = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return formatted;
        }
        return null;
    }

    public static String dateToString_dd_MM_yyyy__HH_mm_ss(Date date) {
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String formatted = dateFormat.format(date);
            return formatted;
        }
        return null;
    }
}
