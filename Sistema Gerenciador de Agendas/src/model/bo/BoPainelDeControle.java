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

	private static String nomeAgente = "Visitante" + (int)(Math.random() * 9999999);	

	public void encerrar() {
		System.exit(0);
	}
	
	public void criarAgente(ContainerController containerController) {
		try {
			AgentController ag = containerController.createNewAgent(getNomeAgente(), 
					"model.agentes.AgenteAgenda", 
					new Object[] {});//arguments
			ag.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

	}

	public void destruirAgente(ContainerController containerController){
		try {
			containerController.getAgent(getNomeAgente()).kill();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	public void mudarNomeAgente(String nome, ContainerController containerController) {
		destruirAgente(containerController);
		setNomeAgente(nome);
		criarAgente(containerController);
	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}


}
