package com.sena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
//    Es string pero por el momento esta en void
    public void mostrarUsuarios(String tipo_documento, int n_documento, String nombres,String apellidos,String fecha_creacion){
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1.tipo_documento);
            query.setInt(2.n_documento);
            query.setString(3.nombres);
            query.set

        }
    }
    public void crearUsuario(int n_documento, String correo_electronico, String password, String rol){
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
    public void modificarUsuarios(){

    }
    public void eliminarUsuarios(){

    }

}
