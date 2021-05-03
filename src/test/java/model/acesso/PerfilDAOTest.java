package model.acesso;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

/**
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public class PerfilDAOTest {

	@Test
	public void testeCriacaoPerfilVazio() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.criarPerfilVazio("RH");
	}
	
	@Test
	public void deletarPerfilNoBancoDeDados() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.deletarPerfil(2);
	}
	
	@Test
	public void atualizarPerfilNoBancoDeDados() {
		PerfilDAO perfilDAO = new PerfilDAO();
		PerfilModel pm = new PerfilModel("Teste", LocalDate.of(2015,5,25), LocalDate.of(2022, 4, 13), true);
		perfilDAO.atualizarPerfil(3, pm);
	}
	
	
	@Test
	public void verificaSeOcorreACriacaoDeUmPerfilVazioDAO() {

		PerfilDAO perfilDAO = new PerfilDAO();
		PerfilModel perfilEsperado = new PerfilModel(10, "Ponto", null);

		Integer idDoPerfil = 10;
		String nomeDoPerfil = "Ponto";
		
		PerfilModel perfilCriado = perfilDAO.criarPerfilVazio(idDoPerfil, nomeDoPerfil);

		assertEquals(perfilEsperado.getIdDoPerfil(), perfilCriado.getIdDoPerfil());
		assertEquals(perfilEsperado.getNomeDoPerfil(), perfilCriado.getNomeDoPerfil());
	}

	@Test
	public void verificaSeOcorreACriacaoDeDoisPerfisVaziosDAO() {

		PerfilDAO perfilDAO = new PerfilDAO();

		Integer idDoPerfil1 = 10;
		String nomeDoPerfil1 = "Ponto";

		Integer idDoPerfil2 = 20;
		String nomeDoPerfil2 = "Férias";

		perfilDAO.criarPerfilVazio(idDoPerfil1, nomeDoPerfil1);
		perfilDAO.criarPerfilVazio(idDoPerfil2, nomeDoPerfil2);

		assertEquals(2, perfilDAO.lerListaDePerfisCriados().size());
	}

	
	@Test
	public void verificaSeOPerfilVazioFoiExcluidoDAO() {
		
		PerfilDAO perfilDAO = new PerfilDAO();

		Integer idDoPerfil1 = 10;
		String nomeDoPerfil1 = "Ponto";
		
		perfilDAO.criarPerfilVazio(idDoPerfil1, nomeDoPerfil1);
		perfilDAO.deletarPerfil(idDoPerfil1);
		
		assertEquals(0, perfilDAO.lerListaDePerfisCriados().size());
	}
	
	@Test
	public void verificaSeONomeDoPerfilVazioFoiAlteradoDAO() {
		PerfilDAO perfilDAO = new PerfilDAO();
		
		Integer idDoPerfil = 10;
		String nomeDoPerfilAntigo = "Ponto";
		String nomeDoPerfilNovo = "Cadastro";
		
		perfilDAO.criarPerfilVazio(idDoPerfil, nomeDoPerfilAntigo);
		PerfilModel perfilAlterado = perfilDAO.alterarNomePerfil(idDoPerfil, nomeDoPerfilNovo);
		
		assertEquals(nomeDoPerfilNovo, perfilAlterado.getNomeDoPerfil());
		
		
	}
	
	@Test
	public void verificaSeADataDeInicioDoPerfilFoiAlteradaDAO() {
		PerfilDAO perfilDAO = new PerfilDAO();
		Integer idDoPerfil = 1;
		LocalDate data = LocalDate.of(2020, 3, 16);
		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
		PerfilModel perfilAlterado = perfilDAO.alterarInicioValidadePerfil(idDoPerfil, data);
		assertEquals(data, perfilAlterado.getInicioValidadePerfil());
	}
	
	@Test
	public void verificaSeADataDeFimDoPerfilFoiAlteradaDAO() {
		PerfilDAO perfilDAO = new PerfilDAO();
		Integer idDoPerfil = 1;
		LocalDate data = LocalDate.of(2020, 3, 16);
		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
		PerfilModel perfilAlterado = perfilDAO.alterarFimValidadePerfil(idDoPerfil, data);
		assertEquals(data, perfilAlterado.getFimValidadePerfil());
	}
	
	@Test
	public void verificaSeOEstadoDeAtividadeDoPerfilFoiAlteradoDAO() {
		PerfilDAO perfilDAO = new PerfilDAO();
		Integer idDoPerfil = 1;
		boolean estadoPerfil = true;
		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
		PerfilModel perfilAlterado = perfilDAO.alterarPerfilAtivo(idDoPerfil, estadoPerfil);
		assertEquals(estadoPerfil, perfilAlterado.isPerfilAtivo());
	}
	
}
