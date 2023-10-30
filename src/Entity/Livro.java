package Entity;

public class Livro {

	String titulo;
	String isbn;
	int publisherId;
	double price;

	public Livro(String aTitulo, String aIsbn, int aPublisherId, double aPrice) {
		super();
		this.titulo = aTitulo;
		this.isbn = aIsbn;
		this.publisherId = aPublisherId;
		this.price = aPrice;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public double getPrice() {
		return price;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Livro \nTitulo: " + titulo + ", \nIsbn: " + isbn + "\nPublisherId:" + publisherId + "\nPrice:" + price;
	}

}
