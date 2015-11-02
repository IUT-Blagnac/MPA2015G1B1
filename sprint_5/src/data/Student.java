package data;

import model.StudentManager;

/**
 * 
 * @author Emmanuel Chebbi
 */
public class Student extends Person implements Comparable <Student> {
	
	// *****************************************************************************
	//					ATTRIBUTES
	// **********************************************************
	
	private Group group;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************

	/**
	 * Creates a new {@link Student} with default 
	 * id, name and firstName.
	 */
	public Student() {
		this("Name", "FirstName");
	}
	
	/**
	 * Creates a new Student given him a name and as first name.
	 * <br/>
	 * His id is set to {@link MyObject#DEFAULT_INVALID_ID}.
	 * 
	 * @param name the name of the Student
	 * @param firstName the first name of the Student
	 */
	public Student(String name, String firstName) {
		this(DEFAULT_INVALID_ID, name, firstName);
	}
	
	/**
	 * Creates a new Student given him an id, a name and a
	 * first name.
	 * <br/>
	 * Caution: you shouldn't call this constructor directly.
	 * Let the {@link StudentManager} do his job.
	 * 
	 * @param id the id of the Student
	 * @param name the name of the Student
	 * @param firstName the first name of the Student
	 */
	public Student(int id, String name, String firstName) {
		super(id, name, firstName);
		this.group = null;
	}
	
	/**
	 * Creates a new {@link Student} with the same values than another.
	 * <br>
	 * Caution : the new {@link Student} will have exactly the same 
	 * references and attributes.
	 * <br>
	 * The only exception is the new {@link Student}'s id, which will be
	 * set to {@link MyObject#DEFAULT_INVALID_ID}.
	 * 
	 * @param original the Project to copy
	 */
	public Student(Student original) {
		super( DEFAULT_INVALID_ID, original.getName(), original.getFirstName() );
		this.group = original.group;
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************
	
	public Group getGroup() {
		return group;
	}
	
	// *****************************************************************************
	//					SETTERS
	// **********************************************************
	
	/**
	 * Sets the group.
	 * <br/>
	 * Caution : this method shouldn't be called directly,
	 * because it keeps the given {@link Group} unchanged.
	 * <br/>
	 * Prefer call the method {@link Group#addStudent(Student)}
	 * instead, because this one manages all the modifications.
	 * 
	 * @param group the Group in which the Student is.
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
	
	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	/**
	 * Returns whether two {@link Student}s are equal or not.
	 * @param s the other Student
	 * @return whether two Students are equal or not
	 */
	public boolean equals(Student s) {
		return this.getId() == s.getId() 
			&& this.getName().equals(s.getName())
			&& this.getFirstName().equals(s.getFirstName());
	}
	
	/**
	 * Returns a new {@link Student} with the same name and the same first name as the current one.
	 * @return  a new Student with the same name and the same first name as the current one.
	 */
	@Override
	public Student clone() {
		return new Student( getName(), getFirstName() );
	}
	
	/**
	 * Compares two {@link Student}s by name.
	 */
	@Override
	public int compareTo(Student rhs) {
		int compare = getName().compareTo(rhs.getName());
		
		if( compare != 0 )
			return compare;
		
		return getFirstName().compareTo(rhs.getFirstName());
	}
}
