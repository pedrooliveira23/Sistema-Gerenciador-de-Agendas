package controller;

import java.util.ArrayList;

import model.agentes.AgenteAgenda;
import model.bo.BoSolicitarAgendamento;

public class ControllerSolicitarAgendamento {
	private BoSolicitarAgendamento solicitarAgendamento = new BoSolicitarAgendamento();

	public String[] obterParticipantes() {
		return solicitarAgendamento.obterAgentes();
	}
}
