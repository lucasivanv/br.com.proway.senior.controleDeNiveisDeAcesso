package model.acesso;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe PerfilModel
 * 
 * Define os atributos necessarios para instanciar um perfil, o que e
 * constitui­do por permissoes.
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
public class PerfilModel {

	private Integer idDoPerfil;
	private String nomeDoPerfil;
	private LocalDate inicioValidadePerfil = LocalDate.of(1970, 1, 1);
	private LocalDate fimValidadePerfil = LocalDate.of(2200, 1, 1);
	private boolean perfilAtivo = true;

	public boolean isPerfilAtivo() {
		return perfilAtivo;
	}

	public void setPerfilAtivo(boolean perfilAtivo) {
		this.perfilAtivo = perfilAtivo;
	}

	public LocalDate getInicioValidadePerfil() {
		return inicioValidadePerfil;
	}

	public void setInicioValidadePerfil(LocalDate inicioValidadePerfil) {
		this.inicioValidadePerfil = inicioValidadePerfil;
	}

	public LocalDate getFimValidadePerfil() {
		return fimValidadePerfil;
	}

	public void setFimValidadePerfil(LocalDate fimValidadePerfil) {
		this.fimValidadePerfil = fimValidadePerfil;
	}

	public Integer getIdDoPerfil() {
		return idDoPerfil;
	}

	public void setIdDoPerfil(Integer idDoPerfil) {
		this.idDoPerfil = idDoPerfil;
	}

	public String getNomeDoPerfil() {
		return nomeDoPerfil;
	}

	public void setNomeDoPerfil(String nomeDoPerfil) {
		this.nomeDoPerfil = nomeDoPerfil;
	}

	public PerfilModel(Integer idDoPerfil, String nomeDoPerfil, LocalDate inicioValidadePerfil,
			LocalDate fimValidadePerfil, boolean perfilAtivo) {
		this.idDoPerfil = idDoPerfil;
		this.nomeDoPerfil = nomeDoPerfil;
		this.inicioValidadePerfil = inicioValidadePerfil;
		this.fimValidadePerfil = fimValidadePerfil;
		this.perfilAtivo = perfilAtivo;
	}

	/**
	 * Construtor da classe PerfilModel.
	 * 
	 * Nao engloba os atributos inicioValidadePerfil, fimValidadePerfil e
	 * perfilAtivo, criando um perfil permanente (sem validade).
	 * 
	 * @param idDoPerfil   Integer
	 * @param nomeDoPerfil String
	 */
	public PerfilModel(Integer idDoPerfil, String nomeDoPerfil) {
		this.idDoPerfil = idDoPerfil;
		this.nomeDoPerfil = nomeDoPerfil;
	}

	/**
	 * Construtor da classe PerfilModel
	 * 
	 * Nao engloba os atributos idPerfil e listaDePermissoesDoPerfil.
	 * 
	 * @param nomeDoPerfil
	 * @param inicioValidadePerfil
	 * @param fimValidadePerfil
	 * @param perfilAtivo
	 */
	public PerfilModel(String nomeDoPerfil, LocalDate inicioValidadePerfil, LocalDate fimValidadePerfil,
			boolean perfilAtivo) {
		this.nomeDoPerfil = nomeDoPerfil;
		this.inicioValidadePerfil = inicioValidadePerfil;
		this.fimValidadePerfil = fimValidadePerfil;
		this.perfilAtivo = perfilAtivo;
	}

	@Override
	public String toString() {
		return "PerfilModel [idDoPerfil=" + idDoPerfil + ", nomeDoPerfil=" + nomeDoPerfil + ", inicioValidadePerfil="
				+ inicioValidadePerfil + ", fimValidadePerfil=" + fimValidadePerfil + ", perfilAtivo=" + perfilAtivo
				+ "]";
	}
}
