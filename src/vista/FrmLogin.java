package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloCerrar;
import mantenimientos.GestionEmpleados;
import model.Empleado;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Font;
import utils.MySQLConexion;

public class FrmLogin extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFondo;
	private JPanel panel;
	private JLabel lblLogo;
	private JButton btnIngresar;
	private JButton btnSalir;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	public static JLabel lblMensaje;
	private JLabel lblAdvertencia;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQLConexion.getConexion();
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/imgs/Icono.png")));
		setTitle("Inicio de Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(110, 26, 231, 360);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/logo.png")));
		lblLogo.setBounds(0, 0, 231, 73);
		panel.add(lblLogo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/Ingresar.png")));
		btnIngresar.setForeground(Color.BLACK);
		btnIngresar.setEnabled(false);
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(10, 315, 107, 34);
		btnIngresar.setBackground(new Color(0, 153, 51));
		panel.add(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/Salir.png")));
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setBackground(Color.RED);
		btnSalir.addActionListener(this);
		btnSalir.setBounds(125, 315, 96, 34);
		panel.add(btnSalir);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(this);
		txtUsuario.setBounds(73, 147, 120, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.addKeyListener(this);
		txtClave.setBounds(73, 206, 120, 20);
		panel.add(txtClave);
		
		lblUsuario = new JLabel("");
		lblUsuario.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/Usuario.png")));
		lblUsuario.setBounds(30, 140, 25, 33);
		panel.add(lblUsuario);
		
		lblContraseña = new JLabel("");
		lblContraseña.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/Contrase\u00F1a.png")));
		lblContraseña.setBounds(23, 194, 40, 40);
		panel.add(lblContraseña);
		
		lblAdvertencia = new JLabel("");
		lblAdvertencia.setForeground(Color.RED);
		lblAdvertencia.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAdvertencia.setBounds(12, 252, 211, 16);
		panel.add(lblAdvertencia);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(22, 280, 180, 14);
		panel.add(lblMensaje);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(FrmLogin.class.getResource("/imgs/Fondo.png")));
		lblFondo.setBounds(0, 0, 434, 431);
		contentPane.add(lblFondo);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		iniciaTiempo();
		
	}
	void iniciaTiempo(){
		
		HiloCerrar hilo = new HiloCerrar(this);
		hilo.start();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
		if (arg0.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(arg0);
		}
	}
	public static Empleado e = new Empleado();
	
	void ingresarPrincipal(){
		String usuario, clave;
		usuario = leerUsuario();
		clave = leerClave();
		
		//Proceso
		GestionEmpleados ge = new GestionEmpleados();
		e = ge.validaAcceso(usuario, clave);
		//Salida
		
		if(e == null){
			lblAdvertencia.setText("¡Usuario y/o Contraseña incorrecta!");
			txtUsuario.setText("");
			txtClave.setText("");
			txtUsuario.requestFocus();
		}else{
				FrmPrincipal fp = new FrmPrincipal();
				fp.setVisible(true);
				fp.setTitle("CyberSolutions | Bienvenido: " + e.getNom_empleado()+ " " + e.getApe_empleado() +
						"     "+"Fecha Actual: "+obtenerFecha());
				dispose();
		}
	}
	
	private String leerUsuario(){
		return txtUsuario.getText();
	}
	@SuppressWarnings("deprecation")
	private String leerClave(){
		return txtClave.getText();
	}
	private String obtenerFecha(){
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
	
	
	protected void actionPerformedBtnIngresar(ActionEvent arg0) {
		ingresarPrincipal();
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		System.exit(0);
		
	}
	
	
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtUsuario) {
			keyPressedTxtUsuario(e);
		}
		if (e.getSource() == txtClave) {
			keyPressedTxtClave(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtClave) {
			keyReleasedTxtClave(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	@SuppressWarnings("deprecation")
	protected void keyReleasedTxtClave(KeyEvent e) {
		if(txtClave.getText().length()>0){
			btnIngresar.setEnabled(true);
		}else{
			btnIngresar.setEnabled(false);
		}
	}
	protected void keyPressedTxtUsuario(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			txtClave.requestFocus();
			txtClave.selectAll();
		}
	}
	
	protected void keyPressedTxtClave(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			ingresarPrincipal();
		}
	}
}
