package controller;

import model.bo.BoConfiguracoes;
import model.bo.BoPainelDeControle;

public class ControllerConfiguracoes {
	private static ControllerPainelDeControle painelDeControle = new ControllerPainelDeControle();
	private static BoConfiguracoes configuracoes = new BoConfiguracoes();
	public void salvarConfiguracoes(String nome) {
		painelDeControle.mudarNomeAgente(nome);
	}

}
