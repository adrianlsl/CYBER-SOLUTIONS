package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionVenta;
import model.FechaVentas;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.ImageIcon;

public class FrmListado_Ventas extends JInternalFrame implements ActionListener, InternalFrameListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "cerrado";
	private JLabel lblFechaInicial;
	private JDateChooser txtFechaInic;
	private JLabel lblFechaFinal;
	private JDateChooser txtFechaFin;
	private JButton btnListar;
	private JTable tblFechaVentas;
	private JScrollPane scrollPane;
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
					FrmListado_Ventas frame = new FrmListado_Ventas();
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
	public FrmListado_Ventas() {
		setFrameIcon(new ImageIcon(FrmListado_Ventas.class.getResource("/imgs/reporte.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte | Listado de Ventas");
		addInternalFrameListener(this);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		lblFechaInicial = new JLabel("Fecha inicial");
		lblFechaInicial.setBounds(12, 12, 95, 16);
		getContentPane().add(lblFechaInicial);
		
		lblFechaFinal = new JLabel("Fecha final");
		lblFechaFinal.setBounds(371, 12, 75, 16);
		getContentPane().add(lblFechaFinal);
		
		txtFechaInic = new JDateChooser();
		txtFechaInic.setDateFormatString("yyyy/MM/dd");
		txtFechaInic.setBounds(115, 12, 113, 20);
		getContentPane().add(txtFechaInic);
		
		txtFechaFin = new JDateChooser();
		txtFechaFin.setDateFormatString("yyyy/MM/dd");
		txtFechaFin.setBounds(464, 12, 113, 20);
		getContentPane().add(txtFechaFin);
		
		btnListar = new JButton("LISTADO");
		btnListar.addActionListener(this);
		btnListar.setBounds(464, 39, 118, 20);
		getContentPane().add(btnListar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 91, 570, 268);
		getContentPane().add(scrollPane);
		
		tblFechaVentas = new JTable();
		scrollPane.setViewportView(tblFechaVentas);
		tblFechaVentas.setModel(modelo);
		
		btnPdf = new JButton("PDF");
		btnPdf.addActionListener(this);
		btnPdf.setBounds(464, 65, 118, 21);
		getContentPane().add(btnPdf);
		modelo.addColumn("Numero");
		modelo.addColumn("Fecha");
		modelo.addColumn("Nombre Cliente");
		modelo.addColumn("Nombre Empleado");
		modelo.addColumn("Total");
		
		ajustarAnchoColumnas();
	}
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblFechaVentas.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2));  
		tcm.getColumn(1).setPreferredWidth(anchoColumna(3));  
		tcm.getColumn(2).setPreferredWidth(anchoColumna(6));  
		tcm.getColumn(3).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(2));
	}
	int anchoColumna(int porcentaje){
		return porcentaje * scrollPane.getWidth() / 10;
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPdf) {
			actionPerformedBtnPdf(arg0);
		}
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
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
		estado="cerrado";
	}
	
	protected void actionPerformedBtnListar(ActionEvent arg0) {
		listado();
	}
	void listado(){                                   
		String fecha1,fecha2;
		fecha1 = leerFechaInicial();
		fecha2 = leerFechaFinal();
		
		GestionVenta gv = new GestionVenta();
		ArrayList<FechaVentas> lista = gv.listadoxfechas(fecha1, fecha2);
		
		if(lista == null){
			JOptionPane.showMessageDialog(this, "Lista Vacia");
		}else{
			modelo.setRowCount(0);
			for (FechaVentas fv : lista) {
				Object aDatos[] = {
						fv.getNum_boleta(),fv.getFech_bol(),
						fv.getNom_cli(),fv.getNom_empl(),
						fv.getTot_bol()
				};
				modelo.addRow(aDatos);
			}
			
		}
	}
	
	private String leerFechaInicial(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaInicial = new Date();
		String fechan = null;
		try {
			if(txtFechaInic.getDate().before(fechaInicial)){
				fechan = sdf.format(txtFechaInic.getDate());
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha menor a la actual");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seleccione Fecha");
		}
		return fechan;
	}
	
	private String leerFechaFinal(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaFinal = new Date();
		String fechad = null;
		try {
			if(txtFechaFin.getDate().before(fechaFinal)){
				fechad = sdf.format(txtFechaFin.getDate());
			}else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha menor a la actual");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seleccione Fecha");
		}
		return fechad;
	}
	
	protected void actionPerformedBtnPdf(ActionEvent arg0) {
		imprime_pdf();
	}
	private void imprime_pdf() {
		String nombre_archivo = "Reporte_Ventas.pdf";
		try{
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nombre_archivo);
			@SuppressWarnings("unused")
			PdfWriter pdf = PdfWriter.getInstance(doc, fos);
			doc.open();
			Image img = Image.getInstance("src/imgs/logopdf.png");
			img.scaleToFit(500,150);
			img.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(img);
			Paragraph p1 = new Paragraph("Reporte de Ventas",
								   		 FontFactory.getFont("helvetica",30,Font.ITALIC));
										 p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			p1 =  new Paragraph("_______________________________");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph(" "));
			p1 = new Paragraph("Entre las fechas: " + leerFechaInicial() + " / " + leerFechaFinal());
			p1.setAlignment(Chunk.ALIGN_LEFT);
			doc.add(p1);
			doc.add(new Paragraph(" "));
			p1 = new Paragraph("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			p1.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p1);
			doc.add(new Paragraph(" "));
			PdfPTable tabla = new PdfPTable(5);
			ArrayList<FechaVentas> lista = new GestionVenta().listadoxfechas(leerFechaInicial(), leerFechaFinal());
				Paragraph num = new Paragraph("N° BOLETA",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(num);
				Paragraph fec = new Paragraph("FEC. BOLETA",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(fec);
				Paragraph cli = new Paragraph("CLIENTE",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(cli);
				Paragraph emp = new Paragraph("EMPLEADO",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(emp);
				Paragraph tot = new Paragraph("TOTAL",FontFactory.getFont("helvetica",14,Font.BOLD));
				tabla.addCell(tot);				
				for (FechaVentas fv : lista){
					tabla.addCell(fv.getNum_boleta()+"");
					tabla.addCell(fv.getFech_bol());
					tabla.addCell(fv.getNom_cli());
					tabla.addCell(fv.getNom_empl());
					tabla.addCell(fv.getTot_bol()+"");
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
