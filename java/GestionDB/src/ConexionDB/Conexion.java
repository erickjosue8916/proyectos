package ConexionDB;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private static Conexion conexion = null;

// propiedades para gestion de la base de datos
    private Connection cn; // conexion con la base de datos
    protected PreparedStatement pst; // declaracion preparada
    protected Statement st; // declaracion
    protected ResultSet rs; // resultado de un consulta
    protected String sql; // codigo sql a ejecutar

// propiedades para la conexion
    private String url; // url de la base de datos
    private String usuario; // usuario de la base de datos
    private String contrasenia; // contraseña del usuario
    private String gestor; // tipo de gestor con el que se trabajara

    // constructor
    private Conexion(String url, String user, String pass, String gestorDB) {
        this.url = url;
        this.usuario = user;
        this.contrasenia = pass;
        this.gestor = gestorDB;
    }

    public static Conexion getInstance (String url, String user, String pass, String gestorDB) {
        if (conexion == null) {
            conexion = new Conexion(url, user, pass, gestorDB);
        }
        return conexion;
    }

    // realizar la conexion con la base de datos
    public void connect(){
        try {
// identificar que gestor se utilizara
            if (this.gestor.equalsIgnoreCase("MySql")){ // mysql
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
        } catch(ClassNotFoundException ex) { // hubo un error al conectar con e gestor de la Base de datos
            System.err.println("hay un error");
        }

// conectar con la base de datos
        try {
            cn = DriverManager.getConnection(this.url, this.usuario, this.contrasenia); // realizar la conexion
        } catch (SQLException e) { // error al hacer la conexion
            cn = null;
            e.printStackTrace();
            System.out.println("No se encontro la base de datos");
        }
    }

    public Connection getConnect(){
        return cn;
    }

    /**
     * @def inserta un nuevo registro en la base de datos
     * @param tabla: nombre de la tabla
     * @param nombreModelo: nombre de la clase que contiene el modelo de la tabla
     * @param datos: lista de datos correspondientes al registro a ingresar
     */
    public void insertar(String tabla, String nombreModelo, List<Object> datos, int inicioCampos) {
        this.sql = "INSERT INTO " + tabla + "(";
        try {
            Class _modelo = Class.forName(nombreModelo); // obtiene la informacion del modelo
            Field campos[] = _modelo.getFields(); // obtiene los campos del modelo
            sqlInsertar(campos, inicioCampos); // crea la sentencia sql que se necesitara

            insertarEnDB(campos, datos, inicioCampos); // inserta los datos en el modelo
        } catch (ClassNotFoundException e) {
            System.out.println("Error el modelo no existe");
        }
    }

    private void sqlInsertar(Field[] campos, int n) {
        for (int i = n; i < campos.length; i++) {
            this.sql += campos[i].getName();
            this.sql += (i < campos.length - 1) ? ",": ")";
        }

        this.sql += " VALUES (";

        for (int i = n; i < campos.length; i++) {
            this.sql += (i < campos.length - 1) ? "?,": "?);";
        }
    }

    private void insertarEnDB(Field[] campos, List<Object> datos, int n) {
        int indexDB = 1;
        try {
            pst = conexion.getConnect().prepareStatement(this.sql);
            for (int i = n; i < campos.length; i++) {

                setPrepareStatement(indexDB, campos[i], datos.get(i));
                indexDB++;
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar los datos");
        }
    }

    public void eliminar (String tabla, String clave, String valor, boolean unico){
        this.sql = "DELETE FROM " + tabla + " WHERE " + clave; // se crea la consulta
        this.sql += (unico) ? "=?;" : " LIKE %?%;"; // si se eliminara pos clave primaria o no
        System.out.println(sql);
        try {
            pst = conexion.getConnect().prepareStatement(this.sql);
            if (unico) {
                pst.setInt(1, Integer.parseInt(valor));
            } else {
                pst.setString(1, valor);
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos");
        }
    }

    public void actualizar (String tabla, String nombreModelo, List<Object> datos) {
        this.sql = "UPDATE " + tabla + " SET ";
        try {
            Class _modelo = Class.forName(nombreModelo); // obtiene la informacion del modelo
            Field campos[] = _modelo.getFields(); // obtiene los campos del modelo
            sqlActualizar(campos); // crea la sentencia sql que se necesitara
            System.out.println(this.sql);
            actualizarEnDB(campos, datos); // inserta los datos en el modelo
        } catch (ClassNotFoundException e) {
            System.out.println("Error el modelo no existe");
        }
    }

    private void sqlActualizar(Field[] campos) {
        for (int i = 1; i < campos.length ; i++) {
            this.sql += campos[i].getName() + "=?";
            this.sql += (i < campos.length - 1) ? ",": " ";
        }
        this.sql += "WHERE " + campos[0].getName() + "=?;";
    }

    private void actualizarEnDB(Field[] campos, List<Object> datos){
        try {
            pst = conexion.getConnect().prepareStatement(this.sql);
            for (int i = 1; i < campos.length; i++) {
                setPrepareStatement(i, campos[i], datos.get(i));
            }

            setPrepareStatement(campos.length, campos[0], datos.get(0));

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al modificar los datos");
        }
    }
    private void setPrepareStatement(int i, Field campo, Object dato) {
        String tipo; // tipo del campo actualmente recorrido
        tipo = "" + campo.getType();

        try {

            if (tipo.equalsIgnoreCase("int")) {

                pst.setInt(i , (Integer) dato);
            } else if (tipo.equalsIgnoreCase("double")) {

                pst.setDouble(i , (Double) dato);
            } else if (tipo.equalsIgnoreCase("class java.lang.String")) {

                pst.setString(i , (String) dato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo la preparacion");
        }
    }

    public List<List<Object>> select (String tabla, String modelo, String condicion) {
        this.sql = "SELECT * FROM " + tabla; // codigo sql para seleccionar todo de una tabla
        List<Object> fila = new ArrayList<>(); // lista que almacena los campos de un registro
        List<List<Object>> registros = new ArrayList<>(); // almacena todos los registros devueltos por la busqueda

        // si se seleccional los elementos en base a una condicion
        if (!condicion.equalsIgnoreCase("")) {
            this.sql += " " + condicion + ";"; //
        } else {
            this.sql += ";";
        }
        
        try {
            Class _modelo = Class.forName(modelo); // obtiene la informacion del modelo

            Field campos[] = _modelo.getFields(); // obtiene los campos del modelo


            st = conexion.getConnect().createStatement(); // crea la declaracion
            rs = st.executeQuery(this.sql); // ejecuta la declaracion

            while (rs.next()) {

                System.out.println(fila);
                registros.add(getStatement(campos, fila));
            }
            //insertarEnDB(campos, datos); // inserta los datos en el modelo
        } catch (ClassNotFoundException e) {
            System.out.println("Error el modelo no existe");
        } catch (SQLException e) {

        }
        return registros;
    }

    private List<Object> getStatement(Field[] campos, List<Object> fila) {
        fila = new ArrayList<>();
        String tipo;
        try {
            for (int i = 0; i < campos.length; i++) {
                tipo  = "" + campos[i].getType();
                if (tipo.equalsIgnoreCase("int")) {
                    fila.add(rs.getInt("" + campos[i].getName())) ;
                } else if (tipo.equalsIgnoreCase("double")) {
                    fila.add(rs.getDouble("" + campos[i].getName()));
                } else if (tipo.equalsIgnoreCase("class java.lang.String")) {
                    fila.add(rs.getString("" + campos[i].getName()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
        return fila;
    }

    public int conteo(String tabla, String condicion) {
        int rows = 0;
        this.sql = "SELECT Count(*) AS rows from " + tabla + " " + condicion + ";";
        try {

            st = conexion.getConnect().createStatement(); // crea la declaracion
            rs = st.executeQuery(this.sql); // ejecuta la declaracion
            while (rs.next()) {
                rows = rs.getInt("rows");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rows = -1;
        }
        return rows;
    }
}