package model.acesso;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import db.DBConnection;
import model.interfaces.InterfaceUsuarioDAO;
/**
 * Classe UsuarioDAO
 * 
 * Classe que implementa a interface que se relaciona com o banco de dados de
 * usuarios.
 * 
 * @author Sprint 3
 * @author David Willian, david.oliveira@senior.com.br
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * @author Vitor Peres, vitor.peres@senior.com.br
 * 
 * @author Sprint 4
 * @author Elton Oliveira, elton.oliveira@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Vitor Goncalves, vitor.goncalves@senior.com.br
 * @author Vitor Gehrke, vitor.gehrke@senior.com.br
 */

public class UsuarioDAO implements InterfaceUsuarioDAO<UsuarioModel> {
	
	public DBConnection db;

	public ArrayList<UsuarioModel> user = new ArrayList<UsuarioModel>();
		
	public UsuarioDAO() {
		db = DBConnection.getInstance();
	}

	/**
	 * Metodo criarUsuario
	 * 
	 * Metodo responsavel por inserir um usuario no banco de dados conforme
	 * atributos associados
	 * 
	 * @param hashSenha String
	 * @param loginUsuario String
	 * @return boolean
	 * 
	 */
	public boolean criarUsuario(String hashSenha, String loginUsuario) {
		String insertUsuario = "INSERT INTO usuariostabela(hashsenha, login) values('" + hashSenha + "', '"
				+ loginUsuario + "');";
		try {
			db.executeUpdate(insertUsuario);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo deletarUsuario
	 * 
	 * Metodo responsavel por deletar um usuario existente no banco de dados a
	 * partir do id informado
	 * 
	 * @param idUsuario Integer
	 * @return boolean
	 */
	public boolean deletarUsuario(Integer idUsuario) {
		String deletarUsuario = "DELETE from usuariostabela where idusuario=" + idUsuario + ";";
		try {
			db.executeUpdate(deletarUsuario);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo deletarUsuario
	 * 
	 * Metodo responsavel por deletar um usuario existente no banco de dados a
	 * partir do nome.
	 * 
	 * @param login String
	 * @return boolean
	 */
	public boolean deletarUsuarioPorLogin(String login){
		String deletarUsuarioPorLogin = "DELETE from usuariostabela where login='"+ login +"';";
		try {
			db.executeUpdate(deletarUsuarioPorLogin);
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Atualiza um usuario no banco de dados.
	 * 
	 * @param idUsuario Integer
	 * @param usuario   UsuarioModel
	 * @return boolean
	 */
	public boolean atualizarUsuario(Integer idUsuario, UsuarioModel usuario) {
		String atualizarUsuario = "UPDATE usuariostabela set hashsenha= '" + usuario.getHashSenhaDoUsuario()
				+ "', login='" + usuario.getLoginDoUsuario() + "' where idUsuario=" + idUsuario + ";";
		try {
			db.executeUpdate(atualizarUsuario);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Busca um usuario no banco de dados a partir de seu idUsuario
	 * 
	 * @param idUsuario Integer
	 * @return UsuarioModel
	 */
	public UsuarioModel buscarUsuario(Integer idUsuario) {
		ArrayList<String> resultado = new ArrayList<String>();
		String buscarUsuario = "SELECT * from usuariosTabela where idusuario=" + idUsuario + ";";
		try {
			ResultSet rs = db.executeQuery(buscarUsuario);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			if (rs.next()) {
				for (int i = 1; i <= totalColunas; i++) {
					resultado.add(rs.getString(i));
				}
			} else {
				return null;
			}
			return new UsuarioModel(Integer.parseInt(resultado.get(0)), resultado.get(1), resultado.get(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Metodo buscarUsuario
	 * 
	 * Metodo responsavel por buscar, atraves do nome, um usuario dentro do banco de dados. 
	 * Se o usuario existe, retorna o mesmo. Se nao, retorna nulo.
	 * 
	 * @param login String
	 * @return UsuarioModel
	 */
	public UsuarioModel buscarUsuario(String login) {
		ArrayList<String> resultado = new ArrayList<String>();
		String buscarUsuario = "SELECT * from usuariostabela where login='"+ login+"';";
		try {
			ResultSet rs = db.executeQuery(buscarUsuario);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			if(rs.next()) {
				for (int i = 1; i <= totalColunas; i ++) {
					resultado.add(rs.getString(i));
				}
			} else {
				return null;
			}
			if(!resultado.isEmpty()) {
				return new UsuarioModel(Integer.parseInt(resultado.get(0)), resultado.get(1), resultado.get(2));
			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Lista todos os usuarios cadastrados no banco, retornando seus dados.
	 * 
	 * @return ArrayList<UsuarioModel>
	 */
	public ArrayList<UsuarioModel> buscarTodosUsuarios() {
		ArrayList<UsuarioModel> resultado = new ArrayList<UsuarioModel>();
		String buscarUsuarios = "SELECT * from usuariostabela;";
		try {
			ResultSet rs = db.executeQuery(buscarUsuarios);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColunas = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> linha = new ArrayList<String>();
				for (int i = 1; i <= totalColunas; i++) {
					linha.add(rs.getString(i));
				}
				UsuarioModel pm = new UsuarioModel(Integer.parseInt(linha.get(0)), linha.get(1), linha.get(2));
				resultado.add(pm);
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
