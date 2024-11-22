/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector instance; // Instancia única del singleton
    private Connection connection; // Conexión única

    private static final String URL = "jdbc:mysql://localhost:3306/appJava?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Constructor privado para evitar la creación de instancias directas
    private DatabaseConnector() throws SQLException {
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Crear la conexión
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage());
        }
    }

    // Método sincronizado para obtener la instancia única
    public static synchronized DatabaseConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión y reiniciar la instancia
    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                instance = null; // Reinicia la instancia para permitir una nueva conexión en el futuro
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
