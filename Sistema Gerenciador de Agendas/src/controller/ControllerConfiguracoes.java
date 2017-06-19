package controller;

import model.bo.BoConfiguracoes;
import model.bo.BoPainelDeControle;
import view.ViewPainelDeControle;

public class ControllerConfiguracoes {
	private static ControllerPainelDeControle painelDeControle;
	private ControllerJadeContainer container = null;
	public ControllerConfiguracoes(ControllerJadeContainer container) {
		painelDeControle = new ControllerPainelDeControle(container);
		this.container = container;
	}
	private static BoConfiguracoes configuracoes = new BoConfiguracoes();
	public void salvarConfiguracoes(String nome) {
		painelDeControle.mudarNomeAgente(nome, container);
	}

}
