package model.interfaces;

import java.util.ArrayList;

import model.acesso.PerfilModel;

/**
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public interface InterfacePerfilDAO {
	
	
	public boolean criarPerfilVazio(String nomeDoPerfil);
	
	public boolean deletarPerfil(Integer idDoPerfil);
	
	public boolean atualizarPerfil(Integer idDoPerfil, PerfilModel perfil);
	
	public PerfilModel buscarPerfil(Integer idDoPerfil);
	
	public ArrayList<PerfilModel> buscarTodasAsPerfil();
	
	
}
