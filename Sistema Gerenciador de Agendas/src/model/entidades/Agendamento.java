package model.entidades;

import java.util.Date;

import model.agents.AgentAgenda;

public class Agendamento {
	private Date data;
	private String Objetivo;
	private AgentAgenda[] participantes;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getObjetivo() {
		return Objetivo;
	}
	public void setObjetivo(String objetivo) {
		Objetivo = objetivo;
	}
	public AgentAgenda[] getParticipantes() {
		return participantes;
	}
	public void setParticipantes(AgentAgenda[] participantes) {
		this.participantes = participantes;
	}
}
