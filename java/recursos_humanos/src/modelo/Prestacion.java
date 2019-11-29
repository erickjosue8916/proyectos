package modelo;

import basics.FormatoDatos;
import conexionDB.Conexion;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public class Prestacion {
    private SimpleDoubleProperty AFP;
    private SimpleDoubleProperty ISSS;
    private SimpleDoubleProperty renta;
    private SimpleDoubleProperty sueldo;
    private Conexion db;
    private List<ModeloImpuesto> imp_isss;
    private List<ModeloImpuesto> imp_afp;
    private List<ModeloImpuesto> imp_renta;


    public Prestacion(double sueldo) {
        db = Conexion.getInstance();
        setImps();
        this.ISSS = new SimpleDoubleProperty(FormatoDatos.redondeo(calculo_isss(sueldo)));
        this.AFP = new SimpleDoubleProperty(FormatoDatos.redondeo(calculo_afp(sueldo)));
        this.renta = new SimpleDoubleProperty(FormatoDatos.redondeo(calculo_renta(sueldo)));
        this.sueldo = new SimpleDoubleProperty(FormatoDatos.redondeo(sueldo - (getAFP() + getISSS() + getRenta())));
    }

    public double getAFP() {
        return AFP.get();
    }

    public SimpleDoubleProperty AFPProperty() {
        return AFP;
    }

    public double getISSS() {
        return ISSS.get();
    }

    public SimpleDoubleProperty ISSSProperty() {
        return ISSS;
    }

    public double getRenta() {
        return renta.get();
    }

    public SimpleDoubleProperty rentaProperty() {
        return renta;
    }

    public double getSueldo() {
        return sueldo.get();
    }

    public SimpleDoubleProperty sueldoProperty() {
        return sueldo;
    }

    // obtiene los detalles de cada impuesto
    private void setImps() {
        List<List<Object>> datos_isss = db.select("impuestos", "modelo.ModeloImpuesto", "where tipo='isss'");
        List<List<Object>> datos_afp = db.select("impuestos", "modelo.ModeloImpuesto", "where tipo='afp'");
        List<List<Object>> datos_renta = db.select("impuestos", "modelo.ModeloImpuesto", "where tipo='renta'");

        imp_isss = ModeloImpuesto.listaImpuestos(datos_isss);
        imp_afp = ModeloImpuesto.listaImpuestos(datos_afp);
        imp_renta = ModeloImpuesto.listaImpuestos(datos_renta);
    }

    private double calculo_isss(double sueldo){
        double impuesto = 0;
        for (int i = imp_isss.size() - 1; i >= 0; i--) {
            if (sueldo >= imp_isss.get(i).vigenciaInit) {
                impuesto = sueldo * imp_isss.get(i).porcentaje + imp_isss.get(i).cuota;
                break;
            }
        }

        return impuesto;
    }

    private double calculo_afp(double sueldo) {
        double impuesto = 0;
        for (int i = imp_afp.size() - 1; i >= 0; i--) {
            if (sueldo >= imp_afp.get(i).vigenciaInit) {
                impuesto = sueldo * imp_afp.get(i).porcentaje + imp_afp.get(i).cuota;
            }
        }
        return impuesto;
    }

    private double calculo_renta(double sueldo){
        double impuesto = 0;
        for (int i = imp_renta.size() - 1; i >= 0; i--) {

            if (sueldo >= imp_renta.get(i).vigenciaInit) {
                System.out.println(imp_renta.get(i).toString());
                impuesto = (sueldo - imp_renta.get(i).vigenciaInit) * imp_renta.get(i).porcentaje + imp_renta.get(i).cuota;
                break;
            }
        }
        return impuesto;

    }
}
