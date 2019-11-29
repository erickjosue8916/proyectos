package basics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatoDatos {
    public static double redondeo (double n){
        n *= 100;
        n = Math.round(n);
        n /= 100;
        return n;
    }

    public static String fecha (String formato) {
        Date fecha = new Date();
        DateFormat hourFormat = new SimpleDateFormat(formato);
        return hourFormat.format(fecha);
    }
}
