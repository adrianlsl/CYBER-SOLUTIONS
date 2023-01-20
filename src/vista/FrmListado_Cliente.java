package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionClientes;
import model.Cliente;
import model.Estado;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrmListado_Cliente extends JInternalFrame implements InternalFrameListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JTable tbListaCliente;
	private JScrollPane scrollPane;
	private JButton btnListar;
	private JLabel lblTipoxCliente;
	private JComboBox<Object> cboEstado;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnPDF;

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
					FrmListado_Cliente frame = new FrmListado_Cliente();
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
	public FrmListado_Cliente() {
		setTitle("Reporte | Listado Cliente");
		addInternalFrameListener(this);
		setFrameIcon(new ImageIcon(FrmListado_Cliente.class.getResource("/imgs/ListCli.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 570, 300);
		getContentPane().setLayout(null);

		lblTipoxCliente = new JLabel("Tipo de cliente");
		lblTipoxCliente.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTipoxCliente.setBounds(10, 24, 163, 20);
		getContentPane().add(lblTipoxCliente);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 542, 185);
		getContentPane().add(scrollPane);

		tbListaCliente = new JTable();
		tbListaCliente.setModel(modelo);
		scrollPane.setViewportView(tbListaCliente);
		modelo.addColumn("Codigo");
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Estado");
		modelo.addColumn("Cantidad Compras");

		cboEstado = new JComboBox<Object>();
		cboEstado.setBounds(110, 24, 135, 20);
		getContentPane().add(cboEstado);

		btnListar = new JButton("LISTADO");
		btnListar.addActionListener(this);
		btnListar.setBounds(451, 18, 101, 20);
		getContentPane().add(btnListar);

		btnPDF = new JButton("PDF");
		btnPDF.addActionListener(this);
		btnPDF.setBounds(451, 44, 101, 20);
		btnPDF.setEnabled(false);
		getContentPane().add(btnPDF);
		ajustarAnchoColumnas();
		llenacombo();

	}

	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbListaCliente.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(3));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(7));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(3));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(2));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(5));
	}

	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 10;
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
		if (arg0.getSource() == btnPDF) {
			actionPerformedBtnPDF(arg0);
		}
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
	}

	protected void actionPerformedBtnListar(ActionEvent arg0) {
		listado();

	}

	void llenacombo() {
		GestionClientes gc = new GestionClientes();
		ArrayList<Estado> lista = gc.listadoEstado();
		cboEstado.addItem("Seleccione Estado");
		for (Estado t : lista) {
			cboEstado.addItem(t.getId_estado() + "-" + t.getDes_estado());
		}
	}

	private int leerEstado() {
		int estado = 0;
		if (cboEstado.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Selecciona un Estado");
			btnPDF.setEnabled(false);
		} else {
			estado = cboEstado.getSelectedIndex();
			btnPDF.setEnabled(true);
		}
		return estado;
	}

	void listado() {
		int estado;
		estado = leerEstado();
		GestionClientes gu = new GestionClientes();
		ArrayList<Cliente> lista = gu.listadpxEstadoCant(estado);
		if (lista == null) {
			JOptionPane.showMessageDialog(null, "Lista Vacía");
		} else {

			modelo.setRowCount(0);
			for (Cliente c : lista) {
				Object aDatos[] = { c.getId_cliente(), c.getDni_cliente(), c.getNomCompletoCli(), c.getTelef_cliente(),
						c.getDescripcion(), c.getCantidad() };
				modelo.addRow(aDatos);

			}
		}
	}

	protected void actionPerformedBtnPDF(ActionEvent arg0) {
		imprime_pdf();
		btnPDF.setEnabled(false);
	}

	private void imprime_pdf() {
		String nombre_archivo = "Listado_Cliente.pdf";
		try {
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nombre_archivo);
			@SuppressWarnings("unused")
			PdfWriter pdf = PdfWriter.getInstance(doc, fos);
			doc.open();
			Image img = Image.getInstance("src/imgs/logo.png");
			img.scaleToFit(120, 50);
			img.setAlignment(Chunk.ALIGN_RIGHT);
			doc.add(img);
			Paragraph p1 = new Paragraph("Reporte de Clientes por Estado",
					FontFactory.getFont("helvetica", 36, Font.ITALIC));
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			p1 = new Paragraph("_________________________________");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph(" "));
			p1 = new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			p1.setAlignment(Chunk.ALIGN_RIGHT);
			doc.add(p1);
			doc.add(new Paragraph(" "));
			p1 = new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph("  "));
			PdfPTable tabla = new PdfPTable(6);
			ArrayList<Cliente> lista = new GestionClientes().listadpxEstadoCant(leerEstado());
			Paragraph Codigo = new Paragraph("CODIGO", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(Codigo);
			Paragraph dni = new Paragraph("DNI", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(dni);
			Paragraph cliente = new Paragraph("CLIENTE", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(cliente);
			Paragraph telef = new Paragraph("TELEFONO", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(telef);
			Paragraph esta = new Paragraph("ESTADO", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(esta);
			Paragraph can = new Paragraph("COMPRAS", FontFactory.getFont("helvetica", 14, Font.BOLD));
			tabla.addCell(can);
			for (Cliente c : lista) {
				tabla.addCell(c.getId_cliente() + "");
				tabla.addCell(c.getDni_cliente());
				tabla.addCell(c.getNomCompletoCli());
				tabla.addCell(c.getTelef_cliente());
				tabla.addCell(c.getDescripcion());
				tabla.addCell(c.getCantidad() + "");

			}
			tabla.setWidthPercentage(100);
			doc.add(tabla);
			doc.close();
			Desktop.getDesktop().open(new File(nombre_archivo));
		} catch (Exception e) {
			System.out.println("Error al crear archivo: " + e.getMessage());
		}
	}
}
