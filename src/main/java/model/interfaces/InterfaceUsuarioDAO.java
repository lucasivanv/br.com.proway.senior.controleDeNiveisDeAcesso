package model.interfaces;

import java.util.ArrayList;

import model.acesso.UsuarioModel;

/**
 * 
 * @author Vitor Peres
 * @author David Willian
 * @author Leonardo Pereira
 * vitor.peres@senior.com.br
 * leonardo.pereira@senior.com.br
 * david.oliveira@senior.com.br
 * 
 * ver 2 - integração com PostgreSQL + JDBC
 
 */
public interface InterfaceUsuarioDAO<T> {
	public boolean criarUsuario(String hashSenha, String loginUsuario);
	
	public boolean deletarUsuario(Integer idUsuario);
	
	public boolean atualizarUsuario(Integer idUsuario, UsuarioModel usuario);
	
	public UsuarioModel buscarUsuario(Integer idUsuario);
	
	public ArrayList<UsuarioModel> buscarTodosUsuarios();
	
	
}
