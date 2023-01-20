package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class FrmPrincipal extends JFrame implements ActionListener, Runnable {

	/**
	 * 
	 */
	//  Variables globales
	int posX = 0, posY = 0,
	    unoX = 1, unoY = 1;
	Thread hilo;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane Escritorio;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmCliente;
	private JMenuItem mntmEmpleado;
	private JMenuItem mntmCategoria;
	private JMenuItem mntmProducto;
	private JMenu mnRegistro;
	private JMenuItem mntmVenta;
	private JMenu mnReporte;
	private JMenuItem mntmListadoCliente;
	private JMenuItem mntmListadoEmpleado;
	private JLabel lblFondo;
	private Toolkit t = Toolkit.getDefaultToolkit();
	private Dimension d = t.getScreenSize();
	private JLabel lblGato;
	private JMenuItem mntmListadoVentas;

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
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/imgs/CS_Escritorio.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnSistema = new JMenu("Sistema");
		mnSistema.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/proceso1.png")));
		menuBar.add(mnSistema);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/Salir1.png")));
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_MASK));
		mntmSalir.addActionListener(this);
		mnSistema.add(mntmSalir);

		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/caja-de-herramientas.png")));
		menuBar.add(mnMantenimiento);

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/cliente.png")));
		mntmCliente.addActionListener(this);
		mnMantenimiento.add(mntmCliente);

		mntmEmpleado = new JMenuItem("Empleado");
		mntmEmpleado.addActionListener(this);
		mntmEmpleado.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/Empleado.png")));
		mnMantenimiento.add(mntmEmpleado);

		mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(this);
		mntmCategoria.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/categoria.png")));
		mnMantenimiento.add(mntmCategoria);

		mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(this);
		mntmProducto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/Productos.png")));
		mnMantenimiento.add(mntmProducto);

		mnRegistro = new JMenu("Registro");
		mnRegistro.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/anadir (1).png")));
		menuBar.add(mnRegistro);

		mntmVenta = new JMenuItem("Venta");
		mntmVenta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/Venta.png")));
		mntmVenta.addActionListener(this);
		mnRegistro.add(mntmVenta);

		mnReporte = new JMenu("Reporte");
		mnReporte.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/marketing (1).png")));
		menuBar.add(mnReporte);

		mntmListadoCliente = new JMenuItem("Listado Cliente");
		mntmListadoCliente.addActionListener(this);
		mntmListadoCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/ListCli.png")));
		mnReporte.add(mntmListadoCliente);

		mntmListadoEmpleado = new JMenuItem("Listado Empleado");
		mntmListadoEmpleado.addActionListener(this);
		mntmListadoEmpleado.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/ListEmp.png")));
		mnReporte.add(mntmListadoEmpleado);
		
		mntmListadoVentas = new JMenuItem("Listado Ventas");
		mntmListadoVentas.addActionListener(this);
		mntmListadoVentas.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/reporte.png")));
		mnReporte.add(mntmListadoVentas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Escritorio = new JDesktopPane();
		contentPane.add(Escritorio, BorderLayout.CENTER);

		lblFondo = new JLabel("");
		lblFondo.setSize((int) d.getWidth(), (int) d.getHeight() - 155);
		ImageIcon fondo = new ImageIcon(FrmPrincipal.class.getResource("/imgs/Escritorio.png"));
		ImageIcon fondo_redimensionado = new ImageIcon(fondo.getImage().getScaledInstance((int) d.getWidth(),
				(int) d.getHeight(), java.awt.Image.SCALE_SMOOTH));
		
		lblGato = new JLabel("");
		lblGato.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/imgs/Pana.png")));
		lblGato.setBounds(0, 0, 142, 187);
		Escritorio.add(lblGato);
		lblFondo.setIcon(fondo_redimensionado);
		Escritorio.add(lblFondo);
		activar();
		hilo = new Thread(this);
 		hilo.start();
	}

	void activar() {	
		switch (FrmLogin.e.getId_tipo()) {
		case 2:
			mnMantenimiento.setEnabled(false);
			mnReporte.setEnabled(false);
			break;
		default:
			mnRegistro.setEnabled(false);
			
			break;
		}
	}
	
