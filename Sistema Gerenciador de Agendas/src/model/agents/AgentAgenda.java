package model.agents;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import model.agentes.comportamentos.FazerCheckIn;
import model.entidades.Agendamento;

public class AgentAgenda extends Agent {

	private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();

	protected void setup() {
		setAgendamentos(new ArrayList());
		addBehaviour(new FazerCheckIn());
		
        addBehaviour(new TickerBehaviour(this, 5000) {
            
        protected void onTick() {
                ACLMessage msgRx = receive();
                if (msgRx != null) {
                    System.out.println(msgRx.getSender().getLocalName() + " me mandou mensagem:" +
                            msgRx.getContent());
                }
            }
        });
		
	}
	
    protected void takeDown() {
        try {
            DFService.deregister(this);
            System.out.println("CheckOut " + this.getLocalName());
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
       
    }

	public ArrayList<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(ArrayList<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
}
