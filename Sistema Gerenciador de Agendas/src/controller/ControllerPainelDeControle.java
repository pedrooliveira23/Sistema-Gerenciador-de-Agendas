package controller;

import jade.Boot;
import model.bo.BoPainelDeControle;

public class ControllerPainelDeControle {
	private BoPainelDeControle painelDeControle = new BoPainelDeControle();
	public void encerrarAplicacao() {
		painelDeControle.encerrar();
	}
	
	public void abrirRMA() {
		painelDeControle.abrirRMA();
	}
	
	public void CriarAgenteAgenda() {
		painelDeControle.criarAgente();
	}

	public void mudarNomeAgente(String nome) {
		painelDeControle.mudarNomeAgente(nome);
	}
}

