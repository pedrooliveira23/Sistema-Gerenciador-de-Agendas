package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerConfiguracoes;
import controller.ControllerJadeContainer;
import controller.ControllerPainelDeControle;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConfiguracoes {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	private static ControllerConfiguracoes controller = new ControllerConfiguracoes();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConfiguracoes window = new ViewConfiguracoes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewConfiguracoes() {
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
		
		JLabel lblConfiguraes = new JLabel("Configurações");
		lblConfiguraes.setBounds(12, 0, 424, 41);
		lblConfiguraes.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblConfiguraes);
		
		JLabel lblSeuNome = new JLabel("Nome:");
		lblSeuNome.setBounds(12, 53, 55, 15);
		frame.getContentPane().add(lblSeuNome);
		
		textField = new JTextField();
		textField.setBounds(68, 53, 368, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.salvarConfiguracoes(textField.getText());
				frame.setVisible(false);
			}
		});
		btnSalvar.setBounds(319, 237, 117, 25);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancelar.setBounds(12, 237, 117, 25);
		frame.getContentPane().add(btnCancelar);
	}
}
