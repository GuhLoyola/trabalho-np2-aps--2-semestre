package Entity;

public class Editora {

	String nome;
	String url;
	int publisherId;

	public Editora(String nome, String url, int publisherId) {
		this.nome = nome;
		this.url = url;
		this.publisherId = publisherId;
	}

	public String getNome() {
		return nome;
	}

	public String getUrl() {
		return url;
	}

	public int getPublisherId() {
		return publisherId;
	}

	@Override
	public String toString() {
		return "Editora \nNome: " + nome + "\nUrl: " + url + "\nPublisherId: " + publisherId;
	}

}
