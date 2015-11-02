package model;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import data.Group;
import data.MyObject;
import data.Student;
import data.Subject;
import utils.annotations.ExpensiveOperation;
import utils.csv.CSVReader;
import utils.csv.CSVWriter;

/**
 * Manage students, intervenants and clients.
 * 
 * The GroupManager has been conceived as a delegate-class of {@link Model};
 * 
 * @author Emmanuel Chebbi
 */
public class StudentManager {
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** The list of Subjects */
	private Hashtable <Integer,Student> students; //FunctionalList <Subject> subjects;
	
	/** Manage Subjects'id to avoid doubloon */
	private IdManager studentIdManager;
	
	/** The list of Groups */
	private Hashtable <String,Group> groups;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************
	
	public StudentManager() {
		students = new Hashtable <Integer,Student>();
		studentIdManager = new IdManager();
		
		groups = new Hashtable <String,Group>();
		groups.put(" ", new Group(" "));
	}
	
	// *****************************************************************************
	//					ACCESSEURS
	// **********************************************************
	
	/**
	 * Get a {@link Student} from his id.
	 * 
	 * @param id the Student's id
	 * @return the Student corresponding to the id or null if it is not found
	 */
	public Student getStudent( int id ) {
		return students.get(id);
	}
	
	/**
	 * Returns the list of {@link Student}s
	 * @return the list of Students
	 */
	public Set <Student> getStudents() {
		return new TreeSet <Student> (students.values());
	}
	
	/**
	 * Returns a {@link Group} from his id.
	 * 
	 * @param id the Group's id
	 * @return the Group corresponding to the id or null if it is not found
	 */
	public Group getGroup( String id ) {
		return groups.get(id);
	}
	
	/**
	 * Add a {@link Group} only if it doesn't already exist (i.e. if none
	 * {@link Group} with the same id could be found).
	 *
	 * @param newSubject the Subject to add
	 * @return whether the Subject could have been added
	 */
	public boolean addGroup( Group newGroup ) {
		
		if( groups.containsKey(newGroup.getId()) )
			return false;
		
		groups.put(newGroup.getId(), newGroup);
		return true;
	}
	/**
	 * Return the list of {@link Group}s 
	 * @return the list of Groups
	 */
	public Set <Group> getGroups() {
		return new TreeSet <Group> (groups.values());
	}
	
	/**
	 * Returns the {@link Subject}s selected as wish number X.
	 *
	 * @param rank the number of the wish
	 * @return the Subjects selected as wish number X
	 */
	public Set <Subject> getSubjectsAtRank(int rank) {
		Set <Subject> subjectsAtDesiredRank = new LinkedHashSet <Subject> ();
		
		for( Group group : groups.values() )
			subjectsAtDesiredRank.add( group.getWish(rank) );
		
		return subjectsAtDesiredRank;
	}
	
	/**
	 * Returns a list containing the number of competitors per rank of wish.
	 * <br>
	 * The first item corresponding to the number of competitors ({@link Group}s)
	 * for the rank 1, the second for the rank 2, and so on.
	 * 
	 * @param group the group to compare with the others
	 * @return a list containing the number of competitors per rank of wish
	 */
	@ExpensiveOperation
	public List <Integer> getCompetitorsFor(Group group) {
		
		ArrayList <Integer> concurrents = new ArrayList <Integer> (group.getWishes().size());
		
		for( Group concurrent : groups.values() ) {
			
			if( concurrent == group )
				continue;
			
			for( int i = 0 ; i < concurrents.size() ; i++ ) {
				if( concurrent.getWish(i).equals(group.getWish(i)) ) {
					int nbr = concurrents.get(i);
					concurrents.set(i, ++nbr);
				}
			}
		}		
		return concurrents;
	}
	
	/**
	 * Returns a list containing the number of wishes per rank for a {@link Subject}.
	 * <br>
	 * The first item corresponding to the number of wishes as rank 1 for the given
	 * {@link Subject}, the second as rank 2, and so on.
	 * 
	 * @param subject the wish 
	 * @return a list containing the number of wishes per rank for a Subject
	 */
	@ExpensiveOperation
	public List <Integer> getNumberOfWishesPerRank(Subject subject) {
		
		ArrayList <Integer> numberOfWishes = new ArrayList <Integer> ();
		
		for( Group group : groups.values() ) {
			for( int rank = 0 ;rank < group.getWishes().size() ; rank++ ) 
				if( group.getWish(rank).equals(subject) ) {
					int nbr = numberOfWishes.get(rank);
					numberOfWishes.set(rank, ++nbr);
				}
		}
		return numberOfWishes;
	}
	
	// *****************************************************************************
	//					MUTATORS
	// **********************************************************
	
