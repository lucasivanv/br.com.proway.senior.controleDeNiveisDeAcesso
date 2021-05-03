package model.acesso;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.PermissaoController;

/**
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public class PermissaoDAOTest {
	
	@Test
	public void criarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.criarPermissao("Alterar dados");
	}
	
	@Test
	public void deletarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.deletarPermissao(1);
	}
	
	@Test
	public void buscarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		PermissaoModel pm = permissaoDAO.buscarPermissao(2);
		System.out.println(pm.toString());
	}
	
	@Test
	public void buscarTodasAsPermissaoesNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		ArrayList<PermissaoModel> pm = permissaoDAO.buscarTodasAsPermissões();
		System.out.println(pm.toString());
	}
	
	@Test
	public void atualizarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		PermissaoModel pm = new PermissaoModel("Deletar");
		permissaoDAO.atualizarPermissao(2, pm);
	}

	@Test
	public void verificaSeOcorreACriacaoDeUmaPermissaoDAO() {

		PermissaoDAO permissaoDAO = new PermissaoDAO();
		PermissaoModel permissaoEsperada = new PermissaoModel(10, "Atribuir algo");

		Integer idDaPermissao = 10;
		String nomeDaPermissao = "Atribuir algo";
		
		PermissaoModel permissaoCriada = permissaoDAO.criarPermissao(idDaPermissao, nomeDaPermissao);

		assertEquals(permissaoEsperada.getIdDaPermissao(), permissaoCriada.getIdDaPermissao());
		assertEquals(permissaoEsperada.getNomeDaPermissao(), permissaoCriada.getNomeDaPermissao());
	}

	@Test
	public void verificaSeOcorreACriacaoDeDuasPermissoesDAO() {

		PermissaoDAO permissaoDAO = new PermissaoDAO();

		Integer idDaPermissao1 = 10;
		String nomeDaPermissao1 = "Atribuir algo";

		Integer idDaPermissao2 = 20;
		String nomeDaPermissao2 = "Deletar algo";

		permissaoDAO.criarPermissao(idDaPermissao1, nomeDaPermissao1);
		permissaoDAO.criarPermissao(idDaPermissao2, nomeDaPermissao2);

		assertEquals(2, permissaoDAO.lerListaDePermissoesCriadas().size());
	}

	
	@Test
	public void verificaSeAPermissaoFoiExcluidaDAO() {
		
		PermissaoDAO permissaoDAO = new PermissaoDAO();

		Integer idDaPermissao1 = 10;
		String nomeDaPermissao1 = "Atribuir algo";
		
		permissaoDAO.criarPermissao(idDaPermissao1, nomeDaPermissao1);
		permissaoDAO.deletarPermissao(idDaPermissao1);
		
		assertEquals(0, permissaoDAO.lerListaDePermissoesCriadas().size());
		
	}

}
