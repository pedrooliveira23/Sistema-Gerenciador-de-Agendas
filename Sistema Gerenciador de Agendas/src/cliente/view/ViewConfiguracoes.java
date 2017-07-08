package cliente.view;

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

import cliente.model.agents.gui.AgentConfiguracoes;
import cliente.model.agents.gui.AgentPainelDeControle;
import jade.gui.GuiEvent;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConfiguracoes {

	private JFrame frame;
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JTextField textField;
	private String nomeAgente;
	
	public String getNomeAgente() {
		return nomeAgente;
	}
	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}

	/**
	 * Launch the application.
	 */
	
	private static AgentConfiguracoes controller = null;

	/**
	 * @wbp.parser.constructor
	 */

	public ViewConfiguracoes() {
		initialize();
	}

	public ViewConfiguracoes(AgentConfiguracoes agentConfiguracoes) {
		controller = agentConfiguracoes;
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
				GuiEvent evento = new GuiEvent(null, controller.SALVAR);
				setNomeAgente(textField.getText());
				controller.postGuiEvent(evento);
			}
		});
		btnSalvar.setBounds(319, 237, 117, 25);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiEvent evento = new GuiEvent(null, controller.CANCELAR);
				controller.postGuiEvent(evento);
			}
		});
		btnCancelar.setBounds(12, 237, 117, 25);
		frame.getContentPane().add(btnCancelar);
	}
}
