package model.acesso;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabelaLigacaoPerfilPermissaoTest {

	@Test
	public void testPermissoesDoPerfil() {
		TabelaLigacaoPerfilPermissao tb = new TabelaLigacaoPerfilPermissao();
		
		tb.adicionarLigacao(223, 65);
		tb.adicionarLigacao(222, 69);
		tb.adicionarLigacao(220, 67);
		tb.adicionarLigacao(222, 70);
		tb.adicionarLigacao(224, 70);
		tb.adicionarLigacao(223, 70);
		System.out.println(tb.perfisDaPermissao(70).toString());
		System.out.println(tb.permissoesDoPerfil(222).toString());
		
	}

}
