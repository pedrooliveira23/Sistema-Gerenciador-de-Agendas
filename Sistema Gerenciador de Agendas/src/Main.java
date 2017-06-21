import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {

	public static void main(String[] args) {
		Boot jadeBoot = new Boot();
		String[] jadeArgs = new String[1];
		jadeArgs[0] = "-gui";
		jadeBoot.main(jadeArgs);
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "Agendas");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		ContainerController container = runtime.createAgentContainer(profile);
		AgentController ag;
		try {
			ag = container.createNewAgent("Gui", 
					"model.agents.AgentPainelDeControle", 
					new Object[] {});
			ag.start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//arguments
	}

}
