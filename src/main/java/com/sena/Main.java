package com.sena;

import java.sql.Connection;
import java.sql.SQLException;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class Main {
    //Colores
    Color colorSena = new Color(57, 169, 0);
    Color colorSecundario = new Color(136, 231, 136);
    Color azulclaro = new Color(87, 185, 255);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        // Comprobamos la conexión a la base de datos
        try (Connection conexion = ConexionDB.getConnection()) {
            if (conexion != null) {
                System.out.println("¡Conexión establecida con éxito usando ConexionDB!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        panelLogin();
    }

    private void panelLogin() {
        //Labels
        JLabel titulo;
        JLabel correo;
        JLabel password;
        //Inputs
        JTextField textEmail;
        JPasswordField textPassword;
        JButton btnIngresar;


        //Configuracion ventana
        JFrame Login = new JFrame();
        Login.setTitle("Sistema de Ingreso SENA");
        Login.setSize(500, 400);
        Login.getContentPane().setBackground(colorSena);
        Login.setLocationRelativeTo(null);
        Login.setLayout(null);
        Login.setResizable(false);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo Principal
        titulo = new JLabel("Inicio de Sesion ");
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(150, 20, 200, 100); //Posicion
        Login.add(titulo);

        //Correo
        correo = new JLabel("Correo electronico");
        correo.setForeground(Color.white);
        correo.setHorizontalAlignment(JLabel.CENTER);
        correo.setBounds(50, 120, 150, 30);
        Login.add(correo);

        textEmail = new JTextField();
        textEmail.setBounds(200, 120, 200, 30);
        Login.add(textEmail);

        //Password
        password = new JLabel("Password:");
        password.setHorizontalAlignment(JLabel.CENTER);
        password.setForeground(Color.WHITE);
        password.setBounds(50, 180, 200, 30);
        Login.add(password);

        textPassword = new JPasswordField();
        textPassword.setBounds(200, 180, 200, 30);
        Login.add(textPassword);

        //Boton ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(colorSecundario);
        btnIngresar.setBounds(150, 220, 150, 40);
        Login.add(btnIngresar);

        btnIngresar.addActionListener(event -> {
            String txtEmail = textEmail.getText();
            char[] txtPassword = textPassword.getPassword();
            //Validacion entrada de texto
            if (txtEmail.isEmpty() || txtPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el email");
            }
            if (txtPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el password");
            }
            Usuario usuario = new Usuario();
            List<Object[]> dato = usuario.iniciarSesion(txtEmail, txtPassword);
            for(Object[] data : dato) {
                Integer n_documento = (Integer) data[0];
                String rol= (String) data[1];
                Boolean primerIngreso= (Boolean) data[2];
                if(rol.equals("administrador")&& primerIngreso.equals(false)) {
                    panelAdmin();
                }else if(rol.equals("administrador")&& primerIngreso.equals(true)) {
                    primerIngreso(n_documento);
                    usuario.actulizarIngreso(n_documento);
                }
            }
            //Vacio de valores al iniciar sesion
            textEmail.setText("");
            textPassword.setText("");
        });
        Login.setVisible(true);
    }

    private void panelAdmin() {
        //Labels
        JLabel titulo;
        //Botones
        JButton btnPanelUsuario;
        JButton btnPanelRegistros;
        JButton btnCloseSession;

        //Configuracion Ventana
        JFrame PanelAdmin = new JFrame();
        PanelAdmin.setTitle("Panel Admin");
        PanelAdmin.setSize(400, 400);
        PanelAdmin.setLocationRelativeTo(null);
        PanelAdmin.setLayout(null);
        PanelAdmin.setResizable(false);
        PanelAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo
        titulo = new JLabel("Panel Administrador");
        titulo.setForeground(Color.black);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 400, 100);
        PanelAdmin.add(titulo);

        // Boton panel usuarios
        btnPanelUsuario = new JButton("Panel Usuario");
        btnPanelUsuario.setBackground(Color.ORANGE);
        btnPanelUsuario.setBounds(80, 120, 200, 40);
        PanelAdmin.add(btnPanelUsuario);

        //Boton panel registros
        btnPanelRegistros = new JButton("Historial Ingresos");
        btnPanelRegistros.setBackground(azulclaro);
        btnPanelRegistros.setBounds(80, 180, 200, 40);
        PanelAdmin.add(btnPanelRegistros);

        //Boton Cerrar Sesion
        btnCloseSession = new JButton("Cerrar Sesion");
        btnCloseSession.setBackground(Color.RED);
        btnCloseSession.setForeground(Color.white);
        btnCloseSession.setBounds(100, 300, 150, 35);
        PanelAdmin.add(btnCloseSession);

        btnPanelUsuario.addActionListener(event -> panelUsuarios());
//      btnPanelRegistros.addActionListener(event -> panelIngresos()) PENDIENTE
        btnCloseSession.addActionListener(event -> PanelAdmin.dispose());
        PanelAdmin.setVisible(true);
    }

    private void panelUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnGestionUsuario;
        JButton btnMostrarUsuario;
        JButton btnBuscarUsuario;

        //Configuracion ventana
        JFrame PanelUsuarios = new JFrame();
        PanelUsuarios.setTitle("Panel Usuarios");
        PanelUsuarios.setSize(400, 400);
        PanelUsuarios.setLocationRelativeTo(null);
        PanelUsuarios.setLayout(null);
        PanelUsuarios.setResizable(false);
        PanelUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Boton Volver
        btnReturn = new JButton("Volver");
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBounds(40, 20, 100, 30);
        PanelUsuarios.add(btnReturn);

        //Titulo principal
        titulo = new JLabel("Panel Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(100, 40, 200, 100);
        PanelUsuarios.add(titulo);

        //Boton gestion de usuario
        btnGestionUsuario = new JButton("Gestion Usuario");
        btnGestionUsuario.setBackground(colorSecundario);
        btnGestionUsuario.setBounds(80, 120, 200, 40);
        PanelUsuarios.add(btnGestionUsuario);

        //Boton mostrar Usuarios
        btnMostrarUsuario = new JButton("Mostrar Usuario");
        btnMostrarUsuario.setBackground(Color.YELLOW);
        btnMostrarUsuario.setBounds(80, 180, 200, 40);
        PanelUsuarios.add(btnMostrarUsuario);

        //Boton mostrar info de un usuario
        btnBuscarUsuario = new JButton("Buscar Usuario");
        btnBuscarUsuario.setBackground(Color.RED);
        btnBuscarUsuario.setBounds(80, 240, 200, 40);
        PanelUsuarios.add(btnBuscarUsuario);

        btnReturn.addActionListener(event -> PanelUsuarios.dispose());
        btnGestionUsuario.addActionListener(event -> {
            gestionUsuarios();
            PanelUsuarios.dispose();
        });
        btnMostrarUsuario.addActionListener(event -> mostrarUsuarios());
        btnBuscarUsuario.addActionListener(event -> mostrarUsuario());
        PanelUsuarios.setVisible(true);
    }
    public void primerIngreso(int n_documento){
        //Label
        JLabel titulo;
        //Input
        JPasswordField password;
        //Boton
        JButton enviar;

        JFrame PrimerIngreso=new JFrame();
        PrimerIngreso.setTitle("Inicio de sesion");
        PrimerIngreso.setSize(400,200);
        PrimerIngreso.getContentPane().setBackground(colorSena);
        PrimerIngreso.setLocationRelativeTo(null);
        PrimerIngreso.setLayout(null);
        PrimerIngreso.setResizable(false);
        PrimerIngreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo
        titulo=new JLabel("Ingrese la nueva contraseña");
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(40,20,400,50);
        PrimerIngreso.add(titulo);

        //Password
        password=new JPasswordField();
        password.setFont(new Font("Serif",Font.BOLD,16));
        password.setBounds(40,100,150,30);
        PrimerIngreso.add(password);

        //Enviar
        enviar=new JButton("Enviar");
        enviar.setBackground(colorSecundario);
        enviar.setBounds(200,100,100,30);
        PrimerIngreso.add(enviar);

        enviar.addActionListener(event -> {
            char[] passwordDigitada=password.getPassword();
            Usuario usuario=new Usuario();
            usuario.actualizarContrasena(passwordDigitada,n_documento);
            panelAdmin();
        });
        PrimerIngreso.setVisible(true);
    }

    private Integer convertirNumero(String numeroStr) {
        try {
            return Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero invalido");
            return null;
        }
    }

    private void gestionUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnCreateUser;
        JButton btnUpdateUser;
        JButton btnDeleteUser;

        //Configuracion ventana
        JFrame GestionUsuarios = new JFrame();
        GestionUsuarios.setTitle("Panel Gestion de usuarios");
        GestionUsuarios.setSize(400, 400);
        GestionUsuarios.setLocationRelativeTo(null);
        GestionUsuarios.setLayout(null);
        GestionUsuarios.setResizable(false);
        GestionUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Boton Volver
        btnReturn = new JButton("VOLVER");
        btnReturn.setBounds(40, 20, 100, 30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        GestionUsuarios.add(btnReturn);

        //Titulo Principal
        titulo = new JLabel("Gestion de Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 40, 400, 100);
        GestionUsuarios.add(titulo);

        //Boton Crear Usuario
        btnCreateUser = new JButton("Crear Usuario");
        btnCreateUser.setBackground(colorSecundario);
        btnCreateUser.setForeground(Color.black);
        btnCreateUser.setBounds(80, 120, 200, 40);
        GestionUsuarios.add(btnCreateUser);

        //Boton Modificar Usuario
        btnUpdateUser = new JButton("Modificar Usuario");
        btnUpdateUser.setBounds(80, 180, 200, 40);
        btnUpdateUser.setBackground(Color.YELLOW);
        btnUpdateUser.setForeground(Color.black);
        GestionUsuarios.add(btnUpdateUser);

        //Boton Eliminar Usuario
        btnDeleteUser = new JButton("Eliminar Usuario");
        btnDeleteUser.setBackground(Color.RED);
        btnDeleteUser.setForeground(Color.black);
        btnDeleteUser.setBounds(80, 240, 200, 40);
        GestionUsuarios.add(btnDeleteUser);

        btnReturn.addActionListener(event -> {
            GestionUsuarios.dispose();
            panelUsuarios();
        });
        btnCreateUser.addActionListener(event -> crearUsuario());
        btnUpdateUser.addActionListener(event -> buscarUsuario("update"));
        btnDeleteUser.addActionListener(event -> buscarUsuario("delete"));
        GestionUsuarios.setVisible(true);
    }

    private void crearUsuario() {
        //Labels
        JLabel titulo;
        JLabel lblNumeroId;
        JLabel lblNombre;
        JLabel lblApellidos;
        JLabel lblEmail;
        JLabel lblRol;
        //Inputs
        JComboBox<String> tipoId;
        JTextField numeroId;
        JTextField nombre;
        JTextField apellido;
        JTextField correo_electronico;
        JComboBox<String> rol;
        //Botones
        JButton create;
        JButton cancel;

        //Configuracion Ventana
        JFrame CrearUsuario = new JFrame();
        CrearUsuario.setTitle("Crear usuario");
        CrearUsuario.setSize(500, 400);
        CrearUsuario.setLocationRelativeTo(null);
        CrearUsuario.setLayout(null);
        CrearUsuario.setResizable(false);
        CrearUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo
        titulo = new JLabel("Crear Nuevo Usuario");
        titulo.setForeground(colorSena);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 300, 30);
        CrearUsuario.add(titulo);

        //Tipo ID
        JLabel lblTipoId = new JLabel("T.I");
        lblTipoId.setBounds(60, 60, 50, 30);
        CrearUsuario.add(lblTipoId);

        String[] tipos = {
                "CC",
                "TI",
                "CE",
                "PPT"
        };
        tipoId = new JComboBox<>(tipos);
        tipoId.setBackground(Color.WHITE);
        tipoId.setBounds(60, 100, 150, 30);
        CrearUsuario.add(tipoId);

        //NUMERO DE IDENTIFICACION
        lblNumeroId = new JLabel("Numero de identificación");
        lblNumeroId.setBounds(250, 60, 150, 30);
        CrearUsuario.add(lblNumeroId);

        numeroId = new JTextField();
        numeroId.setBounds(250, 100, 150, 30);
        CrearUsuario.add(numeroId);

        //NOMBRE
        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(60, 140, 150, 30);
        CrearUsuario.add(lblNombre);

        nombre = new JTextField();
        nombre.setBounds(60, 180, 150, 30);
        CrearUsuario.add(nombre);

        //Apellidos
        lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(250, 140, 150, 30);
        CrearUsuario.add(lblApellidos);

        apellido = new JTextField();
        apellido.setBounds(250, 180, 150, 30);
        CrearUsuario.add(apellido);

        //Email
        lblEmail = new JLabel("Correo electronico");
        lblEmail.setBounds(60, 220, 200, 30);
        CrearUsuario.add(lblEmail);

        correo_electronico = new JTextField();
        correo_electronico.setBounds(60, 250, 150, 30);
        CrearUsuario.add(correo_electronico);

        //ROL
        lblRol = new JLabel("Rol");
        lblRol.setBounds(230, 220, 50, 30);
        CrearUsuario.add(lblRol);

        String[] opcionesRol = {
                "aprendiz",
                "funcionario",
                "administrador"
        };
        rol = new JComboBox<>(opcionesRol);
        rol.setBackground(Color.WHITE);
        rol.setBounds(230, 250, 100, 30);
        CrearUsuario.add(rol);

        //CAMPO SEGUN ROL SELECCIONADO
        JLabel lblRolSelected = new JLabel("Ficha");
        lblRolSelected.setBounds(340, 220, 100, 30);
        CrearUsuario.add(lblRolSelected);

        JTextField txt = new JTextField();
        txt.setBounds(340, 250, 100, 30);
        CrearUsuario.add(txt);

        rol.addActionListener(event -> {
            String itemSeleccionado = (String) rol.getSelectedItem();
            lblRolSelected.setVisible(true);
            txt.setVisible(true);
            if (itemSeleccionado.equals("aprendiz")) {
                lblRolSelected.setText("Ficha");
            } else if (itemSeleccionado.equals("funcionario")) {
                lblRolSelected.setText("Cargo");
            } else {
                lblRolSelected.setVisible(false);
                txt.setVisible(false);
            }
        });

        //CREAR
        create = new JButton("CREAR");
        create.setBackground(azulclaro);
        create.setBounds(330, 300, 100, 30);
        CrearUsuario.add(create);

        //CANCELAR
        cancel = new JButton("CANCELAR");
        cancel.setBackground(Color.WHITE);
        cancel.setBounds(200, 300, 100, 30);
        CrearUsuario.add(cancel);

        create.addActionListener(event -> {
            String tipo = tipoId.getSelectedItem().toString();
            String numero = numeroId.getText();
            Integer numeroIdConvertido = convertirNumero(numero);
            String nombres = nombre.getText().toLowerCase();
            String apellidos = apellido.getText().toLowerCase();
            String email = correo_electronico.getText();
            String password = numeroId.getText();
            String rolAsignado = rol.getSelectedItem().toString();
            String txtDigitado = txt.getText();
            Admin admin = new Admin();
            if (rolAsignado.equals("aprendiz")) {
                Integer nFichaConvertido = convertirNumero(txtDigitado);
                admin.crearUsuarioAprendiz(tipo, numeroIdConvertido, nombres, apellidos, email, password, nFichaConvertido);
            } else if (rolAsignado.equals("funcionario")) {
                admin.crearUsuarioFuncionario(tipo, numeroIdConvertido, nombres, apellidos, email, password, txtDigitado);

            }
            //Limpiar variables
            tipoId.setSelectedIndex(0);
            numeroId.setText("");
            nombre.setText("");
            apellido.setText("");
            correo_electronico.setText("");
            rol.setSelectedIndex(0);
            txt.setText("");
        });
        cancel.addActionListener(event -> {
            CrearUsuario.dispose();
        });
        CrearUsuario.setVisible(true);
    }

    private void buscarUsuario(String panel) {
        //Labels
        JLabel titulo;
        JLabel lblTipo;
        JLabel lblNumero;
        //Inputs
        JComboBox<String> tipoId;
        JTextField n_documento;
        //Botones
        JButton btnBuscar;
        JButton btnCancelar;

        //Configuracion Ventana
        JFrame BuscarUsuario = new JFrame();
        BuscarUsuario.setTitle("Buscar Usuario");
        BuscarUsuario.setSize(400, 300);
        BuscarUsuario.setLocationRelativeTo(null);
        BuscarUsuario.setLayout(null);
        BuscarUsuario.setResizable(false);
        BuscarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Titulo
        titulo = new JLabel("Buscar Usuario");
        titulo.setForeground(colorSena);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 400, 40);
        BuscarUsuario.add(titulo);
        //Tipo
        lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(80, 80, 100, 40);
        BuscarUsuario.add(lblTipo);

        String[] tipo = {
                "CC",
                "TI",
                "Pasaporte",
                "CE"
        };
        tipoId = new JComboBox<>(tipo);
        tipoId.setBackground(Color.WHITE);
        tipoId.setBounds(80, 120, 50, 30);
        BuscarUsuario.add(tipoId);

        //Numero
        lblNumero = new JLabel("Numero de identificacion");
        lblNumero.setBounds(180, 80, 200, 40);
        BuscarUsuario.add(lblNumero);

        n_documento = new JTextField();
        n_documento.setBounds(180, 120, 150, 30);
        BuscarUsuario.add(n_documento);

        //Boton buscar
        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBackground(azulclaro);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(230, 180, 100, 30);
        BuscarUsuario.add(btnBuscar);

        //Boton Cancelar
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(Color.white);
        btnCancelar.setBounds(120, 180, 100, 30);
        BuscarUsuario.add(btnCancelar);
        switch (panel) {
            case "actualizar":
                System.out.println("Panel Actulizar informacion");
                break;
            case "eliminar":
                System.out.println("Panel Eliminar informacion");
                break;
            case "mostrarInfo":
                System.out.println("Panel mostrar informacion");
                break;
        }
        BuscarUsuario.setVisible(true);
    }

    private void actualizarInfo(String tipoId, String n_documento) {
        JFrame ActualizarInfo = new JFrame();
    }

    private void eliminarUsuario(String tipoId, String n_documento) {
        JFrame EliminarUsuario = new JFrame();
        EliminarUsuario.setTitle("Eliminar Usuario");
    }
    private void mostrarUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnFuncionarios;
        JButton btnAprendices;

        //Configuracion Ventana
        JFrame MostrarUsuario = new JFrame();
        MostrarUsuario.setTitle("Mostrar Usuario");
        MostrarUsuario.setSize(400, 300);
        MostrarUsuario.setLocationRelativeTo(null);
        MostrarUsuario.setLayout(null);
        MostrarUsuario.setResizable(false);
        MostrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Volver
        btnReturn = new JButton("VOLVER");
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBounds(40, 20, 100, 30);
        MostrarUsuario.add(btnReturn);

        //Titulo
        titulo = new JLabel("Mostrar Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(100, 40, 400, 100);
        MostrarUsuario.add(titulo);

        //Boton funcionarios
        btnFuncionarios = new JButton("Funcionarios");
        btnFuncionarios.setBackground(colorSecundario);
        btnFuncionarios.setBounds(100, 120, 150, 40);
        MostrarUsuario.add(btnFuncionarios);

        //Boton aprendices
        btnAprendices = new JButton("Aprendices");
        btnAprendices.setBackground(Color.YELLOW);
        btnAprendices.setBounds(100, 180, 150, 40);
        MostrarUsuario.add(btnAprendices);
        btnReturn.addActionListener(event -> {
           MostrarUsuario.setVisible(false);
           MostrarUsuario.dispose();
        });
        btnFuncionarios.addActionListener(event -> {
            mostrarFuncionarios();
        });
        btnAprendices.addActionListener(event -> {
            mostrarAprendices();
        });
        MostrarUsuario.setVisible(true);
    }

    private void mostrarAprendices() {
        JFrame MostrarAprendices=new JFrame();
        MostrarAprendices.setTitle("Mostrar Aprendices");
        MostrarAprendices.setSize(800,500);
        MostrarAprendices.setLocationRelativeTo(null);
        MostrarAprendices.setResizable(false);
        MostrarAprendices.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Admin admin = new Admin();
        //Tabla
        String[] columnas= {"T.I","N_IDENTIFICACION","NOMBRE","FECHA CREACION"};
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn(columnas[0]);
        model.addColumn(columnas[1]);
        model.addColumn(columnas[2]);
        model.addColumn(columnas[3]);
        JTable table=new JTable(model);
        JScrollPane scroll=new JScrollPane(table);
        MostrarAprendices.add(scroll,BorderLayout.CENTER);
        //Agregar filas
        List<Object[]> lista = admin.mostrarAprendices();
        if (lista.size() != 0){
            for (Object[] elemento : lista) {
                String tipo = (String) elemento[0];
                Integer n_documento = (Integer) elemento[1];
                String nombre= (String) elemento[2];
                String apellido= (String) elemento[3];
                String fecha=(String) elemento[4];
                model.addRow(new Object[]{tipo,n_documento,(nombre+apellido),fecha});
                MostrarAprendices.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen datos encontrados");
            MostrarAprendices.setVisible(false);
        }


    }
    private void mostrarFuncionarios() {
        JFrame MostrarFuncionarios=new JFrame();
        MostrarFuncionarios.setTitle("Mostrar Funcionarios");
        MostrarFuncionarios.setLocationRelativeTo(null);
        MostrarFuncionarios.setSize(800,500);
        MostrarFuncionarios.setResizable(false);
        MostrarFuncionarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Admin admin = new Admin();
        //Tabla
        String[] columnas= {"T.I","N_IDENTIFICACION","NOMBRE","FECHA CREACION"};
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn(columnas[0]);
        model.addColumn(columnas[1]);
        model.addColumn(columnas[2]);
        model.addColumn(columnas[3]);
        JTable table=new JTable(model);
        JScrollPane scroll=new JScrollPane(table);
        MostrarFuncionarios.add(scroll,BorderLayout.CENTER);
        //Agregar filas
        List<Object[]> lista = admin.mostrarFuncionarios();
        if (lista.size() != 0){
            for (Object[] elemento : lista) {
                String tipo = (String) elemento[0];
                Integer n_documento = (Integer) elemento[1];
                String nombre= (String) elemento[2];
                String apellido= (String) elemento[3];
                String fecha=(String) elemento[4];
                model.addRow(new Object[]{tipo,n_documento,(nombre+apellido),fecha});
                MostrarFuncionarios.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen datos encontrados");
            MostrarFuncionarios.setVisible(false);
        }
    }

    private void mostrarUsuario(){
        //Labels
        JLabel titulo;
        JLabel lblTituloId;
        JLabel lblNumero;
        JLabel lblTipoId;
        JLabel lblNombre;
        JLabel lblRol;
        JLabel txt;
        //Inputs
        JLabel infTipoId;
        JLabel infNumeroId;
        JLabel infNombre;
        JLabel info; //Ficha o cargo
        JLabel infRol;
        //Input
        JTextField numeroDigitado;

        //Butons
        JButton btnBuscar;
        JButton btnReturn;

        JFrame MostrarUsuario=new JFrame();
        MostrarUsuario.setTitle("Buscar información usuario");
        MostrarUsuario.setSize(500,400);
        MostrarUsuario.setLocationRelativeTo(null);
        MostrarUsuario.setLayout(null);
        MostrarUsuario.setResizable(false);
        MostrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Boton volver
        btnReturn=new JButton("Volver");
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBounds(40,20,80,30);
        MostrarUsuario.add(btnReturn);


        //Titulo
        titulo=new JLabel("Consultar Usuario");
        titulo.setForeground(colorSecundario);
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        titulo.setBounds(80,60,300,30);
        MostrarUsuario.add(titulo);

        //Input
        lblTituloId=new JLabel("Numero de identificacion");
        lblTituloId.setBounds(80,90,150,30);
        MostrarUsuario.add(lblTituloId);

        numeroDigitado=new JTextField();
        numeroDigitado.setBounds(80, 120, 150, 30);
        MostrarUsuario.add(numeroDigitado);

        btnBuscar=new JButton("BUSCAR");
        btnBuscar.setBackground(azulclaro);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(240,120,100,30);
        MostrarUsuario.add(btnBuscar);

        //Tipo de id
        lblTipoId=new JLabel("T.I :");
        lblTipoId.setBounds(80,160,50,30);
        MostrarUsuario.add(lblTipoId);

        infTipoId=new JLabel();
        infTipoId.setBounds(120,160,100,30);
        MostrarUsuario.add(infTipoId);

        //Numero de identificacion
        lblNumero=new JLabel("Numero de identificacion:");
        lblNumero.setBounds(200,160,150,30);
        MostrarUsuario.add(lblNumero);

        infNumeroId=new JLabel();
        infNumeroId.setBounds(360,160,100,30);
        MostrarUsuario.add(infNumeroId);

        //Nombre
        lblNombre=new JLabel("Nombre:");
        lblNombre.setBounds(80,180,100,30);
        MostrarUsuario.add(lblNombre);

        infNombre=new JLabel();
        infNombre.setBounds(140,180,300,30);
        MostrarUsuario.add(infNombre);

        //Rol
        lblRol=new JLabel("Rol:");
        lblRol.setBounds(80,200,100,30);
        MostrarUsuario.add(lblRol);

        infRol=new JLabel();
        infRol.setBounds(120,200,100,30);
        MostrarUsuario.add(infRol);

        //Cargo o ficha
        txt=new JLabel();
        txt.setBounds(240,200,150,30);
        MostrarUsuario.add(txt);

        info=new JLabel();
        info.setBounds(300,200,150,30);
        MostrarUsuario.add(info);
        btnReturn.addActionListener(event -> {
           MostrarUsuario.dispose();
        });
        btnBuscar.addActionListener(event ->{
            Integer numero=convertirNumero(numeroDigitado.getText());
            numeroDigitado.setText("");
            Admin admin=new Admin();
            List<Object[]> lista=admin.mostrarUsuario(numero);
            for(Object[] arreglo:lista){
                String tipo=(String)arreglo[0];
                Integer intNumero=(Integer) arreglo[1];
                String nombres=(String) arreglo[2];
                String apellidos=(String) arreglo[3];
                String rol=(String)arreglo[4];
                if(arreglo[4].equals("aprendiz")){
                    Integer ficha=(Integer)arreglo[5];
                    txt.setText("Ficha: ");
                    info.setText(ficha.toString());
                }else if(arreglo[4].equals("funcionario")){
                    String cargo =(String) arreglo[5];
                    txt.setText("Cargo: ");
                    info.setText(cargo);
                }
                infTipoId.setText(tipo);
                infNumeroId.setText(intNumero.toString());
                infNombre.setText(nombres+" "+apellidos);
                infRol.setText(rol);

            }
        });

        MostrarUsuario.setVisible(true);
    }
}
