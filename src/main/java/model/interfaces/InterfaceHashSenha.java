package model.interfaces;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Sprint 3
 * @author Leonardo Pereira, leonardo.pereira@senior.com.br
 */
public interface InterfaceHashSenha {

	public boolean validarHashSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
