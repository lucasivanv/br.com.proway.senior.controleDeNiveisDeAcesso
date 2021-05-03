package model.acesso;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import model.interfaces.InterfacePermissaoDAO;

/**
 * Classe PermissaoDAO
 * 
 * Classe que implementa a interface que se relaciona com o banco de dados de permissões
 * 
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 * 
 */

public class PermissaoDAO implements InterfacePermissaoDAO {
	
	public DBConnection db;
	
	public PermissaoDAO() {
		db = DBConnection.getInstance();
	}

	private ArrayList<PermissaoModel> listaDePermissoesCriadas = new ArrayList<PermissaoModel>();

	/**
	 * Método criarPermissao
	 * 
	 * Método responsável por inserir uma permissao no banco de dados conforme
	 * atributos associados
	 * 
	 * @param nomeDaPermissao String
	 * @return boolean 
	 * 
	 */
	public boolean criarPermissao(String nomeDaPermissao) {
		String insertPermissao = "INSERT INTO permissoesTabela(nome) values('" + nomeDaPermissao +"');";
	
		try {
			db.executeUpdate(insertPermissao);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	/**
	 * Método deletarPermissao
	 * 
	 * Método responsável por deletar uma permissao existente no banco de dados a partir do
	 * id informado
	 * 
	 * @param idDaPermissao Integer
	 * @return boolean
	 * 
	 */
	public boolean deletarPermissao(Integer idDaPermissao) {
		String deletarPermissao = "DELETE from permissoesTabela where idPermissao="+ idDaPermissao+";";
		try {
			db.executeUpdate(deletarPermissao);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método buscarPermissao
	 * 
	 * Método responsável por buscar, através do id, uma permissão dentro do banco de dados. 
	 * Se a permissao existe, retorna a mesma. Se não, retorna nulo.
	 * 
	 * @param idDaPermissao Integer
	 * @return PermissaoModel
	 */
	public PermissaoModel buscarPermissao(Integer idDaPermissao) {
		ArrayList<String> resultado = new ArrayList<String>();
		String selecionarPermissao = "SELECT * from permissoesTabela where idPermissao="+ idDaPermissao+";";
		try {
			ResultSet rs = db.executeQuery(selecionarPermissao);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			if(rs.next()) {
				for (int i = 1; i <= totalColunas; i ++) {
					resultado.add(rs.getString(i));
				}
			}
			return new PermissaoModel(Integer.parseInt(resultado.get(0)), resultado.get(1));
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Método buscarTodasAsPermissões.
	 * 
	 * Método responsável por buscar todas as permissões dentro do banco de dados. 
	 * 
	 * @param idDaPermissao Integer
	 * @return PermissaoModel
	 */
	public ArrayList<PermissaoModel> buscarTodasAsPermissões() {
		ArrayList<PermissaoModel> resultado = new ArrayList<PermissaoModel>();
		String selecionarPermissao = "SELECT * from permissoesTabela;";
		try {
			ResultSet rs = db.executeQuery(selecionarPermissao);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			while(rs.next()) {
				ArrayList<String> linha = new ArrayList<String>();
				for (int i = 1; i <= totalColunas; i ++) {
					linha.add(rs.getString(i));
				}
				PermissaoModel pm = new PermissaoModel(Integer.parseInt(linha.get(0)), linha.get(1));
				resultado.add(pm);
			}
			return resultado;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Método updatePermissao.
	 * 
	 * Atualiza uma permissao no banco de dados.
	 * @param idPermissao Integer
	 * @param novaPermissao PermissaoModel
	 * @return boolean
	 */
	public boolean atualizarPermissao(Integer idPermissao, PermissaoModel novaPermissao) {
		String atualizar = "UPDATE permissoesTabela set nome='"+ 
				novaPermissao.getNomeDaPermissao()+"' where idPermissao ="+idPermissao+";";
		try {
			db.executeUpdate(atualizar);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método lerListaDePermissoesCriadas
	 * 
	 * Retorna a lista de permissoes criadas
	 *  
	 * @return ArrayList<PermissaoModel>
	 */
	public ArrayList<PermissaoModel> lerListaDePermissoesCriadas() {
		return listaDePermissoesCriadas;
	}
	
	/**
	 * 
	 */


}
