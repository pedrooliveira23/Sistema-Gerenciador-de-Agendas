package model.bo;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import model.agentes.AgenteAgenda;

public class BoSolicitarAgendamento {

	public String[] obterAgentes() {
		AgenteAgenda agenteLocal = new AgenteAgenda();
		String[] listaAgentes = new String[agenteLocal.obterAgentes().length];
		for(int i = 0; i<=agenteLocal.obterAgentes().length;i++) {
			listaAgentes[i] = agenteLocal.obterAgentes()[i].getLocalName();
		}
		return listaAgentes;
	}

}
