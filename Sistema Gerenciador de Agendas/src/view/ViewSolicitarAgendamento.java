package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

public class ViewSolicitarAgendamento {

	private JFrame frame;
	private JCalendar calendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSolicitarAgendamento window = new ViewSolicitarAgendamento();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSolicitarAgendamento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 329);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(262, 90, 142, 24);
		frame.getContentPane().add(comboBox);
		
		JList list = new JList();
		list.setBounds(262, 163, 142, 87);
		frame.getContentPane().add(list);
		
		JButton button = new JButton("+");
		button.setBounds(360, 126, 44, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setBounds(262, 126, 44, 25);
		frame.getContentPane().add(button_1);
		
		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.setBounds(268, 262, 136, 25);
		frame.getContentPane().add(btnSolicitar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 262, 117, 25);
		frame.getContentPane().add(btnCancelar);;
	}
}
