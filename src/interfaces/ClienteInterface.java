package interfaces;

import java.util.ArrayList;

import model.Cliente;
import model.ClientexEstado;
import model.Estado;

public interface ClienteInterface {
	public String generaNumCliente();
	public int registrar(Cliente c);
	public int actualizar(Cliente c);
	public ArrayList<Cliente> listado();
	public Cliente buscar (String idcliente);
	public ArrayList<ClientexEstado> consultaxEstado(int idestado);
	public ArrayList<Cliente> listadoxEstados(int Estado);
	public ArrayList<Estado> listadoEstado();
	public ArrayList<Cliente> listadpxEstadoCant(int idestado);
	
}
