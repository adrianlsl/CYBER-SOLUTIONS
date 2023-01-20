package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionClientes;
import model.Cliente;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmCliente extends JInternalFrame implements InternalFrameListener, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JPanel panel;
	private JLabel lblCdigo;
	private JLabel lblDni;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JTextField txtCodigo;
	private JTextField txtDNI;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JButton btnBuscar;
	private JButton btnActualizar;
	private JButton btnRegistrar;
	private JButton btnLimpiar;
	private JTable tblCliente;
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel lblAutogenerado_1;
	private JLabel lblEstado;
	private JComboBox<String> cboEstado;

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
					FrmCliente frame = new FrmCliente();
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
	public FrmCliente() {
		addInternalFrameListener(this);
		setTitle("Mantenimiento|Cliente");
		setFrameIcon(new ImageIcon(FrmCliente.class.getResource("/imgs/cliente.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 403, 170);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 33, 66, 14);
		panel.add(lblCdigo);

		lblDni = new JLabel("DNI");
		lblDni.setBounds(12, 58, 66, 14);
		panel.add(lblDni);

		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(12, 83, 66, 14);
		panel.add(lblNombres);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(12, 108, 66, 14);
		panel.add(lblApellidos);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(12, 133, 66, 14);
		panel.add(lblTelefono);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setBounds(88, 27, 110, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(88, 52, 110, 20);
		panel.add(txtDNI);
		txtDNI.setColumns(10);
		txtDNI.requestFocus();

		txtNombres = new JTextField();
		txtNombres.setBounds(88, 77, 110, 20);
		panel.add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(88, 102, 110, 20);
		panel.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setBounds(88, 127, 110, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		lblAutogenerado_1 = new JLabel("AUTOGENERADO");
		lblAutogenerado_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblAutogenerado_1.setBounds(88, 12, 110, 16);
		panel.add(lblAutogenerado_1);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(228, 27, 55, 16);
		panel.add(lblEstado);
		
		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccionar Estado", "Activo", "Inactivo"}));
		cboEstado.setBounds(280, 27, 90, 20);
		cboEstado.setSelectedIndex(1);
		panel.add(cboEstado);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(500, 90, 114, 23);
		getContentPane().add(btnBuscar);

		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(500, 22, 114, 23);
		getContentPane().add(btnRegistrar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(500, 56, 114, 23);
		getContentPane().add(btnActualizar);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(500, 124, 114, 23);
		getContentPane().add(btnLimpiar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 604, 246);
		getContentPane().add(scrollPane);

		tblCliente = new JTable();
		scrollPane.setViewportView(tblCliente);

		tblCliente.setModel(modelo);
		modelo.addColumn("CÛdigo");
		modelo.addColumn("DNI");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("TelÈfono");
		modelo.addColumn("Estado");
		listar();
	}

	public void internalFrameActivated(InternalFrameEvent arg0) {
	}

	public void internalFrameClosed(InternalFrameEvent arg0) {
		if (arg0.getSource() == this) {
			internalFrameClosedThis(arg0);
		}
	}

	public void internalFrameClosing(InternalFrameEvent arg0) {
	}

	public void internalFrameDeactivated(InternalFrameEvent arg0) {
	}

	public void internalFrameDeiconified(InternalFrameEvent arg0) {
	}

	public void internalFrameIconified(InternalFrameEvent arg0) {
	}

	public void internalFrameOpened(InternalFrameEvent arg0) {
	}

	protected void internalFrameClosedThis(InternalFrameEvent arg0) {
		estado = "cerrado";
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(arg0);
		}
		if (arg0.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtCodigo) {
			keyTypedTxtCodigo(arg0);
		}
		if (arg0.getSource() == txtTelefono) {
			keyTypedTxtTelefono(arg0);
		}
	}
	protected void keyTypedTxtTelefono(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(Character.isAlphabetic(c))
			arg0.consume();
	}
	protected void actionPerformedBtnLimpiar(ActionEvent arg0) {
		limpiarGUI();
	}
	
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		registrarCliente();
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		actualizarCliente();
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		buscarCliente();
	}
	
	void registrarCliente(){
		String numCliente;
		String dni, nombre, apellido, telefono;
		int descripcion;
		
		numCliente = obtenerNumCliente();
		dni = leerDNI();
		nombre = leerNombre();
		apellido = leerApellido();
		telefono = leerTelefono();
		descripcion = leerEstado();
		
		Cliente c = new Cliente();
		c.setId_cliente(numCliente);
		c.setDni_cliente(dni);
		c.setNom_cliente(nombre);
		c.setApe_cliente(apellido);
		c.setTelef_cliente(telefono);
		c.setIdestado(descripcion);
		GestionClientes gc = new GestionClientes();
		if (dni != null && nombre != null && apellido != null) {
			int ok = gc.registrar(c);
			if (ok == 0)
				JOptionPane.showMessageDialog(this, "Error en el registro", "ERROR", 0);
			else
				JOptionPane.showMessageDialog(this, "Registro Exitoso");
			listar();
		}else{
			JOptionPane.showMessageDialog(this, "Datos incorrectos");
		}
	}
	void actualizarCliente(){
		String codigo;
		String nombre,telefono;
		int estado;
		codigo = leerCodigo();
		nombre = leerNombre();
		telefono = leerTelefono();
		estado = leerEstado();
		Cliente c = new Cliente();
		c.setId_cliente(codigo);
		c.setNom_cliente(nombre);
		c.setTelef_cliente(telefono);
		c.setIdestado(estado);
		GestionClientes gc = new GestionClientes();
		if(codigo != null && estado !=0){
			int ok = gc.actualizar(c);
			if(ok !=0){
				JOptionPane.showMessageDialog(this, "Cliente "+c.getNom_cliente()+" Actualizado");
				listar();
			}else{
				JOptionPane.showMessageDialog(this, "Error en Actualizar");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Corregir Datos");
		}
		
	}
	void buscarCliente(){
		String codigo;
		codigo = leerCodigo();
		Cliente c = new Cliente();
		GestionClientes gc = new GestionClientes();
		c = gc.buscar(codigo);
		if(c != null){
			txtDNI.setText(c.getDni_cliente());
			txtNombres.setText(c.getNom_cliente());
			txtApellidos.setText(c.getApe_cliente());
			txtTelefono.setText(c.getTelef_cliente());
			cboEstado.setSelectedIndex(c.getIdestado());
		}else{
			JOptionPane.showMessageDialog(this, "Error al Buscar");
		}
	}
	
	void limpiarGUI(){
		txtDNI.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDNI.requestFocus();
	}
	
	void listar(){
		GestionClientes gc = new GestionClientes();
		ArrayList<Cliente> lista = gc.listado();
		modelo.setRowCount(0);
		for (Cliente c: lista) {
			Object[] fila = {
					c.getId_cliente(),
					c.getDni_cliente(),
					c.getNom_cliente(),
					c.getApe_cliente(),
					c.getTelef_cliente(),
					c.getDescripcion()
			};
			modelo.addRow(fila);
		}
	}
	
	private String leerCodigo(){
		String codigo = null;
		if(txtCodigo.getText().trim().matches("[CL0-9]{5}")){
			codigo = txtCodigo.getText();
		}else{
			JOptionPane.showMessageDialog(this, "Codigo Incorrecto: \n" + "Ej: CL000");
		}
			
		return codigo;
	}
	private String obtenerNumCliente(){
		return new GestionClientes().generaNumCliente();
	}
	
	private String leerDNI() {
		String dni = null;
		if (txtDNI.getText().trim().matches("[0-9]{8}")) {
			dni = txtDNI.getText().trim();
		}else{
			JOptionPane.showMessageDialog(this, "DNI Incorrecto: \n" + "Debe ser DNI de 8 Digitos");
		}
		return dni;
	}

	private String leerNombre() {
		String nombre = null;
		if (txtNombres.getText().trim().matches("([a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄][\\s]{0,1}){2,40}")) {
			nombre = txtNombres.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Nombre Incorrecto");
		}
		return nombre;
	}

	private String leerApellido() {
		String apellido = null;
		if (txtApellidos.getText().trim().matches("([a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄][\\s]{0,1}){2,40}")) {
			apellido = txtApellidos.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Apellido Incorrecto");
		}
		return apellido;
	}

	private String leerTelefono() {
		String telefono = null;
		if (txtTelefono.getText().trim().matches("[0-9]{9}")) {
			telefono = txtTelefono.getText().trim();
		} else if (txtTelefono.getText().isEmpty()) {
			return telefono;
		} else {
			JOptionPane.showMessageDialog(this, "TelÈfono Incorrecto: \n" + "Se registrara sin Telefono");
		}
		return telefono;
	}
	private int leerEstado(){
		int estado = 0;
		if(cboEstado.getSelectedIndex() ==0){
			JOptionPane.showMessageDialog(this, "Selecciona Estado");
		}else{
			estado = cboEstado.getSelectedIndex();
		}
		return estado;
	}
		
	
	protected void keyTypedTxtCodigo(KeyEvent arg0) {
		Character c= arg0.getKeyChar();
		if(Character.isLetter(c)){
			arg0.setKeyChar(Character.toUpperCase(c));
		}
	}
}
