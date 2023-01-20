package model;

public class Categoria {
	private int id_categoria;
	private String nom_cat;
	private String desc_cat;
	
	public Categoria() {	
		
	}
	public Categoria (int id_categoria, String nom_cat, String desc_cat) {
		this.id_categoria = id_categoria;
		this.nom_cat = nom_cat;
		this.desc_cat = desc_cat;
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
	public void setNom_cat(String nom_cat){
		this.nom_cat = nom_cat;
	}
	public String getDesc_cat() {
		return desc_cat;
	}
	public void setDesc_cat (String desc_cat) {
		this.desc_cat = desc_cat;
	}
}
