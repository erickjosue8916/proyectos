package sample;

import basics.Validacion;
import db.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Employee;
import models.EmployeeData;
import models.EmployeeTable;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public AnchorPane menu;
    public AnchorPane panelIngreso;
    public AnchorPane panelVistas;
    public ImageView btnPanel2;
    public ImageView btnPanel1;
    public ImageView btnSignOut;
    public TextField txtCode;
    public TextField txtLastName;
    public TextField txtTel;
    public ComboBox comboPosition;
    public ComboBox comboDepartment;
    public TextField txtName;
    public TextField txtAge;
    public TextField txtSalary;
    public Button btnSave;
    public TextField txtSearch;
    public ComboBox comboSearch;
    public ImageView btnSearch;
    public ImageView btnDelete;
    public ComboBox comboField;
    public ComboBox comboType;
    public TableView tableEmployee;
    public TableColumn colId;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colLastName;
    public TableColumn colSalary;
    public TableColumn colAge;
    public ComboBox comboCountry;

    private int opc;
    private Employee auxEmployee;
    private List<Employee> employeeList;
    private List<Employee> auxEmployeeList;
    private List<List<Object>> employeeListObject;
    private final ObservableList<EmployeeTable> tableEmployeeModel = FXCollections.observableArrayList();

    private ConnectDB db;
    private Alert dialog;
    private int employeeSelected;

    public void initialize() {
        employeeSelected = -1;
        auxEmployeeList = new ArrayList<>();
        dialog = new Alert(Alert.AlertType.ERROR);
        db = ConnectDB.getInstance();
        opc = 1;
        btnPanel1.setOpacity(1.0);
        createComboBoxes();
        configureTable();
        updatedata();
    }

    private void showAlert(String mensaje, int tipo) {
        if (tipo == 1) {
            dialog.setTitle("ERROR!!");
            dialog.setAlertType(Alert.AlertType.ERROR);
        } else if (tipo == 2) {
            dialog.setTitle("Operacion exitosa!!");
            dialog.setAlertType(Alert.AlertType.INFORMATION);
        }
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        dialog.showAndWait();
    }

    private void configureTable() {

        colId.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("id")
        );

        colCode.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("code")
        );

        colName.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("name")
        );

        colLastName.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("lastName")
        );

        colSalary.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("salary")
        );

        colAge.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("age")
        );

        tableEmployee.setItems(tableEmployeeModel);
    }

    private void createComboBoxes(){
        comboDepartment.setItems(FXCollections.observableArrayList(
                "Recursos Humanos",
                "Mantenimiento",
                "Oficina"
        ));

        comboDepartment.setValue("Recursos Humanos");

        comboPosition.setItems(FXCollections.observableArrayList(
                "Cajero",
                "Ordenanza",
                "Vendedor",
                "Pasante"
        ));
        comboPosition.setValue("Cajero");

        comboField.setItems(FXCollections.observableArrayList(
                "Id",
                "Sueldo",
                "Edad"
        ));
        comboField.setValue("Id");

        comboType.setItems(FXCollections.observableArrayList(
                "Ascendente",
                "Descendente"
        ));
        comboType.setValue("Ascendente");

        comboSearch.setItems(FXCollections.observableArrayList(
                "nombre",
                "apellido"
        ));
        comboSearch.setValue("nombre");

        comboCountry.setItems(FXCollections.observableArrayList(
                "Argentina",
                "Belice",
                "Costa Rica",
                "El Salvador",
                "Guatemala",
                "Honduras",
                "Nicaragua",
                "Panama"
        ));
        comboCountry.setValue("El Salvador");
    }

    public void saveEmployee(ActionEvent actionEvent) {
        boolean inserted;
        if (valiadateForm()) {
            auxEmployee = new Employee( 1, txtCode.getText(), txtName.getText(), txtLastName.getText(),
                    Integer.parseInt(txtAge.getText()), txtTel.getText(), Integer.parseInt(txtSalary.getText()),
                     "" + comboPosition.getValue(), "" + comboCountry.getValue(), "" + comboDepartment.getValue()
            );

            inserted = db.insertar("empleados", "models.Employee", EmployeeData.getData(auxEmployee), 1);
            if (inserted) {
                updatedata();
                showAlert("Los datos del empleado se guardaron en la base de datos", 2);
                clearForm();
            } else {
                showAlert("ERROR!!! Datos no guardados", 1);
            }
        } else {
            showAlert("Los datos son invalidos", 1);
        }
    }

    private boolean valiadateForm() {
        boolean code = Validacion.validar(txtCode.getText(), Validacion.numbers, true) && !txtCode.getText().isEmpty();
        boolean name = Validacion.validar(txtName.getText(), Validacion.letters, true) && !txtName.getText().isEmpty();
        boolean lastName = Validacion.validar(txtLastName.getText(), Validacion.letters, true) && !txtLastName.getText().isEmpty();
        boolean telephone = Validacion.validar(txtTel.getText(), Validacion.numbers, true) && !txtTel.getText().isEmpty();
        boolean age = Validacion.validar(txtAge.getText(), Validacion.numbers, true) && !txtAge.getText().isEmpty();
        boolean salary = Validacion.validar(txtSalary.getText(), Validacion.numbers, true) && !txtSalary.getText().isEmpty();

        return (
                 ((code && name) && (lastName && telephone)) && (age && salary)
                );
    }

    private void fillTable(boolean reverse) {
        tableEmployeeModel.clear();
        //EmployeeData.quicksort(auxEmployeeList, 0, auxEmployeeList.size() - 1, 1);
        if (!reverse) {
            for (int i = 0; i < auxEmployeeList.size(); i++) {
                tableEmployeeModel.add(new EmployeeTable("" + auxEmployeeList.get(i).id, auxEmployeeList.get(i).codigo, auxEmployeeList.get(i).nombre,
                        auxEmployeeList.get(i).apellido, "" + auxEmployeeList.get(i).salarioBase, "" + auxEmployeeList.get(i).edad));
            }
        } else {
            for (int i = auxEmployeeList.size() - 1; i >= 0; i--) {
                tableEmployeeModel.add(new EmployeeTable("" + auxEmployeeList.get(i).id, auxEmployeeList.get(i).codigo, auxEmployeeList.get(i).nombre,
                        auxEmployeeList.get(i).apellido, "" + auxEmployeeList.get(i).salarioBase, "" + auxEmployeeList.get(i).edad));
            }
        }
    }

    private void updatedata() {
        employeeListObject = db.select("empleados", "models.Employee", "");
        employeeList = EmployeeData.createList(employeeListObject);
        filterData("", "");
        fillTable(false);
    }

    private void filterData(String condition, String field) {
        auxEmployeeList.clear();
        for (int i = 0; i < employeeList.size(); i++) {
            if (field.equalsIgnoreCase("")) {
                auxEmployeeList.add(employeeList.get(i));
            } else {
                if (field.equalsIgnoreCase("nombre") && Validacion.validar(employeeList.get(i).nombre, condition, false)) {
                    auxEmployeeList.add(employeeList.get(i));
                } else if (field.equalsIgnoreCase("apellido") && Validacion.validar(employeeList.get(i).apellido, condition, false)) {
                    auxEmployeeList.add(employeeList.get(i));
                }
            }
        }
    }

    public void searchEmployee() {
        filterData(txtSearch.getText(), "" + comboSearch.getValue());
        comboField.setValue("Id");
        comboType.setValue("Ascendente");
        fillTable(false);
    }

    public void changeSort(ActionEvent actionEvent) {
        String field = comboField.getValue().toString();
        int type;
        if (field.equalsIgnoreCase("Id")){
            type = 1;
        } else if (field.equalsIgnoreCase("Sueldo")){
            type = 2;
        } else {
            type = 3;
        }
        EmployeeData.quicksort(auxEmployeeList, 0, auxEmployeeList.size() - 1, type);
        comboType.setValue("Ascendente");
        fillTable(false);
    }

    public void sort(ActionEvent actionEvent) {
        fillTable(comboType.getValue().toString().equalsIgnoreCase("Descendente"));
    }

    public void iconIn(MouseEvent e) {
        ImageView btn = (ImageView) e.getSource();
        btn.setOpacity(1.0);
    }

    public void iconOut(MouseEvent e) {
        ImageView btn = (ImageView) e.getSource();
        if (opc == 1) {
            if (e.getSource() != btnPanel1)
                btn.setOpacity(0.3);
        } else {
            if (e.getSource() != btnPanel2)
                btn.setOpacity(0.3);
        }
    }

    public void viewDataPanel(MouseEvent mouseEvent) {
        panelVistas.setVisible(true);
        panelIngreso.setVisible(false);
        btnPanel1.setOpacity(0.3);
        btnPanel2.setOpacity(1.0);
        opc = 2;

    }

    public void viewInsertPanel(MouseEvent mouseEvent) {
        panelIngreso.setVisible(true);
        panelVistas.setVisible(false);
        btnPanel2.setOpacity(0.3);
        btnPanel1.setOpacity(1.0);
        opc = 1;

    }

    public void signOut(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void selectEmployee(MouseEvent mouseEvent) {
        employeeSelected = tableEmployee.getSelectionModel().getFocusedIndex();
        auxEmployee = employeeList.get(employeeSelected);
    }

    public void showData(MouseEvent mouseEvent) {
        if (employeeSelected != -1) {
            System.out.println(auxEmployee.toString());
            showAlert(auxEmployee.toString(), 2);
        } else {
            showAlert("Selecciona un Empleado", 1);
        }

    }

    public void delete(MouseEvent mouseEvent) {
        if (employeeSelected != -1) {
            if (db.eliminar("empleados", "id", "" + auxEmployee.id, true)) {
                employeeSelected = -1;
                showAlert("El empleado fue eliminado de forma exitosa", 2);
                updatedata();
            } else {
                showAlert("ERROR!!!! No se pudo eliminar al empleado", 1);
            }
        }  else {
            showAlert("Selecciona un Empleado", 1);
        }
    }

    public void clearForm(){
        txtCode.setText("");
        txtName.setText("");
        txtLastName.setText("");
        txtTel.setText("");
        txtSalary.setText("");
        txtAge.setText("");
        comboCountry.setValue("El Salvador");
        comboPosition.setValue("Cajero");
        comboDepartment.setValue("Recursos Humanos");


    }
}