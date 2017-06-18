package model.entidades;

import java.util.Date;

import model.agentes.AgenteAgenda;

public class Agendamento {
	private Date data;
	private String Objetivo;
	private AgenteAgenda[] participantes;
	
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
	public AgenteAgenda[] getParticipantes() {
		return participantes;
	}
	public void setParticipantes(AgenteAgenda[] participantes) {
		this.participantes = participantes;
	}
}