//  Controla el tiempo
	public void run() {
	 	while (hilo != null)
	 		try {
	 			Thread.sleep(15);
	 			procesar();	 			
	 		}
	 		catch (Exception e) {
	 		}
	}
	//  Métodos tipo void (sin parámetros)	
	void procesar() {
	 	posX += unoX;
	 	posY += unoY;
	 	if (posX + lblGato.getWidth() > getWidth() - 15)
	 		unoX = -1;
	 	if (posX < 0)
	 		unoX = 1;
	 	if (posY + lblGato.getHeight() > getHeight() - 40)
	 		unoY = -1;
	 	if (posY < 0)
	 		unoY = 1;
	 	lblGato.setLocation(posX, posY);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmListadoVentas) {
			actionPerformedMntmListadoVentas(e);
		}
		if (e.getSource() == mntmListadoEmpleado) {
			actionPerformedMntmListadoEmpleado(e);
		}
		if (e.getSource() == mntmListadoCliente) {
			actionPerformedMntmListadoCliente(e);
		}
		if (e.getSource() == mntmProducto) {
			actionPerformedMntmProducto(e);
		}
		if (e.getSource() == mntmVenta) {
			actionPerformedMntmVenta(e);
		}
		if (e.getSource() == mntmCategoria) {
			actionPerformedMntmCategoria(e);
		}
		if (e.getSource() == mntmEmpleado) {
			actionPerformedMntmEmpleado(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
		if (e.getSource() == mntmCliente) {
			actionPerformedMntmCliente(e);
		}
	}

	protected void actionPerformedMntmCliente(ActionEvent e) {

		if (FrmCliente.estado == "cerrado") {
			FrmCliente fc = new FrmCliente();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmCliente.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Mantenimiento|Cliente ya esta abierto", "ADVERTENCIA", 1);
		}

	}

	protected void actionPerformedMntmSalir(ActionEvent e) {
		System.exit(0);
	}
	protected void actionPerformedMntmEmpleado(ActionEvent e) {
		if (FrmEmpleado.estado == "cerrado") {
			FrmEmpleado fc = new FrmEmpleado();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmEmpleado.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Mantenimiento|Empleado ya esta abierto", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmCategoria(ActionEvent e) {
		if (FrmCategoria.estado == "cerrado") {
			FrmCategoria fc = new FrmCategoria();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmCategoria.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Mantenimiento|Categoria ya esta abierto", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmVenta(ActionEvent e) {
		if (FrmBoleta.estado == "cerrado") {
			FrmBoleta fc = new FrmBoleta();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmBoleta.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Registro|Venta ya esta abierta", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmProducto(ActionEvent e) {
		if (FrmProducto.estado == "cerrado") {
			FrmProducto fc = new FrmProducto();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmProducto.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Mantenimiento|Producto ya esta abierta", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmListadoCliente(ActionEvent e) {
		if (FrmListado_Cliente.estado == "cerrado") {
			FrmListado_Cliente fc = new FrmListado_Cliente();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmListado_Cliente.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Reporte|Listado Cliente ya esta abierta", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmListadoEmpleado(ActionEvent e) {
		if (FrmListado_Empleado.estado == "cerrado") {
			FrmListado_Empleado fc = new FrmListado_Empleado();
			Escritorio.add(fc);

			int x = (Escritorio.getWidth() / 2) - (fc.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fc.getHeight() / 2);
			fc.setLocation(x, y);
			fc.setVisible(true);
			FrmListado_Empleado.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Reporte|Listado Empleado ya esta abierta", "ADVERTENCIA", 1);
		}
	}
	protected void actionPerformedMntmListadoVentas(ActionEvent e) {
		if (FrmListado_Ventas.estado == "cerrado") {
			FrmListado_Ventas fv = new FrmListado_Ventas();
			Escritorio.add(fv);

			int x = (Escritorio.getWidth() / 2) - (fv.getWidth() / 2);
			int y = (Escritorio.getHeight() / 2) - (fv.getHeight() / 2);
			fv.setLocation(x, y);
			fv.setVisible(true);
			FrmListado_Ventas.estado = "abierto";
		} else {
			JOptionPane.showMessageDialog(this, "Reporte|Listado Empleado ya esta abierta", "ADVERTENCIA", 1);
		}
	}
}
