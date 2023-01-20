package interfaces;

import java.util.ArrayList;

import model.Empleado;

public interface EmpleadoInterface {
	public String generaNumEmpleado();
	public int registrar(Empleado e);
	public int actualizar(Empleado e);
	public Empleado buscar (String idempleado);
	public ArrayList<Empleado> listado();
	public Empleado validaAcceso(String usuario, String clave);
	public ArrayList<Empleado> listadoTipo();
	public ArrayList<Empleado> listadoxTipoEmp(int idtipo);
}
