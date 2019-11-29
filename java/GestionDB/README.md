## **Conexion.java**
Autor: Erick Saravia <br>
Ultima modificacion: 24 / 11 / 2019 <br>
correo: ericksaravia16@gmail.com <br>
version: 1.0.0
***
> ## **Finalidad**
> Crear una herramienta que permita simplificar, la interaccion con las bases de datos, la cual permita ser utilizada con independencia del modelo que se quiera trabajar

## Descripcion
---
 Maneja todas las acciones realizadas con la base sin importar la estructura de la entidad con la que se trabaje, siempre y cuando se respeten las opciones de la sintaxis, propuesta y mencionada a cotinuacion. Sin embargo aun se encuentra en version inicial


## **Herramientas** 
---
- **IDE:** 
    - IntelliJ IDEA Ultimate 2019.2
- **Gestor de base de datos:** 
    - MariaDB 
    - MySql
- **Lenguajes utilizados**
    - Java
    - SQL
- **JDK:** OpeJDK 11.0.2
- **Conexion.java:** Clase para interactuar con bases de datos con java(proyecto propio) [leer mas](../GestionDB/README.md)
- **Linux Mint**

## Estructura a seguir para trabajr con Conexion.java
---
- Para cada instancia de la base de datos con la que se desee trabajar se tiene que crear un modelo que debe cumplir los siguientes requisitos:
    - Las propiedades deben ser publicas
    - El nombre de las propiedades deben ser declaradas de la misma manera que en la tabla de la base de datos (mismo tipo, mismo escritura, ejemplo "Id" no es lo mismo que "id")
        
- El modelo debe contener los siguientes metodos estaticos (puede ser externo a la clase pero se debe contar con lasiguiente estructura)
    - Obtener los datos del modelo en forma de una lista de objetos
    ```java
    public  static List<Object> obtenerDatos() {        
        List<Object> datos = new ArrayList<>()
         // agregar a la lista las propiedades del objeto, en el orden que se pasaran al constructor
         // en caso del uso de herencia, primero deben ser agregadas la    propiedades, de la clase, seguida de las originales   

            return datos;
    } 
    ```
        
    - Obtener un objeto del modelo a partir de una lista de objetos (La lista se obtiene al aplicar el mentodoanterior)
    ```java
    public static ClaseModelo crear(List<Object> datos) { 
         return (new ClaseModelo( (casting) datos.get(0),  ...,  (casting) datos.get(datos.size() - 1)  ));
    } 
     ```
        
    - Obtener una lista del modelo 
    ```java
       public static List<Modelo> crearLista(List<List<Object>> listaDatos) {             
            List<Modelo> mod = new ArrayList<>(); 
            for (int i = 0; i < listaDatos.size(); i++) 
                mod.add(Modelo.crear(listaDatos.get(i)));              
            return mod; 
        }
    ```  
 

- Iniciar
    -
    - Antes que nada se debe agregar el driver necesario para trabajar con el gestor deseado
    - Crear una instancia de la clase, de preferencia en la clase pincipal
        ```java
         Conexion db = Conexion.getInstance(url, usuario, contraseña, gestor); // configura los parametros
         db.connet(); // hace la conexion
        ```        
        - **url**: direccion de la base de datos ej. jdbc:mysql://servidor:puerto/baseDeDatos
        - **usuario**: usuario con el que se accedera a la base de datos
        - **contraseña**: contraseña del usuario
        - **gestor**: nombre de gestor de bases de datos relacionales 
        > El gestor esta presente para que en una version futura se realize la conexion independientemente el gestor de bases de datos con el que se utilize, siempre que esta se trabaje con sql
    - Donde se requiera interactuar con la base de datos se debe tener un objeto de tipo conexion pero para iniciarlo se debe utilizar el mentodo
        ```java
        Conexion db = Conexion.getInstace();
        ```
        ---
        > Se a utilizado para el desarrollo de esta clase el patron de diseño Singleton, por lo que en este caso y cualquier otro no se crea una nueva conexion, solo se accede a la que ya esta instanciada con la configuracion inicial
- Metodos disponibles
    - 
    ```java
    bool insertar(String tabla, String nombreModelo, List<Object> datos, int init) 
    // verdadero si se insertaron los datos
    ```
    - **tabla:** nombre de la tabla sobre la cual se insertaran los datos
    - **nombreModelo:** nombre de la clase que define el modelo de tabla (debe incluir los paquetes donde seencuentra, ej. modelos.ClaseModelo)
    - **datos:** deben ser obtenidos con el metodo modelo.obtenerDatos
    - **init:** numero de propiedades a ignorar (partiendo desde la primera), en caso que se autogeneren o puedan ser nulas
    ```java
    bool actualizar(String tabla, String nombreModelo, List&lt;Object> datos) //verdadero si se actualizaron los datos
    ```
     - **ver parametros en insertar**
    ```java
    bool eliminar(String tabla, String clave, String valor, bool unico) // verdadero si se eliminaron los datos 
    ```
    - **tabla:** nombre de la tabla sobre la cual se eliminaran los datos
    - **clave:** clave que se utilizara para la condicion de eliminacion
    - **valor:** valor con el que se evaluara el campo a comparar para eliminar
    - **unico:** verdadero: si es una clave numerica, falso si es una cadena
     ```java
    List<List<Object>>  select(String tabla, String modelo, String condicion);
     ```
     - **tabla:** nombre de la tabla sobre la cual se seleccionaran los registros
     - **modelo:** nombre del modelo que contenga los campos de la tabla que se desean extraer
    - **condicion:** codigo sql que contenga la condicion para llevar a cabo laseleccion
    
    ```java
    int conteo(String tabla, String condicion); // cuenta los resultado que cumplen una condicion en  la tabla
    ```
             
    - **tabla:** nombre de la tabla sobre la cual se seleccionaran los registros
    - **condicion:** codigo sql que contenga la condicion para llevar a cabo la seleccion
