package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.CategoriasInterface;
import model.Categoria;
import utils.MySQLConexion;

public class GestionCategorias implements CategoriasInterface{

	@Override
	public int registrar(Categoria e) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_Categorias values (null,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getNom_cat());
			pst.setString(2, e.getDesc_cat());
			rs=pst.executeUpdate();
			
		} catch (Exception e2) {
			System.out.println("Error en registrar" + e2.getMessage());;
		} finally{
			try{
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch (SQLException e2) {
				System.out.println ("Error al cerrar : " + e2.getMessage());
			}
		}
		return rs;
	}


	@Override
	public ArrayList<Categoria> listado() {
		ArrayList<Categoria> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql= 
					"select * from tb_categorias";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Categoria>();
			
			while (rs.next()){
				Categoria c = new Categoria();
				c.setId_categoria(rs.getInt(1));
				c.setNom_cat(rs.getString(2));
				c.setDesc_cat(rs.getString(3));
				lista.add(c);
			}
		}catch (Exception e) {
			System.out.println("Error en listado" + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en listado:" +e.getMessage());
		}finally{
			try{
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista;	
	}

	@Override
	public int actualizar(Categoria c) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = "update tb_categorias set nom_cat=?,desc_cat=? where id_categoria=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNom_cat());
			pst.setString(2, c.getDesc_cat());
			pst.setInt(3, c.getId_categoria());
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
	public Categoria buscar(int codigo) {
		Categoria c = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
			try{
				con = MySQLConexion.getConexion();
				String sql = 
						"select * from tb_categorias where id_categoria=?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, codigo);
				rs = pst.executeQuery();
				while(rs.next()){
					c = new Categoria();
					c.setNom_cat(rs.getString(2));
					c.setDesc_cat(rs.getString(3));
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
		return c;	
	}

}
