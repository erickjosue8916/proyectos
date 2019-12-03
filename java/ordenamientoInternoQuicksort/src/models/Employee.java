package models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public int id;
    public String codigo;
    public String nombre;
    public String apellido;
    public int edad;
    public String telefono;
    public int salarioBase;
    public String puesto;
    public String pais;
    public String departamento;

    public Employee(int id, String codigo, String nombre, String apellido, int edad, String telefono, int salarioBase, String puesto, String pais, String departamento) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
        this.puesto = puesto;
        this.pais = pais;
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Datos:" +
                "\nid          : " + id +
                "\ncodigo      : " + codigo +
                "\nnombre      : " + nombre +
                "\napellido    : " + apellido +
                "\nedad        : " + edad +
                "\ntelefono    : " + telefono +
                "\nsalarioBase : " + salarioBase +
                "\npuesto      : " + puesto +
                "\npais        : " + pais +
                "\ndepartamento: " + departamento;
    }
}
