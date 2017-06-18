package model.bo;

import controller.ControllerJadeContainer;
import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class BoPainelDeControle {

	private static String nomeAgente;	
	private ControllerJadeContainer container;
	public BoPainelDeControle() {
		nomeAgente = "Visitante" + (int) (Math.random() * 999999999);
		container = new ControllerJadeContainer();
	}

	public void encerrar() {
		System.exit(0);
	}
	
	public void abrirRMA() {
		Boot jadeBoot = new Boot();
		String[] jadeArgs = new String[1];
		jadeArgs[0] = "-gui";
		jadeBoot.main(jadeArgs);
	}

	public void criarAgente() {
		try {
			AgentController ag = container.getContainer().createNewAgent(getNomeAgente(), 
					"model.agentes.AgenteAgenda", 
					new Object[] {});//arguments
			ag.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

	}

	public void destruirAgente(){
		try {
			container.getContainer().getAgent(getNomeAgente()).kill();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	public void mudarNomeAgente(String nome) {
		destruirAgente();
		setNomeAgente(nome);
		criarAgente();
	}

	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}


}