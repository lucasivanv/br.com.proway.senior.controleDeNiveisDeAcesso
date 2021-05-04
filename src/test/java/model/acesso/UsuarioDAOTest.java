package model.acesso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


/**
 * 
 * @author Vitor Peres vitor.peres@senior.com.br
 * @author David Willian david.oliveira@senior.com.br
 * @author Leonardo Pereira leonardo.pereira@senior.com.br
 *        
 *         versão 2.0 -- implementação postgreSQL + jdbc
 * @author Elton F Oliveira elton.oliveira@senior.com.br
 * @author Vitor A Gehrke vitor.gehrke@senior.com.br
 */
public class UsuarioDAOTest {
	
	
	@Test
	public void testCriarUsuarioNoBancoDeDados() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.db.limparDB("usuariostabela");
		usuarioDAO.criarUsuario("qweqweqweqweqwe", "Ricardo");
		try {
			ResultSet rs = usuarioDAO.db.executeQuery("select * from usuariostabela");
			if(rs.next()) {
				assertEquals("qweqweqweqweqwe", "Ricardo", rs.getString(3));
			}
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void testDeletarusuarioPorLoginNoBancoDeDados() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.db.limparDB("usuariostabela");
		usuarioDAO.criarUsuario("Remover", "Elton");
		usuarioDAO.criarUsuario("Remover2", "Vitor");
		int i = 0;
		try {
			usuarioDAO.deletarUsuarioPorLogin("Elton");
			
			ResultSet rs = usuarioDAO.db.executeQuery("select * from usuariostabela");	
			rs.next();
			assertEquals(rs.getString(3), "Vitor");	
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	

	@Test
	public void testBuscarUsuarioPorNomeNoBancoDeDados() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.db.limparDB("usuariostabela");
		usuarioDAO.criarUsuario("irioweuriowueriouwer", "Joao");;
		UsuarioModel pmteste = new UsuarioModel("irioweuriowueriouwer", "Joao");
		UsuarioModel pm = usuarioDAO.buscarPorLoginUsuario("Joao");
		assertTrue(pmteste.getLoginDoUsuario().equals(pm.getLoginDoUsuario()));		
	 
	}
	
	@Test
	public void buscarTodosOsUsuariosNoBancoDeDados() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.db.limparDB("usuariostabela");
		usuarioDAO.criarUsuario("Remover", "Joao");
		usuarioDAO.criarUsuario("Adicionar", "Maria");
		usuarioDAO.criarUsuario("Mover", "José");
		UsuarioModel umteste1 = new UsuarioModel("Remover", "Joao");
		UsuarioModel umteste2 = new UsuarioModel("Adicionar", "Maria");
		UsuarioModel umteste3 = new UsuarioModel("Mover", "José");
		ArrayList<UsuarioModel> listaUMteste = new ArrayList<UsuarioModel>();
		listaUMteste.addAll(Arrays.asList(umteste1, umteste2, umteste3));
		int i = 0;
		
		ArrayList<UsuarioModel> listaUM = usuarioDAO.buscarTodosUsuarios();
		
		assertTrue(listaUMteste.get(0).getLoginDoUsuario().equals(listaUM.get(0).getLoginDoUsuario()));		
		assertTrue(listaUMteste.get(1).getLoginDoUsuario().equals(listaUM.get(1).getLoginDoUsuario()));		
		assertTrue(listaUMteste.get(2).getLoginDoUsuario().equals(listaUM.get(2).getLoginDoUsuario()));		
	}
	
	@Test
	public void testAtualizarUsuarioNoBancoDeDados() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.db.limparDB("usuariostabela");
		usuarioDAO.criarUsuario("Remover", "Carol");
		UsuarioModel pm = new UsuarioModel("qweqweqwe", "Aline");
		int i = 0;
		try {
			ResultSet rs = usuarioDAO.db.executeQuery("select max(idusuario) from usuariostabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			usuarioDAO.atualizarUsuario(i, pm);
			assertEquals(usuarioDAO.buscarUsuario(i).getLoginDoUsuario(), "Aline");
			assertEquals(usuarioDAO.buscarUsuario(i).getHashSenhaDoUsuario(), "qweqweqwe");
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	
	
	//
	
	
	@Test
	public void testeCriacaoUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.criarUsuario("uiluiluiuio", "Teste2");
	}
	
	@Test
	public void testeDeletarUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.deletarUsuario(1);
	}
	
	@Test
	public void testeAtualizarUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioModel um = new UsuarioModel("CXFSDSds", "Elton"); //String loginDoUsuario, String hashSenhaDoUsuario
		usuarioDAO.atualizarUsuario(2, um);
	}
	
	@Test
	public void testeBuscarUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioModel usuario = usuarioDAO.buscarUsuario(5);
		System.out.println(usuario.toString());
	}

	@Test
	public void testeBuscarTodosUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioModel> todosUsuarios = usuarioDAO.buscarTodosUsuarios();
		System.out.println(todosUsuarios.toString());
	}
}
