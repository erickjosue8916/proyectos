package Interfaz;

import basics.Mensaje;
import basics.Validacion;
import db.Conexion;
import modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class LibrosGUI extends JFrame{
    Conexion connect;
    private JPanel panel;
    private JButton btnSave;
    private JTextField id;
    private JTextField titulo;
    private JTextField autor;
    private JTextField genero;
    private JTextField precio;
    private JTable tabla;
    private DefaultTableModel model;

    public LibrosGUI() {

        /*setSize(500,500);
        setVisible(true);
        add(panel);*/

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
        model.setColumnIdentifiers(new String[] {"id", "titulo", "autor", "genero", "precio"});
        tabla.setModel(model);
        llenarTabla();
    }

    private void llenarTabla() {
        limpiar();
        model.addRow(new Object[] {"id", "titulo", "autor", "genero", "precio"});
        List<List<Object>> libros = connect.select("libros", "modelo.Libro", "");
        List<Libro> lista_libros = Libro.crearLibro(libros);
        for (int i = 0; i < lista_libros.size(); i++) {

            model.addRow(lista_libros.get(i).getDatosOrd());
        }
    }

    private void limpiar () {
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
        autor.setText("");
        precio.setText("");
    }

    private boolean datoVacio() {
        return ((id.getText().isEmpty() || titulo.getText().isEmpty()) ||
                (genero.getText().isEmpty() || (autor.getText().isEmpty() || precio.getText().isEmpty())) );
    }

    private void guardar() {
        Libro p = new Libro();
        p.setId(Integer.parseInt(id.getText()));
        p.setTitulo(titulo.getText());
        p.setAutor(autor.getText());
        p.setGenero(genero.getText());
        p.setPrecio(Double.parseDouble(precio.getText()));
        connect.insertar("libros", "modelo.Libro", p.getDatos());
        clear();
        llenarTabla();
    }

    public JPanel getPanel(){
        return panel;
    }
   /* private void createUIComponents() {

        connect = Conexion.getInstance();
        panel = new JPanel();
        // TODO: place custom component creation code here
    }

    private void $$$setupUI$$$(){
        createUIComponents();
    }*/
}
