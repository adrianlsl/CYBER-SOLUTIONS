package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionCategorias;
import model.Categoria;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmCategoria extends JInternalFrame implements InternalFrameListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JPanel panel;
	private JLabel lblCdigo;
	private JLabel lblNombre;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JTable tblCategoria;
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel label;

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
					FrmCategoria frame = new FrmCategoria();
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
	public FrmCategoria() {
		addInternalFrameListener(this);
		setTitle("Mantenimiento | Categoria");
		setFrameIcon(new ImageIcon(FrmCategoria.class.getResource("/imgs/categoria.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 568, 425);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos de la Categoria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 378, 147);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 33, 55, 16);
		panel.add(lblCdigo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 61, 55, 16);
		panel.add(lblNombre);
		
		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(12, 89, 75, 16);
		panel.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(105, 87, 114, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(105, 59, 114, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(105, 31, 114, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		label = new JLabel("AUTOGENERADO");
		label.setFont(new Font("Dialog", Font.ITALIC, 10));
		label.setBounds(106, 12, 88, 16);
		panel.add(label);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(421, 12, 114, 23);
		getContentPane().add(btnRegistrar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(421, 46, 114, 20);
		getContentPane().add(btnActualizar);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(421, 77, 114, 20);
		getContentPane().add(btnBuscar);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(421, 108, 114, 20);
		getContentPane().add(btnLimpiar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 170, 530, 214);
		getContentPane().add(scrollPane);
		
		tblCategoria = new JTable();
		tblCategoria.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCategoria);
		tblCategoria.setModel(modelo);
		modelo.addColumn("CÛdigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("DescripciÛn");
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
	}
	
	protected void btnBuscarActionPerformed(ActionEvent arg0) {
		buscar();
	}
	protected void btnLimpiarActionPerformed(ActionEvent arg0) {
		limpiar();
	}
	private void listar() {
		GestionCategorias gc = new GestionCategorias();
		ArrayList<Categoria> lista = gc.listado();
		modelo.setRowCount(0);
		for (Categoria c: lista) {
			Object [] fila = {
					c.getId_categoria(),
					c.getNom_cat(),
					c.getDesc_cat()
			};
			modelo.addRow(fila);
		}
		
	}
	void registrar(){
		String nombre, descripcion;
		nombre = leerNombre();
		descripcion = leerDescripcion();
		
		Categoria c = new Categoria(99, nombre, descripcion);
		GestionCategorias gc = new GestionCategorias();
		if (nombre != null && descripcion != null) {
			int ok = gc.registrar(c);
			if (ok == 0){
				JOptionPane.showMessageDialog(this, "Error en Registro");
			}else{
				JOptionPane.showMessageDialog(this, "Registro Exitoso");
				listar();
			}
				
		} else{
			JOptionPane.showMessageDialog(this, "Corregir datos");
		}
		
	}
	void actualizar(){
		String nombre, descripcion;
		int codigo;
		codigo = leerCodigo();
		nombre = leerNombre();
		descripcion = leerDescripcion();
		
		Categoria c = new Categoria();
		c.setNom_cat(nombre);
		c.setDesc_cat(descripcion);
		c.setId_categoria(codigo);
		GestionCategorias gc = new GestionCategorias();
		if(codigo != 0 && nombre != null && descripcion !=null){
		int ok = gc.actualizar(c);
		if(ok !=0){
			JOptionPane.showMessageDialog(this, "Categoria " + c.getNom_cat()+" Actualizada");
			listar();
		}else{
			JOptionPane.showMessageDialog(this, "Error al actualizar");
		}
		}else{
			JOptionPane.showMessageDialog(this, "Corregir Datos");
		}
		
	}
	void buscar(){
		int codigo;
		codigo = leerCodigo();
		Categoria c = new Categoria();	
		GestionCategorias gc = new GestionCategorias();
		c = gc.buscar(codigo);
		if(c == null){
			JOptionPane.showMessageDialog(this, "Error al buscar");
		}else{
			txtNombre.setText(c.getNom_cat());
			txtDescripcion.setText(c.getDesc_cat());
		}
	}
	
	void limpiar(){
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtNombre.requestFocus();
	}
	
	private int leerCodigo() {
		int codigo = 0;
		try {
			codigo =  Integer.parseInt(txtCodigo.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Codigo incorrecto");
		}
		return codigo;
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
	private String leerDescripcion() {
		String descripcion = null;
		if (txtDescripcion.getText().trim().matches("([0-9a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄,][\\s]{0,1}){2,40}")) {
			descripcion = txtDescripcion.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Descripcion Incorrecta");
		}
		return descripcion;
	}
}
