package Models;

import com.google.gson.annotations.Expose;

public class Person {

	@Expose
	private String name;
	@Expose
	private String surname;
	@Expose
	private String gender;
	@Expose
	private String region;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String toString() {
		return "name: " +name + " " + surname +"\ngender: "+ gender +"\nregion: " + region ;
	}


}
