package modelo;

import java.util.ArrayList;
import java.util.List;

public class Libro extends Publicacion{

    public String autor;
    public double precio;

    public Libro() {
    }

    public Libro(String autor, double precio,  int id, String titulo,  String genero ) {
        super(id, titulo, genero);
        this.autor = autor;
        this.precio = precio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public List<Object> getDatos() {
            List<Object> datos = new ArrayList<Object>();
            datos.add(autor);
            datos.add(precio);
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
        datos[3] = autor;
        datos[4] = precio;
        return datos;
    }


    public String toString () {
        String datos;

        datos = "\nid: " + id;
        datos += "\ntitulo: " + titulo;
        datos += "\nautor: " + autor;
        datos += "\ngenero: " + genero;
        datos += "\nprecio: " + precio + "\n";
        return datos;
    }

        public static Libro crear(List<Object> datos) {
            return (new Libro((String) datos.get(0), (double) datos.get(1), (Integer) datos.get(2), (String) datos.get(3), (String) datos.get(4)));
        }

        public static List<Libro> crearLibro(List<List<Object>> libros) {
            List<Libro> lista_libros = new ArrayList<>();
            for (int i = 0; i < libros.size(); i++) {
                lista_libros.add(Libro.crear(libros.get(i)));
            }
            return lista_libros;
        }
}