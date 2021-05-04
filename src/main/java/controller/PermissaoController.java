package controller;

import java.util.ArrayList;

import model.acesso.PermissaoDAO;
import model.acesso.PermissaoModel;

/**
 * Classe PermissaoController
 * 
 * Classe responsável pelas validações e verificações das entradas e saídas
 * 
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 *
 */

public class PermissaoController {

	private PermissaoDAO dao = new PermissaoDAO();
	
	public PermissaoDAO getDao() {
		return dao;
	}

	public void setDao(PermissaoDAO dao) {
		this.dao = dao;
	}

	/**
	 * Método criarPermissaoController
	 * 
	 * Método responsável pela criação da permissão, verificando previamente se a mesma 
	 * já existe na lista de permissões cadastradas. 
	 * 
	 * @param idDaPermissao Integer
	 * @param nomeDaPermissao String
	 * @return boolean
	 */
	public boolean criarPermissaoController(String nomeDaPermissao) {
		ArrayList<PermissaoModel> permissaoModelLista = dao.buscarTodasAsPermissoes();		
		for (PermissaoModel permissaoModel : permissaoModelLista) {
			if(permissaoModel.getNomeDaPermissao().equals(nomeDaPermissao)) {
				return false;
			}
		} 
		dao.criarPermissao(nomeDaPermissao);
		return true;
	}
	
	/**
	 * Método deletarPermissaoController
	 * 
	 * Método realiza a exclusão da permissão conforme id informado
	 * 
	 * @param idDaPermissao Integer
	 * @return void
	 */
	public boolean deletarPermissaoController(Integer idDaPermissao) {
		return dao.deletarPermissao(idDaPermissao);
	}
	
	/**
	 * Método deletarPermissaoController
	 * 
	 * Método realiza a exclusão da permissão conforme nome informado
	 * 
	 * @param idDaPermissao Integer
	 * @return void
	 */
	public void deletarPermissaoController(String nomeDaPermissao) {
		dao.deletarPermissao(nomeDaPermissao);
	}

	/**
	 * Método buscarTodasAsPermissoes
	 * 
	 * Método retorna a lista de permissões criadas
	 * 
	 * @return ArrayList<PermissaoModel>
	 */
	public ArrayList<PermissaoModel> buscarTodasAsPermissoes() {
		return dao.buscarTodasAsPermissoes();
	}
	
	/**
	 * Método buscarPermissao
	 * 
	 * Método retorna a permissao com o nome indicado
	 * @param nome String
	 * @return PermissaoModel
	 */
	public PermissaoModel buscarPermissao(String nome) {
		return dao.buscarPermissao(nome);
	}
	
	/**
	 * Método buscarPermissao
	 * 
	 * Método retorna a permissao com o id indicado
	 * @param id Integer
	 * @return PermissaoModel
	 */
	public PermissaoModel buscarPermissao(Integer id) {
		return dao.buscarPermissao(id);
	}
	
	/**
	 * Método atualizarPermissao
	 * 
	 * Atualiza uma permissão com base no id e na permissão atualizada.
	 * 
	 * @param id Integer
	 * @param novaPermissao PermissaoModel
	 * @return boolean
	 */
	public boolean atualizarPermissao(Integer id, PermissaoModel novaPermissao) {
		return dao.atualizarPermissao(id, novaPermissao);
	}
}
