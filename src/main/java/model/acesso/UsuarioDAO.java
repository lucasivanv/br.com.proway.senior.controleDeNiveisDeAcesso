package model.acesso;

import java.util.ArrayList;
import model.interfaces.InterfaceUsuarioDAO;

/**
 * Classe UsuarioDAO
 * 
 * Classe que implementa a interface que se relaciona com o banco de dados de
 * usuarios
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

public class UsuarioDAO implements InterfaceUsuarioDAO<UsuarioModel> {

	public ArrayList<UsuarioModel> user = new ArrayList<UsuarioModel>();

	/**
	 * Cria usuário novo.
	 * 
	 * Recebe objeto e adiciona no ArrayList<Usuario> user da classe UsuarioDAO
	 * 
	 * @param UsuarioModel novoUser como parâmetro para o DAO adicionar na lista de
	 *                     usuarios.
	 * @return void
	 */
	public void create(UsuarioModel novoUsuario) {
		UsuarioModel user = novoUsuario;
		this.user.add(user);
	}

	/**
	 * Retorna objeto usuário da lista de usuários
	 * 
	 * @param int id do usuário que quer buscar
	 * @return retorna objeto Usuario da lista de usuarios.
	 */
	public UsuarioModel get(int id) {
		for (int i = 0; i < this.user.size(); i++) {
			if (this.user.get(i).getIdDoUsuario() == id) {
				return this.user.get(i);

			}
		}
		return null;
	}

	/**
	 * Atualiza objeto usuario na lista de usuarios da classe UsuarioDAO
	 * 
	 * @param UsuarioModel usuario atualizado que substitui objeto na lista de
	 *                     usuarios
	 * @return Boolean true apos atualizacao
	 */
	public boolean update(UsuarioModel usuarioAtualizado) {
		user.set(usuarioAtualizado.getIdDoUsuario(), usuarioAtualizado);
		return true;
	}

	/**
	 * Retorna lista de todos os usuarios da ArrayList de usuarios
	 * 
	 * @return ArrayList<Usuario>
	 */
	public ArrayList<UsuarioModel> getAll() {

		return user;
	}

	/**
	 * Método que remove usuario da lista de usuarios.
	 * 
	 * Recebe id do usuario a ser removido, percorre arraylist e remove pelo id
	 * igual.
	 * 
	 * @param int id do usuario a ser removido.
	 * @return void *
	 */
	public void remove(int id) {
		for (int i = 0; i < user.size(); i++) {
			if (user.get(i).getIdDoUsuario() == id) {
				user.remove(i);
			}
		}

	}

}
