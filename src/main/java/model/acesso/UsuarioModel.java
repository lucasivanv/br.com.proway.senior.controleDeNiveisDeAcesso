package model.acesso;

import java.util.ArrayList;

public class UsuarioModel {

	private int idDoUsuario;
	private String loginDoUsuario;
	private String hashSenhaDoUsuario;
	private ArrayList<PerfilModel> listaDePerfisDoUsuario = new ArrayList<PerfilModel>();

	public UsuarioModel(int idDoUsuario, String loginDoUsuario, String hashSenhaDoUsuario,
			ArrayList<PerfilModel> listaDePerfisDoUsuario) {
		super();
		this.idDoUsuario = idDoUsuario;
		this.loginDoUsuario = loginDoUsuario;
		this.hashSenhaDoUsuario = hashSenhaDoUsuario;
		this.listaDePerfisDoUsuario = listaDePerfisDoUsuario;
	}
	
	public UsuarioModel() {
		 
	}

	public ArrayList<PerfilModel> getListaDePerfisDoUsuario() {
		return listaDePerfisDoUsuario;
	}

	public int getIdDoUsuario() {
		return idDoUsuario;
	}

	public void setIdDoUsuario(int id) {
		this.idDoUsuario = id;
	}	

	public void setListaDePerfisDoUsuario(ArrayList<PerfilModel> listaDePerfisDoUsuario) {
		this.listaDePerfisDoUsuario = listaDePerfisDoUsuario;
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
