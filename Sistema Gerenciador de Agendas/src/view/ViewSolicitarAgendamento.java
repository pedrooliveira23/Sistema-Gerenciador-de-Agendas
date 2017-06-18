package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import java.awt.BorderLayout;
import javax.swing.JComboBox;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		calendar = new JCalendar();
		frame.getContentPane().add(calendar);
		calendar.setBounds(10, 10, 240, 240);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(262, 10, 174, 24);
		frame.getContentPane().add(comboBox);;
	}
}
