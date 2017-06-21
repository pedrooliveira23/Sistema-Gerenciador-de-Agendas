package model.agents.gui;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import model.bo.BoConfiguracoes;
import model.bo.BoPainelDeControle;
import view.ViewConfiguracoes;
import view.ViewPainelDeControle;

public class AgentConfiguracoes extends GuiAgent {
	private static BoPainelDeControle painelDeControle = new BoPainelDeControle();
	transient protected ViewConfiguracoes configuracoes;
	public static final int CANCELAR = 0;
	public static final int SALVAR = 1;
	
	protected void setup() {
		configuracoes = new ViewConfiguracoes(this);
		configuracoes.getFrame().setVisible(true);
	}
	
	private void salvarConfiguracoes(String nome, String classe) {
		painelDeControle.mudarNomeAgente(nome, classe, this.getContainerController());
	}
	@Override
	protected void onGuiEvent(GuiEvent arg0) {
		switch(arg0.getType()) {
			case SALVAR:
				salvarConfiguracoes(configuracoes.getNomeAgente(), "model.agents.AgentAgenda");
				configuracoes.getFrame().dispose();
				configuracoes = null;
				doDelete();
				break;
			case CANCELAR:
				configuracoes.getFrame().dispose();
				configuracoes = null;
				doDelete();
				break;
		}
	}

}
