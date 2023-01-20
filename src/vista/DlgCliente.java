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

import mantenimientos.GestionClientes;
import model.ClientexEstado;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DlgCliente extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblCliente;
	DefaultTableModel modelo = new DefaultTableModel();
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
			DlgCliente dialog = new DlgCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCliente() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Buscar Cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 12, 416, 215);
			contentPanel.add(scrollPane);
			
			tblCliente = new JTable();
			scrollPane.setViewportView(tblCliente);
			tblCliente.setModel(modelo);
			modelo.addColumn("Codigo");
			modelo.addColumn("Nombre Completo");
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
		ArrayList<ClientexEstado> consulta = new GestionClientes().consultaxEstado(1);
		for (ClientexEstado c : consulta) {
			Object aDatos[] = {
					c.getId_cliente(),
					c.getNombreCompleto()
			};
			modelo.addRow(aDatos);
		}
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
		int fila = tblCliente.getSelectedRow();
		try {
			FrmBoleta.txtCodCliente.setText(tblCliente.getValueAt(fila, 0).toString());
			FrmBoleta.txtNomCliente.setText(tblCliente.getValueAt(fila, 1).toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seleccione un Cliente");
		}finally{
			dispose();
		}
	}
	
}
