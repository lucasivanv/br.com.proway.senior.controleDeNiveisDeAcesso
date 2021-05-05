package model.acesso;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import db.DBConnection;
import model.interfaces.InterfacePerfilDAO;

/**
 * Classe PerfilDAO
 * 
 * Classe que implementa a interface que se relaciona com o banco de dados de perfis
 * 
 * @author Sprint 3
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 * 
 * @author Sprint 4
 * @author Elton Oliveira, elton.oliveira@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Vitor Goncalves, vitor.goncalves@senior.com.br
 * @author Vitor Gehrke, vitor.gehrke@senior.com.br
 */

public class PerfilDAO implements InterfacePerfilDAO{
	
	public DBConnection db;
	
	/**
	 * Método para conectar com o DB
	 */
	public PerfilDAO() {
		db = DBConnection.getInstance();
	}
	
	/**
	 * Metodo criarPerfilVazio
	 * 
	 * Metodo responsavel por criar um perfil vazio (sem permissoes), sem validade e ativo no banco de dados conforme
	 * atributos associados
	 * 
	 * @param nomeDoPerfil String
	 * @return boolean
	 * 
	 */
	public boolean criarPerfilVazio(String nomeDoPerfil) {
		String insertPerfil = "INSERT INTO perfilTabela(nomePerfil, dataInicio, dataFim, status) "
				+ "values('" + nomeDoPerfil +"', '" + 
				LocalDate.of(1970, 1, 1).toString() + "', '" + 
				LocalDate.of(2200, 1, 1).toString() + "', " + 
				true + ");";
		
		try {
			db.executeUpdate(insertPerfil);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * Metodo criarPerfilVazioTemporario
	 * 
	 * Metodo responsavel por criar um perfil vazio (sem permissoes), com validade e ativo no banco de dados conforme
	 * atributos associados
	 * 
	 * @param perfil PerfilModel
	 * @return boolean
	 * 
	 */
	public boolean criarPerfilVazioTemporario(PerfilModel perfil) {
		String insertPerfil = "INSERT INTO perfilTabela(nomePerfil, dataInicio, dataFim, status) "
				+ "values('" + perfil.getNomeDoPerfil() +"', '" + 
				perfil.getInicioValidadePerfil().toString() + "', '" + 
				perfil.getFimValidadePerfil().toString() + "', " + 
				perfil.isPerfilAtivo() + ");";
		
		try {
			db.executeUpdate(insertPerfil);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * Metodo deletarPerfil
	 * 
	 * Metodo responsavel por deletar um perfil existente no banco de dados a partir do
	 * id informado
	 * 
	 * @param idDoPerfil Integer
	 * @return boolean
	 * 
	 */
	public boolean deletarPerfil(Integer idDoPerfil) {
		String deletarPerfil = "DELETE from perfilTabela where idPerfil="+ idDoPerfil+";";
		try {
			db.executeUpdate(deletarPerfil);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	/**
	 * Metodo deletarPerfil
	 * 
	 * Metodo responsável por deletar um perfil existente no banco de dados a partir do
	 * nome informado do perfil
	 * 
	 * @param nomeDoPerfil String
	 * @return boolean
	 * 
	 */
	public boolean deletarPerfil(String nomeDoPerfil) {
		String deletarPerfil = "DELETE from perfilTabela where nomePerfil='"+ nomeDoPerfil+"';";
		try {
			db.executeUpdate(deletarPerfil);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	/**
	 * Metodo atualizar
	 * 
	 * Metodo responsavel por atualizar um perfil existente no banco de dados a partir do
	 * id informado
	 * 
	 * @param idDoPerfil Integer
	 * @param perfil PerfilModel
	 * @return boolean
	 * 
	 */
	public boolean atualizarPerfil(Integer idDoPerfil, PerfilModel perfil) {
		String deletarPerfil = "UPDATE perfilTabela set nomeperfil= '" + perfil.getNomeDoPerfil()+ "', dataInicio='"
				+ perfil.getInicioValidadePerfil().toString() + "', dataFim= '" + 
				perfil.getFimValidadePerfil() + "', status= "+ perfil.isPerfilAtivo()+" where idPerfil="+ idDoPerfil+";";
		try {
			db.executeUpdate(deletarPerfil);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo buscarPerfil
	 * 
	 * Metodo responsável por buscar, através do id, um perfil dentro de uma
	 * lista de Perfis. Se o perfil existe, retorna o mesmo. Se nao, retorna
	 * nulo.
	 * 
	 * @param idDoPerfil Integer
	 * @return PerfilModel
	 */
	public PerfilModel buscarPerfil(Integer idDoPerfil) {
		ArrayList<String> resultado = new ArrayList<String>();
		String buscarPerfil = "SELECT * from perfilTabela where idPerfil="+ idDoPerfil +";";
		try {
			ResultSet rs = db.executeQuery(buscarPerfil);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			if(rs.next()) {
				for (int i = 1; i <= totalColunas; i ++) {
					resultado.add(rs.getString(i));
				}
			}
			if(!resultado.isEmpty()) {
				return new PerfilModel(Integer.parseInt(resultado.get(0)), resultado.get(1), LocalDate.parse(resultado.get(2)), LocalDate.parse(resultado.get(3)), Boolean.parseBoolean(resultado.get(4)));				
			}
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo buscarPerfil
	 * 
	 * Metodo responsavel por buscar, atraves do nome do perfil, um perfil dentro de uma
	 * lista de Perfis. Se o perfil existe, retorna o mesmo. Se nao, retorna
	 * nulo.
	 * 
	 * @param nomePerfil String
	 * @return PerfilModel
	 */
	public PerfilModel buscarPerfil(String nomePerfil) {
		ArrayList<String> resultado = new ArrayList<String>();
		String buscarPerfil = "SELECT * from perfilTabela where nomeperfil='"+ nomePerfil +"';";
		try {
			ResultSet rs = db.executeQuery(buscarPerfil);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			if(rs.next()) {
				for (int i = 1; i <= totalColunas; i ++) {
					resultado.add(rs.getString(i));
				}
			}
			if(!resultado.isEmpty()) {
				return new PerfilModel(Integer.parseInt(resultado.get(0)), resultado.get(1), LocalDate.parse(resultado.get(2)), LocalDate.parse(resultado.get(3)), Boolean.parseBoolean(resultado.get(4)));
			}
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo buscarPerfil
	 * 
	 * Método responsavel por buscar, atraves do status, todos os perfis dentro de uma
	 * lista de Perfis que possuem o mesmo status. 
	 * 
	 * @param status boolean
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> buscarPerfil(boolean status) {
		ArrayList<PerfilModel> resultado = new ArrayList<PerfilModel>();
		String buscarPerfil = "SELECT * from perfilTabela where status='"+ status +"';";
		try {
			ResultSet rs = db.executeQuery(buscarPerfil);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			while(rs.next()) {
				ArrayList<String> linha = new ArrayList<String>();
				for (int i = 1; i <= totalColunas; i ++) {
					linha.add(rs.getString(i));
				}
				PerfilModel pm = new PerfilModel(Integer.parseInt(linha.get(0)), linha.get(1), LocalDate.parse(linha.get(2)), LocalDate.parse(linha.get(3)), Boolean.parseBoolean(linha.get(4)));
				resultado.add(pm);
			}
			if(!resultado.isEmpty()) {
				return resultado;
			} 
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo buscarTodosOsPerfis
	 * 
	 * Metodo responsavel por buscar todos os perfis dentro de uma
	 * lista de Perfis.
	 *
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> buscarTodosOsPerfis() {
		ArrayList<PerfilModel> resultado = new ArrayList<PerfilModel>();
		String selecionarPerfil = "SELECT * from perfilTabela;";
		try {
			ResultSet rs = db.executeQuery(selecionarPerfil);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			while(rs.next()) {
				ArrayList<String> linha = new ArrayList<String>();
				for (int i = 1; i <= totalColunas; i ++) {
					linha.add(rs.getString(i));
				}
				PerfilModel pm = new PerfilModel(Integer.parseInt(linha.get(0)), linha.get(1));
				resultado.add(pm);
			}
			if(!resultado.isEmpty()) {
				return resultado;
			} 
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
}
