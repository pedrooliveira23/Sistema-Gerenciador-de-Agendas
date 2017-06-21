package controller;

import model.bo.BoConfiguracoes;
import model.bo.BoPainelDeControle;
import view.ViewPainelDeControle;

public class ControllerConfiguracoes {
	private static ControllerPainelDeControle painelDeControle = new ControllerPainelDeControle();
	public void salvarConfiguracoes(String nome) {
		painelDeControle.mudarNomeAgente(nome);
	}

}
