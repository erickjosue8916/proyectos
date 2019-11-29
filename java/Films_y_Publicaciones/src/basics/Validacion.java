package basics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public static String dui = "0+\\d{7}-\\d$";
    public static String numeros = "[^0-9]";
    public static String letras = "[^A-Za-z]";
    public static String nit = "\\d{4}-\\d{6}-\\d{3}-\\d{1}";
    public static String decimal = "\\d{1,4}\\.\\d{0,2}";
    public static String fecha = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";

    public static boolean validar(String cadena, String regExp) {
        Pattern patron;
        Matcher match;
        patron = Pattern.compile(regExp);
        match = patron.matcher(cadena);
        return match.find();
    }

    public static boolean isInteger(String n){
        int number;
        try {
            number = Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String n, int min) {
        int number;
        try {
            number = Integer.parseInt(n);
            if (number < min) return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean isDouble(String n, double min) {
        double number;
        try {
            number = Double.parseDouble(n);
            if (number < min) return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
