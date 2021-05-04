package model.acesso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.PerfilController;
import controller.UsuarioController;
import db.DBConnection;

public class TabelaLigacaoUsuarioPerfil {

	public DBConnection db;
	
	public TabelaLigacaoUsuarioPerfil() {
		db = DBConnection.getInstance();
	}
	
	public ArrayList<PerfilModel> perfisDoUsuario(Integer idUsuario) {
		PerfilController pc = new PerfilController();
		ArrayList<PerfilModel> resultado = new ArrayList<PerfilModel>();
		try {
			ResultSet rs = db.executeQuery("select * from perfilLigacaoTabela where idUsuario=" + idUsuario + ";");
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
	
	public ArrayList<UsuarioModel> usuariosDoPerfil(Integer idPerfil) {
		UsuarioController pc = new UsuarioController();
		ArrayList<UsuarioModel> resultado = new ArrayList<UsuarioModel>();
		try {
			ResultSet rs = db.executeQuery("select * from perfilLigacaoTabela where idPerfil=" + idPerfil + ";");
			while(rs.next()) {
				UsuarioModel pm = pc.buscarUsuario(rs.getInt(3));
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
	
	public boolean adicionarLigacao(Integer idPerfil, Integer idUsuario) {
		try {
			db.executeUpdate("insert into perfilligacaotabela (idperfil, idusuario) values(" + idPerfil + ", + " + idUsuario +");");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
