package cliente.model.entidades;

import java.util.ArrayList;
import java.util.Date;

import cliente.model.agents.AgentAgenda;

public class Agendamento {
	private String data;
	private int horaInicial;
	private int horaFinal;
	private int minutoInicial;
	private int minutoFinal;
	private String local;
	private String objetivo;
	private String[] participantes;
	private String solicitante;
	
	public Agendamento(String data2, int horaInicial, int horaFinal, int minutoInicial, int minutoFinal, String local, String objetivo, String[] participantes2, String solicitante) {
		this.data = data2;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.minutoInicial = minutoInicial;
		this.minutoFinal = minutoFinal;
		this.local = local;
		this.objetivo = objetivo;
		this.participantes = participantes2;
		this.setSolicitante(solicitante);
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(int horaInicial) {
		this.horaInicial = horaInicial;
	}
	public int getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}
	public int getMinutoInicial() {
		return minutoInicial;
	}
	public void setMinutoInicial(int minutoInicial) {
		this.minutoInicial = minutoInicial;
	}
	public int getMinutoFinal() {
		return minutoFinal;
	}
	public void setMinutoFinal(int minutoFinal) {
		this.minutoFinal = minutoFinal;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String[] getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String[] participantes) {
		this.participantes = participantes;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
}
