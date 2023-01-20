package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.EmpleadoInterface;
import model.Empleado;
import utils.MySQLConexion;

public class GestionEmpleados implements EmpleadoInterface{

	@Override
	public int registrar(Empleado e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_empleado values(?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getId_empleado());
			pst.setString(2, e.getDni_empleado());
			pst.setString(3, e.getNom_empleado());
			pst.setString(4, e.getApe_empleado());
			pst.setString(5, e.getFec_empleado());
			pst.setString(6, e.getUsu_empleado());
			pst.setString(7, e.getPass_empleado());
			pst.setString(8, e.getTelef_empleado());
			pst.setInt(9, e.getId_tipo());
			rs = pst.executeUpdate();
		}catch(Exception ex){
			System.out.println("Error en registrar "+ex.getMessage());
		}finally{
			try{
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(SQLException se){
				System.out.println("Error al cerrar "+se.getMessage());
			}
		}
		return rs;
	}
	@Override
	public Empleado buscar(String dni) {
		Empleado e = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
			try{
				con = MySQLConexion.getConexion();
				String sql = 
						"select * from tb_empleado where id_empleado=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, dni);
				rs = pst.executeQuery();
				while(rs.next()){
					e = new Empleado();
					e.setId_empleado(rs.getString(1));
					e.setDni_empleado(rs.getString(2));
					e.setNom_empleado(rs.getString(3));
					e.setApe_empleado(rs.getString(4));
					e.setUsu_empleado(rs.getString(6));
					e.setPass_empleado(rs.getString(7));
					e.setTelef_empleado(rs.getString(8));
					e.setId_tipo(rs.getInt(9));
				}
			}catch(Exception ex){
				System.out.println("Error en buscar "+ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en buscar "+ex.getMessage());
			}finally{
				try{
					if(pst !=null)pst.close();
					if(con !=null)con.close();
				}catch(SQLException ex){
					System.out.println("Error al cerrar "+ex.getMessage());
				}
			}
		return e;
	}
	
	@Override
	public int actualizar(Empleado e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = 
					"update tb_empleado set dni_empleado=?,nom_empleado=?,ape_empleado=?,usu_empleado=?,pass_empleado=?,telef_empleado=?,id_tipo=? where id_empleado=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getDni_empleado());
			pst.setString(2, e.getNom_empleado());
			pst.setString(3, e.getApe_empleado());
			pst.setString(4, e.getUsu_empleado());
			pst.setString(5, e.getPass_empleado());
			pst.setString(6, e.getTelef_empleado());
			pst.setInt(7, e.getId_tipo());
			pst.setString(8, e.getId_empleado());
			rs = pst.executeUpdate();
		}catch(Exception ex){
			System.out.println("Error en actualizar: " + ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al actualizar:" + ex.getMessage());
		}finally{
			try{
				con.close();if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(SQLException ex){
				System.out.println("Error al cerrar" + ex.getMessage());
			}
		}
		return rs;
	}
	@Override
	public ArrayList<Empleado> listado() {
		ArrayList<Empleado> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
			try{
				con = MySQLConexion.getConexion();
				String sql = 
						"{call usp_ListadoEmpleado()}";
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				lista = new ArrayList<Empleado>();
				while(rs.next()){
					Empleado e = new Empleado();
					e.setId_empleado(rs.getString(1));
					e.setDni_empleado(rs.getString(2));
					e.setNom_empleado(rs.getString(3));
					e.setApe_empleado(rs.getString(4));
					e.setFec_empleado(rs.getString(5));
					e.setTelef_empleado(rs.getString(6));
					e.setDesc_tipo(rs.getString(7));;
					lista.add(e);
				}
			}catch(Exception e){
				System.out.println("Error en listado "+e.getMessage());
				JOptionPane.showMessageDialog(null, "Error en listado "+e.getMessage());
			}finally{
				try{
					if(pst !=null)pst.close();
					if(con !=null)con.close();
				}catch(SQLException e){
					System.out.println("Error al cerrar "+e.getMessage());
				}
			}
		return lista;
	}
	@Override
	public Empleado validaAcceso(String usuario, String clave) {
		Empleado e = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "{call usp_validaUsuario(?,?)};";
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				e = new Empleado();
				e.setId_empleado(rs.getString(1));
				e.setNom_empleado(rs.getString(3));
				e.setApe_empleado(rs.getString(4));
				e.setTelef_empleado(rs.getString(7));
				e.setId_tipo(rs.getInt(9));
			}
			
		} catch (Exception e2) {
			System.out.println("Error en Usuario " + e2.getMessage());
			JOptionPane.showMessageDialog(null, "Error en Usuario " + e2.getMessage());
		}finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar ");
			}
		}
		return e;
	}
	@Override
	public ArrayList<Empleado> listadoxTipoEmp(int idtipo) {
		ArrayList<Empleado> lista = null;
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				try {
					con = MySQLConexion.getConexion();
					String sql = "{call usp_ListaEmpleadoxTipo(?)}";
					pst = con.prepareStatement(sql);
					pst.setInt(1, idtipo);					
					rs = pst.executeQuery();					
					lista=new ArrayList<Empleado>();
					while(rs.next()){
						Empleado u = new Empleado();
						u.setId_empleado(rs.getString(1));
						u.setDni_empleado(rs.getString(2));
						u.setNom_completo(rs.getString(3));
						u.setFec_empleado(rs.getString(4));
						u.setDesc_tipo(rs.getString(5));
						lista.add(u);
					}					
				} catch (Exception e) {
					System.out.println("Error en listar " + e.getMessage());
				} finally {
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println("Error al cerrar : " + e.getMessage());
					}
				}
		return lista;
	}
	@Override
	public ArrayList<Empleado> listadoTipo() {
		ArrayList<Empleado> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_tipo";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Empleado>();
			while (rs.next()) {
				Empleado t = new Empleado();
				t.setId_tipo(rs.getInt(1));
				t.setDesc_tipo(rs.getString(2));
				lista.add(t); 
			}
		} catch (Exception e) {
			System.out.println("Error en listado " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en listado: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		return lista;	
	}
	@Override
	public String generaNumEmpleado() {
		String codigo = "EM001"; //Codigo x Default, si no hay datos
		//Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substring(id_empleado,3)"
					+ "from tb_empleado order by id_empleado desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				DecimalFormat df = new DecimalFormat("000");
				codigo = "EM" + df.format(Integer.parseInt(rs.getString(1))+1);
			}
			
		} catch (Exception e) {
			System.out.println("Error en generar numero de Empleado: " + e.getMessage());
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		
		return codigo;
	}
}
