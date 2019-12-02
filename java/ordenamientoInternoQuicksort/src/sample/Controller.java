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
import models.EmployeeTable;

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
    public TextField txtCountry;
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

    private int opc;
    private Employee auxEmployee;
    private List<Employee> employeeList;
    private List<List<Object>> employeeListObject;
    private final ObservableList<Employee> tableEmployeeModel = FXCollections.observableArrayList();

    private ConnectDB db;
    private Alert dialog;

    public void initialize() {
        dialog = new Alert(Alert.AlertType.ERROR);
        db = ConnectDB.getInstance();
        opc = 1;
        btnPanel1.setOpacity(1.0);
        createComboBoxes();
        configureTable();
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
                new PropertyValueFactory<EmployeeTable, String>("nombre")
        );

        colName.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("cargo")
        );

        colLastName.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("sueldo")
        );

        colSalary.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("sueldo")
        );

        colAge.setCellValueFactory(
                new PropertyValueFactory<EmployeeTable, String>("sueldo")
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
                "Edad",
                "Sueldo",
                "id"
        ));
        comboField.setValue("Edad");

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
    }
    
    public void saveEmployee(ActionEvent actionEvent) {
        boolean inserted;

        if (valiadateForm()) {
            auxEmployee = new Employee( 1, txtCode.getText(), txtName.getText(), txtLastName.getText(),
                    Integer.parseInt(txtAge.getText()), txtTel.getText(), Integer.parseInt(txtSalary.getText()),
                     "" + comboPosition.getValue(), txtCountry.getText(), "" + comboDepartment.getValue()
            );

            inserted = db.insertar("empleados", "models.Employee", auxEmployee.getData(), 1);
            if (inserted) {
                showAlert("Los datos del empleado se guardaron en la base de datos", 2);
            } else {
                showAlert("ERROR!! Los datos no se pudieron guardar", 2);
            }
        } else {
            System.out.println("Los datos son invalidos");
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

    public void searchEmployee(MouseEvent mouseEvent) {
    }

    public void changeSort(ActionEvent actionEvent) {
    }

    public void sort(ActionEvent actionEvent) {
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


}