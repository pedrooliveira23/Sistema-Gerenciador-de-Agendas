package model.agents.gui;


import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import model.bo.BoSolicitarAgendamento;
import model.entidades.Agendamento;
import view.ViewPainelDeControle;
import view.ViewSolicitarAgendamento;

public class AgentSolicitarAgendamento extends GuiAgent {
	private BoSolicitarAgendamento boSolicitarAgendamento = new BoSolicitarAgendamento();
	private ViewSolicitarAgendamento solicitarAgendamento;
	private AID AIDagenteLocal;
	public static final int SOLICITAR = 0;
	public static final int CANCELAR = 1;
	public Date data;
	public int horaInicial;
	public int horaFinal;
	public int minutoInicial;
	public int minutoFinal;
	public String local;
	public String objetivo;
	public Object[] participantes;

	protected void setup() {
		solicitarAgendamento = new ViewSolicitarAgendamento(this);
		solicitarAgendamento.getFrame().setVisible(true);
	}

	public String[] obterParticipantes() {
		AID[] agendas = null;
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType( "Agenda" );
		dfd.addServices(sd);

		DFAgentDescription[] result;

		String[] listaAgendas = null;
		try {
			result = DFService.search(this, dfd);        
			System.out.println(result.length + " results" );
			listaAgendas = new String[result.length];
			for(int i = 0; i < result.length; i++) {
				System.out.println(" " + result[0].getName() );
				listaAgendas[i] = result[i].getName().getLocalName();
				if(result[0].getName().getLocalName().equals(AgentPainelDeControle.nomeAgente)) {
					AIDagenteLocal = result[0].getName();
				}
			}
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return listaAgendas;
	}


	@Override
	protected void onGuiEvent(GuiEvent arg0) {
		switch (arg0.getType()) {
		case SOLICITAR:
			Agendamento agendamento = new Agendamento(data, horaInicial, horaFinal, minutoInicial, minutoFinal, local, objetivo, participantes);
			Gson gson = new Gson();
			String msg = gson.toJson(agendamento);
			sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), AIDagenteLocal,"Portugues",msg);
			break;
		case CANCELAR:
			solicitarAgendamento.getFrame().dispose();
			solicitarAgendamento = null;
			doDelete();
			break;
		}

	}

	protected void sendMessageSolicitacao(ACLMessage msgTxParam, AID aidParam, String languageParam, String contentParam) {
		ACLMessage msgTx = msgTxParam;
		msgTx.addReceiver(aidParam);
		msgTx.setLanguage(languageParam);
		msgTx.setContent(contentParam);
		send(msgTx);
		System.out.println(getAID().getName() + ": enviou mensagem: " + msgTx.toString());
	}
}
