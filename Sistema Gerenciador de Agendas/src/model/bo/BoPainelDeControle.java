package model.bo;

import controller.ControllerJadeContainer;
import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class BoPainelDeControle {

	private static String nomeAgente;	

	public void encerrar() {
		System.exit(0);
	}
	
	public void criarAgente(ControllerJadeContainer container) {
		try {
			AgentController ag = container.getContainer().createNewAgent(getNomeAgente(), 
					"model.agentes.AgenteAgenda", 
					new Object[] {});//arguments
			ag.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

	}

	public void destruirAgente(ControllerJadeContainer container){
		try {
			container.getContainer().getAgent(getNomeAgente()).kill();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	public void mudarNomeAgente(String nome, ControllerJadeContainer container) {
		destruirAgente(container);
		setNomeAgente(nome);
		criarAgente(container);
	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}


}
