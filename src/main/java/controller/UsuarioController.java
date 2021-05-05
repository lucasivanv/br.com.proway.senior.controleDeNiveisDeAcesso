package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.acesso.HashSenha;
import model.acesso.PerfilModel;
import model.acesso.PermissaoModel;
import model.acesso.UsuarioModel;
import model.acesso.UsuarioDAO;
import model.interfaces.InterfaceUsuarioController;

/**
 * Classe UsuarioController
 * 
 * Classe respons�vel pelas valida��es e verifica��es das entradas e sa�das
 * 
 * @author Sprint 3
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 * 
 * @author Sprint 4
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 */
public class UsuarioController implements InterfaceUsuarioController {

	public UsuarioDAO daoUsuario = new UsuarioDAO();
	
	/**
	 * Verifica se os endere�os de email foram cadastrados corretamente ou se
	 * possuem caracteres especiais.
	 * 
	 * A vari�vel expression relaciona os caracteres que ser�o buscados dentro da
	 * vari�vel email. O m�todo matcher() � empregado para procurar um padr�o na
	 * string, retornando um objeto Matcher que cont�m informa��es sobre a pesquisa
	 * realizada.
	 * 
	 * @param String email
	 * @return isValidaEmail
	 * 
	 */	
	public boolean validarEmail(String email) {
		boolean emailValido = false;
		if (email != null && email.length() > 0) {
			String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				emailValido = true;
			}
		}
		return emailValido;
	}

	/**
	 * Verifica se a senha corresponde aos pre requisitos da expressao. 
	 *
	 * @param String senha
	 * @return boolean
	 */
	
	public boolean validarSenha(String senha) {
		boolean senhaValida = false;
		if (senha != null && senha.length() > 0) {
			String expressao = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,24}";
			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(senha);
			if (matcher.matches()) {
				senhaValida = true;
			}
		}
		return senhaValida;
	}
	
	/**
	 * Recebe a String correspondente � senha real do usu�rio e retorna o valor codificado (hash) dessa senha.
	 * 
	 * @param senha String
	 * @return String 
	 */
	public String converterSenhaEmHashSenha(String senha) {
		return HashSenha.senhaDoUsuario(senha);
	}
	
	/**
	 * Compara a hash da senha fornecida pelo usu�rio com a hash salva.
	 * 
	 * @param usuarioID Integer
	 * @param senha String
	 * @return boolean
	 */
	public boolean verificarHashSenha(Integer usuarioID, String senha) {		
	
		if(this.converterSenhaEmHashSenha(senha).equals(daoUsuario.buscarUsuario(usuarioID).getHashSenhaDoUsuario())) {
			return true;
		}
		return false;
	}	
	
	/**
	 * M�todo que altera a senha do usuario
	 * 
	 * Verifica se a nova senha atende os crit�rios de senha segura e se a nova senha � diferente da senha atual.
	 * Recebe id e nova senha para alterar
	 * 
	 * @param int id do usuario procurado para altera�ao
	 * @param String senhaNova do usuario
	 */
	public boolean alteraSenha(Integer usuarioID, String senhaNova) {

		if (this.validarSenha(senhaNova) && !this.verificarHashSenha(usuarioID, senhaNova)) {

			daoUsuario.buscarUsuario(usuarioID).setHashSenhaDoUsuario(this.converterSenhaEmHashSenha(senhaNova));

			daoUsuario.atualizarUsuario(usuarioID, daoUsuario.buscarUsuario(usuarioID));
			return true;
		}
		return false;
	}
	
	/**
	 * M�todo que altera o login do usuario
	 * 
	 * Recebe id e novo login para alterar
	 * 
	 * @param int id do usuario procurado para altera�ao
	 * @param String loginNovo do usuario
	 */
	public boolean alteraLogin(Integer usuarioID, String loginNovo) {
		
		if(this.validarEmail(loginNovo)) {

			if(!daoUsuario.buscarUsuario(usuarioID).getLoginDoUsuario().equals(loginNovo)){
				
				daoUsuario.buscarUsuario(usuarioID).setHashSenhaDoUsuario(loginNovo);
				
				daoUsuario.atualizarUsuario(usuarioID, daoUsuario.buscarUsuario(usuarioID));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo que altera perfil do usuario.
	 * 
	 * Recebe id do usuario, e perfil novo para alterar
	 * 
	 * @param int id do usuario procurado para a troca de perfil
	 * @param Perfil perfilNovo do usuario que vai ter o perfil trocado.
	 */
	
//	@Deprecated
//	public void alteraPerfil(int id, ArrayList<PerfilModel> listaPerfil) {
//	
//		UsuarioModel usuarioEscolhido = daoUsuario.get(id);
//		usuarioEscolhido.setListaDePerfisDoUsuario(listaPerfil);
//		daoUsuario.update(usuarioEscolhido);
//	}
	
	/**
	 *  Cria um usu�rio.
	 *  
	 *  Recebe as informa��es de um usu�rio, transforma a senha em hash e cria um objeto UsuarioModel
	 *  @param idDoUsuario int
	 *  @param loginDoUsuario String
	 *  @param senha String
	 *  @return boolean
	 */
	public boolean criarUsuarioController(Integer idDoUsuario, String loginDoUsuario, String senha) {
				
		for (UsuarioModel usuarioModel : daoUsuario.buscarTodosUsuarios()) {
			if(usuarioModel.getLoginDoUsuario().equals(loginDoUsuario)) {
				return false;
			}
		} 
		daoUsuario.criarUsuario(this.converterSenhaEmHashSenha(senha), loginDoUsuario);
		return true;
	}
	
	/**
	 *  Deleta um usu�rio de acordo com o login informado  
	 *  
	 *  @param idDoUsuario int
	 *  @param loginDoUsuario String
	 *  @return boolean
	 */
	public boolean deletarUsuarioController(Integer idDoUsuario, String loginDoUsuario) {
		
		for (UsuarioModel usuarioModel : daoUsuario.buscarTodosUsuarios()) {
			if (usuarioModel.getLoginDoUsuario().equals(loginDoUsuario)) {
				daoUsuario.deletarUsuario(idDoUsuario);
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Busca um usu�rio de acordo com o login informado  
	 *  
	 *  @param idDoUsuario int
	 *  @param loginDoUsuario String
	 *  @return UsuarioModel
	 */
	public UsuarioModel buscarUsuarioController(Integer idDoUsuario, String loginDoUsuario) {
		
		for (UsuarioModel usuarioModel : daoUsuario.buscarTodosUsuarios()) {
			if (usuarioModel.getLoginDoUsuario().equals(loginDoUsuario)) {
				return daoUsuario.buscarUsuario(idDoUsuario);
			}
		}
		return null;
	}
	
	/**
	 *  Busca todos os usuarios cadastrados  
	 *  
	 *  @return ArrayList<UsuarioModel>
	 */
	public ArrayList<UsuarioModel> buscarTodosUsuariosController() {
		return daoUsuario.buscarTodosUsuarios();
	}
	
	/**
	 *  Atualiza um usu�rio de acordo com o login informado  
	 *  
	 *  @param idDoUsuario int
	 *  @param loginDoUsuario String
	 *  @return boolean
	 */
//	public boolean atualizarUsuarioController(Integer idDoUsuario, String loginDoUsuario) {
//		
//		for (UsuarioModel usuarioModel : daoUsuario.buscarTodosUsuarios()) {
//			if (usuarioModel.getLoginDoUsuario().equals(loginDoUsuario)) {
//				daoUsuario.atualizarUsuario(idDoUsuario, usuarioModel);
//				return true;
//			}
//		}
//		return false;
//	}
	
	/**
	 * Envia um e-mail
	 * 
	 * Envia o e-mail para o usu�rio com o c�digo aleat�rio gerado para a
	 * confirma��o.
	 * 
	 * @param email        Email do usu�rio
	 * @param codigoGerado C�digo aleat�rio gerado pelo sistema
	 */
	public String enviarEmail(String loginDoUsuario) {
		
		String codigo = "" + this.gerarCodigo();
		if (this.validarEmail(loginDoUsuario)) {
			// Faz conex�o com BD e envia e-mail para usu�rio
			return codigo;
		}
		return codigo;
	}	
	
	/**
	 * Gera um c�digo aleat�rio
	 * 
	 * Gera o c�gigo random para a verifica��o de usu�rio
	 * 
	 * @return codigo de 5 digitos
	 */
	public boolean gerarCodigo() {

		Random random = new Random();
		int codigo = random.nextInt(99999);
		if (codigo <= 10000) {
			codigo += 10000;
		}
		return true;
	}
	
}
