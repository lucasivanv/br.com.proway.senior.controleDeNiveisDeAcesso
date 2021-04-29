package model.acesso;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe PerfilModel
 * 
 * Defini os atributos necessários para instanciar um perfil,
 * o que é constituído por permissões.
 * 
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Sarah Brito, sarah.brito@senior.com.br
 *
 */
public class PerfilModel {
	
	private Integer idDoPerfil;
	private String nomeDoPerfil;
	private ArrayList<PermissaoModel> listaDePermissoesDoPerfil = new ArrayList<PermissaoModel>();
	private LocalDate inicioValidadePerfil = LocalDate.MIN;
	private LocalDate fimValidadePerfil = LocalDate.MAX;
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

	public ArrayList<PermissaoModel> getListaDePermissoesDoPerfil() {
		return listaDePermissoesDoPerfil;
	}

	public void setListaDePermissoesDoPerfil(ArrayList<PermissaoModel> listaDePermissoesDoPerfil) {
		this.listaDePermissoesDoPerfil = listaDePermissoesDoPerfil;
	}
	
	
	public PerfilModel(Integer idDoPerfil, String nomeDoPerfil, ArrayList<PermissaoModel> listaDePermissoesDoPerfil,
			LocalDate inicioValidadePerfil, LocalDate fimValidadePerfil, boolean perfilAtivo) {
		this.idDoPerfil = idDoPerfil;
		this.nomeDoPerfil = nomeDoPerfil;
		this.listaDePermissoesDoPerfil = listaDePermissoesDoPerfil;
		this.inicioValidadePerfil = inicioValidadePerfil;
		this.fimValidadePerfil = fimValidadePerfil;
		this.perfilAtivo = perfilAtivo;
	}
	
	/**
	 * Construtor da classe PerfilModel.
	 * 
	 * Não engloba os atributos inicioValidadePerfil, fimValidadePerfil e perfilAtivo, criando um 
	 * perfil permanente (sem validade).
	 * @param idDoPerfil Integer
	 * @param nomeDoPerfil String
	 * @param listaDePermissoesDoPerfil ArrayList<PermissaoModel>
	 */
	public PerfilModel(Integer idDoPerfil, String nomeDoPerfil, ArrayList<PermissaoModel> listaDePermissoesDoPerfil) {
		this.idDoPerfil = idDoPerfil;
		this.nomeDoPerfil = nomeDoPerfil;
		this.listaDePermissoesDoPerfil = listaDePermissoesDoPerfil;
	}
	
	/**	
	 * @deprecated
	 * Builder da classe PerfilModel.
	 * 
	 * É utilizado para instanciar um novo PerfilModel]
	 * @author SENIOR
	 */
	@Deprecated
	 public static class PerfilModelBuilder {
		private Integer idDoPerfil;
		private String nomeDoPerfil;
		private ArrayList<PermissaoModel> listaDePermissoesDoPerfil = new ArrayList<PermissaoModel>();
		private LocalDate inicioValidadePerfil;
		private LocalDate fimValidadePerfil;
		private boolean perfilAtivo;
		
		public PerfilModelBuilder() {
			
		}

		public PerfilModelBuilder idDoPerfil(Integer idDoPerfil) {
			this.idDoPerfil = idDoPerfil;
			return this;
		}
		
		public PerfilModelBuilder nomeDoPerfil(String nomeDoPerfil) {
			this.nomeDoPerfil = nomeDoPerfil;
			return this;
		}
		
		public PerfilModelBuilder listaDePermissoesDoPerfil(ArrayList<PermissaoModel> listaDePermissoesDoPerfil) {
			this.listaDePermissoesDoPerfil = listaDePermissoesDoPerfil;
			return this;
		}
		
		public PerfilModelBuilder inicioValidadePerfil(LocalDate inicioValidadePerfil) {
			this.inicioValidadePerfil = inicioValidadePerfil;
			return this;
		}
		
		public PerfilModelBuilder fimValidadePerfil(LocalDate idDfimValidadePerfiloPerfil) {
			this.fimValidadePerfil = fimValidadePerfil;
			return this;
		}
		
		public PerfilModelBuilder perfilAtivo(boolean perfilAtivo) {
			this.perfilAtivo = perfilAtivo;
			return this;
		}
		
		public PerfilModel criarPerfilModel() {
			return new PerfilModel(idDoPerfil, nomeDoPerfil, listaDePermissoesDoPerfil, LocalDate.MIN, LocalDate.MAX, true);
		}
	}
	
	@Override
	public String toString() {
		return "PerfilModel [idDoPerfil=" + idDoPerfil + ", nomeDoPerfil=" + nomeDoPerfil
				+ ", listaDePermissoesDoPerfil=" + listaDePermissoesDoPerfil + ", inicioValidadePerfil="
				+ inicioValidadePerfil + ", fimValidadePerfil=" + fimValidadePerfil + ", perfilAtivo=" + perfilAtivo
				+ "]";
	}
}
