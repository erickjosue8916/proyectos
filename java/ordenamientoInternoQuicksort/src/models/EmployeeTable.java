package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmployeeTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final SimpleStringProperty lastName;
    private final SimpleIntegerProperty salary;
    private final SimpleIntegerProperty age;

    public EmployeeTable(String id, String code, String name, String lastName, String salary, String age) {
        this.id = new SimpleIntegerProperty(Integer.parseInt(id));
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleIntegerProperty(Integer.parseInt(salary));
        this.age = new SimpleIntegerProperty(Integer.parseInt(age));
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public int getSalary() {
        return salary.get();
    }

    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }
}
