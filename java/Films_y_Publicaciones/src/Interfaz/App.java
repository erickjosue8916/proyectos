package Interfaz;

import db.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JButton btnPeliculas;
    private JButton btnSeries;
    private JButton btnLibros;
    private JButton btnRevistas;
    private JButton btnReportes;
    private JPanel panel;
    private JPanel vista;
    private LibrosGUI vLibros;
    private PeliculasGUI peliculasGUI1;
    private JPanel vista2;
    private JPanel vista3;
    private JPanel vista4;
    private JPanel vista5;
    private SeriesGUI seriesGUI1;
    private RevistaGUI revistaGUI1;
    private ReportesGUI reportesGUI1;
    private Conexion connect;

    public App (){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setResizable(false);

        add(panel);

        setVisible(true);

        ocultarTodo();
        vista2.setVisible(true);
        btnPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ocultarTodo();
                vista2.setVisible(true);
            }

        });
        btnSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ocultarTodo();
                vista3.setVisible(true);
            }
        });
        btnLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ocultarTodo();
                vista.setVisible(true);
            }
        });
        btnRevistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ocultarTodo();
                vista4.setVisible(true);
            }
        });
        btnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ocultarTodo();
                vista5.setVisible(true);
            }
        });
    }



    private void createUIComponents() {
        connect = Conexion.getInstance("jdbc:mysql://localhost/practicasProgra", "erickdb", "", "MySql");
        connect.connect();

        panel = new JPanel();
        vista = new JPanel();
        vista2 = new JPanel();

        // TODO: place custom component creation code here
    }

    private void $$$setupUI$$$(){
        createUIComponents();

    }

    private void ocultarTodo(){
        vista.setVisible(false);
        vista2.setVisible(false);
        vista3.setVisible(false);
        vista4.setVisible(false);
        vista5.setVisible(false);
    }
}
