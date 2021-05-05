package model.acesso;

/**
 * Classe PermissaoModel
 * 
 * Recebe os atributos necessarios para instanciar uma permissao, que sera
 * vinculada a um perfil.
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
public class PermissaoModel {

	private Integer idDaPermissao;
	private String nomeDaPermissao;

	public Integer getIdDaPermissao() {
		return idDaPermissao;
	}

	public void setIdDaPermissao(Integer idDaPermissao) {
		this.idDaPermissao = idDaPermissao;
	}

	public String getNomeDaPermissao() {
		return nomeDaPermissao;
	}

	public void setNomeDaPermissao(String nomeDaPermissao) {
		this.nomeDaPermissao = nomeDaPermissao;
	}

	public PermissaoModel(Integer idDaPermissao, String nomeDaPermissao) {
		this.idDaPermissao = idDaPermissao;
		this.nomeDaPermissao = nomeDaPermissao;
	}

	/**
	 * Construtor da classe PermissaoModel
	 * 
	 * Nao engloba o atributo idDaPermissao
	 * 
	 * @param nomeDaPermissao
	 */
	public PermissaoModel(String nomeDaPermissao) {
		this.nomeDaPermissao = nomeDaPermissao;
	}

	@Override
	public String toString() {
		return "PermissaoModel [idDaPermissao=" + idDaPermissao + ", nomeDaPermissao=" + nomeDaPermissao + "]";
	}
}
