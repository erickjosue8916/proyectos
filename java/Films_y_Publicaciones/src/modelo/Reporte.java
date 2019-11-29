package modelo;

import java.util.ArrayList;
import java.util.List;

public class Reporte extends Publicacion{

    public String autor;
    public String fecha;

    public Reporte() {

    }

    public Reporte(String autor, String fecha, int id, String titulo, String genero) {
        super(id, titulo, genero);
        this.autor = autor;
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(autor);
        datos.add(fecha);
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
        datos[4] = fecha;
        return datos;
    }

    @Override
    public String toString() {
        String datos;
        datos = "\nid: " + id;
        datos += "\ntitulo: " + titulo;
        datos += "\ngenero: " + genero;
        datos += "\nAutor: " + autor;
        datos += "\nFecha de publicacion: " + fecha + "\n";
        return datos;
    }

        public static Reporte crear(List<Object> datos) {
            return (new Reporte((String) datos.get(0), (String) datos.get(1), (Integer) datos.get(2), (String) datos.get(3), (String) datos.get(4)));
        }

        public static List<Reporte> crearReporte(List<List<Object>> reposrtes) {
            List<Reporte> lista_reposrtes = new ArrayList<>();
            for (int i = 0; i < reposrtes.size(); i++) {
                lista_reposrtes.add(Reporte.crear(reposrtes.get(i)));
            }
            return lista_reposrtes;
        }
}
