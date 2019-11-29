package Interfaz;

import basics.Mensaje;
import basics.Validacion;
import db.Conexion;
import modelo.Reporte;
import modelo.Revista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportesGUI{
    private JPanel panel;
    private JTable tabla;
    private JButton btnSave;
    private JTextField id;
    private JTextField titulo;
    private JTextField genero;
    private JTextField autor;
    private JTextField fecha;
    private DefaultTableModel model;
    Conexion connect;

    public ReportesGUI() {

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
        model.setColumnIdentifiers(new String[] {"id", "titulo", "genero", "autor", "fecha"});
        tabla.setModel(model);
        llenarTabla();
    }

    private void llenarTabla() {
        limpiar();
        model.addRow(new Object[] {"id", "titulo", "genero", "autor", "fecha"});
        List<List<Object>> reportes = connect.select("reportes", "modelo.Reporte", "");


        List<Reporte> lista_reportes = Reporte.crearReporte(reportes);
        for (int i = 0; i < lista_reportes.size(); i++) {

            model.addRow(lista_reportes.get(i).getDatosOrd());
        }
    }

    public void limpiar () {
        int filas = model.getRowCount();
        for (int i = 0; i < filas; i++) {
            model.removeRow(0);
        }
    }

    private boolean validarDatos() {
        return (Validacion.isInteger(id.getText(), 1));
    }

    private void clear() {
        id.setText("");
        titulo.setText("");
        genero.setText("");
        autor.setText("");
        fecha.setText("");
    }

    private boolean datoVacio() {
        return ((id.getText().isEmpty() || titulo.getText().isEmpty()) ||
                (genero.getText().isEmpty() || (autor.getText().isEmpty() || fecha.getText().isEmpty())) );
    }

    private void guardar() {
        Reporte p = new Reporte();
        p.setId(Integer.parseInt(id.getText()));
        p.setTitulo(titulo.getText());
        p.setAutor(autor.getText());
        p.setGenero(genero.getText());
        p.setFecha(fecha.getText());
        connect.insertar("reportes", "modelo.Reporte", p.getDatos());
        clear();
        llenarTabla();
    }

}
