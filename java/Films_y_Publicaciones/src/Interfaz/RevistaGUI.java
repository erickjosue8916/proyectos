package Interfaz;

import basics.Mensaje;
import basics.Validacion;
import db.Conexion;
import modelo.Libro;
import modelo.Revista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RevistaGUI{
    private JPanel panel;
    private JButton guardarButton;
    private JTable tabla;
    private JTextField id;
    private JTextField titulo;
    private JTextField genero;
    private JTextField editorial;
    private JTextField precio;
    private DefaultTableModel model;
    Conexion connect;

    public RevistaGUI() {
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
        model.setColumnIdentifiers(new String[] {"id", "titulo", "genero", "editorial", "precio"});
        tabla.setModel(model);
        llenarTabla();
    }

    private void llenarTabla() {
        limpiar();
        model.addRow(new Object[] {"id", "titulo", "genero", "editorial", "precio"});
        List<List<Object>> revistas = connect.select("revistas", "modelo.Revista", "");


        List<Revista> lista_revistas = Revista.crearRevista(revistas);
        for (int i = 0; i < lista_revistas.size(); i++) {

            model.addRow(lista_revistas.get(i).getDatosOrd());
        }
    }

    public void limpiar () {
        int filas = model.getRowCount();
        for (int i = 0; i < filas; i++) {
            model.removeRow(0);
        }
    }

    private boolean validarDatos() {
        return (Validacion.isInteger(id.getText(), 1) && Validacion.isDouble(precio.getText(), 1));
    }

    private void clear() {
        id.setText("");
        titulo.setText("");
        genero.setText("");
        editorial.setText("");
        precio.setText("");
    }

    private boolean datoVacio() {
        return ((id.getText().isEmpty() || titulo.getText().isEmpty()) ||
                (genero.getText().isEmpty() || (editorial.getText().isEmpty() || precio.getText().isEmpty())) );
    }

    private void guardar() {
        Revista p = new Revista();
        p.setId(Integer.parseInt(id.getText()));
        p.setTitulo(titulo.getText());
        p.setEditorial(editorial.getText());
        p.setGenero(genero.getText());
        p.setPrecio(Double.parseDouble(precio.getText()));
        connect.insertar("revistas", "modelo.Revista", p.getDatos());
        clear();
        llenarTabla();
    }
}
