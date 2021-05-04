package model.acesso;

import java.util.ArrayList;

/**
 * Classe UsuarioModel
 * 
 * Recebe os atributos necessários para instanciar um usuario
 * 
 * @author Sprint 3
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 * 
 * @author Sprint 4
 * @author Elton Oliveira, elton.oliveira@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Vitor Gonçalves, vitor.goncalves@senior.com.br
 * @author Vitor Gehrke, vitor.gehrke@senior.com.br
 */
public class UsuarioModel {

	
	private int idUsuario;
	private String loginDoUsuario;
	private String hashSenhaDoUsuario;
	
	/**
	 * Modelo para o banco de dados na tabela usuariostabela
	 * @param hashSenhaDoUsuario
	 * @param loginDoUsuario
	 */
	public UsuarioModel(String hashSenhaDoUsuario, String loginDoUsuario) {
		this.hashSenhaDoUsuario = hashSenhaDoUsuario;
		this.loginDoUsuario = loginDoUsuario;
	}
		
	public UsuarioModel(int idUsuario, String hashSenhaDoUsuario, String loginDoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.loginDoUsuario = loginDoUsuario;
		this.hashSenhaDoUsuario = hashSenhaDoUsuario;
	}
	
	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", loginDoUsuario=" + loginDoUsuario + ", hashSenhaDoUsuario="
				+ hashSenhaDoUsuario + "]";
	}

	public UsuarioModel() {
		 
	}

	public int getIdDoUsuario() {
		return idUsuario;
	}

	public void setIdDoUsuario(int id) {
		this.idUsuario = id;
	}	

	public String getLoginDoUsuario() {
		return loginDoUsuario;
	}

	public void setLoginDoUsuario(String login) {
		this.loginDoUsuario = login;
	}

	public String getHashSenhaDoUsuario() {
		return hashSenhaDoUsuario;
	}

	public void setHashSenhaDoUsuario(String senha) {
		this.hashSenhaDoUsuario = senha;
	}
}
