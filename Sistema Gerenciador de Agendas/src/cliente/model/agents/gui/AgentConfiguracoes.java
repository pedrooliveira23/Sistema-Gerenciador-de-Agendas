package cliente.model.agents.gui;

import cliente.model.bo.BoConfiguracoes;
import cliente.model.bo.BoPainelDeControle;
import cliente.view.ViewConfiguracoes;
import cliente.view.ViewPainelDeControle;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

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
				salvarConfiguracoes(configuracoes.getNomeAgente(), "cliente.model.agents.AgentAgenda");
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
