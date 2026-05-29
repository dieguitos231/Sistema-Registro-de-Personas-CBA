package com.sena;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class Main {
    //PANELES

    private JFrame Login;
    private JFrame PanelAdmin;
    private JFrame GestionUsuarios;
    Color colorSena = new Color(57, 169, 0);
    Color colorSecundario = new Color(136,231,136);
    Color azulclaro=new Color(87,185,255);
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.Login.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Main(){
        // Probamos que la nueva clase de conexión funcione correctamente
        try (Connection conexion = ConexionDB.getConnection()) {
            if (conexion != null) {
                System.out.println("¡Conexión establecida con éxito usando ConexionDB!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        Login();
//        crud consultas = new crud();
//        consultas.insertarDatos(12345,"admin@example","admin","Administrador");
    }
    private void Login(){
        JTextField textEmail;
        JPasswordField textPassword;
        JButton btnIngresar;
        Login = new JFrame();
        //-----------CONFIGURACION VENTANA-------------------------
        Login.setTitle("Sistema de Ingreso SENA");
        Login.setSize(500,400);
        //Accion de que operacion se realizara al salir de la aplicacion.
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Define el diseño de como se organizara cada componente
        Login.setLayout(null);
        Login.getContentPane().setBackground(colorSena);

        //---------------------------------------------------------


        //------Titulo ---------
        JLabel lblTitle=new JLabel("Inicio de Sesion ");
        lblTitle.setBounds(150,20,200,100);
        lblTitle.setForeground(Color.white);
        lblTitle.setFont(new Font("Serif",Font.BOLD,24));
        Login.add(lblTitle);



        //-------CONFIGURACION CORREO ELECTRONICO------------
        //-------- LABEL --------------------------
        JLabel lblCorreo=new JLabel("Correo electronico");
        lblCorreo.setBounds(50,120,150,30);
        //Cambiar color al texto
        lblCorreo.setForeground(Color.white);
        lblCorreo.setHorizontalAlignment(JLabel.CENTER);
        Login.add(lblCorreo);
        //------ INPUT ---------------------------

        textEmail=new JTextField();
        textEmail.setBounds(200,120,200,30);
        Login.add(textEmail);

        //--------CONFIGURACION PASSWORD-------------
        //---------LABEL-----------------------------
        JLabel lblPassword=new JLabel("Password:");
        lblPassword.setBounds(50,180,200,30);
        lblPassword.setHorizontalAlignment(JLabel.CENTER);
        lblPassword.setForeground(Color.WHITE);
        Login.add(lblPassword);

        //-------INPUT-----------------------------
        textPassword=new JPasswordField();
        textPassword.setBounds(200,180,200,30);
        Login.add(textPassword);

        //---------CONFIGURACION BUTTON INGRESAR--------
        btnIngresar=new JButton("Ingresar");
        btnIngresar.setBounds(150,220,150,40);
        btnIngresar.setBackground(colorSecundario);
        Login.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String email=textEmail.getText();
                String password=textPassword.getText();
                Usuario usuario=new Usuario();
                String getRol=usuario.iniciarSesion(email,password);
                if(getRol.equals("administrador")){
                    email="";
                    password="";
                    PanelAdmin();
                }
            }
        });
    }
    private void PanelAdmin(){
        JButton btnPanelUsuario;
        JButton btnPanelRegistros;
        JButton btnCloseSession;
        PanelAdmin=new JFrame();
        PanelAdmin.setTitle("Panel Admin");
        PanelAdmin.setSize(500,400);
        PanelAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelAdmin.setLayout(null);


        //TITULO VENTANA
        JLabel textTitle=new JLabel("PANEL ADMINISTRATIVO");
        textTitle.setBounds(100,20,400,100);
        textTitle.setForeground(Color.black);
        textTitle.setFont(new Font("Serif",Font.BOLD,24));
        PanelAdmin.add(textTitle);

        // BOTON PANEL  USUARIOS
        btnPanelUsuario=new JButton("PANEL USUARIOS");
        btnPanelUsuario.setBounds(150,120,150,40);
        btnPanelUsuario.setBackground(Color.RED);
        btnPanelUsuario.setForeground(Color.black);
        PanelAdmin.add(btnPanelUsuario);

        btnPanelUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                GestionUsuarios();
            }
        });

        //BOTON PANEL REGISTROS
        btnPanelRegistros=new JButton("PANEL REGISTROS");
        btnPanelRegistros.setBounds(150,180,150,40);
        btnPanelRegistros.setBackground(azulclaro);
        btnPanelRegistros.setForeground(Color.BLACK);
        PanelAdmin.add(btnPanelRegistros);
        //BOTON CERRAR SESION
        btnCloseSession=new JButton("CERRAR SESION");
        btnCloseSession.setBounds(120,300,200,40);
        btnCloseSession.setBackground(Color.RED);
        btnCloseSession.setForeground(Color.white);
        PanelAdmin.add(btnCloseSession);

        btnCloseSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PanelAdmin.dispose();
            }
        });


        PanelAdmin.setVisible(true);
    }
    private void GestionUsuarios(){
        JButton btnCreateUser;
        JButton btnUpdateUser;
        JButton btnDeleteUser;
        JButton btnReturn;

        //TITULO
        GestionUsuarios=new JFrame();
        GestionUsuarios.setTitle("Panel Gestion de usuarios");
        GestionUsuarios.setSize(400,400);
        GestionUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GestionUsuarios.setLayout(null);

        //BOTON VOLVER
        btnReturn=new JButton("VOLVER");
        btnReturn.setBounds(250,40,100,40);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        GestionUsuarios.add(btnReturn);

        //BOTON CREAR USUARIO
        btnCreateUser=new JButton("CREAR NUEVO USUARIO");
        btnCreateUser.setBounds(100,100,200,40);
        btnCreateUser.setBackground(colorSena);
        btnCreateUser.setForeground(Color.black);
        GestionUsuarios.add(btnCreateUser);
        btnCreateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
//                CrearUsuario();
            }
        });

        //BOTON MODIFICAR USUARIO
        btnUpdateUser=new JButton("MODIFICAR USUARIO");
        btnUpdateUser.setBounds(100,150,200,40);
        btnUpdateUser.setBackground(Color.YELLOW);
        btnUpdateUser.setForeground(Color.black);
        GestionUsuarios.add(btnUpdateUser);

        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
//                BuscarUsuario();
            }
        });

        //BOTON ELIMINAR USUARIO
        btnDeleteUser=new JButton("ELIMINAR USUARIO");
        btnDeleteUser.setBounds(100,200,200,40);
        btnDeleteUser.setBackground(Color.RED);
        btnDeleteUser.setForeground(Color.black);
        GestionUsuarios.add(btnDeleteUser);

        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
//                BuscarUsuario();
            }
        });

        GestionUsuarios.setVisible(true);
    }
}
