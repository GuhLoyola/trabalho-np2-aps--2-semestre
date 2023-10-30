package View;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Controller.EditoraGetter;
import Entity.Autor;
import Entity.Editora;
import Entity.Livro;

@SuppressWarnings("serial")
public class TelaAdicionar extends JTabbedPane {

	PainelAddAutor addAutor = new PainelAddAutor();
	PainelAddEditora addEditora = new PainelAddEditora();
	PainelAddLivros addLivros = new PainelAddLivros();

	public TelaAdicionar init() {

		JComponent autores = addAutor;
		addTab("Autor", null, autores, "tela para add autores");

		JComponent editoras = addEditora;
		addTab("Editora", null, editoras, "tela para add editoras");

		JComponent livros = addLivros;
		addTab("Livros", null, livros, "tela para add livros");

		return this;
	}

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new TelaAdicionar().init());
		j.pack();
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Autor getAutorAdd() {
		return addAutor.getAutorAdd();
	}

	public Editora getEditoraAdd() {
		return addEditora.getEditoraAdd();
	}

	public void addAcaoAdicionarAutor(ActionListener al) {
		addAutor.addAcaoAdicionar(al);
	}

	public void addAcaoAdicionarEditora(ActionListener al) {
		addEditora.addAcaoAdicionar(al);
	}

	public void addAcaoEnviarEditora(EditoraGetter ge) {
		addLivros.addAcaoEnviarEditora(ge);
	}

	public void addAcaoAdicionarLivro(ActionListener al) {
		addLivros.addAcaoAdicionarLivro(al);
	}

	public Livro getLivroAdd() {
		return addLivros.getLivroAdd();
	}

}
