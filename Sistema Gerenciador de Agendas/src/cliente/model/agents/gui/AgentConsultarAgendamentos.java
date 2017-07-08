package cliente.model.agents.gui;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cliente.model.entidades.Agendamento;
import cliente.view.ViewConfiguracoes;
import cliente.view.ViewConsultarAgendamentos;
import cliente.view.ViewPainelDeControle;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentConsultarAgendamentos extends GuiAgent {
	transient protected ViewConsultarAgendamentos consultarAgendamentos;
	private AgentPainelDeControle painelDeControle;
	public static final int CANCELAR = 0;
	public static final int REMOVER = 1;
	ArrayList<Agendamento> agendamentos = null;
	private boolean buscarAgendamentos;
	
	protected void setup() {
		painelDeControle = new AgentPainelDeControle();
		consultarAgendamentos = new ViewConsultarAgendamentos(this);
		consultarAgendamentos.getFrame().setVisible(true);
		buscarAgendamentos = true;
		addBehaviour(new TickerBehaviour(this, 1000) {
			protected void onTick() {
				DFAgentDescription dfd = new DFAgentDescription();
				ServiceDescription sd  = new ServiceDescription();
				sd.setType( "Agenda" );
				dfd.addServices(sd);

				DFAgentDescription[] result;
				try {
					result = DFService.search(myAgent, dfd);		
					for(int i = 0; i < result.length; i++) {
						if(result[i].getName().getLocalName().equals(AgentPainelDeControle.nomeAgente)) {
							sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), result[i].getName(),"Portugues","Consultar Agendamentos");
						}
					}
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Consultar Agendamentos:") && buscarAgendamentos) {
						String msg = msgRx.getContent().replaceAll("Consultar Agendamentos:", "");
						Gson gson = new Gson();
						agendamentos = gson.fromJson(msg, new TypeToken<ArrayList<Agendamento>>(){}.getType());
						for (int i = 0; i < agendamentos.size(); i++){
							String data = agendamentos.get(i).getData();
							int hrInicio = agendamentos.get(i).getHoraInicial();
							int minInicial = agendamentos.get(i).getMinutoInicial();
							int hrFinal = agendamentos.get(i).getHoraFinal();
							int minFinal = agendamentos.get(i).getMinutoFinal();
							String solicitante = agendamentos.get(i).getSolicitante();
							String local = agendamentos.get(i).getLocal();
							String objetivo = agendamentos.get(i).getObjetivo();
							String participantes = "";
							for(int j = 0; j < agendamentos.size(); j++) {
								participantes += agendamentos.get(j).getParticipantes()[j] + ", ";
							}

							Object[] dados = {data, hrInicio, minInicial, hrFinal, minFinal, solicitante, local, objetivo, participantes};
							consultarAgendamentos.getTableModel().addRow(dados);
						}
						buscarAgendamentos = false;
					}
				}
			}
		});
	}

	private void salvarConfiguracoes(String nome, String classe) {
		painelDeControle.mudarNomeAgente(nome, classe, this.getContainerController());
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
			consultarAgendamentos.getFrame().dispose();
			consultarAgendamentos = null;
			doDelete();
			break;
		}
	}

}
