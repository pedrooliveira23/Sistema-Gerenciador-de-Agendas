package model.bo;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class BoPainelDeControle {

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
		//Get the JADE runtime interface (singleton)
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		//Create a Profile, where the launch arguments are stored
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "Agendas");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		//create a non-main agent container
		ContainerController container = runtime.createAgentContainer(profile);
		try {
		        AgentController ag = container.createNewAgent("teste1", 
		                                      "model.agentes.AgenteAgenda", 
		                                      new Object[] {});//arguments
		        ag.start();
		} catch (StaleProxyException e) {
		    e.printStackTrace();
		}
		
	}
	

}
