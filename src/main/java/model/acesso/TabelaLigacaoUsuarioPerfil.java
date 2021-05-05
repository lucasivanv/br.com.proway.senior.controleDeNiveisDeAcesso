package model.acesso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.PerfilController;
import controller.UsuarioController;
import db.DBConnection;

/**
 * Classe responsavel por controlar a tabela de ligacao entre as tabelas de perfil e de usuario.
 * Possui metodos para consultar, criar ou excluir as ligacoes entre elas.
 * 
 * Sprint 4
 * @author Vitor Nathan Goncalves, vitor.goncalves@senior.com.br
 * @author Elton Francisco de Oliveira, elton.oliveira@senior.com.br
 * @author Vitor Andre Gehrke, vitor.gehrke@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 *
 */
public class TabelaLigacaoUsuarioPerfil {

	public DBConnection db;
	
	public TabelaLigacaoUsuarioPerfil() {
		db = DBConnection.getInstance();
	}
	
	/**
	 * Consulta todas os perfis que um usuario possui, utilizando o id do usuario 
	 * para consultar a tabela de ligacao.
	 * @param idUsuario Integer
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> perfisDoUsuario(Integer idUsuario) {
		PerfilController pc = new PerfilController();
		ArrayList<PerfilModel> resultado = new ArrayList<PerfilModel>();
		try {
			ResultSet rs = db.executeQuery("select * from perfilLigacaoTabela where idUsuario=" + idUsuario + ";");
			while(rs.next()) {
				System.out.println(rs.toString());
				PerfilModel pm = pc.buscarPerfil(rs.getInt(2));
				resultado.add(pm);
			}
			if(!resultado.isEmpty()) {
				return resultado;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Consulta todas os usuarios associados a um perfil utilizando o id do perfil
	 * para consultar a tabela de ligacao.
	 * @param idPerfil Integer
	 * @return ArrayList<UsuarioModel>
	 */
	public ArrayList<UsuarioModel> usuariosDoPerfil(Integer idPerfil) {
		UsuarioController pc = new UsuarioController();
		ArrayList<UsuarioModel> resultado = new ArrayList<UsuarioModel>();
		try {
			ResultSet rs = db.executeQuery("select * from perfilLigacaoTabela where idPerfil=" + idPerfil + ";");
			while(rs.next()) {
				UsuarioModel pm = pc.buscarUsuarioController(rs.getInt(3));
				resultado.add(pm);
			}
			if(!resultado.isEmpty()) {
				return resultado;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cria uma nova relacao entre um perfil e um usuario
	 * @param idPerfil Integer
	 * @param idUsuario Integer
	 * @return boolean
	 */
	public boolean adicionarLigacao(Integer idPerfil, Integer idUsuario) {
		try {
			db.executeUpdate("insert into perfilligacaotabela (idperfil, idusuario) values(" + idPerfil + ", + " + idUsuario +");");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Remove uma relacao existente entre um perfil e um usuario.
	 * @param idPerfil Integer
	 * @param idUsuario Integer
	 * @return boolean
	 */
	public boolean removerLigacao(Integer idPerfil, Integer idUsuario) {
		try {
			db.executeUpdate("delete from perfilligacaotabela where idperfil = "+ idPerfil +" and idusuario= "+ idUsuario);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
