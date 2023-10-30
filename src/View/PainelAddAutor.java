package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entity.Autor;

@SuppressWarnings("serial")
public class PainelAddAutor extends JPanel{
	
	JButton adicionar = new JButton("Adicionar");
	
	JLabel lblNome = new JLabel("Nome");
	
	JTextField nomeFld = new JTextField(20);
	
	JLabel lblSobrenome = new JLabel("Sobrenome");
	JTextField sobrenomeFld = new JTextField(20);
		
	public PainelAddAutor() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel informacoes = new JPanel(new GridLayout(2, 1));
		
		add(informacoes, BorderLayout.CENTER);
		informacoes.add(lblNome);
		informacoes.add(nomeFld);
		informacoes.add(lblSobrenome);
		informacoes.add(sobrenomeFld);
		
		
		
		JPanel controle = new JPanel();
		add(controle, BorderLayout.PAGE_END);
		controle.add(adicionar);
		add(Box.createRigidArea(new Dimension(0, 400)));
	}
	
	
	public Autor getAutorAdd() {
		String nome = nomeFld.getText();
		String sobrenome = sobrenomeFld.getText();
		
		return new Autor(0, nome, sobrenome);
	}
	
	public void addAcaoAdicionar(ActionListener al) {
		adicionar.addActionListener(al);
		nomeFld.addActionListener(al);
		sobrenomeFld.addActionListener(al);
	}
	
}
