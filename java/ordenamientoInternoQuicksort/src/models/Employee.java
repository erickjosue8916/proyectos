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

    public Employee(int id, String codigo, String nombre, String apellido, int edad, String telefono, int salaripBase, String puesto, String pais, String departamento) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.salarioBase = salaripBase;
        this.puesto = puesto;
        this.pais = pais;
        this.departamento = departamento;
    }

    public List<Object> getData() {
        List<Object> datos = new ArrayList<Object>();
        datos.add(id);
        datos.add(codigo);
        datos.add(nombre);
        datos.add(apellido);
        datos.add(edad);
        datos.add(telefono);
        datos.add(salarioBase);
        datos.add(puesto);
        datos.add(pais);
        datos.add(departamento);
        return datos;
    }

    public static Employee create(List<Object> datos) {
        return (new Employee((Integer) datos.get(0), (String) datos.get(1),
                (String) datos.get(2), (String) datos.get(3), (Integer) datos.get(4),
                (String) datos.get(5), (Integer) datos.get(6), (String) datos.get(7),
                (String) datos.get(7), (String) datos.get(8) ));
    }

    public static List<Employee> createEmployeeList(List<List<Object>> employees) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeList.add(Employee.create(employees.get(i)));
        }
        return employeeList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", salarioBase=" + salarioBase +
                ", puesto='" + puesto + '\'' +
                ", pais='" + pais + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
