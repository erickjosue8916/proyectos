package Interfaz;

import basics.Mensaje;
import basics.Validacion;
import db.Conexion;
import modelo.Pelicula;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PeliculasGUI{
    private JPanel panel;
    private JTable tabla;
    private JButton btnSave;
    private JTextField id;
    private JTextField titulo;
    private JTextField genero;
    private JTextField duracion;
    private DefaultTableModel model;
    private Conexion connect;

    public PeliculasGUI () {
        connect = Conexion.getInstance();
        iniciarTabla();
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (datoVacio()) {
                    Mensaje.show("Llena todos los campos");
                    return;
                }

                if (!validarDatos()) {
                    Mensaje.show("Los datos no son validos");
                    return;
                }
                guardar();
            }
        });
    }

    private void iniciarTabla() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"id", "titulo", "genero", "duracion"});
        tabla.setModel(model);
        llenarTabla();
    }

    private void llenarTabla() {
        limpiar();
        model.addRow(new Object[] {"id", "titulo", "genero", "duracion"});
        List<List<Object>> peliculas = connect.select("peliculas", "modelo.Pelicula", "");
        List<Pelicula> lista_peliculas = Pelicula.crearPeliculas(peliculas);
        for (int i = 0; i < lista_peliculas.size(); i++) {
            model.addRow(lista_peliculas.get(i).getDatosOrd());
        }
    }

    public void limpiar () {
        int filas = model.getRowCount();
        for (int i = 0; i < filas; i++) {
            model.removeRow(0);
        }
    }

    private boolean validarDatos() {
        return (Validacion.isInteger(id.getText(), 1) && Validacion.isInteger(duracion.getText(), 1));
    }

    private void clear() {
        id.setText("");
        titulo.setText("");
        genero.setText("");
        duracion.setText("");

    }

    private boolean datoVacio() {
        return ((id.getText().isEmpty() || titulo.getText().isEmpty()) ||
                (genero.getText().isEmpty() || duracion.getText().isEmpty() ) );
    }

    private void guardar() {
        Pelicula p = new Pelicula();
        p.setId(Integer.parseInt(id.getText()));
        p.setTitulo(titulo.getText());
        p.setDuracion(Integer.parseInt(duracion.getText()));
        p.setGenero(genero.getText());
        connect.insertar("peliculas", "modelo.Pelicula", p.getDatos());
        clear();
        llenarTabla();
    }


}
