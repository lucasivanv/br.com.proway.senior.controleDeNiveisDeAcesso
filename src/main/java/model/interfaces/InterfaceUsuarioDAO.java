package model.interfaces;

import java.util.ArrayList;

import model.acesso.UsuarioModel;

/**
 * @author Vitor Peres, vitor.peres@senior.com.br
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * 
 * ver 2 - integracao com PostgreSQL + JDBC
 */
public interface InterfaceUsuarioDAO<T> {
	public boolean criarUsuario(String hashSenha, String loginUsuario);
	
	public boolean deletarUsuario(Integer idUsuario);
	
	public boolean atualizarUsuario(Integer idUsuario, UsuarioModel usuario);
	
	public UsuarioModel buscarUsuario(Integer idUsuario);
	
	public ArrayList<UsuarioModel> buscarTodosUsuarios();
	
}
