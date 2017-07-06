package model.agents.gui;

import javax.swing.JOptionPane;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import model.bo.BoPainelDeControle;
import view.ViewPainelDeControle;

public class AgentPainelDeControle extends GuiAgent {
	private BoPainelDeControle painelDeControle = new BoPainelDeControle();
	private ViewPainelDeControle painel;
	public static String nomeAgente = "Visitante" + (int)(Math.random() * 9999999);

	public static final int CONSULTAR = 0;
	public static final int SOLICITAR = 1;
	public static final int CONFIGURAR = 2;
	public static final int SAIR = 3;

	protected void setup() {
		painel = new ViewPainelDeControle(this);
		painel.getFrame().setVisible(true);
		this.CriarAgent(nomeAgente, "model.agents.AgentAgenda");
		this.CriarAgent("A", "model.agents.AgentAgenda");
		this.CriarAgent("B", "model.agents.AgentAgenda");
		this.CriarAgent("C", "model.agents.AgentAgenda");
		this.CriarAgent("D", "model.agents.AgentAgenda");
		this.CriarAgent("E", "model.agents.AgentAgenda");
		this.CriarAgent("F", "model.agents.AgentAgenda");
		
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
					if(msgRx.getContent().startsWith("Confirmar Agendamento")) {
						int opcao = JOptionPane.showConfirmDialog(null, "Confirmar Agendamento", "Confirmar Agendamento", JOptionPane.YES_NO_OPTION);
						if (opcao == JOptionPane.YES_OPTION) {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Confirmar:true");
						} else {
							sendMessageRespostaSolicitacao(new ACLMessage(ACLMessage.INFORM), msgRx.getSender(), "Portugues", "Confirmar:false");
						}
					}
				}
			}
		});
	}

	private void CriarAgent(String nome, String classe) {
		painelDeControle.criarAgent(nome, classe, this.getContainerController());
	}

	private void mudarNomeAgente(String nome, String classe) {
		painelDeControle.mudarNomeAgente(nome, classe, this.getContainerController());
	}

	@Override
	protected void onGuiEvent(GuiEvent ge) {
		switch (ge.getType()) {
		case CONSULTAR: 
			break;
		case SOLICITAR:
			CriarAgent("SolicitarAgendamentoGUI", "model.agents.gui.AgentSolicitarAgendamento");
			break;
		case CONFIGURAR:
			CriarAgent("ConfiguracaoGUI", "model.agents.gui.AgentConfiguracoes");
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
}

