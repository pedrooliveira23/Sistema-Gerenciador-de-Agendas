package servidor;
import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Servidor {

	public static void main(String[] args) {
		Boot jadeBoot = new Boot();
		String[] jadeArgs = new String[1];
		jadeArgs[0] = "-gui";
		jadeBoot.main(jadeArgs);
	}

}
