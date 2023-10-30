package View;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controller.EditoraGetter;
import Entity.Autor;
import Entity.Editora;
import Entity.Livro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	TelaBusca telaBusca = new TelaBusca();

	TelaAdicionar telaAdd = new TelaAdicionar();

	public TelaPrincipal() {
		super("Livraria");
		setLayout(new GridLayout(1, 1));

		JTabbedPane tp = new JTabbedPane();

		JComponent panel1 = makeTextPanel("Aqui é a tela inicial");
		tp.addTab("Inicio", null, panel1, "Inicio");
		tp.setMnemonicAt(0, KeyEvent.VK_1);

		telaAdd.init();
		tp.addTab("Adicionar", null, telaAdd, "Inclusão de registros");
		tp.setMnemonicAt(1, KeyEvent.VK_2);

		telaBusca.init();
		tp.addTab("Buscar e Excluir", null, telaBusca, "Busca de registros");
		tp.setMnemonicAt(2, KeyEvent.VK_3);

		add(tp);

		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		
		pack();

		setVisible(true);
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	public String getBuscaAutor() {
		return telaBusca.getBuscaAutor();
	}

	public String getBuscaLivro() {
		return telaBusca.getBuscaLivro();
	}

	public String getBuscaEditora() {
		return telaBusca.getBuscaEditora();
	}

	public void addAcaoBuscarAutor(ActionListener al) {
		telaBusca.addAcaoBuscarAutor(al);
	}

	public void addAcaoBuscarLivro(ActionListener al) {
		telaBusca.addAcaoBuscarLivro(al);
	}

	public void addAcaoBuscarEditora(ActionListener al) {
		telaBusca.addAcaoBuscarEditora(al);
	}

	public void mostrarBuscaAutor(List<Autor> autores) {
		telaBusca.mostrarBuscaAutor(autores);
	}

	public void mostrarBuscaLivro(List<Livro> livros) {

		telaBusca.mostrarBuscaLivro(livros);
	}

	public void mostrarBuscaEditora(List<Editora> editoras) {
		telaBusca.mostrarBuscaEditora(editoras);
	}

	public String getApagarLivro() {
		return telaBusca.getApagarLivro();
	}

	public void addAcaoExcluirLivro(ActionListener al) {
		telaBusca.addAcaoExcluirLivro(al);
	}

	public Integer getApagarAutor() {
		return telaBusca.getApagarAutor();
	}

	public void addAcaoExcluirAutor(ActionListener al) {
		telaBusca.addAcaoExcluirAutor(al);
	}

	public Integer getApagarEditora() {
		return telaBusca.getApagarEditora();
	}

	public void addAcaoExcluirEditora(ActionListener al) {
		telaBusca.addAcaoExcluirEditora(al);
	}

	public void mostrarConfirmacaoApagarLivro(boolean result) {
		telaBusca.mostrarConfirmacaoApagarLivro(result);
	}

	public void mostrarConfirmacaoApagarAutor(boolean result) {
		telaBusca.mostrarConfirmacaoApagarAutor(result);
	}

	public void mostrarConfirmacaoApagarEditora(boolean result) {
		telaBusca.mostrarConfirmacaoApagarEditora(result);
	}

	public void addAcaoAdicionarAutor(ActionListener al) {
		telaAdd.addAcaoAdicionarAutor(al);
	}

	public void addAcaoAdicionarEditora(ActionListener al) {
		telaAdd.addAcaoAdicionarEditora(al);
	}

	public void addAcaoAdicionarLivro(ActionListener al) {
		telaAdd.addAcaoAdicionarLivro(al);
	}

	public Autor getAutorAdd() {
		return telaAdd.getAutorAdd();
	}

	public Editora getEditoraAdd() {
		return telaAdd.getEditoraAdd();
	}

	public void addAcaoEnviarEditora(EditoraGetter ge) {
		telaAdd.addAcaoEnviarEditora(ge);
	}

	public Livro getLivroAdd() {
		return telaAdd.getLivroAdd();
	}

}
