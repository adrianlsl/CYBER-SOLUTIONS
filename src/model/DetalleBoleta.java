package model;

public class DetalleBoleta {
	private String num_bol,nom_prod;
	private int id_productos,cantidad;
	private double preciovta;
	private double importe;
	

	
	
	public DetalleBoleta() {
	}
	
	
	public String getNom_prod() {
		return nom_prod;
	}



	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}



	public String getNum_bol() {
		return num_bol;
	}
	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}
	public int getId_productos() {
		return id_productos;
	}
	public void setId_productos(int id_productos) {
		this.id_productos = id_productos;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPreciovta() {
		return preciovta;
	}
	public void setPreciovta(double preciovta) {
		this.preciovta = preciovta;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
}
