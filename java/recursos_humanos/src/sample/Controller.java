package principal;

import basics.FormatoDatos;
import basics.Validacion;
import conexionDB.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
    public Button btnBuscar; // boton buscar
    public TextField txtId; // id empleado
    public TextField txtNombre;
    public RadioButton radHombre; // boton de radio hombre
    public RadioButton radMujer; // boton de radio mujer
    public TextField txtCargo; // cargo
    public TextField txtDui;
    public TextField txtNit;
    public DatePicker txtNacimiento; // fecha de nacimiento
    public TextField txtTel;
    public TextField txtSueldo;
    public Button btnSave; // guardar datos

    public Button btnNuevo;
    public PasswordField txtContra;

    // cofiguracion datos de empleado
    public TableView tablEmp;
    public TableColumn colId;
    public TableColumn colNombre;
    public TableColumn colCargo;
    public TableColumn colSueldo;
    private final ObservableList<Empleado> data = FXCollections.observableArrayList();

    // tabla para mostrar las prestaciones del empleado seleccionado
    public TableView tabFil;
    public TableColumn colAFP;
    public TableColumn colISSS;
    public TableColumn colRenta;
    public TableColumn colSalario;
    private final ObservableList<Prestacion> prestacion = FXCollections.observableArrayList();

    // menu vista de administrador (las opciones como iconos)
    public ImageView btnEmp;
    public ImageView btnReg;
    public ImageView btnSalir;

    // paneles secundarios para la vista de administrador
    public AnchorPane pDatos;
    public AnchorPane pDatos1;

    // entrada filtro vista de marcaciones
    public DatePicker txtSearchFecha;

    // propiedades de tabla de marcaciones
    public TableView tabReg;
    public TableColumn colTipo;
    public TableColumn colHora;
    public TableColumn colFecha;
    private final ObservableList<Registro> registro = FXCollections.observableArrayList();

    // login
    public TextField txtLoginUser; // nombre de usuario
    public PasswordField txtLoginPass; // contraseña del usuario
    public Button btnLogin; // efectuar el login

    // menu panel de marcaciones
    public ImageView btnMarcaLlegada;
    public ImageView btnMarcaSalida;
    public ImageView btnSalir2;

    // paneles principales (vistas)
    public AnchorPane panelAdmin; // administrador
    public AnchorPane panelLogin; // login
    public AnchorPane panelEmp; // vista de empleado

    public Label labLlegada;
    public Label labSalida;
    public Label labCerrar;
    public Button btnDel;
    public Button btnMarcasFecha;

    // Modelos de datos
    private Empleado empleado; // modelo para la tabla de empleados
    private ModeloEmpleado modeloEmpleado; // modelo para la conexion con la tabla de empleados
    private ModeloMarcacion modeloMarcacion; // modelo para la conexion con la tabla de marcaciones

    private Conexion connect; // realizar las operaciones con las bases de datos
    private Alert dialogo; // ventana de dialogo

    // gestion de listas empleados obte nidos de la base de datos
    private List<List<Object>> empleados;
    private List<ModeloEmpleado> emp;

    // gestion de listas marcaciones obtenidos de la base de datos
    private List<List<Object>> marcaciones;
    private List<ModeloMarcacion> marcas;


    private int indexEmp; // id del empleado seleccionado
    private int idEmpSelected; // id del empleado logueado
    private int opcion; // opcion seleccionada en el panel de administracion

    // dar un estado inicial para el componente grafico
    public void initialize() {
        indexEmp = -1; // no hay un empleado seleccionado al principio
        dialogo = new Alert(Alert.AlertType.ERROR); // definir la ventana de dialogo que se planea utilizar
        idEmpSelected = -1; //
        connect = Conexion.getInstance();
        connect.connect();

        configTabEmp();
        configTabFil();
        configTabReg();
        reset();
    }

    public void configTabEmp() {
        // asociar los datos con las columnas de la tabla
        // data.clear();
        colId.setCellValueFactory(
                new PropertyValueFactory<Empleado, String>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<Empleado, String>("nombre")
        );

        colCargo.setCellValueFactory(
                new PropertyValueFactory<Empleado, String>("cargo")
        );

        colSueldo.setCellValueFactory(
                new PropertyValueFactory<Empleado, String>("sueldo")
        );

        tablEmp.setItems(data);
    }

    private void configTabFil() {
        colAFP.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("AFP")
        );
        colISSS.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("ISSS")
        );
        colRenta.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("renta")
        );
        colSalario.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("sueldo")
        );
        tabFil.setItems(prestacion);
    }

    private void configTabReg() {

        colTipo.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("tipo")
        );
        colFecha.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("fecha")
        );
        colHora.setCellValueFactory(
                new PropertyValueFactory<Prestacion, Double>("hora")
        );
        tabReg.setItems(registro);
    }

    private void resetBtn() {
        btnEmp.setOpacity(0.3);
        btnReg.setOpacity(0.3);
    }

    private void search(String condicion) {
        data.clear();

        empleados = connect.select("empleados", "modelo.ModeloEmpleado", condicion);
        emp = ModeloEmpleado.crearmodEmpleados(empleados);
        for (int i = 0; i < emp.size(); i++) {
          //  System.out.println(emp.get(i).toString());
            data.add(new Empleado(emp.get(i).id, emp.get(i).nombre, emp.get(i).cargo, emp.get(i).sueldo));
        }
    }

    public void guardar() {
        System.out.println(txtNacimiento.getValue());


        if(valid()) {
           // data.add(new Empleado(Integer.parseInt(txtId.getText()), txtNombre.getText(), txtCargo.getText(), Double.parseDouble(txtSueldo.getText())));
            boolean insertado;
            String sexo = (radHombre.isSelected()) ? radHombre.getText(): radMujer.getText();

            if (indexEmp != -1) {
                modeloEmpleado = new ModeloEmpleado(Integer.parseInt(txtId.getText()), txtNombre.getText(), sexo,
                        txtNit.getText(), txtDui.getText(), txtCargo.getText(), txtTel.getText(), "" + txtNacimiento.getValue(), Double.parseDouble(txtSueldo.getText()), txtContra.getText());

                insertado = connect.actualizar("empleados", "modelo.ModeloEmpleado", modeloEmpleado.getDatos());
            } else {
                modeloEmpleado = new ModeloEmpleado(txtNombre.getText(), sexo,
                        txtNit.getText(), txtDui.getText(), txtCargo.getText(), txtTel.getText(), "" + txtNacimiento.getValue(), Double.parseDouble(txtSueldo.getText()), txtContra.getText());

                insertado = connect.insertar("empleados", "modelo.ModeloEmpleado", modeloEmpleado.getDatos(), 1);
            }
            if (insertado) {
                alerta("Los datos del empleado fueron guardados de forma exitosa", 2);
                search("");
                reset();
            } else {
                alerta("Error al ingresar los datos", 1);
            }
        } else {
            alerta("Los datos que quieres ingresar no son validos", 1);
        }

    }

    private void alerta(String mensaje, int tipo) {
        if (tipo == 1) {
            dialogo.setTitle("ERROR!!");
            dialogo.setAlertType(Alert.AlertType.ERROR);
        } else if (tipo == 2) {
            dialogo.setTitle("Operacion exitosa!!");
            dialogo.setAlertType(Alert.AlertType.INFORMATION);
        }
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.showAndWait();
    }

    public boolean valid () {
        try {
            double d = Double.parseDouble(txtSueldo.getText());
            boolean nombre = Validacion.validar(txtNombre.getText(),Validacion.letras, true);
            boolean sexo = radHombre.isSelected() || radMujer.isSelected();
            boolean nit = Validacion.validar(txtNit.getText(), Validacion.numeros, true);
            boolean dui = Validacion.validar(txtDui.getText(), Validacion.numeros, true) ;
            boolean cargo = Validacion.validar(txtCargo.getText(), Validacion.letras, true);
            boolean telefono = Validacion.validar(txtTel.getText(), Validacion.numeros, true);
            String fecha = "" +  txtNacimiento.getValue();
            return (((nombre && sexo) && (!fecha.isEmpty() && !txtContra.getText().isEmpty()) ) && ((nit && dui) && (cargo && telefono)));
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void select() {
        indexEmp = tablEmp.getSelectionModel().getFocusedIndex();
        empleado = data.get(indexEmp);
        llenarCampos();
        prestacion.clear();
        prestacion.add(new Prestacion(empleado.getSueldo()));
        mostraMarcaciones("where idEmpleado=" + emp.get(indexEmp).id);
    }

    private void llenarCampos() {
        txtId.setText("" + emp.get(indexEmp).id);
        txtNombre.setText("" + emp.get(indexEmp).nombre);
        txtNit.setText("" + emp.get(indexEmp).nit);
        txtDui.setText("" + emp.get(indexEmp).dui);
        txtCargo.setText("" + emp.get(indexEmp).cargo);
        txtTel.setText("" + emp.get(indexEmp).telefono);
        txtSueldo.setText("" + emp.get(indexEmp).sueldo);
        txtContra.setText(emp.get(indexEmp).contrasenia);
        txtNacimiento.setValue(LocalDate.parse(emp.get(indexEmp).fechaNac, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (emp.get(indexEmp).sexo.equalsIgnoreCase("masculino")) {
            radHombre.setSelected(true);
        } else {
            radMujer.setSelected(true);
        }
        txtId.setDisable(true);
    }

    public void btnEmpClicked(){
        resetBtn();
        btnEmp.setOpacity(1.0);
        pDatos.setVisible(true);
        pDatos1.setVisible(false);
        opcion = 1;
    }

    public void btnRegClicked() {
        resetBtn();
        btnReg.setOpacity(1.0);
        pDatos.setVisible(false);
        pDatos1.setVisible(true);
        opcion = 2;

    }

    public void buscar() {
        search("where nombre like '%" + txtId.getText() + "%'");
    }

    public void reset() {
        indexEmp = -1;
        txtId.setText("");
        txtSueldo.setText("");
        txtNombre.setText("");
        txtCargo.setText("");
        txtTel.setText("");
        txtNit.setText("");
        txtDui.setText("");
        txtContra.setText("");
        radHombre.setSelected(false);
        radMujer.setSelected(false);
        txtNacimiento.setValue(LocalDate.of(2000, 01, 01));
        search("");
        prestacion.clear();
        registro.clear();
        resetBtn();
        btnEmp.setOpacity(1.0);
        opcion = 1;
        pDatos.setVisible(true);
        pDatos1.setVisible(false);
        txtId.setDisable(false);
    }

    public void marcaSalida() {
        marcar("salida");
    }

    public void marcaEntrada() { marcar("entrada"); }

    private void marcar(String tipo) {
        System.out.println(idEmpSelected);
        String fecha = FormatoDatos.fecha("yyyy-MM-dd");
        modeloMarcacion = new ModeloMarcacion(fecha, idEmpSelected, FormatoDatos.fecha("hh:mm:ss a"), tipo);

        int existencia = connect.conteo("marcaciones", "where (idEmpleado = " + idEmpSelected + " and fecha='" + fecha + "') and tipo='" +tipo+ "'");

        if (existencia == 0) {
            boolean insertado =connect.insertar("marcaciones", "modelo.ModeloMarcacion", modeloMarcacion.getDatos(), 1);
            if (insertado) {
                alerta("Se realizo la marcacion", 2);
            } else {
                alerta("No puedes realizar esta marcacion", 1);
            }
        } else {
            alerta("Ya realizaste tu marcacion de " + tipo + " este dia", 1);
        }
    }


    private void mostraMarcaciones(String condicion) {
        registro.clear();
        marcaciones = connect.select("marcaciones", "modelo.ModeloMarcacion", condicion);
        marcas = ModeloMarcacion.crearmodMarcaciones(marcaciones);
        for (int i = 0; i < marcas.size(); i++) {
            registro.add(new Registro(marcas.get(i).tipo, marcas.get(i).fecha, marcas.get(i).hora));
        }
    }

    public void login(ActionEvent actionEvent) {
        if (txtLoginUser.getText().equalsIgnoreCase("Admin") && txtLoginPass.getText().equalsIgnoreCase("Admin")) {
            panelLogin.setVisible(false);
            panelAdmin.setVisible(true);

        } else {
            int existencia = connect.conteo("empleados", "where id = " + txtLoginUser.getText() + " and contrasenia='" + txtLoginPass.getText() + "'");
            if (existencia == 1) {
                idEmpSelected = Integer.parseInt(txtLoginUser.getText());
                panelLogin.setVisible(false);
                panelEmp.setVisible(true);
            } else {
                alerta("No tienes acceso a ningun area", 1);
            }

        }
    }

    public void inicio(MouseEvent mouseEvent) {
        txtLoginUser.setText("");
        txtLoginPass.setText("");
        panelEmp.setVisible(false);
        panelAdmin.setVisible(false);
        panelLogin.setVisible(true);
        reset();
    }

    public void inBtn(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btnEmp) {
            btnEmp.setOpacity(1.0);
        } else if (mouseEvent.getSource() == btnReg) {
            btnReg.setOpacity(1.0);
        }  else if (mouseEvent.getSource() == btnSalir) {
            btnSalir.setOpacity(1.0);
        }  else if (mouseEvent.getSource() == btnMarcaLlegada) {
            btnMarcaLlegada.setOpacity(1.0);
            labLlegada.setOpacity(1.0);
        }  else if (mouseEvent.getSource() == btnMarcaSalida) {
            btnMarcaSalida.setOpacity(1.0);
            labSalida.setOpacity(1.0);
        }  else if (mouseEvent.getSource() == btnSalir2) {
            btnSalir2.setOpacity(1.0);
            labCerrar.setOpacity(1.0);
        }
    }

    public void outBtn(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == btnEmp) {
            if (opcion != 1)
                btnEmp.setOpacity(0.3);
        } else if (mouseEvent.getSource() == btnReg) {
            if (opcion != 2)
                btnReg.setOpacity(0.3);
        } else if (mouseEvent.getSource() == btnSalir) {
            btnSalir.setOpacity(0.3);
        }  else if (mouseEvent.getSource() == btnMarcaLlegada) {
            btnMarcaLlegada.setOpacity(0.3);
            labLlegada.setOpacity(0.0);
        }  else if (mouseEvent.getSource() == btnMarcaSalida) {
            btnMarcaSalida.setOpacity(0.3);
            labSalida.setOpacity(0.0);
        }  else if (mouseEvent.getSource() == btnSalir2) {
            btnSalir2.setOpacity(0.3);
            labCerrar.setOpacity(0.0);
        }
    }

    public void eliminar(ActionEvent actionEvent) {
        connect.eliminar("empleados", "id", txtId.getText(), true);
        connect.eliminar("marcaciones", "idEmpleado", txtId.getText(), true);
        reset();
        alerta("El empleado fue eliminado de la base de datos", 2);
    }

    public void filtroMarcas(ActionEvent actionEvent) {
        String fecha = "" +  txtSearchFecha.getValue();
        if (!fecha.isEmpty()) {
                mostraMarcaciones("where idEmpleado=" + emp.get(indexEmp).id + " and fecha='" + fecha +"';");
        } else {
            alerta("La fecha que buscas no es valida", 2);
        }
    }
}