package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Autor;
import Entity.Editora;
import Entity.Livro;

public class DAOConcreto implements DAO {

	private final static String USER = "root";
	private final static String PASS = "";
	private final static String DATABASE = "livraria";
	private final static String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

	static boolean testConnection() {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			System.out.println("Conexão bem sucedida");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	static boolean testResult() {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String comando = "SELECT * FROM books";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(comando);

			while (rs.next()) {
				System.out.println("Title: " + rs.getString("title"));
				System.out.println("Price: " + rs.getDouble("price"));
				System.out.println("");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Nao foi possivel adicionar: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao listar registros", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	@Override
	public List<Livro> buscaLivroByName(String name) {
		List<Livro> livros = new ArrayList<>();

		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String comando = "" + "SELECT * FROM books " + "WHERE LOWER(title)" + "LIKE LOWER(?)";

			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, '%' + name + '%');

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro(rs.getString("title"), rs.getString("isbn"), rs.getInt("publisher_id"),
						rs.getDouble("price"));

				livros.add(livro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return livros;
	}

	@Override
	public List<Autor> buscaAutorByName(String name) {
		List<Autor> autores = new ArrayList<>();

		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {

			String comando = "" + "SELECT * FROM authors " + "WHERE LOWER(name) OR LOWER(fname) " + "LIKE LOWER(?)";

			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, '%' + name + '%');

			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Autor autor = new Autor(rs.getInt("author_id"), rs.getString("name"), rs.getString("fname"));
				autores.add(autor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autores;
	}

	@Override
	public List<Editora> buscaEditora(String name) {

		List<Editora> editoras = new ArrayList<>();

		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {

			String comando = "" + "SELECT * FROM publishers " + "WHERE LOWER(name) " + "LIKE LOWER(?)";

			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, '%' + name + '%');

			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Editora editora = new Editora(rs.getString("name"), rs.getString("url"), rs.getInt("publisher_id"));
				editoras.add(editora);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editoras;
	}

	@Override
	public boolean apagarLivro(String name) {

		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String relacao = "" + "DELETE FROM booksauthors " + "WHERE isbn" + " LIKE ?";

			PreparedStatement s = c.prepareStatement(relacao);

			s.setString(1, name);
			s.executeUpdate();

			String comando = "" + "DELETE FROM books " + "WHERE isbn " + "LIKE ?";

			s = c.prepareStatement(comando);

			s.setString(1, name);
			int res = s.executeUpdate();

			System.out.println(res);

			if (res == 0) {
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel apagar: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean apagarAutor(int id) {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String relacao = "" + "DELETE FROM booksauthors " + "WHERE author_id" + " LIKE ?";

			PreparedStatement s = c.prepareStatement(relacao);

			s.setInt(1, id);
			s.executeUpdate();

			String comando = "" + "DELETE FROM authors " + "WHERE author_id " + "LIKE ?";

			s = c.prepareStatement(comando);

			s.setInt(1, id);
			int res = s.executeUpdate();

			System.out.println(res);

			if (res == 0) {
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel apagar: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean apagarEditora(int id) {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {

			String comando = "" + "DELETE FROM publishers " + "WHERE publisher_id " + "LIKE ?";

			PreparedStatement s = c.prepareStatement(comando);

			s.setInt(1, id);
			int res = s.executeUpdate();

//			System.out.println(res);

			if (res == 0) {
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel apagar: " + e.getMessage());
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean addAutor(Autor autor) {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String comando = "INSERT INTO authors(fname, name) VALUES (?, ?) ";
			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, autor.getName());
			s.setString(2, autor.getFname());

			@SuppressWarnings("unused")
			int res = s.executeUpdate();
			JOptionPane.showMessageDialog(null, "Autor adicionado.", "Autor", JOptionPane.PLAIN_MESSAGE);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel adicionar o autor: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean addEditora(Editora editora) {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String comando = "INSERT INTO publishers(name,url) VALUES(?,?)";
			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, editora.getNome());
			s.setString(2, editora.getUrl());

			@SuppressWarnings("unused")
			int res = s.executeUpdate();
			JOptionPane.showMessageDialog(null, "Editora adicionada.", "Editora", JOptionPane.PLAIN_MESSAGE);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel adicionar a editora: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addLivro(Livro livro, List<Autor> autores) {
		try (Connection c = DriverManager.getConnection(URL, USER, PASS)) {
			String comando = "INSERT INTO books(title, isbn, price, publisher_id) VALUES(?,?,?,?)";
			PreparedStatement s = c.prepareStatement(comando);
			s.setString(1, livro.getTitulo());
			s.setString(2, livro.getIsbn());
			s.setDouble(3, livro.getPrice());
			s.setInt(4, livro.getPublisherId());

			@SuppressWarnings("unused")
			int res = s.executeUpdate();
			JOptionPane.showMessageDialog(null, "Livro adicionado.", "Livro", JOptionPane.PLAIN_MESSAGE);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Não foi possivel adicionar o livro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}