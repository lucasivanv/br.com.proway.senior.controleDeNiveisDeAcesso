package model.acesso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public class PerfilDAOTest {

	@Test
	public void criarPerfilVazioNoBancoDeDados() {
		PerfilDAO perfilDAO = new PerfilDAO();
		
		perfilDAO.db.limparDB("perfilTabela");
		perfilDAO.criarPerfilVazio("Recursos Humanos");
		
		try {
			ResultSet rs = perfilDAO.db.executeQuery("select * from perfilTabela");
			if(rs.next()) {
				assertEquals("Recursos Humanos", rs.getString(2));
			}
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void criarPerfilVazioTemporario() {
		PerfilDAO perfilDAO = new PerfilDAO();		
		perfilDAO.db.limparDB("perfilTabela");
		
		PerfilModel perfil = new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false);
		perfilDAO.criarPerfilVazioTemporario(perfil);
		
		try {
			ResultSet rs = perfilDAO.db.executeQuery("select * from perfilTabela");
			if(rs.next()) {
				assertEquals("Recursos Humanos", rs.getString(2));
				assertEquals(LocalDate.of(2000, 05, 03).toString(), rs.getString(3));
				assertEquals(LocalDate.of(2000, 05, 03).toString(), rs.getString(3));
				assertEquals(LocalDate.of(2000, 05, 03).toString(), rs.getString(4));
				assertEquals("f", rs.getString(5));
			}
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}		
	}
	
	@Test
	public void deletarPerfilNoBancoDeDados() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");
		
		perfilDAO.criarPerfilVazio("Recursos Humanos");
		perfilDAO.criarPerfilVazio("Financeiro");
		
		int i = 0;
		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idPerfil) from perfilTabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			perfilDAO.deletarPerfil(i);
			
			rs = perfilDAO.db.executeQuery("select * from perfilTabela");	
			rs.next();
			
			assertTrue(Integer.parseInt(rs.getString(1)) < i);		
			assertEquals(rs.getString(2), "Recursos Humanos");	
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void atualizarPerfilNoBancoDeDados() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");
		
		PerfilModel perfil = new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false);
		perfilDAO.criarPerfilVazioTemporario(perfil);
		
		PerfilModel perfilAtualizado = new PerfilModel("RH", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false);
		
		int i = 0;
		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idperfil) from perfilTabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			perfilDAO.atualizarPerfil(i, perfilAtualizado);
			assertEquals(perfilDAO.buscarPerfil(i).getNomeDoPerfil(), "RH");
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
		

	@Test
	public void buscarPerfil() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");
		
		PerfilModel perfil = new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false);
		perfilDAO.criarPerfilVazioTemporario(perfil);

		int i = 0;
		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idperfil) from perfilTabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			PerfilModel pm = perfilDAO.buscarPerfil(i);
			
			assertTrue(perfil.getNomeDoPerfil().equals(pm.getNomeDoPerfil()));		
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void buscarPerfilPornomePerfil() {

		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");
		
		PerfilModel perfil = new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false);
		perfilDAO.criarPerfilVazioTemporario(perfil);

		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idperfil) from perfilTabela");
			if(rs.next()) {
			}
			PerfilModel pm = perfilDAO.buscarPerfilPorNomePerfil(perfil.getNomeDoPerfil());
			
			assertTrue(perfil.getNomeDoPerfil().equals(pm.getNomeDoPerfil()));		
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}

	
	@Test
	public void buscarPerfilPorStatus() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");

		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Cadastro", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), true));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Férias", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Controle de Ponto", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Financeiro", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), true));

		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idperfil) from perfilTabela");
			if(rs.next()) {
			}
			ArrayList<PerfilModel> pmLista = perfilDAO.buscarPerfilPorStatus(false);
			
			assertEquals(pmLista.size(), 3);
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void buscarTodasAsPerfil() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.db.limparDB("perfilTabela");

		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Recursos Humanos", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Cadastro", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), true));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Férias", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Controle de Ponto", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), false));
		perfilDAO.criarPerfilVazioTemporario(new PerfilModel("Financeiro", LocalDate.of(2000, 05, 03), LocalDate.of(2000, 05, 03), true));

		try {
			ResultSet rs = perfilDAO.db.executeQuery("select max(idperfil) from perfilTabela");
			if(rs.next()) {
			}
			ArrayList<PerfilModel> pmLista = perfilDAO.buscarTodasAsPerfil();
			
			assertEquals(pmLista.size(), 5);
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	
//	@Test
//	public void verificaSeOcorreACriacaoDeUmPerfilVazioDAO() {
//
//		PerfilDAO perfilDAO = new PerfilDAO();
//		PerfilModel perfilEsperado = new PerfilModel(10, "Ponto", null);
//
//		Integer idDoPerfil = 10;
//		String nomeDoPerfil = "Ponto";
//		
//		PerfilModel perfilCriado = perfilDAO.criarPerfilVazio(idDoPerfil, nomeDoPerfil);
//
//		assertEquals(perfilEsperado.getIdDoPerfil(), perfilCriado.getIdDoPerfil());
//		assertEquals(perfilEsperado.getNomeDoPerfil(), perfilCriado.getNomeDoPerfil());
//	}
//
//	@Test
//	public void verificaSeOcorreACriacaoDeDoisPerfisVaziosDAO() {
//
//		PerfilDAO perfilDAO = new PerfilDAO();
//
//		Integer idDoPerfil1 = 10;
//		String nomeDoPerfil1 = "Ponto";
//
//		Integer idDoPerfil2 = 20;
//		String nomeDoPerfil2 = "Férias";
//
//		perfilDAO.criarPerfilVazio(idDoPerfil1, nomeDoPerfil1);
//		perfilDAO.criarPerfilVazio(idDoPerfil2, nomeDoPerfil2);
//
//		assertEquals(2, perfilDAO.lerListaDePerfisCriados().size());
//	}
//
//	
//	@Test
//	public void verificaSeOPerfilVazioFoiExcluidoDAO() {
//		
//		PerfilDAO perfilDAO = new PerfilDAO();
//
//		Integer idDoPerfil1 = 10;
//		String nomeDoPerfil1 = "Ponto";
//		
//		perfilDAO.criarPerfilVazio(idDoPerfil1, nomeDoPerfil1);
//		perfilDAO.deletarPerfil(idDoPerfil1);
//		
//		assertEquals(0, perfilDAO.lerListaDePerfisCriados().size());
//	}
//	
//	@Test
//	public void verificaSeONomeDoPerfilVazioFoiAlteradoDAO() {
//		PerfilDAO perfilDAO = new PerfilDAO();
//		
//		Integer idDoPerfil = 10;
//		String nomeDoPerfilAntigo = "Ponto";
//		String nomeDoPerfilNovo = "Cadastro";
//		
//		perfilDAO.criarPerfilVazio(idDoPerfil, nomeDoPerfilAntigo);
//		PerfilModel perfilAlterado = perfilDAO.alterarNomePerfil(idDoPerfil, nomeDoPerfilNovo);
//		
//		assertEquals(nomeDoPerfilNovo, perfilAlterado.getNomeDoPerfil());
//		
//		
//	}
//	
//	@Test
//	public void verificaSeADataDeInicioDoPerfilFoiAlteradaDAO() {
//		PerfilDAO perfilDAO = new PerfilDAO();
//		Integer idDoPerfil = 1;
//		LocalDate data = LocalDate.of(2020, 3, 16);
//		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
//		PerfilModel perfilAlterado = perfilDAO.alterarInicioValidadePerfil(idDoPerfil, data);
//		assertEquals(data, perfilAlterado.getInicioValidadePerfil());
//	}
//	
//	@Test
//	public void verificaSeADataDeFimDoPerfilFoiAlteradaDAO() {
//		PerfilDAO perfilDAO = new PerfilDAO();
//		Integer idDoPerfil = 1;
//		LocalDate data = LocalDate.of(2020, 3, 16);
//		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
//		PerfilModel perfilAlterado = perfilDAO.alterarFimValidadePerfil(idDoPerfil, data);
//		assertEquals(data, perfilAlterado.getFimValidadePerfil());
//	}
//	
//	@Test
//	public void verificaSeOEstadoDeAtividadeDoPerfilFoiAlteradoDAO() {
//		PerfilDAO perfilDAO = new PerfilDAO();
//		Integer idDoPerfil = 1;
//		boolean estadoPerfil = true;
//		perfilDAO.criarPerfilVazio(idDoPerfil, "RH");
//		PerfilModel perfilAlterado = perfilDAO.alterarPerfilAtivo(idDoPerfil, estadoPerfil);
//		assertEquals(estadoPerfil, perfilAlterado.isPerfilAtivo());
//	}
	
}
