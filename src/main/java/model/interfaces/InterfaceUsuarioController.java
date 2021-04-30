package model.interfaces;

public interface InterfaceUsuarioController {
	
	public boolean validarEmail(String email);
	public boolean validarSenha(String senha);
	public String enviarEmail(String loginDoUsuario);
	public boolean alteraSenha(int id, String senhaNova);

}
