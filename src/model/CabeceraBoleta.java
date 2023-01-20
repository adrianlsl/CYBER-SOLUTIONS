package model;

public class CabeceraBoleta {
	private String num_bol ;
	private String fch_bol;
	private String id_cliente,id_empleado;
	private double total_bol;
	
	
	public CabeceraBoleta() {
	}
	public String getNum_bol() {
		return num_bol;
	}
	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}
	public String getFch_bol() {
		return fch_bol;
	}
	public void setFch_bol(String fch_bol) {
		this.fch_bol = fch_bol;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}
	public double getTotal_bol() {
		return total_bol;
	}
	public void setTotal_bol(double total_bol) {
		this.total_bol = total_bol;
	}
	
	
}
