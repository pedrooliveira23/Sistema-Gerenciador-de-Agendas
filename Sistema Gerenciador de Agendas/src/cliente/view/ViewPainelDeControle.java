package cliente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import jade.Boot;
import jade.gui.GuiEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cliente.model.agents.gui.AgentPainelDeControle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPainelDeControle {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	private static AgentPainelDeControle controller = null;
	/**
	 * @wbp.parser.constructor
	 */
	public ViewPainelDeControle() {
		initialize();
	}

	public ViewPainelDeControle(AgentPainelDeControle controllerPainelDeControle) {
		controller = controllerPainelDeControle;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 283, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConsultarAgendamentos = new JButton("Consultar Agendamentos");
		btnConsultarAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CONSULTAR);
				controller.postGuiEvent(evento);
			}
		});
		btnConsultarAgendamentos.setBounds(12, 112, 252, 25);
		frame.getContentPane().add(btnConsultarAgendamentos);
		
		JButton btnSolicitarAgendamento = new JButton("Solicitar Agendamento");
		btnSolicitarAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.SOLICITAR);
				controller.postGuiEvent(evento);
			}
		});
		btnSolicitarAgendamento.setBounds(12, 142, 252, 25);
		frame.getContentPane().add(btnSolicitarAgendamento);
		
		JButton btnConfiguraes = new JButton("Configurações");
		btnConfiguraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CONFIGURAR);
				controller.postGuiEvent(evento);
			}
		});
		btnConfiguraes.setBounds(12, 172, 252, 25);
		frame.getContentPane().add(btnConfiguraes);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.SAIR);
				controller.postGuiEvent(evento);
			}
		});
		btnSair.setBounds(12, 237, 252, 25);
		frame.getContentPane().add(btnSair);
		
		JLabel lblSistemaGerenciadorDe = new JLabel("Sistema Gerenciador de Agentas");
		lblSistemaGerenciadorDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaGerenciadorDe.setBounds(12, 12, 257, 84);
		frame.getContentPane().add(lblSistemaGerenciadorDe);
	}
}