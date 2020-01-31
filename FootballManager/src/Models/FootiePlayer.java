package Models;

public class FootiePlayer extends Person {

//	private String name;
	private Position position;
	
	public FootiePlayer(String name, Position position) {
//		setName(name);
		setPosition(position);
	}
	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String toString() {
		return "name: " + super.getName() + " " + super.getSurname() +" position: " + position;
	}

}
