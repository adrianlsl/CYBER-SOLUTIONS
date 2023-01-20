package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import mantenimientos.GestionVenta;
import model.CabeceraBoleta;
import model.DetalleBoleta;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmBoleta extends JInternalFrame implements InternalFrameListener, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JPanel panel;
	private JLabel label;
	private JTextField txtFechaActual;
	private JLabel lblNmero;
	private JTextField txtNumeroBoleta;
	private JLabel lblAutogenerado;
	private JPanel panel_1;
	private JLabel label_1;
	public static JTextField txtCodCliente;
	public static JTextField txtNomCliente;
	private JButton btnBuscarCliente;
	private JPanel panel_2;
	private JLabel label_2;
	public static JTextField txtCodProducto;
	private JTextField txtCantidad;
	private JButton btnAgregarProducto;
	private JLabel label_3;
	private JButton btnBuscarProducto;
	public static JTextField txtNomProducto;
	public static JTextField txtPrecio;
	public static JTextField txtStock;
	private JLabel label_4;
	private JTable tblBoleta;
	private JScrollPane scrollPane;
	private JButton btnNuevo;
	private JButton btnFinalizarCompra;
	private JLabel label_5;
	private JTextField txtTotal;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel lblItems;
	private JButton btnRegistrar;

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
					FrmBoleta frame = new FrmBoleta();
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
	public FrmBoleta() {
		setIconifiable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmBoleta.class.getResource("/imgs/DetalleVenta.png")));
		setTitle("Boleta");
		addInternalFrameListener(this);
		setBounds(100, 100, 590, 550);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 13, 261, 111);
		getContentPane().add(panel);

		label = new JLabel("Fecha:");
		label.setBounds(12, 68, 48, 14);
		panel.add(label);

		txtFechaActual = new JTextField();
		txtFechaActual.setText("A\u00F1o/Mes/D\u00EDa");
		txtFechaActual.setEditable(false);
		txtFechaActual.setColumns(10);
		txtFechaActual.setBounds(70, 65, 97, 20);
		panel.add(txtFechaActual);

		lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(12, 26, 48, 14);
		panel.add(lblNmero);

		txtNumeroBoleta = new JTextField();
		txtNumeroBoleta.setText("B0000");
		txtNumeroBoleta.setEditable(false);
		txtNumeroBoleta.setColumns(10);
		txtNumeroBoleta.setBounds(70, 23, 162, 20);
		panel.add(txtNumeroBoleta);

		lblAutogenerado = new JLabel("AUTOGENERADO");
		lblAutogenerado.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblAutogenerado.setBounds(70, 10, 97, 16);
		panel.add(lblAutogenerado);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(140, 144, 148)), "Datos del Cliente",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(311, 13, 261, 111);
		getContentPane().add(panel_1);

		label_1 = new JLabel("Cliente:");
		label_1.setBounds(10, 21, 70, 25);
		panel_1.add(label_1);

		txtCodCliente = new JTextField();
		txtCodCliente.setEditable(false);
		txtCodCliente.setColumns(10);
		txtCodCliente.setBounds(67, 23, 97, 20);
		panel_1.add(txtCodCliente);

		txtNomCliente = new JTextField();
		txtNomCliente.setEditable(false);
		txtNomCliente.setColumns(10);
		txtNomCliente.setBounds(10, 54, 230, 20);
		panel_1.add(txtNomCliente);

		btnBuscarCliente = new JButton("");
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setIcon(new ImageIcon(FrmBoleta.class.getResource("/imgs/lupa.png")));
		btnBuscarCliente.setContentAreaFilled(false);
		btnBuscarCliente.setBorderPainted(false);
		btnBuscarCliente.setBorder(null);
		btnBuscarCliente.setBounds(203, 15, 37, 37);
		panel_1.add(btnBuscarCliente);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(164, 80, 86, 20);
		panel_1.add(btnRegistrar);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(140, 144, 148)), "Datos del Producto",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 136, 563, 111);
		getContentPane().add(panel_2);

		label_2 = new JLabel("Producto:");
		label_2.setBounds(12, 34, 70, 25);
		panel_2.add(label_2);

		txtCodProducto = new JTextField();
		txtCodProducto.setEditable(false);
		txtCodProducto.setColumns(10);
		txtCodProducto.setBounds(89, 36, 86, 20);
		panel_2.add(txtCodProducto);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(89, 67, 86, 20);
		panel_2.add(txtCantidad);

		btnAgregarProducto = new JButton("");
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregarProducto.setRolloverIcon(new ImageIcon(FrmBoleta.class.getResource("/imgs/carrito-de-compras.png")));
		btnAgregarProducto.setIcon(new ImageIcon(FrmBoleta.class.getResource("/imgs/carro-inteligente.png")));
		btnAgregarProducto.setContentAreaFilled(false);
		btnAgregarProducto.setBorderPainted(false);
		btnAgregarProducto.setBorder(null);
		btnAgregarProducto.setBounds(207, 62, 37, 37);
		panel_2.add(btnAgregarProducto);

		label_3 = new JLabel("Cantidad:");
		label_3.setBounds(12, 70, 70, 14);
		panel_2.add(label_3);

		btnBuscarProducto = new JButton("");
		btnBuscarProducto.addActionListener(this);
		btnBuscarProducto.setIcon(new ImageIcon(FrmBoleta.class.getResource("/imgs/lupa.png")));
		btnBuscarProducto.setContentAreaFilled(false);
		btnBuscarProducto.setBorderPainted(false);
		btnBuscarProducto.setBorder(null);
		btnBuscarProducto.setBounds(502, 60, 37, 37);
		panel_2.add(btnBuscarProducto);

		txtNomProducto = new JTextField();
		txtNomProducto.setEditable(false);
		txtNomProducto.setColumns(10);
		txtNomProducto.setBounds(207, 36, 143, 20);
		panel_2.add(txtNomProducto);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(375, 36, 70, 20);
		panel_2.add(txtPrecio);

		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setColumns(10);
		txtStock.setBounds(469, 36, 70, 20);
		panel_2.add(txtStock);

		label_4 = new JLabel("Agregar producto ");
		label_4.setBounds(252, 73, 148, 14);
		panel_2.add(label_4);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 259, 562, 200);
		getContentPane().add(scrollPane);

		tblBoleta = new JTable();
		scrollPane.setViewportView(tblBoleta);
		tblBoleta.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		modelo.addColumn("Importe");

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(10, 487, 89, 23);
		getContentPane().add(btnNuevo);

		btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(this);
		btnFinalizarCompra.setBounds(109, 487, 144, 23);
		getContentPane().add(btnFinalizarCompra);

		label_5 = new JLabel("Total");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(422, 491, 46, 14);
		getContentPane().add(label_5);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(478, 490, 86, 20);
		getContentPane().add(txtTotal);

		lblItems = new JLabel("");
		lblItems.setBounds(10, 462, 243, 14);
		getContentPane().add(lblItems);

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
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnFinalizarCompra) {
			actionPerformedBtnFinalizarCompra(arg0);
		}
		if (arg0.getSource() == btnBuscarProducto) {
			actionPerformedBtnBuscarProducto(arg0);
		}
		if (arg0.getSource() == btnBuscarCliente) {
			actionPerformedBtnBuscarCliente(arg0);
		}
		if (arg0.getSource() == btnAgregarProducto) {
			actionPerformedBtnAgregarProducto(arg0);
		}
	}

	protected void actionPerformedBtnBuscarCliente(ActionEvent arg0) {
		DlgCliente dc = new DlgCliente();
		dc.setVisible(true);
	}

	protected void actionPerformedBtnBuscarProducto(ActionEvent arg0) {
		DlgProducto dp = new DlgProducto();
		dp.setVisible(true);
	}

	protected void actionPerformedBtnAgregarProducto(ActionEvent arg0) {
		agregarCompra();
	}
	protected void actionPerformedBtnFinalizarCompra(ActionEvent arg0) {
		finalizarCompra();

	}
	
	private String obtenerCodEmpleado() {
		return FrmLogin.e.getId_empleado();
	}

	private String leerCodCliente() {
		return txtCodCliente.getText();
	}

	private String obtenerNumBoleta() {
		return new GestionVenta().generaNumBoleta();
	}

	private String obtenerFechaActual() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private int leerCantidad() {
		int cant = -1;
		try {
			
		cant = Integer.parseInt(txtCantidad.getText());
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ingrese Cantidad Valida");
		}
		return cant;
	}

	private int leerStock() {
		return Integer.parseInt(txtStock.getText());
	}

	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}

	private String leerNomProd() {
		return txtNomProducto.getText();
	}

	private int leerCodProd() {
		return Integer.parseInt(txtCodProducto.getText());
	}

	double total = 0;
	ArrayList<DetalleBoleta> detalle = new ArrayList<DetalleBoleta>();
	
	void agregarCompra() {
		int codProducto;
		String nomProducto;
		int cantidad, stock;
		double precio, importe;

		try {
			codProducto = leerCodProd();
			nomProducto = leerNomProd();
			cantidad = leerCantidad();
			stock = leerStock();
			precio = leerPrecio();
			stock = stock - cantidad;
			if (cantidad != -1 && cantidad !=0) {
				if (stock <0) {
					JOptionPane.showMessageDialog(this, "No suficiente Stock");
				} else {

					txtStock.setText(stock + "");
					importe = cantidad * precio;
					total += importe;

					DetalleBoleta db = new DetalleBoleta();

					db.setId_productos(codProducto);
					db.setNom_prod(nomProducto);
					db.setCantidad(cantidad);
					db.setPreciovta(precio);
					db.setImporte(importe);
					int decision = 0;
					for (DetalleBoleta d : detalle) {
						if (d.getId_productos() == codProducto) {

							d.setCantidad(d.getCantidad() + cantidad);

							d.setImporte(d.getImporte() + importe);
							decision = 1;
							break;
						}
					}
					if (decision == 0) {
						detalle.add(db);
						
					}
					listar();
					txtTotal.setText(total + "");
					lblItems.setText("Tienes " + detalle.size() + " items");
				}
			}else{
				JOptionPane.showMessageDialog(this,"Cantidad no válida");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Seleccione Producto");
		}

	}

	void listar() {
		modelo.setRowCount(0);
		for (DetalleBoleta d : detalle) {
			Object aDatos[] = { d.getId_productos(), d.getNom_prod(), d.getCantidad(), d.getPreciovta(),
					d.getImporte() };
			modelo.addRow(aDatos);
		}
	}
	
	void finalizarCompra(){
		String numbo1, fecha;
		String codEmp,codClie;
		numbo1 = obtenerNumBoleta();
		fecha = obtenerFechaActual();
		codClie = leerCodCliente();
		codEmp = obtenerCodEmpleado();
		
		CabeceraBoleta c = new CabeceraBoleta();
		c.setNum_bol(numbo1);
		c.setFch_bol(fecha);
		c.setId_cliente(codClie);
		c.setId_empleado(codEmp);
		c.setTotal_bol(total);
		
		int ok = new GestionVenta().realizarVenta(c, detalle);
		
		if(ok == -1){
			JOptionPane.showMessageDialog(this, "Compra Fallida");
		}else{
			JOptionPane.showMessageDialog(this, "Compra Exitosa");	
			limpiartxt();
			limpiartabla();
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		DlgRegistroCliente dr = new DlgRegistroCliente();
		dr.setVisible(true);
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCantidad) {
			keyTypedTxtCantidad(e);
		}
	}
	protected void keyTypedTxtCantidad(KeyEvent e) {
		char c = e.getKeyChar();
		if(Character.isAlphabetic(c) || e.getKeyChar() == 45)
			e.consume();
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		limpiartxt();
		limpiartabla();
		
	}
	void limpiartxt(){
		txtNomCliente.setText("");
		txtCodCliente.setText("");
		txtCodProducto.setText("");
		txtNomProducto.setText("");
		txtCantidad.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		detalle = new ArrayList<DetalleBoleta>();
		lblItems.setText("");
		txtTotal.setText("");
	
	}
	void limpiartabla(){
		modelo.setRowCount(0);
	}
}
