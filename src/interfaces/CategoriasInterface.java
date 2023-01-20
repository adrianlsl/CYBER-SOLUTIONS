package interfaces;

import java.util.ArrayList;

import model.Categoria;

public interface CategoriasInterface {
	public int registrar(Categoria e);
	public int actualizar(Categoria c);
	public Categoria buscar(int codigo);
	public ArrayList<Categoria> listado();
}
