package controller;

import java.util.ArrayList;

import model.agentes.AgenteAgenda;
import model.bo.BoSolicitarAgendamento;

public class ControllerSolicitarAgendamento {
	private BoSolicitarAgendamento solicitarAgendamento = new BoSolicitarAgendamento();
	private ControllerPainelDeControle painelDeControle = null;
	private ControllerJadeContainer container = null;
	public ControllerSolicitarAgendamento(ControllerJadeContainer container) {
		this.container = container;
		painelDeControle = new ControllerPainelDeControle(container);
	}
	public String[] obterParticipantes() {
		return solicitarAgendamento.obterAgentes(container.getContainer(), painelDeControle.obterNomeAgente());
	}
}
