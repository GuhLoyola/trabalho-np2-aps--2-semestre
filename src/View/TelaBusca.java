package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Entity.Autor;
import Entity.Editora;
import Entity.Livro;

@SuppressWarnings("serial")
public class TelaBusca extends JPanel {

	JTabbedPane principal = new JTabbedPane();

	Map<String, List<String>> nomeToColunas = new TreeMap<>();
	Map<String, PainelTabelaBusca> nomeToPanels = new TreeMap<>();

	public TelaBusca init() {

		nomeToColunas.put("Livros", List.of("Titulo", "ISBN", "Publisher_Id", "Preço"));

		nomeToColunas.put("Editora", List.of("Nome", "URL"));
		nomeToColunas.put("Autor", List.of("Nome", "Sobrenome"));

		for (String k : nomeToColunas.keySet()) {
			List<String> nomesColunas = nomeToColunas.get(k);
			nomeToPanels.put(k, new PainelTabelaBusca(k, nomesColunas));
			principal.addTab(nomeToPanels.get(k).getName(), nomeToPanels.get(k));
		}

		setLayout(new BorderLayout());
		add(principal, BorderLayout.CENTER);

		return this;
	}

	public String getBuscaAutor() {
		return nomeToPanels.get("Autor").getBusca();
	}

	public String getBuscaLivro() {
		return nomeToPanels.get("Livros").getBusca();
	}

	public String getBuscaEditora() {
		return nomeToPanels.get("Editora").getBusca();
	}

	public void addAcaoBuscarAutor(ActionListener al) {
		nomeToPanels.get("Autor").addAcaoBuscar(al);
	}

	public void addAcaoBuscarLivro(ActionListener al) {
		nomeToPanels.get("Livros").addAcaoBuscar(al);
	}

	public void addAcaoBuscarEditora(ActionListener al) {
		nomeToPanels.get("Editora").addAcaoBuscar(al);
	}

	public void mostrarBuscaAutor(List<Autor> autores) {
		List<List<Object>> tabela = new ArrayList<>();

		for (Autor autor : autores) {
			tabela.add(List.of(autor.getFname(), autor.getName(), autor.getAutorId()));

		}
		nomeToPanels.get("Autor").mostrarBusca(tabela);
	}

	public void mostrarBuscaLivro(List<Livro> livros) {
		List<List<Object>> tabela = new ArrayList<List<Object>>();

		for (Livro livro : livros) {
			tabela.add(List.of(livro.getTitulo(), livro.getIsbn(), livro.getPublisherId(), livro.getPrice()));
		}
		nomeToPanels.get("Livros").mostrarBusca(tabela);
	}

	public void mostrarBuscaEditora(List<Editora> editoras) {
		List<List<Object>> tabela = new ArrayList<List<Object>>();

		for (Editora editora : editoras) {
			tabela.add(List.of(editora.getNome(), editora.getUrl(), editora.getPublisherId()));
		}
		nomeToPanels.get("Editora").mostrarBusca(tabela);
	}

	public void addAcaoExcluirLivro(ActionListener al) {
		nomeToPanels.get("Livros").addAcaoExcluir(al);
	}

	public String getApagarLivro() {
		return nomeToPanels.get("Livros").apagarItem(1);
	}

	public void addAcaoExcluirAutor(ActionListener al) {
		nomeToPanels.get("Autor").addAcaoExcluir(al);
	}

	public Integer getApagarAutor() {
		try {
			return Integer.parseInt(nomeToPanels.get("Autor").apagarItem(2));
		} catch (NumberFormatException e) {
			return null;
		}

	}

	public void addAcaoExcluirEditora(ActionListener al) {
		nomeToPanels.get("Editora").addAcaoExcluir(al);
	}

	public Integer getApagarEditora() {
		try {
			return Integer.parseInt(nomeToPanels.get("Editora").apagarItem(2));
		} catch (NumberFormatException e) {
			return null;
		}

	}

	public void mostrarConfirmacaoApagarLivro(boolean result) {
		nomeToPanels.get("Livros").mostrarConfirmacao(result);
	}
	
	public void mostrarConfirmacaoApagarAutor(boolean result) {
		nomeToPanels.get("Autor").mostrarConfirmacao(result);
	}
	
	public void mostrarConfirmacaoApagarEditora(boolean result) {
		nomeToPanels.get("Editora").mostrarConfirmacao(result);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new TelaBusca().init());
		j.pack();
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
