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
