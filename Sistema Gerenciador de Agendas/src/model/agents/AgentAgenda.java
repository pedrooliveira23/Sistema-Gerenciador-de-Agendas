package model.agents;

import java.util.ArrayList;

import com.google.gson.Gson;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import model.agentes.comportamentos.FazerCheckIn;
import model.agents.gui.AgentPainelDeControle;
import model.entidades.Agendamento;

public class AgentAgenda extends Agent {
	private Agendamento agendamento;
	private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
	private AID senderOriginal;

	protected void setup() {
		setAgendamentos(new ArrayList());
		addBehaviour(new FazerCheckIn());

		addBehaviour(new TickerBehaviour(this, 500) {

			protected void onTick() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Agendamento:")) {
						trataMensagemSolicitacaoAgendamento(msgRx);
					} else if (msgRx.getContent().startsWith("Confirmar Agendamento")) {
						senderOriginal = msgRx.getSender();
						AID[] agendas = null;
						DFAgentDescription dfd = new DFAgentDescription();
						ServiceDescription sd  = new ServiceDescription();
						sd.setType( "Gui" );
						dfd.addServices(sd);

						DFAgentDescription[] result;

						try {
							result = DFService.search(myAgent, dfd);        
							System.out.println(result.length + " results" );
							for(int i = 0; i < result.length; i++) {
								if(result[i].getName().getLocalName().equals("PainelDeControleGUI")) {
									sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), result[i].getName(), "Portugues", "Confirmar Agendamento");
								}
							}
						} catch (FIPAException e) {
							e.printStackTrace();
						}
					} else if (msgRx.getContent().startsWith("Confirmar")) {
						String msg = msgRx.getContent().replaceAll("Confirmar:", "");
						if(msg.equals("true")) {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), senderOriginal, "Portugues", "Confirmar:true");
						} else {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), senderOriginal, "Portugues", "Confirmar:false");
						}
					} else if (msgRx.getContent().equals("Agendar")) {
						agendamentos.add(agendamento);
					}
				}
			}

			private void trataMensagemSolicitacaoAgendamento(ACLMessage msgRx) {
				String msg = msgRx.getContent().replaceAll("Agendamento:", "");
				Gson gson = new Gson();
				agendamento = gson.fromJson(msg, Agendamento.class);
				int duracao = ((agendamento.getHoraFinal()*60)+agendamento.getMinutoFinal() - (agendamento.getHoraInicial()*60)+agendamento.getMinutoInicial());
				int novoInicial = (agendamento.getHoraInicial()*60) + agendamento.getMinutoInicial();
				int novoFinal = (agendamento.getHoraFinal()*60) + agendamento.getMinutoFinal();
				int[] listaDeHoras = new int[5];
				listaDeHoras[0] = podeAgendar(novoInicial, agendamento.getData(), duracao);
				listaDeHoras[1] = podeAgendar(novoInicial-duracao, agendamento.getData(), duracao);
				listaDeHoras[2] = podeAgendar(novoInicial-(duracao*2), agendamento.getData(), duracao);
				listaDeHoras[3] = podeAgendar(novoFinal+duracao, agendamento.getData(), duracao);
				listaDeHoras[4] = podeAgendar(novoFinal+(duracao*2), agendamento.getData(), duracao);



				if(novoInicial == listaDeHoras[0]) {
					sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Agendamento:true"); 

				} else {
					String resp = "";
					for(int i=0;i<listaDeHoras.length;i++) {
						if(listaDeHoras[i] > 0 && listaDeHoras[i]/60 <= 24) {
							resp+=listaDeHoras[i]+";";
						}
					}
					sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Agendamento:"+resp);
				}
			}

			private int podeAgendar(int novoInicial,String data, int duracao) {

				for(Agendamento ag : agendamentos) {
					String dataExistente = ag.getData();
					String dataNova = data;
					int existenteInicial = (ag.getHoraInicial()*60) + ag.getMinutoInicial();
					int existenteFinal = (ag.getHoraFinal()*60) + ag.getMinutoFinal();
					int novoFinal = novoInicial + duracao;

					if(existenteInicial == novoInicial) {
						novoInicial = 0;
					}

					if(existenteInicial < novoInicial) {
						if (!((novoInicial > existenteFinal) && ((existenteInicial < novoFinal) && (novoFinal > existenteFinal)))) {
							novoInicial = 0;
						}
					} else {
						if ((novoInicial > existenteFinal) && ((existenteInicial < novoFinal) && (novoFinal > existenteFinal))) {
							novoInicial = 0;
						}
					}
				}
				return novoInicial;
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
