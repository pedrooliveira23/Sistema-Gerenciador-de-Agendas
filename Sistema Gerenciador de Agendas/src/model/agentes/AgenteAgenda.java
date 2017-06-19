package model.agentes;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import model.entidades.Agendamento;

public class AgenteAgenda extends Agent {
	
	private ArrayList<Agendamento> agendamentos;
	protected void setup() {
		setAgendamentos(new ArrayList());
		System.out.println("blabla " + this.getAID().getLocalName());
	}
	
	public ArrayList<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(ArrayList<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
}
