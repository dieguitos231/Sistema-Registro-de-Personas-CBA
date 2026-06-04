package com.sena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
//    //Es string pero por el momento esta en void
    public void mostrarUsuarios(String tipo_documento, int n_documento, String nombres,String apellidos,String fecha_creacion){
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1,tipo_documento);
            query.setInt(2,n_documento);
            query.setString(3, nombres);
            query.setString(4, apellidos);
            query.setString(5, fecha_creacion);
            query.executeQuery();
        }
        catch (SQLException e){
            System.out.println("Error al obtener el detalle de usuario" +  e.getMessage() );
        }
    }
    public void crearUsuario(String tipo_documento,int n_documento,String nombres,String apellidos,String rol, String correo_electronico, String password){
        String query ="INSERT INTO usuario(n_documento,correo_electronico, password, rol) VALUES (?,?,crypt(?,gen_salt('bf')),?)";
        String query2 ="INSERT INTO detalle_usuario(tipo_documento,n_documento,nombres,apellidos) VALUES (?,?,?,?)";
        try (Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(query);){
                ps1.setInt(1, n_documento);
                ps1.setString(2, correo_electronico);
                ps1.setString(3, password);
                ps1.setString(4, rol);
                ps1.executeUpdate();
                System.out.println("Datos Insertados de usuario");
            }
            try( PreparedStatement ps2 = con.prepareStatement(query2)){
                ps2.setString(1,tipo_documento);
                ps2.setInt(2, n_documento);
                ps2.setString(3, nombres);
                ps2.setString(4, apellidos);
                ps2.executeUpdate();
                System.out.println("Datos Insertados de usuario");

            }
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
