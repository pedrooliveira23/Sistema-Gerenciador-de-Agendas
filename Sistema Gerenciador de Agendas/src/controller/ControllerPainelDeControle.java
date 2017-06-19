package controller;

import jade.Boot;
import model.bo.BoPainelDeControle;

public class ControllerPainelDeControle {
	private BoPainelDeControle painelDeControle = new BoPainelDeControle();
	private ControllerJadeContainer container;
	
	public ControllerPainelDeControle(ControllerJadeContainer container) {
		this.setContainer(container);
	}

	public void encerrarAplicacao() {
		painelDeControle.encerrar();
	}
	
	public void CriarAgenteAgenda() {
		painelDeControle.criarAgente(container);
	}

	public void mudarNomeAgente(String nome, ControllerJadeContainer container) {
		painelDeControle.mudarNomeAgente(nome, container);
	}
	
	public String obterNomeAgente() {
		return painelDeControle.getNomeAgente();
	}

	public ControllerJadeContainer getContainer() {
		return container;
	}

	public void setContainer(ControllerJadeContainer container) {
		this.container = container;
	}
}

