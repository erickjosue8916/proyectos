package modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Registro {
    private final SimpleStringProperty tipo;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty hora;

    public Registro(String tipo, String fecha, String hora) {
        this.tipo = new SimpleStringProperty(tipo);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
    }


    public String getTipo() {
        return tipo.get();
    }

    public SimpleStringProperty tipoProperty() {
        return tipo;
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public String getHora() {
        return hora.get();
    }

    public SimpleStringProperty horaProperty() {
        return hora;
    }
}
