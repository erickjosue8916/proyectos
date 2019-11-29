package modelos;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    public int id;
    public String nombre;
    public int edad;

    public Persona(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona(String nombre, int edad) {
        this.id = 0;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(id);
        datos.add(nombre);
        datos.add(edad);
        return datos;
    }

    public static Persona crear(List<Object> datos) {
        return (new Persona((Integer) datos.get(0), (String) datos.get(1), (Integer) datos.get(2)));
    }

    public static List<Persona> crearPersonas(List<List<Object>> personas) {
        List<Persona> person = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++) {
            person.add(Persona.crear(personas.get(i)));
        }
        return person;
    }

    public String toString() {
        String datos;
        datos = "\n.::: Persona :::.";
        datos += "\nid: " + id;
        datos += "\nnombre: " + nombre;
        datos += "\nedad: " + edad;
        return datos;
    }
}
