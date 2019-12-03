package models;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Object> getData(Employee e) {
        List<Object> datos = new ArrayList<Object>();
        datos.add(e.id);
        datos.add(e.codigo);
        datos.add(e.nombre);
        datos.add(e.apellido);
        datos.add(e.edad);
        datos.add(e.telefono);
        datos.add(e.salarioBase);
        datos.add(e.puesto);
        datos.add(e.pais);
        datos.add(e.departamento);
        return datos;
    }

    public static Employee create(List<Object> datos) {
        return (new Employee((Integer) datos.get(0), (String) datos.get(1),
                (String) datos.get(2), (String) datos.get(3), (Integer) datos.get(4),
                (String) datos.get(5), (Integer) datos.get(6), (String) datos.get(7),
                (String) datos.get(8), (String) datos.get(9) ));
    }

    public static List<Employee> createList(List<List<Object>> employees) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeList.add(EmployeeData.create(employees.get(i)));
        }
        return employeeList;
    }
    
    public static void sort(List<Employee> e) {
        
    }

    public static void  quicksort(List<Employee> a, int first, int last, int type)
    {
        
        int i, j, central;
        Employee pivote; // elemento central
        central = (first + last) / 2; // seleccion elemento central
        pivote = a.get(central); //
        
        i = first;
        j = last;
        do {
            while (compare(a.get(i), type, pivote, 1)) {

                i++; // lleva i hasta una posicion de antes del pivote
            }
            while (compare(a.get(j), type, pivote, 2)) {

                j--; // lleva j hasta una posicion de despues del pivote
            }
            if (i <= j)
            {
                // Si se cumple la condicion se deben intercambiar (ya que i hizo terminar el bucle se debe hacer intercambio)
                swap(a, i, j);
                i++;
                j--;
            }
        }while (i <= j); // repetir
        if (first < j)
            quicksort(a, first, j, type); // mismo proceso con sublista izqda
        if (i < last)
            quicksort(a, i, last, type); // mismo proceso con sublista drcha
    }

    public static boolean compare(Employee e, int type, Employee value, int condition) {
        if (condition == 1) {
            if (type == 1) {
                return e.id < value.id;
            } else if (type == 2) {

                return e.salarioBase < value.salarioBase;
            } else if (type == 3) {
                return e.edad < value.edad;
            }
        } else  {
            if (type == 1) {
                return e.id > value.id;
            } else if (type == 2) {
                return e.salarioBase > value.salarioBase;
            } else if (type == 3) {
                return e.edad > value.edad;
            }
        }
        return false;
    }

    private static void swap(List<Employee> e,  int i, int j) {

        Employee aux = e.get(i);
        e.set(i, e.get(j));
        e.set(j, aux);
    }
}
