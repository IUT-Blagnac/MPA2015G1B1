package data;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Group implements Comparable <Group> { 
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	private String id;
	private Project project;
	
	private Set <Student> students;
	private Hashtable <Integer,Subject> wishes;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************
	
	/**
	 * Creates a new {@link Group} with the given id
	 * @param id the Group's id
	 */
	public Group(String id) {
		this.id = id;
		this.project = null;
		this.students = new LinkedHashSet <Student> ();
		this.wishes = new Hashtable <Integer,Subject> ();
	}
	
	/**
	 * Creates a new {@link Group} with the same values than another.
	 * <br>
	 * Caution : the new {@link Group} will have exactly the same 
	 * references and attributes.
	 * 
	 * @param original the Group to copy
	 */
	public Group(Group original) {
		this.id = original.id;
		this.project = original.project;
		this.students = original.students;
		this.wishes = original.wishes;
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************

	/**
	 * Returns the id.
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the {@link Project} associated with the {@link Group}.
	 * @return the Project associated with the Group
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Returns the {@link Student}s of the {@link Group}
	 * @return the Students of the Group
	 */
	public Set <Student> getStudents() {
		return students;
	}
	
	/**
	 * Returns the {@link Subject} set at the given rank.
	 * 
	 * Returns null if no Subject is registered for the rank.
	 * 
	 * @param rank the rank of the Subject to get
	 * @return the {@link Subject} set at the given rank.
	 */
	public Subject getWish(int rank) {
		return wishes.get(rank);
	}

	/**
	 * Returns the {@link Subject} registered as wishes.
	 * @return the Subject registered as wishes
	 */
	public Collection <Subject> getWishes() {
		return wishes.values();
	}
	
	// *****************************************************************************
	//					SETTERS
	// **********************************************************

	/**
	 * Sets the {@link Group}'s id.
	 * @param id the Group's id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Sets the {@link Project} associated with the {@link Group}.
	 * <br/>
	 * Caution : this method shouldn't be called directly,
	 * because it keeps the given {@link Project} unchanged.
	 * <br/>
	 * Prefer call the method {@link Project#setGroup(group)}
	 * instead, because this one manages all the modifications.
	 * 
	 * @param project the Project associated with the Group
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	// *****************************************************************************
	//					MUTATORS
	// **********************************************************
	
	/**
	 * Adds a {@link Student} to the {@link Group}.
	 * <br/>
	 * This method should be called instead of 
	 * {@link Student#setGroup(Group)}.
	 * 
	 * @param student the Student to add to the Group
	 * 
	 * @see #removeStudent(Student)
	 */
	public void addStudent(Student student) {
		Group lastGroup = student.getGroup();
		
		if( lastGroup != null )
			lastGroup.removeStudent(student);
		
		students.add(student);
		student.setGroup(this);
	}
	
	/**
	 * Removes a {@link Student} from the {@link Group}.
	 * @param student the Student to remove
	 * 
	 * @see #removeAllStudents()
	 */
	public void removeStudent(Student student) {
		students.remove(student);
		student.setGroup(null);
	}
	
	/**
	 * Removes all {@link Student}s from the {@link Group}.
	 * @see #removeStudent(Student)
	 */
	public void removeAllStudents() {
		Iterator <Student> it = students.iterator();
		
		while( it.hasNext() ) 
			removeStudent( it.next() );
	}
	
	/**
	 * Associates a {@link Subject} with a rank as a wish.
	 * @param rank the rank of the wish
	 * @param subject the Subject
	 */
	public void setWish(int rank, Subject subject) {
		wishes.put(rank, subject);
	}

	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	@Override
	public String toString() {
		return id;
	}
	
	/**
	 * Compares two {@link Group}s by their ids.
	 * 
	 * @param other
	 * @return
	 */
	@Override
	public int compareTo(Group other) {
		return this.id.compareTo( other.getId() );
	}
}
