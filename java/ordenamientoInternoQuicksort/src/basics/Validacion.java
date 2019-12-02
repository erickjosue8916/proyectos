package basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public static String numbers = "[^0-9]";
    public static String letters = "[^A-Za-z ]";

    public static boolean validar(String cadena, String regExp, boolean invertir) {
        Pattern patron;
        Matcher match;
        patron = Pattern.compile(regExp);
        match = patron.matcher(cadena);
        return (invertir) ?  !match.find() : match.find();
    }

    public static boolean validar(String cadena, String regExp, int longMax, boolean invertir) {
        boolean sintax = validar(cadena, regExp, invertir);
        boolean longitud =  cadena.length() > 0 && cadena.length() < longMax;
        return sintax && longitud;
    }

    public static boolean numeroEntero(String dato) {
        int number;
        try {
            number = Integer.parseInt(dato);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean numeroEntero(String dato, int min) {
        if (numeroEntero(dato) && Integer.parseInt(dato) >= min){
            return true;
        }
        return false;
    }

    public static boolean decimal(String dato) {
        double number;
        try {
            number = Double.parseDouble(dato);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean decimal(String dato, double min) {
        if (decimal(dato) && Double.parseDouble(dato) >= min){
            return true;
        }
        return false;
    }

    public static boolean fecha(String f) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            formato.setLenient(false);
            formato.parse(f);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}