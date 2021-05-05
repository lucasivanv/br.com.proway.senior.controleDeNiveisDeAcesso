package model.acesso;

import java.math.BigInteger;
import java.security.MessageDigest;

import model.interfaces.InterfaceHashSenha;

/**
 * Classe HashSenha
 * 
 * Classe responsavel pela conversao da senha em hashSenha
 * 
 * Sprint 3
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 * 
 * Sprint 4
 * @author Vitor Nathan Goncalves, vitor.goncalves@senior.com.br
 * @author Elton Francisco de Oliveira, elton.oliveira@senior.com.br
 * @author Vitor Andre Gehrke, vitor.gehrke@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 */

public class HashSenha {

	/**
	 * Criptografia para sennha.
	 * 
	 * Neste metodo esta sendo utilizado uma API do java "BigInteger" para gerar um
	 * algoritmo para realizar a HASH da senha utilizando criptografia SHA-512.
	 *
	 * @param String senha
	 * @return valorCodificado
	 */
	public static String senhaDoUsuario(String senha) {
		String valorCodificado = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(senha.getBytes("utf8"));
			valorCodificado = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valorCodificado;
	}
}
