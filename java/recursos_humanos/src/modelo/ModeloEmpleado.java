package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloEmpleado {
    public int id;
    public String nombre;
    public String sexo;
    public String nit;
    public String dui;
    public String cargo;
    public String telefono;
    public String fechaNac;
    public double sueldo;
    public String contrasenia;

    public ModeloEmpleado(int id, String nombre, String sexo, String nit, String dui, String cargo, String telefono, String fechaNac, double sueldo, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.nit = nit;
        this.dui = dui;
        this.cargo = cargo;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.sueldo = sueldo;
        this.contrasenia = contrasenia;
    }

    public ModeloEmpleado( String nombre, String sexo, String nit, String dui, String cargo, String telefono, String fechaNac, double sueldo, String contrasenia) {

        this.nombre = nombre;
        this.sexo = sexo;
        this.nit = nit;
        this.dui = dui;
        this.cargo = cargo;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.sueldo = sueldo;
        this.contrasenia = contrasenia;
    }

    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(id);
        datos.add(nombre);
        datos.add(sexo);
        datos.add(nit);
        datos.add(dui);
        datos.add(cargo);
        datos.add(telefono);
        datos.add(fechaNac);
        datos.add(sueldo);
        datos.add(contrasenia);
        return datos;
    }

    public static ModeloEmpleado crear(List<Object> datos) {
        return (new ModeloEmpleado((Integer) datos.get(0), (String) datos.get(1),
                (String) datos.get(2), (String) datos.get(3), (String) datos.get(4),
                (String) datos.get(5), (String) datos.get(6), (String) datos.get(7),
                (double) datos.get(8), (String) datos.get(9)) );
    }

    public static List<ModeloEmpleado> crearmodEmpleados(List<List<Object>> personas) {
        List<ModeloEmpleado> person = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++) {
            person.add(ModeloEmpleado.crear(personas.get(i)));
        }
        return person;
    }

    @Override
    public String toString() {
        return "ModeloEmpleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nit='" + nit + '\'' +
                ", dui='" + dui + '\'' +
                ", cargo='" + cargo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", sueldo=" + sueldo +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
