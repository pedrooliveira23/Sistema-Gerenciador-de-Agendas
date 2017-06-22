package model.agents.gui;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
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
}

