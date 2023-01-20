package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionEmpleados;
import model.Empleado;

import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmListado_Empleado extends JInternalFrame implements InternalFrameListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JLabel lblListadoEmpleados;
	private JLabel lblTipoEmpleado;
	private JComboBox<String> cboTipo;
	private JTable tblTipoEmpleado;
	private JScrollPane scrollPane;
	private JButton btnListar;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnPdf;
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
					FrmListado_Empleado frame = new FrmListado_Empleado();
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
	public FrmListado_Empleado() {
		addInternalFrameListener(this);
		setFrameIcon(new ImageIcon(FrmListado_Empleado.class.getResource("/imgs/ListEmp.png")));
		setTitle("Reporte | Listado Empleado");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);
		
		lblListadoEmpleados = new JLabel("Listado Empleados");
		lblListadoEmpleados.setBounds(12, 12, 125, 16);
		getContentPane().add(lblListadoEmpleados);
		
		lblTipoEmpleado = new JLabel("Tipo Empleado");
		lblTipoEmpleado.setBounds(22, 40, 84, 16);
		getContentPane().add(lblTipoEmpleado);
		
		cboTipo = new JComboBox<String>();
		cboTipo.addActionListener(this);
		cboTipo.setBounds(124, 40, 101, 20);
		getContentPane().add(cboTipo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 522, 183);
		getContentPane().add(scrollPane);
		
		tblTipoEmpleado = new JTable();
		tblTipoEmpleado.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTipoEmpleado);
		tblTipoEmpleado.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Años Trabajando");
		modelo.addColumn("Tipo");
		
		btnListar = new JButton("LISTADO");
		btnListar.addActionListener(this);
		btnListar.setBounds(433, 23, 101, 20);
		getContentPane().add(btnListar);
		
		btnPdf = new JButton("PDF");
		btnPdf.addActionListener(this);
		btnPdf.setBounds(433, 47, 101, 20);
		btnPdf.setEnabled(false);
		
		getContentPane().add(btnPdf);
		llenacombo();
		ajustarAnchoColumnas();
	}
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblTipoEmpleado.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2));  
		tcm.getColumn(1).setPreferredWidth(anchoColumna(2));  
		tcm.getColumn(2).setPreferredWidth(anchoColumna(7));  
		tcm.getColumn(3).setPreferredWidth(anchoColumna(4));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(3));
	}
	int anchoColumna(int porcentaje){
		return porcentaje * scrollPane.getWidth() / 10;
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
		if (arg0.getSource() == btnPdf) {
			actionPerformedBtnPdf(arg0);
		}
		if (arg0.getSource() == cboTipo) {
			actionPerformedCboTipo(arg0);
		}
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
	}
	protected void actionPerformedBtnListar(ActionEvent arg0) {
		listado();
	}
	private void listado() {
		int tipo;
		tipo = leerTipo();
		GestionEmpleados ge = new GestionEmpleados();
		ArrayList<Empleado> lista = ge.listadoxTipoEmp(tipo);
		if (lista == null) {
			JOptionPane.showMessageDialog(null, "Lista Vacía");
		} else {
			modelo.setRowCount(0);
			for (Empleado r : lista){
			Object aDatos[] = {r.getId_empleado(), 
							   r.getDni_empleado(), 
							   r.getNom_completo(), 
							   r.getFec_empleado(), 
							   r.getDesc_tipo()};
			modelo.addRow(aDatos);
			}
		}
	}

	void llenacombo(){
		GestionEmpleados ge = new GestionEmpleados();
		ArrayList<Empleado> lista = ge.listadoTipo();
		cboTipo.addItem("Seleccione Tipo");
		for(Empleado t: lista){
			cboTipo.addItem(t.getId_tipo()+"-"+t.getDesc_tipo());
		}
	}
	private int leerTipo() {
		int tipo = 0;
		if(cboTipo.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(this, "Selecciona un Tipo");
			btnPdf.setEnabled(false);
		}else{
			tipo = cboTipo.getSelectedIndex();
			btnPdf.setEnabled(true);
		}
		return tipo;
	}
	protected void actionPerformedCboTipo(ActionEvent arg0) {

	}
	protected void actionPerformedBtnPdf(ActionEvent arg0) {
		imprime_pdf();
		btnPdf.setEnabled(false);
	}
	private void imprime_pdf() {
		String nombre_archivo = "Listado_Empleado.pdf";
		try{
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nombre_archivo);
			@SuppressWarnings("unused")
			PdfWriter pdf = PdfWriter.getInstance(doc, fos);
			doc.open();
			Image img = Image.getInstance("src/imgs/logo.png");
			img.scaleToFit(120,50);
			img.setAlignment(Chunk.ALIGN_RIGHT);
			doc.add(img);
			Paragraph p1 = new Paragraph("Reporte de Empleado",
								   		 FontFactory.getFont("helvetica",30,Font.ITALIC));
										 p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			p1 =  new Paragraph("_______________________________");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph("  "));
			p1 = new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			p1.setAlignment(Chunk.ALIGN_RIGHT);
			doc.add(p1);
			doc.add(new Paragraph("  "));
			p1 = new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph("  "));
			PdfPTable tabla = new PdfPTable(6);
			ArrayList<Empleado> lista = new GestionEmpleados().listadoxTipoEmp(leerTipo());
				Paragraph foto = new Paragraph("FOTO",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(foto);
				Paragraph cod = new Paragraph("CODIGO",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(cod);
				Paragraph dni = new Paragraph("DNI",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(dni);
				Paragraph emp = new Paragraph("EMPLEADO",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(emp);
				Paragraph años = new Paragraph("AÑOS DE T.",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(años);
				Paragraph tip = new Paragraph("TIPO",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(tip);
				
				for (Empleado e : lista){
					Image ifoto = Image.getInstance("src/imgs/"+e.getId_empleado()+".jpg");
					ifoto.scaleToFit(100,100);
					tabla.addCell(ifoto);
					tabla.addCell(e.getId_empleado()+"");
					tabla.addCell(e.getDni_empleado());
					tabla.addCell(e.getNom_completo());
					tabla.addCell(e.getFec_empleado());
					tabla.addCell(e.getDesc_tipo());
			}
			tabla.setWidthPercentage(100);
			doc.add(tabla);
			doc.close();
			Desktop.getDesktop().open(new File(nombre_archivo));
		}catch(Exception e){
			System.out.println("Error al crear archivo: "+e.getMessage());
		}
	}
}
