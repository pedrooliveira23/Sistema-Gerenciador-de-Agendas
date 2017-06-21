package model.bo;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import model.agentes.AgenteAgenda;

public class BoSolicitarAgendamento {

	public String[] obterAgentes(AgentController agentController) {
		String[] listaAgentes = new String[agentController.ge..length];
		for(int i = 0; i<=agenteLocal.obterAgentes().length;i++) {
			listaAgentes[i] = agenteLocal.obterAgentes()[i].getLocalName();
		}
		return listaAgentes;
	}

}
