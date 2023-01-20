package model;

public class Empleado {
	 private int id_tipo;
	   private String id_empleado,dni_empleado,nom_empleado,ape_empleado,fec_empleado,usu_empleado,pass_empleado,telef_empleado;
	   private String desc_tipo;
	   private String nom_completo;
	public Empleado() {
	}
	public String getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}
	public String getNom_empleado() {
		return nom_empleado;
	}
	public void setNom_empleado(String nom_empleado) {
		this.nom_empleado = nom_empleado;
	}
	public String getApe_empleado() {
		return ape_empleado;
	}
	public void setApe_empleado(String ape_empleado) {
		this.ape_empleado = ape_empleado;
	}
	public String getFec_empleado() {
		return fec_empleado;
	}
	public void setFec_empleado(String fec_empleado) {
		this.fec_empleado = fec_empleado;
	}
	public String getUsu_empleado() {
		return usu_empleado;
	}
	public void setUsu_empleado(String usu_empleado) {
		this.usu_empleado = usu_empleado;
	}
	public String getPass_empleado() {
		return pass_empleado;
	}
	public void setPass_empleado(String pass_empleado) {
		this.pass_empleado = pass_empleado;
	}
	public String getTelef_empleado() {
		return telef_empleado;
	}
	public void setTelef_empleado(String telef_empleado) {
		this.telef_empleado = telef_empleado;
	}
	public String getDni_empleado() {
		return dni_empleado;
	}
	public void setDni_empleado(String dni_empleado) {
		this.dni_empleado = dni_empleado;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getDesc_tipo() {
		return desc_tipo;
	}
	public void setDesc_tipo(String desc_tipo) {
		this.desc_tipo = desc_tipo;
	}
	public String getNom_completo() {
		return nom_completo;
	}
	public void setNom_completo(String nom_completo) {
		this.nom_completo = nom_completo;
	}
	   
}
