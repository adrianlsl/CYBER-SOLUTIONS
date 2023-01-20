package model;

public class FechaVentas {
	private String nom_cli, fech_bol, nom_empl, num_boleta;
	private double tot_bol;
	
	public FechaVentas() {
	}
	
	public String getNom_cli() {
		return nom_cli;
	}
	public void setNom_cli(String nom_cli) {
		this.nom_cli = nom_cli;
	}
	public String getFech_bol() {
		return fech_bol;
	}
	public void setFech_bol(String fech_bol) {
		this.fech_bol = fech_bol;
	}
	public String getNom_empl() {
		return nom_empl;
	}
	public void setNom_empl(String nom_empl) {
		this.nom_empl = nom_empl;
	}
	public String getNum_boleta() {
		return num_boleta;
	}
	public void setNum_boleta(String num_boleta) {
		this.num_boleta = num_boleta;
	}
	public double getTot_bol() {
		return tot_bol;
	}
	public void setTot_bol(double tot_bol) {
		this.tot_bol = tot_bol;
	}
}
