package basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public static String dui = "0+\\d{7}-\\d$";
    public static String numeros = "[^0-9]";
    public static String letras = "[^A-Za-z ]";
    public static String nit = "\\d{4}-\\d{6}-\\d{3}-\\d{1}";
    public static String decimal = "\\d{1,4}\\.\\d{0,2}";
    public static String fecha = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";

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