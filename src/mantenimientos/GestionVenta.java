package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.VentaInterface;
import model.CabeceraBoleta;
import model.DetalleBoleta;
import model.FechaVentas;
import utils.MySQLConexion;

public class GestionVenta implements VentaInterface{

	@Override
	public String generaNumBoleta() {
		String codigo = "B0001"; //Codigo x Default, si no hay datos
		//Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substring(num_bol,2)"
					+ "from tb_cab_boleta order by num_bol desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				DecimalFormat df = new DecimalFormat("0000");
				codigo = "B" + df.format(Integer.parseInt(rs.getString(1))+1);
			}
			
		} catch (Exception e) {
			System.out.println("Error en generar numero de Boleta: " + e.getMessage());
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		
		return codigo;
	}

	@Override
	public int realizarVenta(CabeceraBoleta cab, ArrayList<DetalleBoleta> det) {
		int ok = -1;
		
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		
		try {
			con =  MySQLConexion.getConexion();
			con.setAutoCommit(false);
			
			String sql1 = "insert into tb_cab_boleta values(?,?,?,?,?)";
			
			pst1 = con.prepareStatement(sql1);
			
			pst1.setString(1, cab.getNum_bol());
			pst1.setString(2, cab.getFch_bol());
			pst1.setString(3, cab.getId_cliente());
			pst1.setString(4, cab.getId_empleado());
			pst1.setDouble(5, cab.getTotal_bol());
			
			ok = pst1.executeUpdate();
			
			//
			String sql2 = "insert into tb_det_boleta values(?,?,?,?,?,?)";
			
			for (DetalleBoleta d : det) {
				pst2 = con.prepareStatement(sql2);
				
				pst2.setString(1, cab.getNum_bol());
				pst2.setInt(2, d.getId_productos());
				pst2.setString(3, d.getNom_prod());
				pst2.setInt(4, d.getCantidad());
				pst2.setDouble(5, d.getPreciovta());
				pst2.setDouble(6, d.getImporte());
				
				ok = pst2.executeUpdate();
			}
			///
			String sql3 = "update tb_productos set stock_prod = stock_prod - ? where id_productos = ?";
			
			for (DetalleBoleta d : det) {
				pst3 = con.prepareStatement(sql3);
				
				pst3.setInt(1, d.getCantidad());
				pst3.setInt(2, d.getId_productos());
				
				ok = pst3.executeUpdate();
			}
			//Confirmar transacciones, si no hay problemas
			con.commit();
			
		} catch (Exception e) {
			System.out.println("Error al realizar venta: "  + e.getMessage());
			ok = -1; //Valor de control, para la validacion
			try {
				con.rollback();
			} catch (Exception e2) {
				System.out.println("Error al restaurar: " + e.getMessage());
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return ok;	}

	@Override
	public ArrayList<FechaVentas> listadoxfechas(String fecha1, String fecha2) {
		ArrayList<FechaVentas> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			String sql = "{call usp_ListaVentasFechas(?,?)}";			
			pst = con.prepareStatement(sql);			
			pst.setString(1, fecha1);
			pst.setString(2, fecha2);
			rs = pst.executeQuery(); 
			lista = new ArrayList<FechaVentas>();		
		while (rs.next()) {
			 FechaVentas fv = new FechaVentas();
			fv.setNum_boleta(rs.getString(1));
			fv.setFech_bol(rs.getString(2));
			fv.setNom_cli(rs.getString(3));
			fv.setNom_empl(rs.getString(4));
			fv.setTot_bol(rs.getDouble(5));
			lista.add(fv);
		}
	} catch (Exception e) {
		System.out.println("Error en Listado " + e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en Listado: \n" + e.getMessage());
	} finally {
		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar ");
		}
	}
	return lista;	
	}

}
