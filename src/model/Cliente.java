package model;

public class Cliente {
	private String id_cliente;
	private String dni_cliente;
	private String nom_cliente;
	private String ape_cliente;
	private String telef_cliente;
	
	private int	idestado;
	private String descripcion;
	
	private String nomCompletoCli;
	private int cantidad;
	
	
	public Cliente() {
		
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getIdestado() {
		return idestado;
	}
	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getDni_cliente() {
		return dni_cliente;
	}
	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}
	public String getNom_cliente() {
		return nom_cliente;
	}
	public void setNom_cliente(String nom_cliente) {
		this.nom_cliente = nom_cliente;
	}
	public String getApe_cliente() {
		return ape_cliente;
	}
	public void setApe_cliente(String ape_cliente) {
		this.ape_cliente = ape_cliente;
	}
	public String getTelef_cliente() {
		return telef_cliente;
	}
	public void setTelef_cliente(String telef_cliente) {
		this.telef_cliente = telef_cliente;
	}


	public String getNomCompletoCli() {
		return nomCompletoCli;
	}


	public void setNomCompletoCli(String nomCompletoCli) {
		this.nomCompletoCli = nomCompletoCli;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
