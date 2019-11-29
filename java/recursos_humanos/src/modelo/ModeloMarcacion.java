package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloMarcacion {
    public int id;
    public String fecha;
    public int idEmpleado;
    public String hora;
    public String tipo;

    public ModeloMarcacion(int id, String fecha, int idEmpleado, String hora, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.hora = hora;
        this.tipo = tipo;
    }

    public ModeloMarcacion(String fecha, int idEmpleado, String hora, String tipo) {
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.hora = hora;
        this.tipo = tipo;
    }

    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(id);
        datos.add(fecha);
        datos.add(idEmpleado);
        datos.add(hora);
        datos.add(tipo);
        return datos;
    }

    public static ModeloMarcacion crear(List<Object> datos) {
        return (new ModeloMarcacion((Integer) datos.get(0), (String) datos.get(1),
                (Integer) datos.get(2), (String) datos.get(3), (String) datos.get(4)));
    }

    public static List<ModeloMarcacion> crearmodMarcaciones(List<List<Object>> marcaciones) {
        List<ModeloMarcacion> marcacion = new ArrayList<>();
        for (int i = 0; i < marcaciones.size(); i++) {
            marcacion.add(ModeloMarcacion.crear(marcaciones.get(i)));
        }
        return marcacion;
    }

    @Override
    public String toString() {
        return "ModeloMarcacion{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", hora='" + hora + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
