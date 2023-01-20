package model;

public class Producto {
	private int id_productos, id_categoria;
	private String nom_prod;
	private double prec_prod;
	private int stock_prod;
	private String nom_cat;
		
	public Producto() {
		
	}
	
	public int getId_productos() {
		return id_productos;
	}
	public void setId_productos(int id_productos) {
		this.id_productos = id_productos;
	}
	public String getNom_prod() {
		return nom_prod;
	}
	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}
	public double getPrec_prod() {
		return prec_prod;
	}
	public void setPrec_prod(double prec_prod) {
		this.prec_prod = prec_prod;
	}
	public int getStock_prod() {
		return stock_prod;
	}
	public void setStock_prod(int stock_prod) {
		this.stock_prod = stock_prod;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNom_cat() {
		return nom_cat;
	}

	public void setNom_cat(String nom_cat) {
		this.nom_cat = nom_cat;
	}
	
	
}
