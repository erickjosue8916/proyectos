package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    public int opc;


    public void initialize() {
        opc = 1;
        btnPanel1.setOpacity(1.0);

        createComboBoxes();
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