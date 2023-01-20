package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionProductos;
import model.Producto;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DlgProducto extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblProducto;
	DefaultTableModel modelo = new DefaultTableModel();
	JScrollPane scrollPane = new JScrollPane();
	private JButton btnOk;
	private JButton btnCancelar;
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
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Buscar Producto");
		setBounds(100, 100, 520, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			scrollPane.setBounds(10, 11, 488, 216);
			contentPanel.add(scrollPane);
			tblProducto = new JTable();
			scrollPane.setViewportView(tblProducto);
			tblProducto.setModel(modelo);
		}
		{
			modelo.addColumn("Codigo");
			modelo.addColumn("Producto");
			modelo.addColumn("Precio");
			modelo.addColumn("Stock");
			ajustarAnchoColumnas();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.addActionListener(this);
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(this);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
				btnCancelar.setBackground(Color.red);
			}
		}
		listado();
	}
	void listado(){
		
		ArrayList<Producto> listado = new GestionProductos().listado();
		for (Producto p : listado) {
			Object aDatos[] = {
					p.getId_productos(),
					p.getNom_prod(),
					Double.valueOf(p.getPrec_prod()),
					Integer.valueOf(p.getStock_prod())
			};
			modelo.addRow(aDatos);
		}
	}
	
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblProducto.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5));  
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));  
		tcm.getColumn(2).setPreferredWidth(anchoColumna(5));  
		tcm.getColumn(3).setPreferredWidth(anchoColumna(4));
	}
	int anchoColumna(int porcentaje){
		return porcentaje * scrollPane.getWidth() / 10;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == btnOk) {
			actionPerformedBtnOk(arg0);
		}
	}
	protected void actionPerformedBtnOk(ActionEvent arg0) {
		enviarDatos();
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		dispose();
	}
	
	void enviarDatos(){
		int fila = tblProducto.getSelectedRow();
		try {
			FrmBoleta.txtCodProducto.setText(tblProducto.getValueAt(fila, 0).toString());
			FrmBoleta.txtNomProducto.setText(tblProducto.getValueAt(fila, 1).toString());
			FrmBoleta.txtPrecio.setText(tblProducto.getValueAt(fila, 2).toString());
			FrmBoleta.txtStock.setText(tblProducto.getValueAt(fila, 3).toString());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seleccione Producto");
		}finally {
			dispose();
			
		}
		
	}
}
