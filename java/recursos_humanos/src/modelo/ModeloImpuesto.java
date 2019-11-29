package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloImpuesto {
    public int id;
    public String tipo;
    public double porcentaje;
    public double cuota;
    public double vigenciaInit;

    public ModeloImpuesto(int id, String tipo, double porcentaje, double cuota, double vigenciaInit) {
        this.id = id;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.cuota = cuota;
        this.vigenciaInit = vigenciaInit;
    }

    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(id);
        datos.add(tipo);
        datos.add(porcentaje);
        datos.add(cuota);
        datos.add(vigenciaInit);
        return datos;
    }

    public static ModeloImpuesto crear(List<Object> datos) {
        return (new ModeloImpuesto((Integer) datos.get(0), (String) datos.get(1),
                (double) datos.get(2), (double) datos.get(3), (double) datos.get(4)));
    }

    public static List<ModeloImpuesto> listaImpuestos(List<List<Object>> impuestos) {
        List<ModeloImpuesto> imp = new ArrayList<>();
        for (int i = 0; i < impuestos.size(); i++) {
            imp.add(ModeloImpuesto.crear(impuestos.get(i)));
        }
        return imp;
    }

    @Override
    public String toString() {
        return "ModeloImpuesto{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", porcentaje=" + porcentaje +
                ", cuota=" + cuota +
                ", vigenciaInit=" + vigenciaInit +
                '}';
    }
}
