package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Set;

import data.Group;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;
import utils.annotations.ExpensiveOperation;
 
/**
 * Make actions on data.
 * 
 * To manage the whole program's data, the Model
 * uses three delegate-class :
 * <ul>
 * 	<li>{@link SubjectManager} : manage the {@link Subject}s</li>
 * 	<li>{@link StudentManager} : manage the {@link Student}s and the {@link Group}s</li>
 * 	<li>{@link ProjectManager} : manage the {@link Project}s</li>
 * </ul>
 * 
 * @author Emmanuel Chebbi
 */
public class Model {
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** Manage the {@link Subject}s */
	private SubjectManager subjectManager;
	
	/** Manage the {@link Student}s */
	private StudentManager studentManager;
	
	/** Manage the {@link Project}s */
	private ProjectManager projectManager;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************

	/**
	 * Initialize sub-managers, i.e. :
	 * <ul>
	 * 	<li>{@link SubjectManager} : manage the {@link Subject}s</li>
	 * 	<li>{@link StudentManager} : manage the {@link Student}s and the {@link Group}s</li>
	 * 	<li>{@link ProjectManager} : manage the {@link Project}s</li>
	 * </ul>
	 */
	public Model() {
		subjectManager = new SubjectManager();
		studentManager = new StudentManager();
		projectManager = new ProjectManager();
	}
	
	// *****************************************************************************
	//				SUBJECTS MANAGEMENT
	// **********************************************************

	/**
	 * Loads {@link Subject}s from a CSV file.
	 * 
	 * @param 	path the path toward the CSV file
	 * @throws 	FileNotFoundException if the file could not be reached.
	 */
	public void loadSubjects( String path ) throws IOException {
		subjectManager.loadFile(path);
	}
	
	
	/**
	 * Adds a {@link Subject}.
	 * 
	 * @param 	newSubject the Subject to add.
	 * @return	true if the Subject has been removed, false otherwise.
	 */
	public void addSubject( Subject newSubject ) {
		subjectManager.addSubject(newSubject);
	}
	
	
	/**
	 * Removes a {@link Subject}.
	 * 
	 * @param 	oldSubject the Subject to remove.
	 */
	public void removeSubject( Subject oldSubject ) {
		subjectManager.removeSubject(oldSubject);
	}
	
	
	/**
	 * Modifies a {@link Subject}.
	 * 
	 * If none Subject is set at the specified id, the 
	 * Subject gave as parameter is simply added to the base.
	 * 
	 * @param	id the id of the Subject to replace
	 * @param 	newSubject the Subject modified
	 */
	public void modifySubject( int id, Subject newSubject ) {
		newSubject.setId(id);
		subjectManager.addOrReplaceSubject(newSubject);
	}
	
	/**
	 * Returns the list of {@link Subject}s
	 * @return the list of Subjects
	 */
	public Collection <Subject> getSubjects() {
		return subjectManager.getSubjects();
	}
	
	/**
	 * Returns the {@link Subject}s selected as wish number X.
	 *
	 * @param rank the number of the wish
	 * @return the Subjects selected as wish number X
	 */
	public Set <Subject> getSubjectsAtRank(int rank) {
		return studentManager.getSubjectsAtRank(rank);
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
		return studentManager.getCompetitorsFor(group);
	}

	/**
	 * Saves the {@link Subject}s in a file.
	 * @param file the file in which save the Subjects
	 * @throws IOException 
	 */
	public void saveSubjects(File file) throws IOException {
		subjectManager.saveInFile(file);
	}
	
	// *****************************************************************************
	//				PROJECTS MANAGEMENT
	// **********************************************************
	
	/**
	 * Loads {@link Project}s from a CSV file.
	 * 
	 * @param 	path the path toward the CSV file
	 * @throws 	IOException if the file could not be reached.
	 */
	public void loadProjects( String path ) throws IOException {
		projectManager.loadProjectsFromFile(path, studentManager, subjectManager);
	}
	
	/**
	 * Adds a {@link Project}.
	 * 
	 * @param 	path the path toward the CSV file
	 * @throws 	IOException if the file could not be reached.
	 */
	public Project addProject( Project project ) {
		return projectManager.addProject(project);
	}
	
	/**
	 * Removes a {@link Project}.
	 * 
	 * @param 	oldProject the Project to remove.
	 */
	public void removeProject( Project oldProject ) {
		projectManager.removeProject(oldProject);
	}
	
	/**
	 * Modifies a {@link Project}.
	 * 
	 * If none Project is set at the specified id, the 
	 * Student gave as parameter is simply added to the base.
	 * 
	 * @param	id the id of the Project to replace
	 * @param 	newProject the modified Project
	 */
	public void modifyProject( int id, Project newProject ) {
		newProject.setId(id);
		projectManager.addOrReplaceProject(newProject);
	}
	
	/**
	 * Returns the list of {@link Project}s
	 * @return the list of Projects
	 */
	public Collection <Project> getProjects() {
		return projectManager.getProjects();
	}
	
