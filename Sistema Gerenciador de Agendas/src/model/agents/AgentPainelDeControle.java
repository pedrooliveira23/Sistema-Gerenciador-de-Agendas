package model.agents;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import model.bo.BoPainelDeControle;
import view.ViewPainelDeControle;

public class AgentPainelDeControle extends GuiAgent {
	private BoPainelDeControle painelDeControle = new BoPainelDeControle();
	private ViewPainelDeControle painel;
	
	public static final int CONSULTAR = 0;
	public static final int SOLICITAR = 1;
	public static final int CONFIGURAR = 2;
	public static final int SAIR = 3;
	
	protected void setup() {
		painel = new ViewPainelDeControle(this);
		painel.getFrame().setVisible(true);
		this.CriarAgent("Visitante" + (int)(Math.random() * 9999999), "model.agents.AgentAgenda");
	}

	private void CriarAgent(String nome, String classe) {
		painelDeControle.criarAgent(nome, classe, this.getContainerController());
	}

	private void mudarNomeAgente(String nome, String classe) {
		painelDeControle.mudarNomeAgente(nome, classe, this.getContainerController());
	}
	
	private String obterNomeAgente() {
		return painelDeControle.getNomeAgente();
	}

	@Override
	protected void onGuiEvent(GuiEvent ge) {
		switch (ge.getType()) {
			case CONSULTAR: 
				break;
			case SOLICITAR:
				break;
			case CONFIGURAR:
				CriarAgent("ConfiguracaoGUI", "model.agents.AgentConfiguracoes");
				break;
			case SAIR:
				painel.getFrame().dispose();
				painel = null;
				doDelete();
				break;
		}
		
	}
}

