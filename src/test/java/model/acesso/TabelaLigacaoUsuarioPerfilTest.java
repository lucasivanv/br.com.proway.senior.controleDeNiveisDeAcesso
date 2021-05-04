package model.acesso;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabelaLigacaoUsuarioPerfilTest {

	@Test
	public void testPerfisDoUsuario() {
		TabelaLigacaoUsuarioPerfil tb = new TabelaLigacaoUsuarioPerfil();
		
		tb.adicionarLigacao(2, 65);
		tb.adicionarLigacao(3, 69);
		tb.adicionarLigacao(4, 67);
		tb.adicionarLigacao(2, 70);
		tb.adicionarLigacao(3, 70);
		tb.adicionarLigacao(1, 70);
		
		System.out.println(tb.perfisDoUsuario(2).toString());
		System.out.println(tb.usuariosDoPerfil(70).toString());
		
	}

}
