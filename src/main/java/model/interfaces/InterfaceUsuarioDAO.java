package model.interfaces;

import java.util.ArrayList;

/**
 * @author Sprint 3 
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 */
public interface InterfaceUsuarioDAO<T> {
	
	ArrayList<T> getAll();
	
	void create(T item);
	
	T get (int id);
	
	boolean update (T item);
	
	void remove (int id);
}
