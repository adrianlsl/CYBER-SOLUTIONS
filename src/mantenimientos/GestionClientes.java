package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ClienteInterface;
import model.Cliente;
import model.ClientexEstado;
import model.Estado;
import utils.MySQLConexion;

public class GestionClientes implements ClienteInterface {

	@Override
	public int registrar(Cliente c) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_Clientes values(?,?,?,?,?,default)";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getId_cliente());
			pst.setString(2, c.getDni_cliente());
			pst.setString(3, c.getNom_cliente());
			pst.setString(4, c.getApe_cliente());
			pst.setString(5, c.getTelef_cliente());

			rs = pst.executeUpdate();

		} catch (Exception e2) {
			System.out.println("Error en registrar" + e2.getMessage());
			JOptionPane.showMessageDialog(null, "Razones de no registro: \n" + "Dni Existente \n" +"Telefono Existente");
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar : " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public Cliente buscar(String idcliente) {
		Cliente c = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_clientes where id_cliente=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, idcliente);
			rs = pst.executeQuery();
			while (rs.next()) {
				c = new Cliente();
				c.setId_cliente(rs.getString(1));
				c.setDni_cliente(rs.getString(2));
				c.setNom_cliente(rs.getString(3));
				c.setApe_cliente(rs.getString(4));
				c.setTelef_cliente(rs.getString(5));
				c.setIdestado(rs.getInt(6));
			}
		} catch (Exception e) {
			System.out.println("Error en buscar " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en buscar: \n" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return c;
	}

	@Override
	public ArrayList<Cliente> listado() {
		ArrayList<Cliente> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // tipo de resultado
		try {
			con = MySQLConexion.getConexion();
			String sql = "{call usp_ListadoCliente()}";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(); // tipo de ejecución

			// Acciones adicionales en caso de consultas
			lista = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId_cliente(rs.getString(1));
				c.setDni_cliente(rs.getString(2));
				c.setNom_cliente(rs.getString(3));
				c.setApe_cliente(rs.getString(4));
				c.setTelef_cliente(rs.getString(5));
				c.setDescripcion(rs.getString(6));
				lista.add(c);
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

	@Override
	public int actualizar(Cliente c) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_clientes set nom_cliente=?,telef_cliente=?,idestado=? where id_cliente=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNom_cliente());
			pst.setString(2, c.getTelef_cliente());
			pst.setInt(3, c.getIdestado());
			pst.setString(4, c.getId_cliente());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al actualizar: \n" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar" + e.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<ClientexEstado> consultaxEstado(int idestado) {
		ArrayList<ClientexEstado> lista = null;
		// Arreglar
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; // tipo de resultado
		try {
			con = MySQLConexion.getConexion();
			String sql = "{call usp_consultaClientexEstado(?)}";

			pst = con.prepareStatement(sql);
			// parámetros según la sentencia

			pst.setInt(1, idestado);

			rs = pst.executeQuery(); // tipo de ejecución

			// Acciones adicionales en caso de consultas
			lista = new ArrayList<ClientexEstado>();
			while (rs.next()) {
				ClientexEstado ce = new ClientexEstado();

				ce.setId_cliente(rs.getString(1));
				ce.setNombreCompleto(rs.getString(2));
				ce.setDescEstado(rs.getString(3));

				lista.add(ce);
			}
		} catch (Exception e) {
			System.out.println("Error en consultar cliente x Estado " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en consultar Cliente x Estado " + e.getMessage());
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

	@Override
	public ArrayList<Cliente> listadoxEstados(int Estado) {
				ArrayList<Cliente> lista = null;
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
					try {
						con = MySQLConexion.getConexion();
						String sql = "{call usp_consultaClientexEstado(?)}";
						pst = con.prepareStatement(sql);
						pst.setInt(1, Estado);
						rs = pst.executeQuery();
						lista=new ArrayList<Cliente>();
							while(rs.next()){
								Cliente u = new Cliente();
								u.setId_cliente(rs.getString(1));
								u.setDni_cliente(rs.getString(2));
								u.setNom_cliente(rs.getString(3));
								u.setApe_cliente(rs.getString(4));
								u.setTelef_cliente(rs.getString(5));
								u.setDescripcion(rs.getString(6));
								lista.add(u);
							}
					} catch (Exception e) {
							System.out.println("Error en registrar " + e.getMessage());
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
	public ArrayList<Estado> listadoEstado() {
				ArrayList<Estado> lista = null;
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null; 
				try {
					con = MySQLConexion.getConexion();
					String sql = "select * from tb_estado";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					lista = new ArrayList<Estado>();
					while (rs.next()) {
						Estado t = new Estado();
						t.setId_estado(rs.getInt(1));
						t.setDes_estado(rs.getString(2));
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
	public ArrayList<Cliente> listadpxEstadoCant(int idestado) {
		ArrayList<Cliente> lista = null;
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				try {
					con = MySQLConexion.getConexion();
					String sql = "{call usp_ListaClientexEstado(?)}";
					pst = con.prepareStatement(sql);
					pst.setInt(1, idestado);
					rs = pst.executeQuery();
					lista=new ArrayList<Cliente>();
					while(rs.next()){ 
						Cliente c = new Cliente();
						c.setId_cliente(rs.getString(1));
						c.setDni_cliente(rs.getString(2));
						c.setNomCompletoCli(rs.getString(3));
						c.setTelef_cliente(rs.getString(4));
						c.setDescripcion(rs.getString(5));
						c.setCantidad(rs.getInt(6));
						lista.add(c);
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
	public String generaNumCliente() {
		String codigo = "CL001"; //Codigo x Default, si no hay datos
		//Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substring(id_cliente,3)"
					+ "from tb_clientes order by id_cliente desc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				DecimalFormat df = new DecimalFormat("000");
				codigo = "CL" + df.format(Integer.parseInt(rs.getString(1))+1);
			}
			
		} catch (Exception e) {
			System.out.println("Error en generar numero de Cliente: " + e.getMessage());
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
