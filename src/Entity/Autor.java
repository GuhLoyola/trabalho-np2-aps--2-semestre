package Entity;

public class Autor {
	
	int autorId;
	String name;
	String fname;
	
	public Autor(int autorId, String name, String fname) {
		super();
		this.autorId = autorId;
		this.name = name;
		this.fname = fname;
	}

	public int getAutorId() {
		return autorId;
	}

	public String getName() {
		return name;
	}

	public String getFname() {
		return fname;
	}

	@Override
	public String toString() {
		return "Autor \nAutorId: " + autorId + "\nName: " + name + "\nFname: " + fname;
	}
	
	

}
