package sophosCollege;

public class Course {

	private int id; 
	private String name;
	private int prerequisite;
	private int nCredits;
	private int availableSpaces;
	private boolean active;
	private Student[] students;
	private int idTeacher;
	
	public Course() {
	}

	public static Course[] getCoursesAttended(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void setCoursesAttended(int studentId, Course[] coursesAttended) {
		// TODO Auto-generated method stub
		
	}
	
	public static Course[] getCurrentCourses(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void setCurrentCourses(int studentId, Course[] currentCourses) {
		// TODO Auto-generated method stub
		
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}
	
	
}
