package model.acesso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.PerfilController;
import controller.PermissaoController;
import controller.UsuarioController;
import db.DBConnection;

/**
 * Classe responsavel por controlar a tabela de ligacao entre as tabelas de perfil e de permissao.
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
public class TabelaLigacaoPerfilPermissao {

	public DBConnection db;
	
	public TabelaLigacaoPerfilPermissao() {
		db = DBConnection.getInstance();
	}
	
	/**
	 * Consulta todas as permissoes que um perfil possui, utilizando o id do perfil 
	 * para consultar a tabela de ligacao.
	 * @param idPerfil Integer
	 * @return ArrayList<PermissaoModel>
	 */
	public ArrayList<PermissaoModel> permissoesDoPerfil(Integer idPerfil) {
		PermissaoController pc = new PermissaoController();
		ArrayList<PermissaoModel> resultado = new ArrayList<PermissaoModel>();
		try {
			ResultSet rs = db.executeQuery("select * from permissoesligacaotabela where idPerfil=" + idPerfil + ";");
			while(rs.next()) {
				PermissaoModel pm = pc.buscarPermissao(rs.getInt(3));
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
	 * Consulta todas os perfis associados a uma permissao utilizando o id da permissao
	 * para consultar a tabela de ligacao.
	 * @param idPermissao Integer
	 * @return ArrayList<PerfilModel>
	 */
	public ArrayList<PerfilModel> perfisDaPermissao(Integer idPermissao) {
		PerfilController pc = new PerfilController();
		ArrayList<PerfilModel> resultado = new ArrayList<PerfilModel>();
		try {
			ResultSet rs = db.executeQuery("select * from permissoesligacaotabela where idPermissao=" + idPermissao + ";");
			while(rs.next()) {
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
	 * Cria uma nova relacao entre um perfil e uma permissao
	 * @param idPerfil Integer
	 * @param idPermissao Integer
	 * @return boolean
	 */
	public boolean adicionarLigacao(Integer idPerfil, Integer idPermissao) {
		try {
			db.executeUpdate("insert into permissoesligacaotabela (idperfil, idpermissao) values(" + idPerfil + ", + " + idPermissao +");");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Remove uma relacao existente entre um perfil e uma permissao.
	 * @param idPerfil Integer
	 * @param idPermissao Integer
	 * @return boolean
	 */
	public boolean removerLigacao(Integer idPerfil, Integer idPermissao) {
		try {
			db.executeUpdate("delete from permissoesligacaotabela where idperfil = "+ idPerfil +" and idpermissao= "+ idPermissao);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
