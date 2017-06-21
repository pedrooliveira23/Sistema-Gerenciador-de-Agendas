package model.agents.gui;


import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import model.bo.BoSolicitarAgendamento;
import view.ViewPainelDeControle;
import view.ViewSolicitarAgendamento;

public class AgentSolicitarAgendamento extends GuiAgent {
	private BoSolicitarAgendamento boSolicitarAgendamento = new BoSolicitarAgendamento();
	private ViewSolicitarAgendamento solicitarAgendamento;
	public static final int SOLICITAR = 0;
	public static final int CANCELAR = 1;
	
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
				break;
			case CANCELAR:
				solicitarAgendamento.getFrame().dispose();
				solicitarAgendamento = null;
				doDelete();
				break;
		}
		
	}
}
