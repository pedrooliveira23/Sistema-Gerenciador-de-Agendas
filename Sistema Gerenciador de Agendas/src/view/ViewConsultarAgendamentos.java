package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import jade.gui.GuiEvent;
import model.agents.gui.AgentConsultarAgendamentos;

import javax.swing.ListSelectionModel;

public class ViewConsultarAgendamentos {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultarAgendamentos window = new ViewConsultarAgendamentos();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	/**
	 * @wbp.parser.constructor
	 */
	public ViewConsultarAgendamentos() {
		initialize();
	}
	
	private AgentConsultarAgendamentos controller = null;
	public ViewConsultarAgendamentos(AgentConsultarAgendamentos agentConsultarAgendamentos) {
		controller = agentConsultarAgendamentos;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JButton btnCancelarAgendamento = new JButton("Remover Agendamento");
		btnCancelarAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelarAgendamento.setBounds(237, 237, 199, 25);
		getFrame().getContentPane().add(btnCancelarAgendamento);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CANCELAR);
				controller.postGuiEvent(evento);
			}
		});
		btnCancelar.setBounds(12, 237, 117, 25);
		getFrame().getContentPane().add(btnCancelar);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(12, 12, 424, 213);
		getFrame().getContentPane().add(table);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
