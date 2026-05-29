package com.sena;
import java.sql.*;

public class crud {
    public void insertarDatos(int n_documento, String correo_electronico, String password, String rol){
        String consulta ="INSERT INTO usuario(n_documento, correo_electronico, password, rol) VALUES (?,?,?,?)";

        try {
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setInt(1, n_documento);
            query.setString(2, correo_electronico);
            query.setString(3, password);
            query.setString(4, rol);
            query.executeUpdate();
            System.out.println("Datos Insertados");
        }
        catch (SQLException ex){
            System.out.println("Error al insertar el registro");
            ex.printStackTrace();
        }
    }
}
