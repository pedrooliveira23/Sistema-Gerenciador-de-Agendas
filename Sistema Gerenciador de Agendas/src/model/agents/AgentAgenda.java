package model.agents;

import java.util.ArrayList;

import com.google.gson.Gson;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import model.agentes.comportamentos.FazerCheckIn;
import model.entidades.Agendamento;

public class AgentAgenda extends Agent {

	private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();

	protected void setup() {
		setAgendamentos(new ArrayList());
		addBehaviour(new FazerCheckIn());
		
        addBehaviour(new TickerBehaviour(this, 500) {
            
        protected void onTick() {
                ACLMessage msgRx = receive();
                if (msgRx != null) {
                    if(msgRx.getContent().startsWith("Agendamento:")) {
                    	String msg = msgRx.getContent().replaceAll("Agendamento:", "");
                    	Gson gson = new Gson();
                    	Agendamento agendamento = gson.fromJson(msg, Agendamento.class);
                    	if(agendamentos.contains(agendamento)) {
                    		sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Não tem");
                    	} else {
                    		agendamentos.add(agendamento);
                    	}
                    }
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
	
	protected void sendMessageRespostaSolicitacao(ACLMessage msgTxParam, AID aidParam, String languageParam, String contentParam) {
		ACLMessage msgTx = msgTxParam;
		msgTx.addReceiver(aidParam);
		msgTx.setLanguage(languageParam);
		msgTx.setContent(contentParam);
		send(msgTx);
		System.out.println(getAID().getName() + ": enviou mensagem: " + msgTx.toString());
	}
}
