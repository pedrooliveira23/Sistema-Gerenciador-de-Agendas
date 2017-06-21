package controller;

import java.util.ArrayList;

import jade.wrapper.ControllerException;
import model.agents.AgentAgenda;
import model.bo.BoSolicitarAgendamento;

public class ControllerSolicitarAgendamento {
	private BoSolicitarAgendamento solicitarAgendamento = new BoSolicitarAgendamento();
	private ControllerJadeContainer container; 

	public ControllerSolicitarAgendamento() {
		container = container.getInstance();
	}
	
	public String[] obterParticipantes() {
		String[] lista = null;
		try {
			lista = solicitarAgendamento.obterAgentes();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
}
