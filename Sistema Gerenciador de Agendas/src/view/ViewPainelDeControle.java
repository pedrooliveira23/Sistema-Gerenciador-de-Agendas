package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import jade.Boot;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.ControllerJadeContainer;
import controller.ControllerPainelDeControle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPainelDeControle {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	private static ControllerJadeContainer container = null;
	private static ControllerPainelDeControle controller = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPainelDeControle window = new ViewPainelDeControle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		container = new ControllerJadeContainer();
		container.abrirRMA();
		controller = new ControllerPainelDeControle(container);
	}

	/**
	 * Create the application.
	 */
	public ViewPainelDeControle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 283, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConsultarAgendamentos = new JButton("Consultar Agendamentos");
		btnConsultarAgendamentos.setBounds(12, 112, 252, 25);
		frame.getContentPane().add(btnConsultarAgendamentos);
		
		JButton btnSolicitarAgendamento = new JButton("Solicitar Agendamento");
		btnSolicitarAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSolicitarAgendamento solicitarAgendamento = new ViewSolicitarAgendamento(container);
				solicitarAgendamento.main(null);
			}
		});
		btnSolicitarAgendamento.setBounds(12, 142, 252, 25);
		frame.getContentPane().add(btnSolicitarAgendamento);
		
		JButton btnConfiguraes = new JButton("Configurações");
		btnConfiguraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewConfiguracoes configuracoes = new ViewConfiguracoes(container);
				configuracoes.main(null);
			}
		});
		btnConfiguraes.setBounds(12, 172, 252, 25);
		frame.getContentPane().add(btnConfiguraes);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.encerrarAplicacao();
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
