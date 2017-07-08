package cliente.model.agents.gui;

import javax.swing.JOptionPane;

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
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class AgentPainelDeControle extends GuiAgent {

	private ViewPainelDeControle painel;
	public static String nomeAgente = "Visitante" + (int)(Math.random() * 9999999);

	public static final int CONSULTAR = 0;
	public static final int SOLICITAR = 1;
	public static final int CONFIGURAR = 2;
	public static final int SAIR = 3;

	protected void setup() {
		painel = new ViewPainelDeControle(this);
		painel.getFrame().setVisible(true);
		AgentPainelDeControle.criarAgent(nomeAgente, "cliente.model.agents.AgentAgenda", this.getContainerController());

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Gui");
		sd.setName(this.getLocalName());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}

		addBehaviour(new TickerBehaviour(this, 500) {

			protected void onTick() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					if(msgRx.getContent().startsWith("Solicitar Agendamento")) {
						int opcao = JOptionPane.showConfirmDialog(null, msgRx.getContent().replace("Solicitar Agendamento:", ""), "Confirmar Agendamento", JOptionPane.YES_NO_OPTION);
						if (opcao == JOptionPane.YES_OPTION) {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Confirmar Agendamento:true");
						} else {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Confirmar Agendamento:false");
						}
					}
				}
			}
		});
	}

	@Override
	protected void onGuiEvent(GuiEvent ge) {
		switch (ge.getType()) {
		case CONSULTAR:
			criarAgent("ConsultarAgendamentoGUI", "cliente.model.agents.gui.AgentConsultarAgendamentos", this.getContainerController());
			break;
		case SOLICITAR:
			criarAgent("SolicitarAgendamentoGUI", "cliente.model.agents.gui.AgentSolicitarAgendamento", this.getContainerController());
			break;
		case CONFIGURAR:
			criarAgent("ConfiguracaoGUI", "cliente.model.agents.gui.AgentConfiguracoes", this.getContainerController());
			break;
		case SAIR:
			painel.getFrame().dispose();
			painel = null;
			doDelete();
			break;
		}

	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}

	protected void sendMessageRespostaSolicitacao(ACLMessage msgTxParam, AID aidParam, String languageParam, String contentParam) {
		ACLMessage msgTx = msgTxParam;
		msgTx.addReceiver(aidParam);
		msgTx.setLanguage(languageParam);
		msgTx.setContent(contentParam);
		send(msgTx);
		System.out.println(getAID().getName() + ": enviou mensagem: " + msgTx.toString());
	}

	public static void criarAgent(String nome, String classe, ContainerController container) {
		try {
			container.getAgent(nome);
			if (classe.equals("model.agents.AgentAgenda")) {
				AgentPainelDeControle.nomeAgente = nome;
			}
		} catch (ControllerException ex) {
			try {
				AgentController ag = container.createNewAgent(nome, 
						classe, 
						new Object[] {});//arguments
				ag.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		}
	}

	public static void destruirAgent(String nomeAnterior, ContainerController container){
		try {
			container.getAgent(nomeAnterior).kill();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	public static void mudarNomeAgente(String nomeNovo, String classe, ContainerController container) {
		destruirAgent(AgentPainelDeControle.nomeAgente, container);
		criarAgent(nomeNovo, classe, container);
		AgentPainelDeControle.nomeAgente = nomeNovo;
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

	}
}

