package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mantenimientos.GestionClientes;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRegistroCliente extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			DlgRegistroCliente dialog = new DlgRegistroCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRegistroCliente() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Registro Cliente");
		setBounds(100, 100, 320, 210);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 140, 288, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 288, 128);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 26, 46, 14);
		panel.add(lblDni);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 54, 46, 14);
		panel.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 79, 46, 14);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(66, 76, 175, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(66, 51, 175, 20);
		panel.add(txtNombre);
		
		txtDni = new JTextField();
		txtDni.setBounds(66, 23, 100, 20);
		panel.add(txtDni);
		txtDni.setColumns(10);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cancelButton) {
			actionPerformedCancelButton(arg0);
		}
		if (arg0.getSource() == okButton) {
			actionPerformedOkButton(arg0);
		}
	}
	protected void actionPerformedOkButton(ActionEvent arg0) {
		registrarCliente();
	}
	void registrarCliente(){
		String codigo,dni, nombre, apellido;
		codigo = obtenerNumCliente();
		dni = leerDNI();
		nombre = leerNombre();
		apellido = leerApellido();
		Cliente c = new Cliente();
		c.setId_cliente(codigo);
		c.setDni_cliente(dni);
		c.setNom_cliente(nombre);
		c.setApe_cliente(apellido);
		GestionClientes gc = new GestionClientes();
		if (nombre != null && apellido != null) {
			int ok = gc.registrar(c);
			if (ok == 0){
				JOptionPane.showMessageDialog(this, "Error en el registro", "ERROR", 0);
			}else{
				JOptionPane.showMessageDialog(this, "Registro Exitoso");
				dispose();
			}
		}else{
			JOptionPane.showMessageDialog(this, "Datos erroneos");
		}
	}
	
	private String obtenerNumCliente(){
		return new GestionClientes().generaNumCliente();
	}
	
	private String leerDNI() {
		String dni = null;
		if (txtDni.getText().trim().matches("[0-9]{8}")) {
			dni = txtDni.getText().trim();
		}else if(txtDni.getText().isEmpty()){
			return dni;
		}else{
			JOptionPane.showMessageDialog(this, "DNI Incorrecto: \n" + "Debe ser DNI de 8 Digitos");
		}
		return dni;
	}

	private String leerNombre() {
		String nombre = null;
		if (txtNombre.getText().trim().matches("([a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄][\\s]{0,1}){2,40}")) {
			nombre = txtNombre.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Nombre Incorrecto");
		}
		return nombre;
	}

	private String leerApellido() {
		String apellido = null;
		if (txtApellido.getText().trim().matches("([a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄][\\s]{0,1}){2,40}")) {
			apellido = txtApellido.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Apellido Incorrecto");
		}
		return apellido;
	}
	protected void actionPerformedCancelButton(ActionEvent arg0) {
		dispose();
	}
}
