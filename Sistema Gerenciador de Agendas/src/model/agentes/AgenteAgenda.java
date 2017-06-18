package model.agentes;

import jade.core.AID;
import jade.core.Agent;

public class AgenteAgenda extends Agent {
	protected void setup() {
		System.out.println("blabla " + this.getAID().getLocalName());
	}
}
