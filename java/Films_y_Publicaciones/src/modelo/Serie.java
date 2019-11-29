package modelo;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Film {
    public int temporadas;
    public int capitulos;

    public Serie() {
    }

    public Serie(int temporadas, int capitulos, int id, String titulo, String genero) {
        super(id, titulo, genero);
        this.temporadas = temporadas;
        this.capitulos = capitulos;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public String toString () {
        String datos;

        datos = "\nid: " + id;
        datos += "\ntitulo: " + titulo;
        datos += "\ngenero: " + genero;
        datos += "\ntemporadas: " + temporadas;
        datos += "\ncapitulos: " + capitulos + "\n";
        return datos;
    }

    public List<Object> getDatos() {
        List<Object> datos = new ArrayList<Object>();

        datos.add(temporadas);
        datos.add(capitulos);
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
        datos[3] = temporadas;
        datos[4] = capitulos;
        return datos;
    }

    public static Serie crear(List<Object> datos) {
        return (new Serie((Integer) datos.get(0), (Integer) datos.get(1), (Integer) datos.get(2), (String) datos.get(3), (String) datos.get(4)));
    }

    public static List<Serie> crearSerie(List<List<Object>> series) {
        List<Serie> listaSeries = new ArrayList<>();
        for (int i = 0; i < series.size(); i++) {
            listaSeries.add(Serie.crear(series.get(i)));
        }
        return listaSeries;
    }
}
