package modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class Empleado {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty cargo;
    private final SimpleDoubleProperty sueldo;

    public Empleado(int id, String nombre, String cargo, double sueldo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.cargo = new SimpleStringProperty(cargo);
        this.sueldo = new SimpleDoubleProperty(sueldo);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public String getCargo() {
        return cargo.get();
    }

    public SimpleStringProperty cargoProperty() {
        return cargo;
    }

    public double getSueldo() {
        return sueldo.get();
    }

    public SimpleDoubleProperty sueldoProperty() {
        return sueldo;
    }
}
