package model.bo;

import controller.ControllerJadeContainer;
import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import model.agents.gui.AgentPainelDeControle;
import view.ViewPainelDeControle;

public class BoPainelDeControle {

	public void encerrar(ViewPainelDeControle painel) {

	}

	public void criarAgent(String nome, String classe, AgentContainer agentContainer) {
		try {
			agentContainer.getAgent(nome);
			if (classe.equals("model.agents.AgentAgenda")) {
				AgentPainelDeControle.nomeAgente = nome;
			}
		} catch (ControllerException ex) {
			try {
				AgentController ag = agentContainer.createNewAgent(nome, 
						classe, 
						new Object[] {});//arguments
				ag.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}

	public void destruirAgent(AgentContainer agentContainer, String nomeAnterior){
		try {
			agentContainer.getAgent(nomeAnterior).kill();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	public void mudarNomeAgente(String nomeNovo, String classe, AgentContainer agentContainer) {
		destruirAgent(agentContainer, AgentPainelDeControle.nomeAgente );
		criarAgent(nomeNovo, classe, agentContainer);
		AgentPainelDeControle.nomeAgente = nomeNovo;
	}
}
