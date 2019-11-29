package modelo;

import java.util.ArrayList;
import java.util.List;

public class Revista extends Publicacion {
    public String editorial;
    public double precio;

    public Revista() {
    }

    public Revista(String editorial, double precio, int id, String titulo, String genero ) {
        super(id, titulo, genero);
        this.editorial = editorial;
        this.precio = precio;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString () {
        String datos;

        datos = "\nid: " + id;
        datos += "\ntitulo: " + titulo;
        datos += "\ngenero: " + genero;
        datos += "\neditorial: " + editorial;
        datos += "\nprecio: " + precio + "\n";
        return datos;
    }

    public List<Object> getDatos() {
            List<Object> datos = new ArrayList<Object>();
            datos.add(editorial);
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
        datos[3] = editorial;
        datos[4] = precio;
        return datos;
    }

        public static Revista crear(List<Object> datos) {
            return (new Revista((String) datos.get(0), (double) datos.get(1), (Integer) datos.get(2), (String) datos.get(3), (String) datos.get(4)));
        }

        public static List<Revista> crearRevista(List<List<Object>> revistas) {
            List<Revista> lista_revistas = new ArrayList<>();
            for (int i = 0; i < revistas.size(); i++) {
                lista_revistas.add(Revista.crear(revistas.get(i)));
            }
            return lista_revistas;
        }
}
