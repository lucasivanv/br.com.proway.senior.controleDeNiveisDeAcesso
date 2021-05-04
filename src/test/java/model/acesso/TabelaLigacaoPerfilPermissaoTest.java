package model.acesso;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabelaLigacaoPerfilPermissaoTest {

	@Test
	public void testPermissoesDoPerfil() {
		TabelaLigacaoPerfilPermissao tb = new TabelaLigacaoPerfilPermissao();
		
		tb.adicionarLigacao(65, 223);
		tb.adicionarLigacao(69, 222);
		tb.adicionarLigacao(67, 220);
		tb.adicionarLigacao(70, 222);
		tb.adicionarLigacao(70, 224);
		tb.adicionarLigacao(70, 223);
		System.out.println(tb.perfisDaPermissao(222).toString());
		System.out.println(tb.permissoesDoPerfil(70).toString());
		
	}

}
