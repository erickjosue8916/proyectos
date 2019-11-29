package Interfaz;

import basics.Mensaje;
import basics.Validacion;
import db.Conexion;
import modelo.Reporte;
import modelo.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SeriesGUI {
    private JPanel panel;
    private JTextField id;
    private JTextField titulo;
    private JTextField genero;
    private JTextField temporadas;
    private JTextField capitulos;
    private JButton guardarButton;
    private JTable tabla;
    private DefaultTableModel model;
    private Conexion connect;

    public SeriesGUI() {

        connect = Conexion.getInstance();
        iniciarTabla();

        guardarButton.addActionListener(new ActionListener() {
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
        model.setColumnIdentifiers(new String[] {"id", "titulo", "genero", "temporadas", "capitulos"});
        tabla.setModel(model);
        llenarTabla();
    }

    private void llenarTabla() {
        limpiar();
        model.addRow(new Object[] {"id", "titulo", "genero", "temporadas", "capitulos"});
        List<List<Object>> series = connect.select("serie", "modelo.Serie", "");


        List<Serie> lista_series = Serie.crearSerie(series);
        for (int i = 0; i < lista_series.size(); i++) {

            model.addRow(lista_series.get(i).getDatosOrd());
        }
    }

    public void limpiar () {
        int filas = model.getRowCount();
        for (int i = 0; i < filas; i++) {
            model.removeRow(0);
        }
    }


    private boolean validarDatos() {
        return ((Validacion.isInteger(id.getText(), 1) && Validacion.isInteger(capitulos.getText(), 1)) &&
                Validacion.isInteger(capitulos.getText(), 1));
    }

    private void clear() {
        id.setText("");
        titulo.setText("");
        genero.setText("");
        temporadas.setText("");
        capitulos.setText("");
    }

    private boolean datoVacio() {
        return ((id.getText().isEmpty() || titulo.getText().isEmpty()) ||
                (genero.getText().isEmpty() || (temporadas.getText().isEmpty() || capitulos.getText().isEmpty())) );
    }

    private void guardar() {
        Serie p = new Serie();
        p.setId(Integer.parseInt(id.getText()));
        p.setTitulo(titulo.getText());
        p.setTemporadas(Integer.parseInt(temporadas.getText()));
        p.setGenero(genero.getText());
        p.setCapitulos(Integer.parseInt(capitulos.getText()));
        connect.insertar("serie", "modelo.Serie", p.getDatos());
        clear();
        llenarTabla();
    }

}
