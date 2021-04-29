package model.acesso;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class testeBuilderPerfilModel {

	@Test
	public void test() {
		PerfilModel pf = new PerfilModel.PerfilModelBuilder().idDoPerfil(1).nomeDoPerfil("RH").
				listaDePermissoesDoPerfil(new ArrayList<PermissaoModel>()).
				inicioValidadePerfil(LocalDate.now()).fimValidadePerfil(LocalDate.MAX).perfilAtivo(true).criarPerfilModel();
		PerfilModel pf2 = new PerfilModel.PerfilModelBuilder().idDoPerfil(2).nomeDoPerfil("Gerência").
				listaDePermissoesDoPerfil(new ArrayList<PermissaoModel>()).criarPerfilModel();
		PerfilModel pf3 = new PerfilModel.PerfilModelBuilder().idDoPerfil(3).nomeDoPerfil("Supervisão").
				listaDePermissoesDoPerfil(new ArrayList<PermissaoModel>()).criarPerfilModel();
	}

}
