package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import cliente.model.agents.gui.AgentConsultarAgendamentos;
import jade.gui.GuiEvent;

import javax.swing.ListSelectionModel;

public class ViewConsultarAgendamentos {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;

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
		getFrame().setBounds(100, 100, 640, 480);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JButton btnCancelarAgendamento = new JButton("Remover Agendamento");
		btnCancelarAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelarAgendamento.setBounds(433, 417, 199, 25);
		getFrame().getContentPane().add(btnCancelarAgendamento);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CANCELAR);
				controller.postGuiEvent(evento);
			}
		});
		btnCancelar.setBounds(12, 417, 117, 25);
		getFrame().getContentPane().add(btnCancelar);
		
		String[] col = {"Data", "Hr de Inicio",	"Min Início", "Hr Término",	"Min Término", "Solicitante", "Local", "Objetivo", "Participantes"};
		tableModel = new DefaultTableModel(col, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 620, 400);
		frame.getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
