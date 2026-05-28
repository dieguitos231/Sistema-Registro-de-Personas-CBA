package com.sena;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicación del Sistema de Ingreso SENA...");

        // Probamos que la nueva clase de conexión funcione correctamente
        try (Connection conexion = ConexionDB.getConnection()) {
            if (conexion != null) {
                System.out.println("¡Conexión establecida con éxito usando ConexionDB!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

    }
}
