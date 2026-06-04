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
    private JFrame PanelUsuarios;
    private JFrame GestionUsuarios;
    private JFrame CrearUsuario;
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



        //-------Configuracion correo electronico------------
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
                    PanelAdmin();
                }
            }
        });
    }
    private void PanelAdmin(){
        JButton btnPanelUsuario;
        JButton btnPanelRegistros;
        JButton btnCloseSession;
        //Titulo Ventana
        PanelAdmin=new JFrame();
        PanelAdmin.setTitle("Panel Admin");
        PanelAdmin.setSize(500,400);
        PanelAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelAdmin.setLayout(null);

        //Titulo
        JLabel textTitle=new JLabel("Panel Administrativo");
        textTitle.setBounds(120,20,400,100);
        textTitle.setForeground(Color.black);
        textTitle.setFont(new Font("Serif",Font.BOLD,24));
        PanelAdmin.add(textTitle);

        // Boton panel usuarios
        btnPanelUsuario=new JButton("Panel Usuario");
        btnPanelUsuario.setBounds(120,120,200,40);
        btnPanelUsuario.setBackground(Color.ORANGE);
        btnPanelUsuario.setForeground(Color.black);
        PanelAdmin.add(btnPanelUsuario);

        //Boton panel registros
        btnPanelRegistros=new JButton("Historial Ingresos");
        btnPanelRegistros.setBounds(120,180,200,40);
        btnPanelRegistros.setBackground(azulclaro);
        btnPanelRegistros.setForeground(Color.BLACK);
        PanelAdmin.add(btnPanelRegistros);

        //Boton Cerrar Sesion
        btnCloseSession=new JButton("Cerrar Sesion");
        btnCloseSession.setBounds(150,300,150,35);
        btnCloseSession.setBackground(Color.RED);
        btnCloseSession.setForeground(Color.white);
        PanelAdmin.add(btnCloseSession);

        btnPanelUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                PanelUsuarios();
            }
        });
        btnCloseSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PanelAdmin.dispose();
            }
        });


        PanelAdmin.setVisible(true);
    }
    private void PanelUsuarios(){
        JButton btnReturn;
        JButton btnGestionUsuario;
        JButton btnMostrarUsuario;
        JButton btnBuscarUsuario;
        //Titulo Ventana
        PanelUsuarios=new JFrame();
        PanelUsuarios.setTitle("Panel Usuarios");
        PanelUsuarios.setSize(500,400);
        PanelUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelUsuarios.setLayout(null);
        //Boton Volver
        btnReturn=new JButton("Volver");
        btnReturn.setBounds(40,20,100,30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        PanelUsuarios.add(btnReturn);
        //Titulo
        JLabel textTitle=new JLabel("Panel Usuarios");
        textTitle.setBounds(150,40,200,100);
        textTitle.setFont(new Font("Serif",Font.BOLD,24));
        PanelUsuarios.add(textTitle);
        //Boton gestion de usuario
        btnGestionUsuario=new JButton("Gestion Usuario");
        btnGestionUsuario.setBounds(120,120,200,40);
        btnGestionUsuario.setBackground(colorSecundario);
        PanelUsuarios.add(btnGestionUsuario);
        //Boton mostrar Usuarios
        btnMostrarUsuario=new JButton("Mostrar Usuario");
        btnMostrarUsuario.setBounds(120,180,200,40);
        btnMostrarUsuario.setBackground(Color.YELLOW);
        PanelUsuarios.add(btnMostrarUsuario);
        //Boton mostrar info de un usuario
        btnBuscarUsuario=new JButton("Buscar Usuario");
        btnBuscarUsuario.setBounds(120,240,200,40);
        btnBuscarUsuario.setBackground(Color.RED);
        PanelUsuarios.add(btnBuscarUsuario);

        btnGestionUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                GestionUsuarios();
            }
        });
        PanelUsuarios.setVisible(true);
    }
    private void GestionUsuarios(){
        JButton btnReturn;
        JButton btnCreateUser;
        JButton btnUpdateUser;
        JButton btnDeleteUser;


        //Titulo Ventana
        GestionUsuarios=new JFrame();
        GestionUsuarios.setTitle("Panel Gestion de usuarios");
        GestionUsuarios.setSize(400,400);
        GestionUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GestionUsuarios.setLayout(null);

        //Boton Volver
        btnReturn=new JButton("VOLVER");
        btnReturn.setBounds(40,20,100,30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        GestionUsuarios.add(btnReturn);

        //Titulo Principal
        JLabel textTitle=new JLabel("Gestion de Usuarios");
        textTitle.setBounds(150,40,400,100);
        textTitle.setFont(new Font("Serif",Font.BOLD,24));
        GestionUsuarios.add(textTitle);

        //Boton Crear Usuario
        btnCreateUser=new JButton("Crear Usuario");
        btnCreateUser.setBounds(150,120,200,40);
        btnCreateUser.setBackground(colorSecundario);
        btnCreateUser.setForeground(Color.black);
        GestionUsuarios.add(btnCreateUser);

        //Boton Modificar Usuario
        btnUpdateUser=new JButton("Modificar Usuario");
        btnUpdateUser.setBounds(150,180,200,40);
        btnUpdateUser.setBackground(Color.YELLOW);
        btnUpdateUser.setForeground(Color.black);
        GestionUsuarios.add(btnUpdateUser);

        //Boton Eliminar Usuario
        btnDeleteUser=new JButton("Eliminar Usuario");
        btnDeleteUser.setBounds(150,240,200,40);
        btnDeleteUser.setBackground(Color.RED);
        btnDeleteUser.setForeground(Color.black);
        GestionUsuarios.add(btnDeleteUser);

        btnCreateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                CrearUsuario();
            }
        });
        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
