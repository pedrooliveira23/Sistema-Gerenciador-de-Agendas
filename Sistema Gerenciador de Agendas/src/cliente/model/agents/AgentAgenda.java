package cliente.model.agents;

import java.util.ArrayList;

import com.google.gson.Gson;

import cliente.model.agents.gui.AgentPainelDeControle;
import cliente.model.entidades.Agendamento;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgentAgenda extends Agent {
	private Agendamento agendamento;
	private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
	private AID senderOriginal;

	protected void setup() {

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Agenda");
		sd.setName(this.getLocalName());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}


		setAgendamentos(new ArrayList());

		addBehaviour(new TickerBehaviour(this, 500) {

			protected void onTick() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Verificar Disponibilidade:")) {
						trataMensagemSolicitacaoAgendamento(msgRx);
					} else if (msgRx.getContent().startsWith("Solicitar Agendamento")) {
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
									sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.REQUEST), result[i].getName(), "Portugues", "Solicitar Agendamento:" + agendamento.getSolicitante() + " deseja marcar uma reunião para " + agendamento.getObjetivo() + " no dia " + agendamento.getData() + " às " + agendamento.getHoraInicial() + "h" + agendamento.getMinutoInicial() + " até às " + agendamento.getHoraFinal() + "h" + agendamento.getMinutoFinal() + ", no " + agendamento.getLocal() + ", deseja participar?");
								}
							}
						} catch (FIPAException e) {
							e.printStackTrace();
						}
					} else if (msgRx.getContent().startsWith("Confirmar Agendamento")) {
						String msg = msgRx.getContent().replaceAll("Confirmar Agendamento:", "");
						if(msg.equals("true")) {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), senderOriginal, "Portugues", "Confirmar Agendamento:true");
						} else {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), senderOriginal, "Portugues", "Confirmar Agendamento:false");
						}
					} else if (msgRx.getContent().equals("Registrar Agendamento")) {
						agendamentos.add(agendamento);
					} else if (msgRx.getContent().equals("Consultar Agendamentos")) {
						Gson gson = new Gson();
						String msg = "Consultar Agendamentos:";
						msg += gson.toJson(agendamentos);
						sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", msg);
					}

				}
			}

			private void trataMensagemSolicitacaoAgendamento(ACLMessage msgRx) {
				String msg = msgRx.getContent().replaceAll("Verificar Disponibilidade:", "");
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
					sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Verificar Disponibilidade:true"); 

				} else {
					String resp = "";
					for(int i=0;i<listaDeHoras.length;i++) {
						if(listaDeHoras[i] > 0 && listaDeHoras[i]/60 <= 24) {
							resp+=listaDeHoras[i]+";";
						}
					}
					sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Verificar Disponibilidade:"+resp);
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
