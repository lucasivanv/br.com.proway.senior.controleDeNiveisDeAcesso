package model.acesso;

import java.util.ArrayList;

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
