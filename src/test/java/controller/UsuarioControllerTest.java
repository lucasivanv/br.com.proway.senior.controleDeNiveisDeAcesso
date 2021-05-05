package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.UsuarioController;
import model.acesso.HashSenha;
import model.acesso.PerfilDAO;
import model.acesso.PerfilModel;
import model.acesso.PermissaoModel;
import model.acesso.UsuarioDAO;
import model.acesso.UsuarioModel;

/**
 * @author Sprint 3
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 * 
 * @author Sprint 4
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 */
public class UsuarioControllerTest {
	
	@Test
	public void testeSeEmailValido() {
		UsuarioController userControl = new UsuarioController();
		UsuarioModel user = new UsuarioModel();
		user.setLoginDoUsuario("colaborador@empresa.com");
		assertTrue(userControl.validarEmail(user.getLoginDoUsuario()));
	}

	@Test
	public void testeSeEmailInvalido() {
		UsuarioController userControl = new UsuarioController();
		UsuarioModel user = new UsuarioModel();
		user.setLoginDoUsuario("colaboradorempresa.com");
		assertFalse(userControl.validarEmail(user.getLoginDoUsuario()));
	}

	@Test
	public void testeSeEmailVazio() {
		UsuarioController userControl = new UsuarioController();
		UsuarioModel user = new UsuarioModel();
		user.setLoginDoUsuario("");
		assertFalse(userControl.validarEmail(user.getLoginDoUsuario()));
	}

	@Test
	public void testeSeSenhaValida() {
		UsuarioController userControl = new UsuarioController();
		assertTrue(userControl.validarSenha("1Vaaaa"));
	}

	@Test
	public void testeSeSenhaInvalida() {
		UsuarioController userControl = new UsuarioController();
		assertFalse(userControl.validarSenha("123124321"));
	}

	@Test
	public void testeSeSenhaMenor() {
		UsuarioController userControl = new UsuarioController();
		assertFalse(userControl.validarSenha("1Pa"));
	}

	@Test
	public void testeSeSenhaMaior() {
		UsuarioController userControl = new UsuarioController();
		assertFalse(userControl.validarSenha("1Pa124126124131364346413523342433244143143131413311"));
	}
	
	@Test
	public void criarNovoUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.db.limparDB("usuariostabela");		
		
		UsuarioController userController = new UsuarioController();
		
		assertTrue(userController.criarUsuarioController("abc@test.com.br", "Aa12345678/"));
	}
	
	@Test
	public void testeFalhaAoCriarNovoUsuarioPoisSenhaInadequada() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.db.limparDB("usuariostabela");		
		
		UsuarioController userController = new UsuarioController();
		
		assertFalse(userController.criarUsuarioController("def@test.com.br", "a78/"));
	}
		
	@Test
	public void testeRemoveUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.db.limparDB("usuariostabela");
		
		UsuarioController userController = new UsuarioController();
		
		userController.criarUsuarioController("colaborador1@empresa.com.br", "Jjhdfa82!78@1");
		userController.criarUsuarioController("colaborador2@empresa.com.br", "DAMKmdsa46615#");
		userController.criarUsuarioController("colaborador3@empresa.com.br", "DAMKmsa466@");
	
		assertTrue(userController.deletarUsuarioController(userController.daoUsuario.buscarPorLoginUsuario("colaborador2@empresa.com.br").getIdDoUsuario(), "colaborador2@empresa.com.br"));		
	}
	
	@Test
	public void testeAtualizaLoginUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.db.limparDB("usuariostabela");
		
		UsuarioController userController = new UsuarioController();
		
		userController.criarUsuarioController("colaborador1@empresa.com.br", "Jjhdfa82!78@1");
		userController.criarUsuarioController("colaborador2@empresa.com.br", "DAMKmdsa46615#");
		userController.criarUsuarioController("colaborador3@empresa.com.br", "DAMKmsa466@");
	
		
		int idUsuarioAtualizar = userController.daoUsuario.buscarPorLoginUsuario("colaborador2@empresa.com.br").getIdDoUsuario();
		
		UsuarioModel usuarioAtualizar = new UsuarioModel(userController.daoUsuario.buscarPorLoginUsuario("colaborador2@empresa.com.br").getHashSenhaDoUsuario(), "atualizado2@empresa.com.br");
				
		assertTrue(userController.atualizaUsuarioController(idUsuarioAtualizar, usuarioAtualizar));		
	}	
	
	@Test
	public void testeAtualizaSenhaUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.db.limparDB("usuariostabela");
		
		UsuarioController userController = new UsuarioController();
		
		userController.criarUsuarioController("colaborador1@empresa.com.br", "Jjhdfa82!78@1");
		userController.criarUsuarioController("colaborador2@empresa.com.br", "DAMKmdsa46615#");
		userController.criarUsuarioController("colaborador3@empresa.com.br", "DAMKmsa466@");
	
		
		int idUsuarioAtualizar = userController.daoUsuario.buscarPorLoginUsuario("colaborador2@empresa.com.br").getIdDoUsuario();
		
		UsuarioModel usuarioAtualizar = new UsuarioModel(userController.converterSenhaEmHashSenha("DAMKmdsa44415#"), "colaborador2@empresa.com.br");
				
		assertTrue(userController.atualizaUsuarioController(idUsuarioAtualizar, usuarioAtualizar));		
	}	
	
	@Test
	public void testeBuscarUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.db.limparDB("usuariostabela");

		UsuarioController userController = new UsuarioController();

		userController.criarUsuarioController("colaborador1@empresa.com.br", "Jjhdfa82!78@1");
		userController.criarUsuarioController("colaborador2@empresa.com.br", "DAMKmdsa46615#");
		userController.criarUsuarioController("colaborador3@empresa.com.br", "DAMKmsa466@");

		UsuarioModel usuarioBuscado = userController.buscarUsuarioController(userController.daoUsuario.buscarPorLoginUsuario("colaborador3@empresa.com.br").getIdDoUsuario());
		
		assertEquals(usuarioBuscado.getLoginDoUsuario(), "colaborador3@empresa.com.br");
		assertEquals(usuarioBuscado.getHashSenhaDoUsuario(), userController.converterSenhaEmHashSenha("DAMKmsa466@"));
	}
	
	@Test
	public void testeBuscarTodosUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuarioDAO.db.limparDB("usuariostabela");

		UsuarioController userController = new UsuarioController();

		userController.criarUsuarioController("colaborador1@empresa.com.br", "Jjhdfa82!78@1");
		userController.criarUsuarioController("colaborador2@empresa.com.br", "DAMKmdsa46615#");
		userController.criarUsuarioController("colaborador3@empresa.com.br", "DAMKmsa466@");

		ArrayList<UsuarioModel> listaDeUsuariosBuscados = userController.buscarTodosUsuariosController();
		
		assertEquals(listaDeUsuariosBuscados.size(),3);
		assertEquals(listaDeUsuariosBuscados.get(0).getLoginDoUsuario(),"colaborador1@empresa.com.br");
		assertEquals(listaDeUsuariosBuscados.get(1).getLoginDoUsuario(),"colaborador2@empresa.com.br");
		assertEquals(listaDeUsuariosBuscados.get(2).getLoginDoUsuario(),"colaborador3@empresa.com.br");		
	}
	
	
	


	@Test
	public void testeGerarCodigo() {
		UsuarioController userControl = new UsuarioController();
		assertTrue(userControl.gerarCodigo());
	}

}
