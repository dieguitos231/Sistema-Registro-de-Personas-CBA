package com.sena;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static String url;
    private static String user;
    private static String password;

    // Bloque estático para cargar las credenciales una sola vez al iniciar la aplicación
    static {
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream("database.properties");
            prop.load(file);
            url = prop.getProperty("db_url");
            user = prop.getProperty("db_user");
            password = prop.getProperty("db_password");
        } catch (Exception e) {
            System.err.println("Error al cargar database.properties: " + e.getMessage());
        }
    }

    /**
     * Retorna una nueva conexión activa a la base de datos PostgreSQL.
     * Cualquier archivo en el proyecto puede llamar a ConexionDB.getConnection()
     * para obtener acceso a la base de datos.
     *
     * @return Connection objeto de conexión JDBC
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
