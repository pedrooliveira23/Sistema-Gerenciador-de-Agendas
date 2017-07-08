package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

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
import javax.swing.SwingConstants;

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
	private JTextField textField;


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
		frame.setBounds(100, 100, 420, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		calendar = new JCalendar();
		frame.getContentPane().add(calendar);
		calendar.setBounds(10, 49, 240, 310);

		String[] horas = new String[24];
		for(int i=0; i<24;i++) {
			horas[i] = Integer.toString(i+1);
		}
		JComboBox cbHoras = new JComboBox(horas);
		cbHoras.setBounds(262, 168, 65, 24);
		frame.getContentPane().add(cbHoras);		

		String[] minutos = new String[61];
		for(int i=0; i<=60;i++) {
			minutos[i] = Integer.toString(i);
		}
		JComboBox cbMinutos = new JComboBox(minutos);
		cbMinutos.setBounds(339, 168, 65, 24);
		frame.getContentPane().add(cbMinutos);

		JLabel lblHora = new JLabel("Horas:");
		lblHora.setBounds(262, 141, 59, 15);
		frame.getContentPane().add(lblHora);

		JLabel lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(339, 141, 74, 15);
		frame.getContentPane().add(lblMinutos);

		JLabel lblParticipantes = new JLabel("Participantes:");
		lblParticipantes.setBounds(268, 204, 110, 15);
		frame.getContentPane().add(lblParticipantes);

		JComboBox comboBox = new JComboBox(controller.obterParticipantes());
		comboBox.setBounds(262, 231, 142, 24);
		frame.getContentPane().add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(262, 304, 142, 87);
		frame.getContentPane().add(scrollPane);
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(listaDeParticipantes);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!listaDeParticipantes.contains(comboBox.getSelectedItem().toString())) {
					listaDeParticipantes.addElement(comboBox.getSelectedItem().toString());
				}
			}
		});
		button.setBounds(360, 267, 44, 25);
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
		button_1.setBounds(265, 267, 44, 25);
		frame.getContentPane().add(button_1);

		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.setBounds(268, 475, 136, 25);
		frame.getContentPane().add(btnSolicitar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CANCELAR);
				controller.postGuiEvent(evento);
			}
		});
		btnCancelar.setBounds(10, 475, 117, 25);
		frame.getContentPane().add(btnCancelar);

		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(10, 398, 70, 15);
		frame.getContentPane().add(lblObjetivo);

		JScrollPane scrollPaneTextArea = new JScrollPane();
		scrollPaneTextArea.setBounds(10, 416, 394, 47);
		frame.getContentPane().add(scrollPaneTextArea);
		JTextArea textArea = new JTextArea();
		scrollPaneTextArea.setViewportView(textArea);
		textArea.setLineWrap(true);

		JLabel lblTrmino = new JLabel("Término");
		lblTrmino.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrmino.setBounds(296, 122, 70, 15);
		frame.getContentPane().add(lblTrmino);

		JLabel label = new JLabel("Minutos:");
		label.setBounds(339, 66, 74, 15);
		frame.getContentPane().add(label);

		JComboBox cbMinutoInicial = new JComboBox(minutos);
		cbMinutoInicial.setBounds(339, 93, 65, 24);
		frame.getContentPane().add(cbMinutoInicial);

		JComboBox cbHoraInicial = new JComboBox(horas);
		cbHoraInicial.setBounds(262, 93, 65, 24);
		frame.getContentPane().add(cbHoraInicial);

		JLabel label_1 = new JLabel("Horas:");
		label_1.setBounds(262, 66, 59, 15);
		frame.getContentPane().add(label_1);

		JLabel lblIncio = new JLabel("Início");
		lblIncio.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncio.setBounds(296, 49, 70, 15);
		frame.getContentPane().add(lblIncio);

		JLabel lblSolicitarAgendamento = new JLabel("Solicitar Agendamento");
		lblSolicitarAgendamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitarAgendamento.setBounds(10, 12, 395, 15);
		frame.getContentPane().add(lblSolicitarAgendamento);

		JLabel lblLocal = new JLabel("Local:");
		lblLocal.setBounds(10, 371, 52, 15);
		frame.getContentPane().add(lblLocal);

		textField = new JTextField();
		textField.setBounds(57, 371, 193, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.data = calendar.getDayChooser().getDay() + "/"+calendar.getMonthChooser().getMonth() + "/" + calendar.getYearChooser().getYear();
				controller.horaInicial = Integer.parseInt(cbHoraInicial.getSelectedItem().toString());
				controller.horaFinal = Integer.parseInt(cbHoras.getSelectedItem().toString());
				controller.minutoInicial = Integer.parseInt(cbMinutoInicial.getSelectedItem().toString());
				controller.minutoFinal = Integer.parseInt(cbMinutos.getSelectedItem().toString());
				controller.participantes = new String[listaDeParticipantes.size()];
				for(int i = 0; i < listaDeParticipantes.size(); i++) {
					controller.participantes[i] = listaDeParticipantes.getElementAt(i).toString();
				}
				controller.local = textField.getText();
				controller.objetivo = textArea.getText();
				GuiEvent evento = new GuiEvent(null, controller.SOLICITAR);
				controller.postGuiEvent(evento);
			}
		});
	}
}
