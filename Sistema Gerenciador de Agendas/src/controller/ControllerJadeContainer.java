package controller;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class ControllerJadeContainer {
	private ContainerController container;
	private static final ControllerJadeContainer instancia = new ControllerJadeContainer();
	private String nomeAgente;
	
	public String getNomeAgente() {
		return nomeAgente;
	}

	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}

	private ControllerJadeContainer() {

	}
	
	public static ControllerJadeContainer getInstance() {
		return instancia;
	}
	
	public ContainerController getContainer() {
		return container;
	}
	
	public void setContainer(ContainerController container) {
		this.container = container;
	}
	
	public void abrirRMA() {
		Boot jadeBoot = new Boot();
		String[] jadeArgs = new String[1];
		jadeArgs[0] = "-gui";
		jadeBoot.main(jadeArgs);
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "Agendas");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		container = runtime.createAgentContainer(profile);
	}
}
