package sophosCollege.classes;

import java.util.ArrayList;

public class Course {

	private int id; 
	private String name;
	private int prerequisite;
	private int nCredits;
	private int availableSpaces;
	private boolean active;
	private ArrayList<Student> students;
	private int idTeacher;
	
	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(int prerequisite) {
		this.prerequisite = prerequisite;
	}

	public int getnCredits() {
		return nCredits;
	}

	public void setnCredits(int nCredits) {
		this.nCredits = nCredits;
	}

	public int getAvailableSpaces() {
		return availableSpaces;
	}

	public void setAvailableSpaces(int availableSpaces) {
		this.availableSpaces = availableSpaces;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}
	
	
}
