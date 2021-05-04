package model.acesso;

import java.util.ArrayList;

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
	
	
//	@Test
//	public void testCreateUsuario() {
//		
//		UsuarioDAO userDAO = new UsuarioDAO();
//		UsuarioModel usuario = new UsuarioModel();
//		usuario.setIdDoUsuario(0);
//		usuario.setLoginDoUsuario("vitorperes1104@gmail.com");
//		userDAO.create(usuario);
//		
//		assertEquals("vitorperes1104@gmail.com", userDAO.user.get(0).getLoginDoUsuario());
//		
//	}
//	
//	@Test
//	public void testGetUsuario() {
//		UsuarioDAO userDAO = new UsuarioDAO();
//		UsuarioModel usuario = new UsuarioModel();
//		usuario.setIdDoUsuario(0);
//		usuario.setLoginDoUsuario("vitorperes1104@gmail.com");
//		userDAO.create(usuario);
//		UsuarioModel userTest = userDAO.get(0);
//		
//		assertEquals("vitorperes1104@gmail.com", userTest.getLoginDoUsuario());
//		
//	}
//	
//	@Test
//	public void testUpdateUsuario() {
//		UsuarioController userControl = new UsuarioController();
//		UsuarioModel usuario = new UsuarioModel();
//		usuario.setIdDoUsuario(0);
//		usuario.setLoginDoUsuario("Teste");
//		userControl.daoUsuario.create(usuario);
//		usuario.setLoginDoUsuario("Teste usuario atualizado");
//		userControl.daoUsuario.update(usuario);
//		assertEquals("Teste usuario atualizado", userControl.daoUsuario.user.get(0).getLoginDoUsuario());
//	}
//	
//	@Test
//	public void testeGetAllUsuario() {
//		UsuarioController userControl = new UsuarioController();
//		
//		PermissaoModel permissao = new PermissaoModel(1, "Permissao alteração de perfil");
//		ArrayList<PermissaoModel> listaPermissao = new ArrayList<PermissaoModel>();
//		listaPermissao.add(permissao);
//		
//		PerfilModel perfilTest = new PerfilModel(1, "Perfil teste", listaPermissao);
//		
//		ArrayList<PerfilModel> listaPerfil = new ArrayList<PerfilModel>();
//		listaPerfil.add(perfilTest);
//
//		UsuarioModel userUm = new UsuarioModel(0, "vcperes@furb.br", "Va123456", listaPerfil);
//		UsuarioModel userDois = new UsuarioModel(1, "vitorperes1104@gmail.com", "Ca123456", listaPerfil);
//		userControl.daoUsuario.user.add(userUm);
//		userControl.daoUsuario.user.add(userDois);
//		ArrayList<UsuarioModel> arrayUsuariosTest = userControl.daoUsuario.getAll();
//		assertEquals("vcperes@furb.br", arrayUsuariosTest.get(0).getLoginDoUsuario());
//		assertEquals("vitorperes1104@gmail.com", arrayUsuariosTest.get(1).getLoginDoUsuario());
//	}
//	
//	@Test
//	public void testeRemoveUsuario() {
//		UsuarioController userControl = new UsuarioController();
//		PermissaoModel permissao = new PermissaoModel(1, "Permissao alteração de perfil");
//		ArrayList<PermissaoModel> listaPermissao = new ArrayList<PermissaoModel>();
//		listaPermissao.add(permissao);
//		
//		PerfilModel perfilTest = new PerfilModel(1, "Perfil teste", listaPermissao);
//		
//		ArrayList<PerfilModel> listaPerfil = new ArrayList<PerfilModel>();
//		listaPerfil.add(perfilTest);
//
//		UsuarioModel userUm = new UsuarioModel(0, "vcperes@furb.br", "Va123456", listaPerfil);
//		UsuarioModel userDois = new UsuarioModel(1, "vitorperes1104@gmail.com", "Ca123456", listaPerfil);
//
//		userControl.daoUsuario.user.add(userUm);
//		userControl.daoUsuario.user.add(userDois);
//		userControl.daoUsuario.remove(0);
//	
//		assertEquals(1, userControl.daoUsuario.user.get(0).getIdDoUsuario());
//	}


}
