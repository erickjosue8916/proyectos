package basics;

import javax.swing.*;

public class Mensaje
{
    // mostrar un mensaje de dialogo
    public static void show(String msn)
    {
        JOptionPane.showMessageDialog(null, msn);
    }

    // mostrar un mensaje expresivo
    /*  Posibles valores para tipo (icono del dialogo)
        0: error
        1: Informacion
        2: Advertencia
        3: Interrogante
        otro valor: sin icono
     */
    public static void show(String mensaje, String tituloVentana, int tipo){

        if (tipo >= 0 && tipo < 4) {
            JOptionPane.showMessageDialog(null, mensaje, tituloVentana, tipo );
        } else {
            JOptionPane.showMessageDialog(null, mensaje, tituloVentana, -1 );
        }

    }

    // pedir un dato al usuario
    public static String input(String msn)
    {
        return JOptionPane.showInputDialog(msn);
    }

    // pedir un dato al usuario
    public static String input(String mensaje, String titulo)
    {
        return JOptionPane.showInputDialog(null, mensaje, titulo, 1);
    }
}
