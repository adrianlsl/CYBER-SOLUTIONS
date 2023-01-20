package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

import mantenimientos.GestionEmpleados;
import model.Empleado;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmEmpleado extends JInternalFrame implements InternalFrameListener, ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JPanel panel;
	private JLabel lblCdigo;
	private JLabel lblFechaDeIngreso;
	private JLabel lblDni;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblTelfono;
	private JLabel lblCargo;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField txtCodigo;
	private JTextField txtDNI;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JDateChooser txtFecha;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JTable tblEmpleado;
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JComboBox<String> cboTipoEmpleado;
	private JLabel lblAutogenerado;
	private JButton btnVisualizarCont;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEmpleado frame = new FrmEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmEmpleado() {
		setFrameIcon(new ImageIcon(FrmEmpleado.class.getResource("/imgs/Empleado.png")));
		setTitle("Mantenimiento | Empleado");
		addInternalFrameListener(this);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 801, 480);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Empleado", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.BLACK));
		panel.setBounds(12, 12, 494, 188);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 30, 55, 16);
		panel.add(lblCdigo);

		lblFechaDeIngreso = new JLabel("Fecha de Ingreso");
		lblFechaDeIngreso.setBounds(217, 114, 107, 16);
		panel.add(lblFechaDeIngreso);

		lblDni = new JLabel("DNI");
		lblDni.setBounds(12, 58, 55, 16);
		panel.add(lblDni);

		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(12, 86, 55, 16);
		panel.add(lblNombres);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(12, 114, 55, 16);
		panel.add(lblApellidos);

		lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(12, 142, 55, 16);
		panel.add(lblTelfono);

		lblCargo = new JLabel("Tipo de Empleado");
		lblCargo.setBounds(217, 86, 107, 16);
		panel.add(lblCargo);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(217, 30, 55, 16);
		panel.add(lblUsuario);

		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(217, 58, 73, 16);
		panel.add(lblContrasea);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setBounds(85, 28, 114, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(85, 56, 114, 20);
		panel.add(txtDNI);
		txtDNI.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(85, 84, 114, 20);
		panel.add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(85, 112, 114, 20);
		panel.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(85, 140, 114, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(331, 28, 114, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(331, 56, 114, 20);
		panel.add(txtContraseña);
		txtContraseña.setColumns(10);

		txtFecha = new JDateChooser();
		txtFecha.setBounds(331, 112, 113, 20);
		panel.add(txtFecha);

		cboTipoEmpleado = new JComboBox<String>();
		cboTipoEmpleado
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccionar", "Administrador", "Cajero" }));
		cboTipoEmpleado.setBounds(331, 84, 114, 20);
		panel.add(cboTipoEmpleado);

		lblAutogenerado = new JLabel("AUTOGENERADO");
		lblAutogenerado.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblAutogenerado.setBounds(85, 12, 88, 16);
		panel.add(lblAutogenerado);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(217, 140, 88, 20);
		panel.add(btnBuscar);
		
		btnVisualizarCont = new JButton("");
		btnVisualizarCont.addActionListener(this);
		btnVisualizarCont.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/imgs/ojo.png")));
		btnVisualizarCont.setContentAreaFilled(false);
		btnVisualizarCont.setBorderPainted(false);
		btnVisualizarCont.setBorder(null);
		btnVisualizarCont.setBounds(445, 58, 26, 16);
		panel.add(btnVisualizarCont);
		btnBuscar.addActionListener(this);

		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(670, 23, 114, 23);
		getContentPane().add(btnRegistrar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(670, 57, 114, 20);
		getContentPane().add(btnActualizar);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(670, 88, 114, 20);
		getContentPane().add(btnLimpiar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 212, 770, 229);
		getContentPane().add(scrollPane);

		tblEmpleado = new JTable();
		tblEmpleado.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblEmpleado);
		tblEmpleado.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("DNI");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Fecha de Cont.");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Tipo");
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
		if (arg0.getSource() == btnVisualizarCont) {
			actionPerformedBtnVisualizarCont(arg0);
		}
		if (arg0.getSource() == btnLimpiar) {
			btnLimpiarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			btnBuscarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			btnActualizarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			btnRegistrarActionPerformed(arg0);
		}
	}

	protected void btnRegistrarActionPerformed(ActionEvent arg0) {
		registrar();
	}

	protected void btnActualizarActionPerformed(ActionEvent arg0) {
		actualizar();
		txtFecha.setEnabled(true);
	}

	protected void btnBuscarActionPerformed(ActionEvent arg0) {
		buscar();
		
	}

	protected void btnLimpiarActionPerformed(ActionEvent arg0) {
		limpiar();
	}

	private void listar() {
		GestionEmpleados ge = new GestionEmpleados();
		ArrayList<Empleado> lista = ge.listado();
		modelo.setRowCount(0);
		for (Empleado e : lista) {
			Object[] fila = { e.getId_empleado(), e.getDni_empleado(), e.getNom_empleado(), e.getApe_empleado(),
					e.getFec_empleado(), e.getTelef_empleado(), e.getDesc_tipo() };
			modelo.addRow(fila);
		}
	}

	void registrar() {
		int tipo;
		String codigo,dni, nombre, apellido, telefono, usuario, contraseña, fecha;
		codigo = obtenerNumEmpleado();
		dni = leerDNI();
		nombre = leerNombre();
		apellido = leerApellido();
		fecha = leerFecha();
		usuario = leerUsuario();
		contraseña = leerContraseña();
		telefono = leerTelefono();
		tipo = leerTipo();
		Empleado e = new Empleado();
		e.setId_empleado(codigo);
		e.setDni_empleado(dni);
		e.setNom_empleado(nombre);
		e.setApe_empleado(apellido);
		e.setFec_empleado(fecha);
		e.setUsu_empleado(usuario);
		e.setPass_empleado(contraseña);
		e.setTelef_empleado(telefono);
		e.setId_tipo(tipo);
		GestionEmpleados ge = new GestionEmpleados();
		if (dni != null && nombre != null && apellido != null && usuario != null && contraseña != null
				&& fecha != null) {

			int ok = ge.registrar(e);
			if (ok == 0) {
				JOptionPane.showMessageDialog(this, "Error en el registro");
			} else {
				JOptionPane.showMessageDialog(this, "Empleado registrado");
				listar();
			}

		} else {
			JOptionPane.showMessageDialog(this, "Corregir datos");
		}
	}

	void actualizar() {
		String codigo,usuario, contraseña, telefono, nombre, apellido, dni;
		int  tipo;
		codigo = leerCodigo();
		dni = leerDNI();
		nombre = leerNombre();
		apellido = leerApellido();
		usuario = leerUsuario();
		contraseña = leerContraseña();
		telefono = leerTelefono();
		tipo = leerTipo();

		Empleado e = new Empleado();
		e.setId_empleado(codigo);
		e.setDni_empleado(dni);
		e.setNom_empleado(nombre);
		e.setApe_empleado(apellido);
		e.setUsu_empleado(usuario);
		e.setPass_empleado(contraseña);
		e.setTelef_empleado(telefono);
		e.setId_tipo(tipo);
		GestionEmpleados ge = new GestionEmpleados();
		if (codigo != null && dni != null && nombre != null && apellido != null && usuario != null
				&& contraseña != null) {
			int ok = ge.actualizar(e);
			if (ok != 0) {
				JOptionPane.showMessageDialog(this, "Empleado " + e.getNom_empleado() + " actualizado");
				listar();
			}

			else {
				JOptionPane.showMessageDialog(this, "Error al actualizar");
			}

		} else {
			JOptionPane.showMessageDialog(this, "Corregir Datos");
		}

	}

	void buscar() {
		String codigo;
		codigo = leerCodigo();
		Empleado e = new Empleado();
		GestionEmpleados ge = new GestionEmpleados();
		e = ge.buscar(codigo);
		if (e != null) {
			txtDNI.setText(e.getDni_empleado());
			txtNombres.setText(e.getNom_empleado());
			txtApellidos.setText(e.getApe_empleado());
			txtUsuario.setText(e.getUsu_empleado());
			txtContraseña.setText(e.getPass_empleado());
			txtTelefono.setText(e.getTelef_empleado());
			cboTipoEmpleado.setSelectedIndex(e.getId_tipo());
			txtFecha.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(this, "Error al buscar");
		}
	}

	void limpiar() {
		txtCodigo.setText("");
		txtDNI.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		txtContraseña.setText("");
		cboTipoEmpleado.setSelectedIndex(0);
		txtFecha.setDate(null);
		txtContraseña.setEchoChar('•');
		txtDNI.requestFocus();
	}

	private String leerCodigo() {
		String codigo = null;
		if(txtCodigo.getText().trim().matches("[EM0-9]{5}")){
			codigo = txtCodigo.getText();
		}else{
			JOptionPane.showMessageDialog(this, "Codigo Incorrecto: \n" + "Ej: EM000");
		}
			
		return codigo;
	}
	
	private String obtenerNumEmpleado(){
		return new GestionEmpleados().generaNumEmpleado();
	}

	private String leerDNI() {
		String dni = null;
		if (txtDNI.getText().trim().matches("[0-9]{8}")) {
			dni = txtDNI.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Datos de DNI erróneos");
		}
		return dni;
	}

	private String leerNombre() {
		String nombre = null;
		if (txtNombres.getText().trim().matches("([a-zA-ZñÑáéíóúÁÉÍÓÚ][\\s]{0,1}){2,40}")) {
			nombre = txtNombres.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Datos de Nombre erróneos");
		}
		return nombre;
	}

	private String leerApellido() {
		String apellido = null;
		if (txtApellidos.getText().trim().matches("([a-zA-ZñÑáéíóúÁÉÍÓÚ][\\s]{0,1}){2,40}")) {
			apellido = txtApellidos.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Datos de Apellido erróneos");
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
			JOptionPane.showMessageDialog(this, "Teléfono Incorrecto: \n" + "Se registrara sin Telefono");
		}
		return telefono;
	}

	private String leerUsuario() {
		String usuario = null;
		if (txtUsuario.getText().trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9]{5,10}")) {
			usuario = txtUsuario.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Usuario contiene carácteres no válidos");
		}
		return usuario;
	}

	@SuppressWarnings("deprecation")
	private String leerContraseña() {
		String contraseña = null;
		if (txtContraseña.getText().trim().matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9]{5,10}")) {
			contraseña = txtContraseña.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Contraseña contine carácteres no válidos");
		}
		return contraseña;
	}

	private String leerFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActual = new Date();
		String fechacon = null;
		try {
			if (txtFecha.getDate().before(fechaActual)) {
				fechacon = sdf.format(txtFecha.getDate());
			} else {
				JOptionPane.showMessageDialog(this, "Fecha no valida");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seleccione Fecha");
		}
		return fechacon;
	}

	private int leerTipo() {
		int tipo = 0;
		if (cboTipoEmpleado.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione un tipo de empleado");
		} else {
			tipo = cboTipoEmpleado.getSelectedIndex();
		}
		return tipo;
	}
	protected void actionPerformedBtnVisualizarCont(ActionEvent arg0) {
		txtContraseña.setEchoChar((char)0);
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtCodigo) {
			keyTypedTxtCodigo(arg0);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent arg0) {
		Character c= arg0.getKeyChar();
		if(Character.isLetter(c)){
			arg0.setKeyChar(Character.toUpperCase(c));
		}
	}
}
