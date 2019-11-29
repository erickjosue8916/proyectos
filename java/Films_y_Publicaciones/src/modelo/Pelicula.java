package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula extends Film {

    public int duracion;

    public Pelicula() { super(); }
    public  Pelicula(int duracion, int id, String titulo,  String genero) {
        super(id, titulo, genero);
        this.duracion = duracion;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String toString () {
        String datos;

        datos = "\nid: " + id;
        datos += "\ntitulo: " + titulo;
        datos += "\nduracion: " + duracion;
        datos += "\ngenero: " + genero + "\n";
        return datos;
    }
    
    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(duracion);
        datos.add(id);
        datos.add(titulo);
        datos.add(genero);
        return datos;
    }

    public Object[] getDatosOrd() {
        Object[] datos = new Object[5];
        datos[0] = id;
        datos[1] = titulo;
        datos[2] = genero;
        datos[3] = duracion;
        return datos;
    }

    public static Pelicula crear(List<Object> datos) {
        return (new Pelicula((Integer) datos.get(0), (Integer) datos.get(1), (String) datos.get(2), (String) datos.get(3)));
    }

    public static List<Pelicula> crearPeliculas(List<List<Object>> peliculas) {
        List<Pelicula> person = new ArrayList<>();
        for (int i = 0; i < peliculas.size(); i++) {
            person.add(Pelicula.crear(peliculas.get(i)));
        }
        return person;
    }
}
