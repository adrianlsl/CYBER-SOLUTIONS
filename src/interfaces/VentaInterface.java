package interfaces;

import java.util.ArrayList;

import model.CabeceraBoleta;
import model.DetalleBoleta;
import model.FechaVentas;

public interface VentaInterface {
	public String generaNumBoleta();
	public int realizarVenta(CabeceraBoleta cab, ArrayList<DetalleBoleta> det);
	public ArrayList<FechaVentas> listadoxfechas(String fecha1, String fecha2);
}
