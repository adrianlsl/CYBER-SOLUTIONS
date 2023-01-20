package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionCategorias;
import mantenimientos.GestionProductos;
import model.Categoria;
import model.Producto;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmProducto extends JInternalFrame implements InternalFrameListener, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JLabel lblCdigo;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblStock;
	private JPanel panel;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JTable tblProductos;
	private JScrollPane scrollPane;
	DefaultTableModel modelo = new DefaultTableModel();
	private JComboBox<Object> cboCategoria;
	private JLabel lblCategor;
	private JButton btnEliminar;

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
					FrmProducto frame = new FrmProducto();
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
	public FrmProducto() {
		addInternalFrameListener(this);
		setTitle("Mantenimiento | Producto");
		setFrameIcon(new ImageIcon(FrmProducto.class.getResource("/imgs/Productos.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 535, 480);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 370, 160);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblStock = new JLabel("Stock");
		lblStock.setBounds(12, 109, 55, 16);
		panel.add(lblStock);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(12, 81, 55, 16);
		panel.add(lblPrecio);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 53, 55, 16);
		panel.add(lblNombre);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(12, 25, 55, 16);
		panel.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(85, 23, 114, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(85, 51, 114, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(this);
		txtPrecio.setBounds(85, 79, 114, 20);
		panel.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtStock = new JTextField();
		txtStock.addKeyListener(this);
		txtStock.setBounds(85, 107, 114, 20);
		panel.add(txtStock);
		txtStock.setColumns(10);

		cboCategoria = new JComboBox<Object>();
		cboCategoria.setBounds(277, 24, 81, 18);
		panel.add(cboCategoria);

		lblCategor = new JLabel("Categor\u00EDa");
		lblCategor.setBounds(204, 25, 69, 16);
		panel.add(lblCategor);

		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(404, 22, 114, 23);
		getContentPane().add(btnRegistrar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(404, 56, 114, 20);
		getContentPane().add(btnActualizar);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(404, 87, 114, 20);
		getContentPane().add(btnBuscar);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(this);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(404, 118, 114, 20);
		getContentPane().add(btnEliminar);
		btnLimpiar.setBounds(404, 149, 114, 20);
		getContentPane().add(btnLimpiar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 501, 251);
		getContentPane().add(scrollPane);

		tblProductos = new JTable();
		tblProductos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProductos);
		tblProductos.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Stock");
		modelo.addColumn("Categoria");
		llenaCombo();
		listar();
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	public void internalFrameClosed(InternalFrameEvent e) {
		if (e.getSource() == this) {
			internalFrameClosedThis(e);
		}
	}

	public void internalFrameClosing(InternalFrameEvent e) {
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	public void internalFrameIconified(InternalFrameEvent e) {
	}

	public void internalFrameOpened(InternalFrameEvent e) {
	}

	protected void internalFrameClosedThis(InternalFrameEvent e) {
		estado = "cerrado";
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
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
	}

	protected void btnBuscarActionPerformed(ActionEvent arg0) {
		buscar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		eliminar();
	}
	
	protected void btnLimpiarActionPerformed(ActionEvent arg0) {
		limpiar();
	}

	void listar() {
		GestionProductos gp = new GestionProductos();
		ArrayList<Producto> lista = gp.listado();
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object[] fila = { p.getId_productos(), p.getNom_prod(), p.getPrec_prod(), p.getStock_prod(),
					p.getNom_cat() };
			modelo.addRow(fila);
		}
	}

	void registrar() {
		String nombre;
		double precio;
		int stock, cate;
		nombre = leerNombre();
		precio = leerPrecio();
		stock = leerStock();
		cate = leerCategoria();
		Producto p = new Producto();
		p.setNom_prod(nombre);
		p.setPrec_prod(precio);
		p.setStock_prod(stock);
		p.setId_categoria(cate);
		GestionProductos gp = new GestionProductos();
		if (nombre != null && precio != 0 && stock != 0 && cate != 0) {
			int ok = gp.registrar(p);
			if (ok == 0){
				JOptionPane.showMessageDialog(this, "Error en el registro");
			}	
			else{
				JOptionPane.showMessageDialog(this, "Registro Exitoso");
				listar();
			}
				
		}else{
			JOptionPane.showMessageDialog(this, "Corregir Datos");
		}
	}

	void actualizar() {
		String nombre;
		double precio;
		int codigo, stock;
		nombre = leerNombre();
		precio = leerPrecio();
		stock = leerStock();
		codigo = leerCodigo();
		Producto c = new Producto();
		c.setNom_prod(nombre);
		c.setPrec_prod(precio);
		c.setStock_prod(stock);
		c.setId_productos(codigo);
		GestionProductos gp = new GestionProductos();
		if(codigo!= 0 &&nombre !=null && stock != 0 && precio !=0){
			int ok = gp.actualizar(c);
			if (ok != 0) {
				JOptionPane.showMessageDialog(this, "Producto " + c.getNom_prod() + " Actualizado");
				listar();
			} else {
				JOptionPane.showMessageDialog(this, "Error al actualizar");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Corregir Datos");
		}
		
		
	}

	void eliminar() {
		int codigo;
		codigo = leerCodigo();
		GestionProductos gp = new GestionProductos();
		int ok = gp.eliminar(codigo);
		if (ok != 0)
			JOptionPane.showMessageDialog(this, "Producto eliminado");
		else
			JOptionPane.showMessageDialog(this, "Error al eliminar");
		listar();
	}

	void buscar() {
		int codigo;
		codigo = leerCodigo();
		Producto p = new Producto();
		GestionProductos gp = new GestionProductos();
		p = gp.buscar(codigo);
		if (p == null) {
			JOptionPane.showMessageDialog(this, "Error al buscar");
		} else {
			txtCodigo.setText(p.getId_productos() + "");
			txtNombre.setText(p.getNom_prod());
			txtPrecio.setText(p.getPrec_prod() + "");
			txtStock.setText(p.getStock_prod() + "");
			cboCategoria.setSelectedIndex(p.getId_categoria());
		}
	}

	void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		cboCategoria.setSelectedIndex(0);
		txtNombre.requestFocus();
	}
	void llenaCombo(){
		GestionCategorias gc = new GestionCategorias();
		ArrayList<Categoria> listado = gc.listado();
		cboCategoria.addItem("Seleccione Categoria");
		for (Categoria c : listado) {
			cboCategoria.addItem(c.getId_categoria()+ "-" + c.getNom_cat());
		}
	}
	private double leerPrecio() {
		double pre = 0;
		try {
			pre = Double.parseDouble(txtPrecio.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Precio Incorrecto");
		}
		return pre;
	}

	private int leerCodigo() {
		int  codigo = 0;
		try {
			codigo = Integer.parseInt(txtCodigo.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Codigo Incorrecto");
		}
		return codigo;
	}

	private int leerStock() {
		int stock = 0;
		try {
			stock = Integer.parseInt(txtStock.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al ingresar stock");
		}
		return stock;
	}

	private String leerNombre() {
		String nombre = null;
		if (txtNombre.getText().trim().matches("([0-9a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄][\\s]{0,1}){2,40}")) {
			nombre = txtNombre.getText().trim();
		} else {
			JOptionPane.showMessageDialog(this, "Ingrese Nombre Correcto");
		}
		return nombre;
	}

	private int leerCategoria() {
		int cate = 0;
		if (cboCategoria.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione una categoria");
		} else {
			cate = cboCategoria.getSelectedIndex();
		}
		return cate;
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtStock) {
			keyTypedTxtStock(arg0);
		}
		if (arg0.getSource() == txtPrecio) {
			keyTypedTxtPrecio(arg0);
		}
	}
	protected void keyTypedTxtPrecio(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if(arg0.getKeyChar() == 45 || Character.isAlphabetic(c)){
			arg0.consume();
		}
	}
	protected void keyTypedTxtStock(KeyEvent arg0) {
		char t = arg0.getKeyChar();
		if(arg0.getKeyChar() == 45 || Character.isAlphabetic(t)){
			arg0.consume();
		}
	}
	
}
