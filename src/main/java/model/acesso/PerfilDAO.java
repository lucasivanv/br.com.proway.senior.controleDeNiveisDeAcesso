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
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 * 
 */

public class PerfilDAO implements InterfacePerfilDAO{
	
	public DBConnection db;
	
	private ArrayList<PerfilModel> listaDePerfisCriados = new ArrayList<PerfilModel>();
	
	public PerfilDAO() {
		db = DBConnection.getInstance();
	}
	
	/**
	 * Método criarPerfil
	 * 
	 * Método responsável por criar um perfil vazio no banco de dados conforme
	 * atribuitos associadados
	 * 
	 * @param idDoPerfil Integer
	 * @param nomeDoPerfil String
	 * @return PerfilModel 
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
	 * Método deletarPerfil
	 * 
	 * Método responsável por deletar um perfil existente no banco de dados a partir do
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
	 * Método atualizar
	 * 
	 * Método responsável por atualizar um perfil existente no banco de dados a partir do
	 * id informado
	 * 
	 * @param idDoPerfil Integer
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
	 * Método buscarPerfil
	 * 
	 * Método responsável por buscar, através do id, um perfil dentro de uma
	 * lista de Perfis. Se o perfil existe, retorna o mesmo. Se não, retorna
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
			return new PerfilModel(Integer.parseInt(resultado.get(0)), resultado.get(1), LocalDate.parse(resultado.get(2)), LocalDate.parse(resultado.get(3)), Boolean.parseBoolean(resultado.get(4)));
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PerfilModel buscarPerfilPorNomePerfil(String nomePerfil) {
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
			return new PerfilModel(Integer.parseInt(resultado.get(0)), resultado.get(1), LocalDate.parse(resultado.get(2)), LocalDate.parse(resultado.get(3)), Boolean.parseBoolean(resultado.get(4)));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<PerfilModel> buscarPerfilPorStatus(boolean status) {
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
			return resultado;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<PerfilModel> buscarTodasAsPerfil() {
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
			return resultado;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	

	/**
	 * Método lerListaDePerfisCriados
	 * 
	 * Retorna a lista de perfis criados
	 *  
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> lerListaDePerfisCriados() {
		return listaDePerfisCriados;
	}
	
	/**
	 * Método alterarPerfil
	 * 
	 * Método procura um perfil, com base no seu id, e altera o seu nome
	 * 
	 * @param idDoPerfil Integer
	 * @param novoNomeDoPerfil String
	 * @return PerfilModel
	 */
	public PerfilModel alterarNomePerfil(Integer idDoPerfil, String novoNomeDoPerfil) {
		PerfilModel perfilAlterado = this.buscarPerfil(idDoPerfil);

		if (perfilAlterado != null) {

			perfilAlterado.setNomeDoPerfil(novoNomeDoPerfil);
			return perfilAlterado;
		}
		return null;
	}
	
	/**
	 * Método alterarInicioValidadePerfil.
	 * 
	 * Método procura um perfil, com base no seu id, e altera a sua data de início de validade.
	 *
	 * 
	 * @param idDoPerfil Integer
	 * @param novoInicioValidadePerfil LocalDate
	 * @return PerfilModel
	 */
	public PerfilModel alterarInicioValidadePerfil(Integer idDoPerfil, LocalDate novoInicioValidadePerfil) {
		PerfilModel perfilAlterado = this.buscarPerfil(idDoPerfil);
		
		if(perfilAlterado != null) {
			perfilAlterado.setInicioValidadePerfil(novoInicioValidadePerfil);
			return perfilAlterado;
		}
		return null;	
	}
	
	/**
	 * Método alterarFimValidadePerfil.
	 * 
	 * Método procura um perfil, com base no seu id, e altera a sua data de fim de validade.
	 *
	 * 
	 * @param idDoPerfil Integer
	 * @param novoFimValidadePerfil LocalDate
	 * @return PerfilModel
	 */
	public PerfilModel alterarFimValidadePerfil(Integer idDoPerfil, LocalDate novoFimValidadePerfil) {
		PerfilModel perfilAlterado = this.buscarPerfil(idDoPerfil);
		
		if(perfilAlterado != null) {
			perfilAlterado.setFimValidadePerfil(novoFimValidadePerfil);
			return perfilAlterado;
		}
		return null;	
	}
	
	/**
	 * Método alterarPerfilAtivo.
	 * 
	 * Método procura um perfil, com base no seu id, e altera o seu estado de ativo
	 *
	 * 
	 * @param idDoPerfil Integer
	 * @param novoEstadoAtivo boolean
	 * @return PerfilModel
	 */
	public PerfilModel alterarPerfilAtivo(Integer idDoPerfil, boolean novoEstadoAtivo) {
		PerfilModel perfilAlterado = this.buscarPerfil(idDoPerfil);
		
		if(perfilAlterado != null) {
			perfilAlterado.setPerfilAtivo(novoEstadoAtivo);
			return perfilAlterado;
		}
		return null;	
	}
	
}