//                BuscarUsuario();
            }
        });
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
//                BuscarUsuario();
            }
        });

        GestionUsuarios.setVisible(true);
    }
    private void CrearUsuario(){
        JComboBox<String> tipoId;
        JTextField numeroId;
        JTextField nombre;
        JTextField apellido;
        JTextField correo_electronico;
        JComboBox<String> rol;

        JButton create;
        JButton cancel;

        CrearUsuario=new JFrame();
        CrearUsuario.setTitle("Crear usuario");
        CrearUsuario.setSize(500,400);
        CrearUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CrearUsuario.setLayout(null);

        //TITULO
        JLabel title=new JLabel("Crear Nuevo Usuario");
        title.setBounds(80,20,300,30);
        title.setForeground(colorSena);
        title.setFont(new Font("Serif",Font.BOLD,24));
        CrearUsuario.add(title);

        //TIPO ID
        //LABEL
        JLabel lblTipoId=new JLabel("T.I");
        lblTipoId.setBounds(80,60,50,30);
        CrearUsuario.add(lblTipoId);
        //INPUT
        String[] tipo={
                "CC",
                "TI",
                "Pasaporte",
                "CE",
                "PPT"
        };
        tipoId=new JComboBox<>(tipo);
        tipoId.setBounds(80,100,50,30);
        CrearUsuario.add(tipoId);

        //NUMERO DE IDENTIFICACION
        //LABEL
        JLabel lblNumeroId=new JLabel("Numero de identificación");
        lblNumeroId.setBounds(140,60,150,30);
        CrearUsuario.add(lblNumeroId);
        //INPUT
        numeroId=new JTextField();
        numeroId.setBounds(140,100,150,30);
        CrearUsuario.add(numeroId);

        //NOMBRE
        //LABEL
        JLabel lblNombre=new JLabel("Nombre");
        lblNombre.setBounds(80,140,150,30);
        CrearUsuario.add(lblNombre);
        //INPUT
        nombre=new JTextField();
        nombre.setBounds(80,180,150,30);
        CrearUsuario.add(nombre);
        //Apellidos
        //LABEL
        JLabel lblApellidos=new JLabel("Apellidos");
        lblApellidos.setBounds(250,140,150,30);
        CrearUsuario.add(lblApellidos);
        //INPUT
        apellido=new JTextField();
        apellido.setBounds(250,180,150,30);
        CrearUsuario.add(apellido);

        //Email
        //LABEL
        JLabel lblEmail=new JLabel("Correo electronico");
        lblEmail.setBounds(80,220,150,30);
        CrearUsuario.add(lblEmail);
        //INPUT
        correo_electronico=new JTextField();
        correo_electronico.setBounds(80,250,150,30);
        CrearUsuario.add(correo_electronico);

        //ROL
        //LABEL
        JLabel lblRol=new JLabel("Rol");
        lblRol.setBounds(250,220,50,30);
        CrearUsuario.add(lblRol);
        //INPUT
        String[] opcionesRol = {
                "Aprendiz",
                "Funcionario",
                "Administrador"
        };
        rol=new JComboBox<>(opcionesRol);
        rol.setBounds(250,250,100,30);
        CrearUsuario.add(rol);

        //CREAR
        create=new JButton("CREAR");
        create.setBounds(330,300,100,30);
        create.setBackground(azulclaro);
        CrearUsuario.add(create);

        //CANCELAR
        cancel=new JButton("CANCELAR");
        cancel.setBounds(200,300,100,30);
        cancel.setBorder(null);
        CrearUsuario.add(cancel);

        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String tipo=tipoId.getSelectedItem().toString();
                //Falta convertir el string a entero
                String nombres=nombre.getText();
                String apellidos=apellido.getText();
                String email=correo_electronico.getText();
                String rolAsignado=rol.getSelectedItem().toString();
                Admin admin=new Admin();
                admin.crearUsuario(tipo,1032,nombres,apellidos,rolAsignado,email,"admin");
            }
        });

        CrearUsuario.setVisible(true);
    }
}
