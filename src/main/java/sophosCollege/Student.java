package sophosCollege;

public class Student {
	private int cc;
	private String name;
	private String email;
	private int cellphone;
	private String address;
	private int creditsNumber;
	private int semester;
	private boolean isActive;
	private Course[] currentCourses;
	private Course[] coursesAttended;

	public Student() {
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
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

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Course[] getCurrentCourses() {
		return currentCourses;
	}

	public Course[] getCoursesAttended() {
		return coursesAttended;
	}
	
}
