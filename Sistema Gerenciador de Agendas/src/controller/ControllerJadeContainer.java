package controller;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ContainerController;

public class ControllerJadeContainer {
	private ContainerController container;
	public ControllerJadeContainer() {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "Agendas");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		setContainer(runtime.createAgentContainer(profile));
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
	}
}
