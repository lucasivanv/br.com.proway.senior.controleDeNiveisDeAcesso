package model.acesso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 */

public class PermissaoDAOTest {
	
	@Test
	public void criarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.db.limparDB("permissoestabela");
		permissaoDAO.criarPermissao("Remover");
		try {
			ResultSet rs = permissaoDAO.db.executeQuery("select * from permissoestabela");
			if(rs.next()) {
				assertEquals("Remover", rs.getString(2));
			}
			
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void deletarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.db.limparDB("permissoestabela");
		permissaoDAO.criarPermissao("Remover");
		permissaoDAO.criarPermissao("Remover2");
		int i = 0;
		try {
			ResultSet rs = permissaoDAO.db.executeQuery("select max(idpermissao) from permissoestabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			permissaoDAO.deletarPermissao(i);
			
			rs = permissaoDAO.db.executeQuery("select * from permissoestabela");	
			rs.next();
			assertTrue(Integer.parseInt(rs.getString(1)) < i);		
			assertEquals(rs.getString(2), "Remover");	
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void buscarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.db.limparDB("permissoestabela");
		permissaoDAO.criarPermissao("Remover");
		PermissaoModel pmteste = new PermissaoModel("Remover");
		int i = 0;
		try {
			ResultSet rs = permissaoDAO.db.executeQuery("select max(idpermissao) from permissoestabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			PermissaoModel pm = permissaoDAO.buscarPermissao(i);
			
			assertTrue(pmteste.getNomeDaPermissao().equals(pm.getNomeDaPermissao()));		
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
	
	@Test
	public void buscarTodasAsPermissoesNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.db.limparDB("permissoestabela");
		permissaoDAO.criarPermissao("Remover");
		permissaoDAO.criarPermissao("Adicionar");
		permissaoDAO.criarPermissao("Mover");
		PermissaoModel pmteste1 = new PermissaoModel("Remover");
		PermissaoModel pmteste2 = new PermissaoModel("Adicionar");
		PermissaoModel pmteste3 = new PermissaoModel("Mover");
		ArrayList<PermissaoModel> listaPMteste = new ArrayList<PermissaoModel>();
		listaPMteste.addAll(Arrays.asList(pmteste1, pmteste2, pmteste3));
		int i = 0;
		
		ArrayList<PermissaoModel> listaPM = permissaoDAO.buscarTodasAsPermissões();
		
		assertTrue(listaPMteste.get(0).getNomeDaPermissao().equals(listaPM.get(0).getNomeDaPermissao()));		
		assertTrue(listaPMteste.get(1).getNomeDaPermissao().equals(listaPM.get(1).getNomeDaPermissao()));		
		assertTrue(listaPMteste.get(2).getNomeDaPermissao().equals(listaPM.get(2).getNomeDaPermissao()));		
	}
	
	@Test
	public void atualizarPermissaoNoBancoDeDados() {
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		permissaoDAO.db.limparDB("permissoestabela");
		permissaoDAO.criarPermissao("Remover");
		PermissaoModel pm = new PermissaoModel("Adicionar");
		int i = 0;
		try {
			ResultSet rs = permissaoDAO.db.executeQuery("select max(idpermissao) from permissoestabela");
			if(rs.next()) {
				i = rs.getInt(1);
			}
			permissaoDAO.atualizarPermissao(i, pm);
			assertEquals(permissaoDAO.buscarPermissao(i).getNomeDaPermissao(), "Adicionar");
		} catch (SQLException e) {
			fail("Não encontrado");
		}
	}
}
