package com.sena;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

    public List<Object[]> iniciarSesion(String correo_electronico, char[] password) {
        List<Object[]> lista= new ArrayList<>();
        String consulta = "SELECT rol, primer_ingreso FROM usuario WHERE correo_electronico=? AND password = crypt(?, password);";

        try {
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, correo_electronico);
            query.setString(2, new String(password));

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    Object[] array= new Object[2];
                    array[0] = rs.getString("rol");
                    array[1] = rs.getBoolean("primer_ingreso");
                    lista.add(array);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
        return lista;
    }
    public actualizarContraseña(){

    }

}

