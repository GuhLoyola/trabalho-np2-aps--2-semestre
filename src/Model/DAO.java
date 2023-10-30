package Model;

import java.util.List;

import Entity.Autor;
import Entity.Editora;
import Entity.Livro;

public interface DAO {

	public List<Livro> buscaLivroByName(String name);

	public List<Autor> buscaAutorByName(String name);

	public List<Editora> buscaEditora(String name);

	public boolean apagarLivro(String name);

	public boolean apagarAutor(int id);

	public boolean apagarEditora(int id);

	public boolean addAutor(Autor autor);

	public boolean addEditora(Editora editora);

	public boolean addLivro(Livro livro, List<Autor> autores);

}
