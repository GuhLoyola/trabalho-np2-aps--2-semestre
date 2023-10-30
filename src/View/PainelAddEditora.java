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

import Entity.Editora;

@SuppressWarnings("serial")
public class PainelAddEditora extends JPanel{
	
	JButton adicionar = new JButton("Adicionar");
	
	JLabel lblNome = new JLabel("Nome");
	
	JTextField nomeFld = new JTextField(20);
	
	JLabel lblUrl = new JLabel("Url");
	JTextField urlFld = new JTextField(20);
	
	
	public PainelAddEditora() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel informacoes = new JPanel(new GridLayout(2, 1));
		
		add(informacoes, BorderLayout.CENTER);
		informacoes.add(lblNome);
		informacoes.add(nomeFld);
		informacoes.add(lblUrl);
		informacoes.add(urlFld);
		
		
		
		JPanel controle = new JPanel();
		add(controle, BorderLayout.PAGE_END);
		controle.add(adicionar);
		add(Box.createRigidArea(new Dimension(0, 400)));
	}
	
	
	public Editora getEditoraAdd() {
		String nome = nomeFld.getText();
		String url = urlFld.getText();
		
		return new Editora(nome, url, 0);
	}
	
	public void addAcaoAdicionar(ActionListener al) {
		adicionar.addActionListener(al);
		nomeFld.addActionListener(al);
		urlFld.addActionListener(al);
	}

}
