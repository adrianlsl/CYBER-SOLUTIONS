package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ProductoInterface;
import model.Producto;
import utils.MySQLConexion;

public class GestionProductos implements ProductoInterface{

	@Override
	public int registrar(Producto e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_productos values(null,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getNom_prod());
			pst.setDouble(2,e.getPrec_prod());
			pst.setInt(3, e.getStock_prod());
			pst.setInt(4, e.getId_categoria());
			rs =pst.executeUpdate();
		} catch (Exception e2) {
			System.out.println("Error en registrar " + e2.getMessage());;
		}finally{
			try {
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar : " + e2.getMessage());
			}
		}
		return rs;	
	}

	@Override
	public int eliminar(int codigo) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = "delete from tb_productos where id_productos=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeUpdate();
		}catch(Exception e){
			System.out.println("Error en eliminar: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al eliminar: "+e.getMessage());
		}finally{
			try{
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(SQLException ex){
				System.out.println("Error al cerrar "+ex.getMessage());
			}
		}
		return rs;	
	}

	@Override
	public int actualizar(Producto e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = "update tb_productos set nom_prod=?,prec_prod=?,stock_prod=? where id_productos=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getNom_prod());
			pst.setDouble(2, e.getPrec_prod());
			pst.setInt(3, e.getStock_prod());
			pst.setInt(4, e.getId_productos());
			rs = pst.executeUpdate();
		}catch(Exception ex){
			System.out.println("Error en actualizar: " + ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al actualizar:" + ex.getMessage());
		}finally{
			try{
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(SQLException ex){
				System.out.println("Error al cerrar" + ex.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<Producto> listado() {
		ArrayList<Producto> lista =null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql= "{call usp_ListadoProductos()}";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Producto>();
			
			while(rs.next()){
				Producto p = new Producto();
				p.setId_productos(rs.getInt(1));
				p.setNom_prod(rs.getString(2));
				p.setPrec_prod(rs.getDouble(3));
				p.setStock_prod(rs.getInt(4));
				p.setNom_cat(rs.getString(5));	
				lista.add(p);
			}
			
		} catch (Exception e) {
			System.out.println("Error en listado" + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en listado: "+ e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista;	
	}

	@Override
	public Producto buscar(int codigo) {
		Producto p = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
			try{
				con = MySQLConexion.getConexion();
				String sql = 
						"select * from tb_productos where id_productos=?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, codigo);
				rs = pst.executeQuery();
				while(rs.next()){
					p = new Producto();
					p.setId_productos(rs.getInt(1));
					p.setNom_prod(rs.getString(2));
					p.setPrec_prod(rs.getDouble(3));
					p.setStock_prod(rs.getInt(4));
					p.setId_categoria(rs.getInt(5));
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
		return p;		
		}

}
