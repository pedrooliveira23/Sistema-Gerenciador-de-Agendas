package controller;

import java.util.ArrayList;

import model.agentes.AgenteAgenda;
import model.bo.BoSolicitarAgendamento;

public class ControllerSolicitarAgendamento {
	BoSolicitarAgendamento solicitarAgendamento = new BoSolicitarAgendamento();
	ControllerJadeContainer container = new ControllerJadeContainer();
	public ArrayList<AgenteAgenda> obterParticipantes() {
		return solicitarAgendamento.obterAgentes(container.getContainer());
	}
}
