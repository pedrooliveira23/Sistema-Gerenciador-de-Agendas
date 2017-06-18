package agentes;

import jade.core.Agent;

public class AgenteAgenda extends Agent {
	protected void setup() {
		System.out.println(this.getAID().getLocalName());
		doDelete();
	}
}
