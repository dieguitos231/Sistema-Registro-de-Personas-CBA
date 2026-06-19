package com.sena;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

    public List<Object[]> iniciarSesion(String correo_electronico, char[] password) {
        List<Object[]> lista= new ArrayList<>();
        String consulta = "SELECT n_documento,rol, primer_ingreso FROM usuario WHERE correo_electronico=? AND password = crypt(?, password);";

        try {
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, correo_electronico);
            query.setString(2, new String(password));

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    Object[] array= new Object[3];
                    array[0] = rs.getInt("n_documento");
                    array[1] = rs.getString("rol");
                    array[2] = rs.getBoolean("primer_ingreso");
                    lista.add(array);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
        return lista;
    }
    public void  actualizarContrasena(char[] password, int n_documento){
        String consulta = "UPDATE usuario SET password=crypt(?,password) WHERE n_documento =?";
        try(Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(consulta)){
                ps1.setString(1, new String(password));
                ps1.setInt(2, n_documento);
                ps1.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println("Error al actualizar los datos: " + e.getMessage());
        }
    }
    public void actulizarIngreso(int n_documento){
        String consulta = "UPDATE usuario set primer_ingreso=false WHERE n_documento=?";
        try(Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(consulta)){
                ps1.setInt(1,n_documento);
                ps1.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println("Error al actualizar los datos: " + e.getMessage());
        }
    }
}