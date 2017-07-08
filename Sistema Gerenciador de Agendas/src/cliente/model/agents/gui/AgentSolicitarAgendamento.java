package cliente.model.agents.gui;


import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.model.bo.BoSolicitarAgendamento;
import cliente.model.entidades.Agendamento;
import cliente.view.ViewPainelDeControle;
import cliente.view.ViewSolicitarAgendamento;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentSolicitarAgendamento extends GuiAgent {
	private BoSolicitarAgendamento boSolicitarAgendamento = new BoSolicitarAgendamento();
	private ViewSolicitarAgendamento solicitarAgendamento;
	private AID AIDAgenteLocal;
	public static final int SOLICITAR = 0;
	public static final int CANCELAR = 1;
	public String data;
	public int horaInicial;
	public int horaFinal;
	public int minutoInicial;
	public int minutoFinal;
	public String local;
	public String objetivo;
	public String[] participantes;
	private Agendamento agendamento;
	
	protected void setup() {
		solicitarAgendamento = new ViewSolicitarAgendamento(this);
		solicitarAgendamento.getFrame().setVisible(true);

		addBehaviour(new TickerBehaviour(this, 500) {
			String alerta = "";
			int cont = 0;
			int contAceitosAgentes = 0;
			int contAceitosHumanos = 0;

			protected void onTick() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Agendamento:")) {
						String msg = msgRx.getContent().replaceAll("Agendamento:", "");
						if(msg.equals("true")) {
							alerta += msgRx.getSender().getLocalName() + " possui disponibilidade neste horário.\n";
							cont++;
							contAceitosAgentes++;
						} else {
							int duracao = ((horaFinal*60)+minutoFinal - (horaInicial*60)+minutoInicial);
							
							String[] horarios = msg.split(";");
							
							String resp = "";
							
							if(horarios.length == 1 && horarios[0].equals("")) {							
								alerta += msgRx.getSender().getLocalName() + " não possui disponiblidade no horário indicado.\n";
								cont++;
							} else {
								for(int i = 0; i < horarios.length; i++){
									resp += (Integer.parseInt(horarios[i])/60) + " até " + ((Integer.parseInt(horarios[i])+duracao)/60) +", ";
								}
								alerta += msgRx.getSender().getLocalName() + " estará ocupado neste horário, porém estará disponível das "+resp +"\n";
								cont++;
							}
						}
					} else if(msgRx.getContent().startsWith("Confirmar:")) {
						String msg = msgRx.getContent().replaceAll("Confirmar:", "");
						if(msg.equals("true")) {
							alerta += msgRx.getSender().getLocalName() + " confirmou a participação na reunião.\n";
							cont++;
							contAceitosHumanos++;
						} else {
							alerta += msgRx.getSender().getLocalName() + " não confirmou a participação na reunião.\n";
							cont++;
						}
					}
				}
				if(participantes != null) {
					if(cont == participantes.length) {
						if(alerta.contains("não confirmou a participação na reunião")) {
							alerta +="\nNão será possível agendar a reunião!\n";
						}
						
						JOptionPane.showMessageDialog(null, alerta);
						
						if(contAceitosAgentes == participantes.length) {
							DFAgentDescription dfd = new DFAgentDescription();
							ServiceDescription sd  = new ServiceDescription();
							sd.setType( "Agenda" );
							dfd.addServices(sd);

							DFAgentDescription[] result;
							try {
								result = DFService.search(myAgent, dfd);
								int pos = 0;
								AID[] participantes = new AID[result.length];
								for(int i = 0; i < result.length; i++) {
									for(int j = 0; j< agendamento.getParticipantes().length; j++) {
										if(result[i].getName().getLocalName().equals(agendamento.getParticipantes()[j].toString())) {
											participantes[pos] = result[i].getName();
											pos++;
										}
									}
								}


								for(int i = 0; i < participantes.length; i++){
									sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), participantes[i],"Portugues","Confirmar Agendamento");
								}
							} catch (FIPAException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						if(contAceitosHumanos == participantes.length) {
							DFAgentDescription dfd = new DFAgentDescription();
							ServiceDescription sd  = new ServiceDescription();
							sd.setType( "Agenda" );
							dfd.addServices(sd);

							DFAgentDescription[] result;
							try {
								result = DFService.search(myAgent, dfd);
								int pos = 0;
								AID[] participantes = new AID[result.length];
								for(int i = 0; i < result.length; i++) {
									for(int j = 0; j< agendamento.getParticipantes().length; j++) {
										if(result[i].getName().getLocalName().equals(agendamento.getParticipantes()[j].toString())) {
											participantes[pos] = result[i].getName();
											pos++;
										}
									}
								}


								for(int i = 0; i < participantes.length; i++){
									sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), participantes[i],"Portugues","Agendar");
								}
							} catch (FIPAException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						alerta="";
						cont=0;
						contAceitosAgentes = 0;
						contAceitosHumanos = 0;
					}
				}
			}
		});
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
				System.out.println(" " + result[i].getName() );
				listaAgendas[i] = result[i].getName().getLocalName();
				if(result[i].getName().getLocalName().equals(AgentPainelDeControle.nomeAgente)) {
					AIDAgenteLocal = result[i].getName();
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
			agendamento = new Agendamento(data, horaInicial, horaFinal, minutoInicial, minutoFinal, local, objetivo, participantes, AIDAgenteLocal.getLocalName());
			Gson gson = new Gson();
			String msg = "Agendamento:";
			msg += gson.toJson(agendamento);

			DFAgentDescription dfd = new DFAgentDescription();
			ServiceDescription sd  = new ServiceDescription();
			sd.setType( "Agenda" );
			dfd.addServices(sd);

			DFAgentDescription[] result;
			try {
				result = DFService.search(this, dfd);
				int pos = 0;
				AID[] participantes = new AID[result.length];
				for(int i = 0; i < result.length; i++) {
					for(int j = 0; j< agendamento.getParticipantes().length; j++) {
						if(result[i].getName().getLocalName().equals(agendamento.getParticipantes()[j].toString())) {
							participantes[pos] = result[i].getName();
							pos++;
						}
					}
				}


				for(int i = 0; i < participantes.length; i++){
					sendMessageSolicitacao(new ACLMessage(ACLMessage.REQUEST), participantes[i],"Portugues",msg);
				}
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        

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