	/**
	 * Adds or replaces a {@link Student}. 
	 * <br>
	 * Caution : if a {@link Student} with the same id as the one
	 * gave as parameter, it will be erase without any precaution.
	 * <br>
	 * That means that if, for example, the {@link Student} erased was
	 * part of a {@link Group}, this {@link Group} will continue to 
	 * reference the old {@link Student}.
	 * 
	 * @param 	newSubject the Subject to add.
	 * 
	 * @see #addStudent(Student)
	 * @see #removeStudent(Student)
	 */
	public void addOrReplaceStudent( Student newStudent ) {
		students.put(newStudent.getId(), newStudent);
		studentIdManager.setIdUsed(newStudent.getId());
	}
	
	/**
	 * Add a {@link Student} only if it doesn't already exist (i.e. if none
	 * {@link Student} with the same id could be found).
	 * <br>
	 * If the id of the {@link Student} gave as parameter equals {@link MyObject#DEFAULT_INVALID_ID},
	 * a valid id is generated for the {@link Student}.
	 * 
	 * @param newSubject the Subject to add
	 * @return whether the Subject could have been added
	 */
	public boolean addStudent( Student newStudent ) {
		if( newStudent.getId() == MyObject.DEFAULT_INVALID_ID )
			newStudent.setId( studentIdManager.getId() );
		
		else if( students.containsKey(newStudent.getId()) )
			return false;
		
		addOrReplaceStudent(newStudent);
		return true;
	}
	
	/**
	 * Removes a {@link Student}.
	 * 
	 * @param 	oldSubject the Student to remove.
	 */
	public void removeStudent( Student oldStudent ) {
		students.remove(oldStudent);
		studentIdManager.setIdUnused(oldStudent.getId());
	}
	
	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	/**
	 * Load {@link Student}s from a CSV formatted file.
	 * <br>
	 * The file must be formatted as follow : <br>
	 * idGroupe;idStudent;studentName;s tudentFirstname
	 * <br><br>
	 * You should also notice that as the first line of the file 
	 * corresponds to columns' name it is not read by this function.
	 * <br>
	 * This method provide unchanged state if an error occurs. 
	 * 
	 * @param 	csvFileName path toward the CSV formatted file
	 * 
	 * @throws IOException if an error occurs while reading the file
	 * @throws UnsupportedEncodingException if the file is not UTF8-encoded 
	 * @throws InvalidPropertiesFormatException if the file is not in a valid format
	 * @throws FileNotFoundException if the file isn't reachable
	 */
	public void loadFile( String csvFileName ) 
			throws UnsupportedEncodingException, FileNotFoundException, InvalidPropertiesFormatException, IOException {
		
		Hashtable <Integer,Student> tmpStudents = new Hashtable <Integer,Student>();
		IdManager tmpStudentIdManager = new IdManager();
		
		ArrayList <String[]> data = CSVReader.fromFile(csvFileName);
		
		Iterator <String[]> studentData = data.iterator();
		studentData.next();	// Skip first line
		
		try {
			while( studentData.hasNext() ) {
				String[] current = studentData.next();
				
				// Group initialization
				
				Group group = null;
				String groupId = current[0];
				
				if( groupId.equals("") || groupId.equals(" ")) {
					group = groups.get(" ");
				}
				else if( ! groups.containsKey(groupId) ) {
					group = new Group(groupId);
					groups.put(groupId, group);					
				}
				else
					group = groups.get(groupId);
				
				// Student initialization
				
				int studentId = Integer.parseInt(current[1]);
				
				if( tmpStudents.containsKey(studentId) )
					throw new InvalidPropertiesFormatException("The ID "+studentId+" is associated to at least two students");
				
				tmpStudentIdManager.setIdUsed(studentId);
				
				Student student = new Student( studentId, current[2], current[3] );
				
				// Student is added to his group
				
				group.addStudent(student);
				
				tmpStudents.put( studentId, student );
			}
			
		} catch( NullPointerException e ) {
			throw new InvalidPropertiesFormatException("The file "+csvFileName+" is not in a valid format.");
		}

		students = tmpStudents;
		studentIdManager = tmpStudentIdManager;
	}
	
	/**
	 * Loads {@link Student}s from a CSV formatted file.
	 * 
	 * The file will be formatted as follow : 
	 * idGroupe;idStudent;studentName;studentFirstname
	 * 
	 * You should also notice that as the first line of the file 
	 * corresponds to columns' name it is not read by this function.
	 * 
	 * @param 	csvFileName path toward the CSV formatted file
	 * 
	 * @throws IOException if an error occurs while reading the file
	 * @throws UnsupportedEncodingException if the file is not UTF8-encoded 
	 * @throws FileNotFoundException if the file isn't reachable
	 */

	public void saveInFile(File file) throws IOException{

		ArrayList<String[]> output = new ArrayList<String[]>(students.size());
		
		String[] line = new String[4];
		line[0] = "groupe";
		line[1] = "id";
		line[2] = "nom";
		line[3] = "prenom";
		output.add(line);
		
		for( Student student : students.values() ) {
			
			line = new String[4];
			if(student.getGroup() == null)
				line[0] = "";
			else
				line[0] = student.getGroup().getId();
			line[1] = "" + student.getId();
			line[2] = student.getName();
			line[3] = student.getFirstName();
			output.add(line);
		}
		
		CSVWriter.toFile(file, output);
		
	}
}
