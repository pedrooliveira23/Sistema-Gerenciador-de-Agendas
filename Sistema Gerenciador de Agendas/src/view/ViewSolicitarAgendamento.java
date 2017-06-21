package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import controller.ControllerJadeContainer;
import jade.gui.GuiEvent;
import model.agents.gui.AgentSolicitarAgendamento;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ViewSolicitarAgendamento {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JCalendar calendar;

	/**
	 * Launch the application.
	 */
	private AgentSolicitarAgendamento controller;
	private DefaultListModel listaDeParticipantes;


	/**
	 * Create the application.
	 * @param container 
	 */

	public ViewSolicitarAgendamento() {
		initialize();
	}


	/**
	 * @wbp.parser.constructor
	 */
	public ViewSolicitarAgendamento(AgentSolicitarAgendamento agentSolicitarAgendamento) {
		controller = agentSolicitarAgendamento;
		listaDeParticipantes = new DefaultListModel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		calendar = new JCalendar();
		frame.getContentPane().add(calendar);
		calendar.setBounds(10, 10, 240, 240);

		String[] horas = new String[24];
		for(int i=0; i<24;i++) {
			horas[i] = Integer.toString(i+1);
		}
		JComboBox cbHoras = new JComboBox(horas);
		cbHoras.setBounds(262, 37, 65, 24);
		frame.getContentPane().add(cbHoras);		

		String[] minutos = new String[60];
		for(int i=0; i<60;i++) {
			minutos[i] = Integer.toString(i+1);
		}
		JComboBox cbMinutos = new JComboBox(minutos);
		cbMinutos.setBounds(339, 37, 65, 24);
		frame.getContentPane().add(cbMinutos);

		JLabel lblHora = new JLabel("Horas:");
		lblHora.setBounds(268, 10, 59, 15);
		frame.getContentPane().add(lblHora);

		JLabel lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(339, 10, 74, 15);
		frame.getContentPane().add(lblMinutos);

		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setBounds(268, 73, 110, 15);
		frame.getContentPane().add(lblParticipantes);

		JComboBox comboBox = new JComboBox(controller.obterParticipantes());
		comboBox.setBounds(262, 90, 142, 24);
		frame.getContentPane().add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		JList list = new JList();
		scrollPane.setBounds(262, 163, 142, 87);
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		list.setModel(listaDeParticipantes);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!listaDeParticipantes.contains(comboBox.getSelectedItem().toString())) {
					listaDeParticipantes.addElement(comboBox.getSelectedItem().toString());
				}
			}
		});
		button.setBounds(360, 126, 44, 25);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < listaDeParticipantes.size(); i++) {
					if(listaDeParticipantes.get(i).equals(list.getSelectedValue().toString())) {
						listaDeParticipantes.remove(i);
					}
				}
			}
		});
		button_1.setBounds(262, 126, 44, 25);
		frame.getContentPane().add(button_1);

		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.SOLICITAR);
				controller.postGuiEvent(evento);
			}
		});
		btnSolicitar.setBounds(268, 381, 136, 25);
		frame.getContentPane().add(btnSolicitar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CANCELAR);
				controller.postGuiEvent(evento);
			}
		});
		btnCancelar.setBounds(10, 381, 117, 25);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(10, 262, 70, 15);
		frame.getContentPane().add(lblObjetivo);
		
		JScrollPane scrollPaneTextArea = new JScrollPane();
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPaneTextArea.setBounds(10, 282, 394, 87);
		scrollPaneTextArea.setViewportView(textArea);
		frame.getContentPane().add(scrollPaneTextArea);
	}
}
