/*
    Autor: Erick Saravia
    ultima modificacion: 24 / 11 / 2019
 */
package com.company;

import ConexionDB.Conexion;
import modelos.Persona;
import javax.script.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, ScriptException {
        // Modelo de pruebas
        Persona p = new Persona(1, "josue", 22);

        // realizar la conexion
        Conexion connect = Conexion.getInstance("jdbc:mysql://localhost/test", "erickdb", "", "MySql");

        // ejecutar la conexion
        connect.connect();

// operaciones basicas
//        connect.insertar("personas", "modelos.Persona", p.getDatos(), 1);
//        connect.eliminar("personas", "id", "1", true);
//        connect.actualizar("persona", "modelos.Persona", p.getDatos());

// seleccionar todas las personas
       /* List<List<Object>> personas = connect.select("personas", "modelos.Persona", "");
        List<Persona> person = Persona.crearPersonas(personas);
        for (int i = 0; i < person.size(); i++) {
            System.out.println(person.get(i).toString());
        }*/
       // prueba de conteo
        System.out.println(connect.conteo("personas", "where id=2"));
    }
}

