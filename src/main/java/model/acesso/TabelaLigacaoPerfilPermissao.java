package model.acesso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.PerfilController;
import controller.PermissaoController;
import controller.UsuarioController;
import db.DBConnection;

public class TabelaLigacaoPerfilPermissao {

	public DBConnection db;
	
	public TabelaLigacaoPerfilPermissao() {
		db = DBConnection.getInstance();
	}
	
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
	
	public boolean adicionarLigacao(Integer idPerfil, Integer idPermissao) {
		try {
			db.executeUpdate("insert into permissoesligacaotabela (idperfil, idpermissao) values(" + idPerfil + ", + " + idPermissao +");");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
