package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MySQLConexion {
	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/cybersolutions?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root";
			String psw = "mysql";
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!!");
		} catch (SQLException ex) {
			System.out.println("Error >> de conexión con la BD");
			JOptionPane.showMessageDialog(null, "Debe Conectar la base de datos","Error", 0);
		} catch (Exception ex) {
			System.out.println("Error >> Generar: " + ex.getMessage());
		}
		return con;
	}

}
