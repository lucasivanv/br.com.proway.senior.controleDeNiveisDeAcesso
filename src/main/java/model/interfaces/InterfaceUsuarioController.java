package model.interfaces;

import model.acesso.PermissaoModel;
import model.acesso.UsuarioModel;

/**
 * @author Sprint 3
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 */
public interface InterfaceUsuarioController {

	
	public boolean criarUsuario(String hashSenha, String login);
	
	public boolean deletarUsuario(Integer idDUsuario);
	
	public boolean deletarUsuarioLogin(String login);
	
	public UsuarioModel buscarUsuario(Integer idUsuario);
	
	
	
	public boolean validarEmail(String email);

	public boolean validarSenha(String senha);

	public String enviarEmail(String loginDoUsuario);

	public boolean alteraSenha(Integer id, String senhaNova);

}