	// *****************************************************************************
	//				STAKEHOLDER MANAGEMENT
	// **********************************************************
	
	/**
	 * Returns the list of {@link Stakeholder}s
	 * @return the list of Stakeholders
	 */
	public Collection <Stakeholder> getStakeholders() {
		return projectManager.getStakeholders();
	}

	/**
	 * Load {@link Stakeholder}s from a CSV file.
	 * 
	 * @param 	path the path toward the CSV file
	 * @throws 	IOException if the file could not be reached.
	 */
	public void loadStakeholders( String path ) throws IOException {
		projectManager.loadStakeholdersFromFile(path);
	}
	
	/**
	 * Adds a {@link Stakeholder}.
	 * 
	 * @param 	path the path toward the CSV file
	 * @throws 	IOException if the file could not be reached.
	 */
	public Stakeholder addStakeholder( Stakeholder stakeholder ) {
		return projectManager.addStakeholder(stakeholder);
	}
	
	/**
	 * Removes a {@link Stakeholder}.
	 * 
	 * @param 	stakeholder the Stakeholder to remove.
	 */
	
	public void removeStakeholder( Stakeholder stakeholder ) {
		projectManager.removeStakeholder(stakeholder);
	}
	
	/**
	 * Modifies a {@link Stakeholder}.
	 * 
	 * If none Project is set at the specified id, the 
	 * Student gave as parameter is simply added to the base.
	 * 
	 * @param	id the id of the Project to replace
	 * @param 	newProject the modified Project
	 */
	public void modifyStakeholder( int id, Stakeholder newStakeholder ) {
		newStakeholder.setId(id);
		projectManager.addOrReplaceStakeholder(newStakeholder);
	}
	
	// *****************************************************************************
	//				STUDENTS MANAGEMENT
	// **********************************************************
	
	/**
	 * Loads {@link Student}s from a CSV file.
	 * 
	 * @param 	path the path toward the CSV file
	 * 
	 * @throws IOException if an error occurs while reading the file
	 * @throws UnsupportedEncodingException if the file is not UTF8-encoded 
	 * @throws InvalidPropertiesFormatException if the file is not in a valid format
	 * @throws FileNotFoundException if the file isn't reachable
	 */
	public void initStudents( String path ) 
			throws UnsupportedEncodingException, FileNotFoundException, InvalidPropertiesFormatException, IOException {
		studentManager.loadFile(path);
	}
	
	/** 
	 * Adds a {@link Student} to the list
	 * 
	 * If a {@link Student} sharing the same id is 
	 * already stored, this method will do nothing
	 * except returning false.
	 * 
	 * Notice that, if the Student has been created out of
	 * the {@link Model} class, his id is not valid. 
	 * <br/>
	 * As this method fix this id problem and then return 
	 * the corrected Student, you shouldn't care about that.
	 * 
	 * @param student The Student to add
	 * @return whether the Student has been added
	 */
	public boolean addStudent( Student student ) {
		return studentManager.addStudent(student);
	}
	
	/**
	 * Removes a {@link Student}.
	 * 
	 * @param 	oldSubject the Student to remove.
	 */
	public void removeStudent( Student oldStudent ) {
		studentManager.removeStudent(oldStudent);
	}
	
	/**
	 * Modifies a {@link Student}.
	 * 
	 * If none {@link Student} is set at the specified id, the 
	 * Student gave as parameter is simply added to the base.
	 * 
	 * @param	id the id of the Student to replace
	 * @param 	newStudent the modified Student
	 */
	public void modifyStudent( int id, Student newStudent ) {
//		Student modified = studentManager.getStudent(id);
//		Group newStudentGroup = newStudent.getGroup(),
//			  modifiedStudentGroup = modified.getGroup();
//		
//		if( newStudentGroup != modifiedStudentGroup ) {
//			if( newStudentGroup != null )
//				newStudentGroup.addStudent(modified);
//		}
//		modified.setEqualsTo(modified);
	}
	
	/**
	 * Return the list of {@link Subject}s.
	 * @return the list of Subject
	 */
	public Collection<Student> getStudents() {
		return studentManager.getStudents();
	}
	
	/**
	 * Returns the {@link Group}s registered.
	 * @return the Groups registered
	 */
	public Collection<Group> getGroups() {
		return studentManager.getGroups();
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
		return studentManager.getNumberOfWishesPerRank(subject);
	}

	/**
	 * Saves the {@link Student}s in a file.
	 * @param file the file in which save the Students
	 * @throws IOException 
	 */
	public void saveStudents(File file) throws IOException {
		studentManager.saveInFile(file);
		
		
	}
	
	/**
	 * Adds a {@link Group}.
	 * 
	 * @param 	newGroup the Group to add.
	 * @return	true if the Group has been removed, false otherwise.
	 */
	public void addGroup( Group newGroup ) {
		studentManager.addGroup(newGroup);
	}
}
