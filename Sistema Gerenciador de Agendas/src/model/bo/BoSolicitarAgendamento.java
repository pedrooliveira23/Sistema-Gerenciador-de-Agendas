package model.bo;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;

public class BoSolicitarAgendamento {

	public String[] obterAgentes(ContainerController containerController, String nome) {
		Agent agente = null;
		try {
			agente = (Agent) containerController.getAgent(nome);
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AMSAgentDescription[] agentes = null;
		
		try {
			SearchConstraints c = new SearchConstraints();
			c.setMaxResults(new Long(-1));
			agentes = AMSService.search(agente, new AMSAgentDescription(), c);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		String[] listaAgentes = new String[agentes.length];
		
		for(int i = 0; i < agentes.length; i++) {
			AID agenteID = agentes[i].getName();
			listaAgentes[i] = agenteID.getLocalName();
		}
		return listaAgentes;
	}

}
