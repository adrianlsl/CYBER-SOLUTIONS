package interfaces;

import java.util.ArrayList;

import model.Producto;

public interface ProductoInterface {
	public int registrar(Producto e);
	public int eliminar(int codigo);
	public int actualizar(Producto e);
	public ArrayList<Producto> listado();
	public Producto buscar (int codigo);
}
