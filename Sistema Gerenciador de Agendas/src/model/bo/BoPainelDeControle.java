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
	private String nomeAgente;

	public void encerrar(ViewPainelDeControle painel) {

	}
	
	public void criarAgent(String nome, String classe, AgentContainer agentContainer) {
		try {
			AgentController ag = agentContainer.createNewAgent(nome, 
					classe, 
					new Object[] {});//arguments
			ag.start();
			setNomeAgente(nome);
		} catch (StaleProxyException e) {
			e.printStackTrace();
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

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}


}
