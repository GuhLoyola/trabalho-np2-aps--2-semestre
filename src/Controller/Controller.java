package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Entity.Autor;
import Entity.Editora;
import Entity.Livro;
import Model.DAO;
import Model.DAOConcreto;
import View.TelaPrincipal;

public class Controller {

	TelaPrincipal view;
	DAO dao;

	public Controller(TelaPrincipal aView, DAO aDao) {
		this.view = aView;
		this.dao = aDao;
	}

	public void init() {
		view.addAcaoBuscarLivro(new AcaoBuscarLivro());
		view.addAcaoBuscarAutor(new AcaoBuscarAutor());
		view.addAcaoBuscarEditora(new AcaoBuscarEditora());
		view.addAcaoExcluirLivro(new AcaoExcluirLivro());
		view.addAcaoExcluirAutor(new AcaoExcluirAutor());
		view.addAcaoExcluirEditora(new AcaoExcluirEditora());
		view.addAcaoAdicionarAutor(new AcaoAdicionarAutor());
		view.addAcaoAdicionarEditora(new AcaoAdicionarEditora());
		view.addAcaoEnviarEditora(new AcaoEnviarEditoras());
		view.addAcaoAdicionarLivro(new AcaoAdicionarLivro());
	}

	class AcaoBuscarLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String busca = view.getBuscaLivro();
			List<Livro> result = dao.buscaLivroByName(busca);

			view.mostrarBuscaLivro(result);

		}

	}

	class AcaoBuscarAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String busca = view.getBuscaAutor();
			List<Autor> result = dao.buscaAutorByName(busca);

			view.mostrarBuscaAutor(result);

		}
	}

	class AcaoBuscarEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String busca = view.getBuscaEditora();
			List<Editora> result = dao.buscaEditora(busca);

			view.mostrarBuscaEditora(result);

		}

	}

	class AcaoExcluirLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String excluir = view.getApagarLivro();

			boolean result = dao.apagarLivro(excluir);

			view.mostrarConfirmacaoApagarLivro(result);

		}

	}

	class AcaoExcluirAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer excluir = view.getApagarAutor();

			if (excluir == null) {
				return;
			}

			boolean result = dao.apagarAutor(excluir);

			view.mostrarConfirmacaoApagarAutor(result);

		}

	}

	class AcaoExcluirEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer excluir = view.getApagarEditora();

			if (excluir == null) {
				return;
			}

			boolean result = dao.apagarEditora(excluir);

			view.mostrarConfirmacaoApagarEditora(result);

		}

	}

	class AcaoAdicionarAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Autor autor = view.getAutorAdd();

			dao.addAutor(autor);

		}

	}

	class AcaoAdicionarEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Editora editora = view.getEditoraAdd();

			dao.addEditora(editora);

		}

	}

	class AcaoEnviarEditoras implements EditoraGetter {

		@Override
		public List<Editora> getEditoras() {
			return dao.buscaEditora("");
		}

	}

	class AcaoAdicionarLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Livro livro = view.getLivroAdd();
			dao.addLivro(livro, new ArrayList<Autor>());

		}

	}

	public static void main(String[] args) {
		Controller cont = new Controller(new TelaPrincipal(), new DAOConcreto());
		cont.init();
	}

}
