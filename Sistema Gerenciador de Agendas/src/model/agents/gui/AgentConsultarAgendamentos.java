package model.agents.gui;

import com.google.gson.Gson;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.ArrayList;
import model.bo.BoConfiguracoes;
import model.bo.BoPainelDeControle;
import model.entidades.Agendamento;
import view.ViewConfiguracoes;
import view.ViewConsultarAgendamentos;
import view.ViewPainelDeControle;

public class AgentConsultarAgendamentos extends GuiAgent {
	private static BoPainelDeControle painelDeControle = new BoPainelDeControle();
	transient protected ViewConsultarAgendamentos consultarAgendamentos;
	public static final int CANCELAR = 0;
	public static final int REMOVER = 1;
	
	protected void setup() {
		consultarAgendamentos = new ViewConsultarAgendamentos(this);
		consultarAgendamentos.getFrame().setVisible(true);
		addBehaviour(new TickerBehaviour(this, 500) {
			protected void onTick() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Lista de Agendamentos:")) {
						String msg = msgRx.getContent().replaceAll("Lista de Agendamentos:", "");
						Gson gson = new Gson();
						ArrayList agendamentos = gson.fromJson(msg, ArrayList.class);
					}
				}
			}
		});
	}
	
	private void salvarConfiguracoes(String nome, String classe) {
		painelDeControle.mudarNomeAgente(nome, classe, this.getContainerController());
	}
	
	public void obterAgendamentos() {		
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType( "Agenda" );
		dfd.addServices(sd);

		DFAgentDescription[] result;
		try {
			result = DFService.search(this, dfd);		
			for(int i = 0; i < result.length; i++) {
				if(result[i].getName().getLocalName().equals(AgentPainelDeControle.nomeAgente)) {
					sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), result[i].getName(),"Portugues","Liste Agendamentos");
				}
			}
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@Override
	protected void onGuiEvent(GuiEvent arg0) {
		switch(arg0.getType()) {
			case REMOVER:
				break;
			case CANCELAR:
				break;
		}
	}

}
