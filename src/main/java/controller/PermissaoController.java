package controller;

import java.util.ArrayList;

import model.acesso.PermissaoDAO;
import model.acesso.PermissaoModel;

/**
 * Classe PermissaoController
 * 
 * Classe respons�vel pelas valida��es e verifica��es das entradas e sa�das
 * 
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 *
 */

public class PermissaoController {

	private PermissaoDAO dao = new PermissaoDAO();
	
	/**
	 * M�todo criarPermissaoController
	 * 
	 * M�todo respons�vel pela cria��o da permiss�o, verificando previamente se a mesma 
	 * j� existe na lista de permiss�es cadastradas. 
	 * 
	 * @param idDaPermissao Integer
	 * @param nomeDaPermissao String
	 * @return boolean
	 */
	public boolean criarPermissaoController(Integer idDaPermissao, String nomeDaPermissao) {
		
		if (dao.lerListaDePermissoesCriadas().size() == 0) {
			dao.criarPermissao(idDaPermissao, nomeDaPermissao);
			return true;
		} else {
			if (dao.buscarPermissao(idDaPermissao) == null) {
				dao.criarPermissao(idDaPermissao, nomeDaPermissao);
				return true;
			} else {
				return false;
			}	
		}			
	}
	
	/**
	 * M�todo deletarPermissaoController
	 * 
	 * M�todo realiza a exclus�o da permiss�o conforme id informado
	 * 
	 * @param idDaPermissao Integer
	 * @return void
	 */
	public void deletarPermissaoController(Integer idDaPermissao) {
		dao.deletarPermissao(idDaPermissao);
	}

	/**
	 * M�todo lerListaDePermissoesCriadas
	 * 
	 * M�todo retorna a lista de permiss�es criadas
	 * 
	 * @return ArrayList<PermissaoModel>
	 */
	public ArrayList<PermissaoModel> lerListaDePermissoesCriadas() {
		return dao.lerListaDePermissoesCriadas();
	}

}
