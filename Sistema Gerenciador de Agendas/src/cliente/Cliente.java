package cliente;

import java.net.NetworkInterface;
import java.net.SocketException;

import javax.swing.JOptionPane;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Cliente {

	public static void main(String[] args) {
		Boot jadeBoot = new Boot();
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.MAIN_HOST, JOptionPane.showInputDialog(null, "Endereço do servidor:"));
		ContainerController container = runtime.createAgentContainer(profile);
		AgentController ag;
		try {
			ag = container.createNewAgent("PainelDeControleGUI-" + NetworkInterface.getNetworkInterfaces().nextElement().getHardwareAddress(), 
					"cliente.model.agents.gui.AgentPainelDeControle", 
					new Object[] {});
			ag.start();
		} catch (StaleProxyException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//arguments
	}

}
