package sophosCollege.classes;

import java.util.ArrayList;

public class Student {
	private long cc;
	private String name;
	private String email;
	private long cellphone;
	private String address;
	private int creditsNumber;
	private int semester;
	private String faculty;
	private boolean active;
	private ArrayList<Course> currentCourses;
	private ArrayList<Course> coursesGiven;

	public Student() {
	}

	public long getCc() {
		return cc;
	}

	public void setCc(long cc) {
		this.cc = cc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCellphone() {
		return cellphone;
	}

	public void setCellphone(long cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}

	public void setCreditsNumber(int creditsNumber) {
		this.creditsNumber = creditsNumber;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Course> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(ArrayList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}

	public ArrayList<Course> getCoursesGiven() {
		return coursesGiven;
	}

	public void setCoursesGiven(ArrayList<Course> coursesGiven) {
		this.coursesGiven = coursesGiven;
	}
	
	
}
