package controller;

import jade.Boot;
import model.bo.BoPainelDeControle;

public class ControllerPainelDeControle {
	private BoPainelDeControle painelDeControle = new BoPainelDeControle();
	private ControllerJadeContainer container; 

	public ControllerPainelDeControle() {
		container = container.getInstance();
	}
	
	public void encerrarAplicacao() {
		painelDeControle.encerrar();
	}
	
	public void CriarAgenteAgenda() {
		painelDeControle.criarAgente(container.getContainer());
	}

	public void mudarNomeAgente(String nome) {
		painelDeControle.mudarNomeAgente(nome, container.getContainer());
	}
	
	public String obterNomeAgente() {
		return painelDeControle.getNomeAgente();
	}
}

