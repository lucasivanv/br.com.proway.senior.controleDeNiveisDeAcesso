package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.acesso.PerfilDAO;
import model.acesso.PerfilModel;
import model.acesso.PermissaoDAO;
import model.acesso.PermissaoModel;

/**
 * Classe PerfilController
 * 
 * Classe responsável pelas validações e verificações das entradas e saídas
 * 
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public class PerfilController {

	private PerfilDAO dao = new PerfilDAO();
	
	public PerfilDAO getDao() {
		return dao;
	}

	public void setDao(PerfilDAO dao) {
		this.dao = dao;
	}

	/**
	 * Método criarPerfilVazioController
	 * 
	 * Método responsável pela criação do perfil vazio, verificando previamente se o
	 * mesmo já existe na lista de perfis cadastrados.
	 * 
	 * @param idDoPerfil   Integer
	 * @param nomeDoPerfil String
	 * @return boolean
	 */
	public boolean criarPerfilVazioController(String nomeDoPerfil) {

		if(dao.buscarTodosOsPerfis() == null || dao.buscarTodosOsPerfis().size() == 0) {
			dao.criarPerfilVazio(nomeDoPerfil);
			return true;
		} else {
			if (dao.buscarPerfil(nomeDoPerfil) == null) {
				dao.criarPerfilVazio(nomeDoPerfil);
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Método criarPerfilVazioController
	 * 
	 * Método responsável pela criação do perfil vazio, verificando previamente se o
	 * mesmo já existe na lista de perfis cadastrados.
	 * 
	 * @param idDoPerfil   Integer
	 * @param nomeDoPerfil String
	 * @return boolean
	 */
	public boolean criarPerfilVazioTemporarioController(PerfilModel perfilModel) {

		if(dao.buscarTodosOsPerfis() == null || dao.buscarTodosOsPerfis().size() == 0) {
			dao.criarPerfilVazioTemporario(perfilModel);
			return true;
		} else {
			if (dao.buscarPerfil(perfilModel.getNomeDoPerfil()) == null) {
				dao.criarPerfilVazioTemporario(perfilModel);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Método deletarPerfilController
	 * 
	 * Método realiza a exclusão do perfil conforme id informado
	 * 
	 * @param idDoPerfil Integer
	 * @return boolean
	 */
	public boolean deletarPerfilController(Integer idDoPerfil) {
		return dao.deletarPerfil(idDoPerfil);
	}
	
	/**
	 * Método deletarPerfilController
	 * 
	 * Método realiza a exclusão do perfil conforme nomeInformado
	 * 
	 * @param idDoPerfil Integer
	 * @return void
	 */
	public boolean deletarPerfilController(String nomeDoPerfil) {
		return dao.deletarPerfil(nomeDoPerfil);
	}
	
	/**
	 * Método buscarPerfil
	 * 
	 * Método retorna o perfil que possui o id especificado
	 * 
	 * @param id Integer
	 * @return PerfilModel
	 */
	public PerfilModel buscarPerfil(Integer id) {
		return dao.buscarPerfil(id);
	}
	
	/**
	 * Método buscarPerfil
	 * 
	 * Método retorna o perfil que possui o nome especificado
	 * 
	 * @param nomeDoPerfil String
	 * @return PerfilModel
	 */
	public PerfilModel buscarPerfil(String nomeDoPerfil) {
		return dao.buscarPerfil(nomeDoPerfil);
	}

	/**
	 * Método buscarTodosOsPerfis
	 * 
	 * Método retorna a lista de perfis criados
	 * 
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> buscarTodosOsPerfis() {
		return dao.buscarTodosOsPerfis();
	}

	/**
	 * Método alterarNomePerfilController
	 * 
	 * Método realiza a alteração do nome de um perfil conforme id informado
	 * 
	 * @param idDoPerfil     Integer
	 * @param novoNomePerfil String
	 * @return boolean
	 */
	public boolean alterarNomePerfilController(Integer idDoPerfil, String novoNomePerfil) {
		PerfilModel perfilModel = this.buscarPerfil(idDoPerfil);
		if(!(perfilModel == null)) {
			perfilModel.setNomeDoPerfil(novoNomePerfil);
			return dao.atualizarPerfil(idDoPerfil, perfilModel);
		} 
		return false;
	}

	/**
	 * Método alterarDataInicioPerfilController
	 * 
	 * Método realiza a alteração da data de inicio de um perfil conforme id informado
	 * 
	 * @param idDoPerfil     Integer
	 * @param novaDataInicio LocalDate
	 * @return boolean
	 */
	public boolean alterarDataInicioPerfilController(Integer idDoPerfil, LocalDate novaDataInicio) {
		PerfilModel perfilModel = this.buscarPerfil(idDoPerfil);
		if(!(perfilModel == null)) {
			perfilModel.setInicioValidadePerfil(novaDataInicio);
			return dao.atualizarPerfil(idDoPerfil, perfilModel);
		} 
		return false;
		
	}
	/**
	 * Método alterarDataFimPerfilController
	 * 
	 * Método realiza a alteração da data fim de validade de um perfil conforme id informado
	 * 
	 * @param idDoPerfil     Integer
	 * @param novaDataFim LocalDate
	 * @return boolean
	 */
	public boolean alterarDataFimPerfilController(Integer idDoPerfil, LocalDate novaDataFim) {
		PerfilModel perfilModel = this.buscarPerfil(idDoPerfil);
		if(!(perfilModel == null)) {
			perfilModel.setFimValidadePerfil(novaDataFim);
			return dao.atualizarPerfil(idDoPerfil, perfilModel);
		} 
		return false;
		
	}
	/**
	 * Método alterarStatusPerfilController
	 * 
	 * Método realiza a alteração do status de um perfil conforme id informado
	 * 
	 * @param idDoPerfil     Integer
	 * @param statusPerfil boolean
	 * @return boolean
	 */
	public boolean alterarStatusPerfilController(Integer idDoPerfil, boolean statusPerfil) {
		PerfilModel perfilModel = this.buscarPerfil(idDoPerfil);
		if(!(perfilModel == null)) {
			perfilModel.setPerfilAtivo(statusPerfil);
			return dao.atualizarPerfil(idDoPerfil, perfilModel);
		} 
		return false;
	}
	
	/**
	 * Método alterarStatusPerfilController
	 * 
	 * Método realiza a alteração do status de um perfil conforme id informado
	 * 
	 * @param idDoPerfil     Integer
	 * @param statusPerfil boolean
	 * @return boolean
	 */
	public boolean atualizarPerfilController(Integer idDoPerfil, PerfilModel novoPerfil) {
		PerfilModel perfilModel = this.buscarPerfil(idDoPerfil);
		if(!(perfilModel == null)) {
			return dao.atualizarPerfil(idDoPerfil, novoPerfil);
		} 
		return false;
	}
	
//	/**
//	 * Método adicionarPermissaoEmUmPerfil
//	 * 
//	 * Método adiciona uma permissão a um perfil, com base nos seus respectivos id's
//	 * 
//	 * @param idDoPerfil
//	 * @param idDaPermissao
//	 * @return void
//	 */
//	public void adicionarPermissaoEmUmPerfil(Integer idDoPerfil, Integer idDaPermissao) {
//		PermissaoDAO permissaoDAO = new PermissaoDAO();
//
//		ArrayList<PermissaoModel> listaDePermissoesDoPerfil = dao.buscarPerfil(idDoPerfil)
//				.getListaDePermissoesDoPerfil();
//
//		listaDePermissoesDoPerfil.add(permissaoDAO.buscarPermissao(idDaPermissao));
//	}
//
//	
//	/**
//	 * Método deletarPermissaoEmUmPerfil
//	 * 
//	 * Método remove uma permissão de um perfil, com base nos seus respectivos id's
//	 * 
//	 * @param idDoPerfil
//	 * @param idDaPermissao
//	 * @return void
//	 */
//	public void deletarPermissaoEmUmPerfil(Integer idDoPerfil, Integer idDaPermissao) {
//		PermissaoDAO permissaoDAO = new PermissaoDAO();
//
//		ArrayList<PermissaoModel> listaDePermissoesDoPerfil = dao.buscarPerfil(idDoPerfil)
//				.getListaDePermissoesDoPerfil();
//
//		listaDePermissoesDoPerfil.remove(permissaoDAO.buscarPermissao(idDaPermissao));
//	}
//
//	/**
//	 * Método listarPermissoesDeUmPerfil
//	 * 
//	 * Método retorna uma lista de permissões atribuídas a um perfil
//	 * 
//	 * @param idDoPerfil
//	 * @return ArrayList<PermissaoModel>
//	 */
//	public ArrayList<PermissaoModel> listarPermissoesDeUmPerfil(Integer idDoPerfil) {
//		return dao.buscarPerfil(idDoPerfil).getListaDePermissoesDoPerfil();
//	}
	
}
