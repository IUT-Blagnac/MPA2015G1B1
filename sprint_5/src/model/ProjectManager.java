package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.management.openmbean.KeyAlreadyExistsException;

import data.Group;
import data.MyObject;
import data.Project;
import data.Stakeholder;
import data.Subject;
import utils.csv.CSVReader;
import utils.csv.CSVWriter;

/**
 * Manage every {@link Project} loaded by the program.
 * 
 * The ProjectManager has been conceived as a delegate-class of {@link Model};
 * 
 * @author Alexa NOEL
 */
public class ProjectManager {
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** Manage projects' id */
	private IdManager idProjectsManager;
	/** The list of projects */
	private Hashtable <Integer,Project> projects;

	/** Manage stakeholders' id */
	private IdManager idStakeholdersManager;
	/** The list of stakeholders */
	private Hashtable <Integer,Stakeholder> stakeholders;
	
	// *****************************************************************************
	//					CONSTRUCTOR
	// **********************************************************
	
	/**
	 * Create a new Collection of {@link Project}
	 */
	public ProjectManager() {
		idProjectsManager = new IdManager();
		projects = new Hashtable <Integer,Project>();
		
		idStakeholdersManager = new IdManager();
		stakeholders = new Hashtable <Integer,Stakeholder> ();
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************
	
	/**
	 * Returns the list of projects
	 * @return the list of projects
	 */
	public Collection <Project> getProjects() {
		return projects.values();
	}
	
	/**
	 * Returns the list of {@link Stakeholder}s
	 * @return the list of Stakeholders
	 */
	public Collection <Stakeholder> getStakeholders() {
		return stakeholders.values();
	}
	
	/**
	 * Returns the {@link Subject}s which are affected to
	 * a {@link Project}
	 * @return the Subjects affected.
	 */
	public Set <Subject> getAffectedSubjects() {
		
		Set <Subject> affectedSubjects = new TreeSet <Subject> ();
		
		for( Project project : projects.values() )
			affectedSubjects.add(project.getSubject());
		
		return affectedSubjects;
	}
		
	/**
	 * From a set of {@link Subject}, returns the ones which aren't
	 * affected to a {@link Project}
	 * 
	 * @param subjects the complete set of Subjects
	 * @return	a set containing the subjects among those gave as parameter 
	 * 			which aren't affected to a {@link: Project}
	 */
	public Set <Subject> getUnaffectedSubjects( Set <Subject> subjects ) {
		
		Set <Subject> affectedSubjects = getAffectedSubjects(),
					  subjectsCopy = new TreeSet <Subject> (subjects);
		
		subjectsCopy.removeAll(affectedSubjects);
		
		return subjectsCopy;
	}
	
	/**
	 * Returns the {@link Group}s which are affected to
	 * a {@link Project}
	 * @return the Groups affected.
	 */
	public Set <Group> getAffectedGroups() {
		
		Set <Group> affectedGroups = new LinkedHashSet <Group> ();

		for( Project project : projects.values() )
			affectedGroups.add(project.getGroup());
		
		return affectedGroups;
	}
	
	/**
	 * From a set of {@link Group}, returns the ones which aren't
	 * affected to a {@link Project}.
	 * 
	 * @param subjects the complete set of Subjects
	 * @return the subjects among those gave as parameter which aren't affected to a Project
	 */
	public Set <Group> getUnaffectedGroups( Set <Group> groups ) {
		
		Set <Group> affectedGroups = getAffectedGroups(),
					groupsCopy = new TreeSet <Group> (groups);
		
		groupsCopy.removeAll(affectedGroups);
		
		return groupsCopy;
	}
	
	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	/**
	 * Replaces a {@link Project}. 
	 * <br>
	 * Caution : if a {@link Project} with the same id as the one
	 * gave as parameter, it will be erase without any precaution.
	 * 
	 * @param 	newProject the Project to add.
	 * 
	 * @see #addProject(Project)
	 * @see #removeProject(Project)
	 */
	public void addOrReplaceProject( Project newProject ) {
		projects.put(newProject.getId(), newProject);
		idProjectsManager.setIdUsed(newProject.getId());
	}
	
	/**
	 * Adds a new {@link Project}.
	 * 
	 * @param 	newProject the Project to add.
	 * @return	the Project added
	 * 
	 * @throws KeyAlreadyExistsException if the Project's id is already used by another one.
	 */
	public Project addProject( Project newProject ) {
		if( projects.containsKey(newProject.getId()) )
			throw new KeyAlreadyExistsException();
		
		if( newProject.getId() == MyObject.DEFAULT_INVALID_ID )
			newProject.setId( idProjectsManager.getId() );
		
		addOrReplaceProject(newProject);
		return newProject;
	}
	
	/**
	 * Removes a {@link Project}.
	 * 
	 * @param 	oldProject the Project to remove.
	 */
	public void removeProject( Project oldProject ) {
		projects.remove(oldProject);
		idProjectsManager.setIdUnused(oldProject.getId());
	}
	
	/**
	 * Replaces a {@link Stakeholder}. 
	 * <br>
	 * Caution : if a {@link Stakeholder} with the same id as the one
	 * gave as parameter, it will be erase without any precaution.
	 * 
	 * @param 	newStakeholder the Stakeholder to add.
	 * 
	 * @see #addStakeholder(Stakeholder)
	 * @see #removeStakeholder(Stakeholder)
	 */
	public void addOrReplaceStakeholder( Stakeholder newStakeholder ) {
		stakeholders.put(newStakeholder.getId(), newStakeholder);
		idStakeholdersManager.setIdUsed(newStakeholder.getId());
	}
	
	/**
	 * Adds a new {@link Stakeholder}.
	 * 
	 * @param 	newStakeholder the Stakeholder to add.
	 * @return	the Stakeholder added
	 * 
	 * @throws KeyAlreadyExistsException if the Stakeholder'id is already used by another one.
	 */
	public Stakeholder addStakeholder( Stakeholder newStakeholder ) {
		if( stakeholders.containsKey(newStakeholder.getId()) ) {
			System.out.println(newStakeholder.getId()+" is already set");
			throw new KeyAlreadyExistsException();
		}
		
		if( newStakeholder.getId() == MyObject.DEFAULT_INVALID_ID )
			newStakeholder.setId( idStakeholdersManager.getId() );
		
		addOrReplaceStakeholder(newStakeholder);
		return newStakeholder;
	}
	
	/**
	 * Removes a {@link Stakeholder}.
	 * 
	 * @param 	oldStakeholder the Stakeholder to remove.
	 */
	public void removeStakeholder( Stakeholder oldStakeholder ) {
		stakeholders.remove(oldStakeholder);
		idStakeholdersManager.setIdUnused(oldStakeholder.getId());
	}
	
	
	/**
	 * Loads {@link Stakeholder}s from a CSV formatted file.
	 * <br>
	 * The file must be formatted as follow : 
	 * idIntervenant;intervenantFirstName;intervenantName
	 * <br><br>
	 * You should also notice that as the first line of the file
	 * corresponds to columns' name it is not read by this function.
	 * 
	 * @param 	csvFileName path toward the CSV formatted file
	 * 
	 * @throws IOException if an error occurs while reading the file
	 * @throws UnsupportedEncodingException if the file is not UTF8-encoded 
	 * @throws InvalidPropertiesFormatException if the file is not in a valid format
	 * @throws FileNotFoundException if the file isn't reachable
	 */
	public void loadStakeholdersFromFile( String csvFileName ) 
			throws UnsupportedEncodingException, FileNotFoundException, InvalidPropertiesFormatException, IOException {

		IdManager tmpIdStakeholdersManager = new IdManager();
		Hashtable <Integer,Stakeholder> tmpStakeholders = new Hashtable <Integer,Stakeholder>();
		
		ArrayList <String[]> data = CSVReader.fromFile(csvFileName);
		
		Iterator <String[]> intervenantData = data.iterator();
		intervenantData.next();	// On passe la première ligne
		
		try {
			while( intervenantData.hasNext() ) {
				String[] current = intervenantData.next();
				
				int stakeholderId = Integer.parseInt(current[0]);
				
				if( stakeholders.containsKey(stakeholderId) )
					throw new InvalidPropertiesFormatException("The ID "+stakeholderId+" is associated to at least two stakeholders");
				
				Stakeholder stakeholder = new Stakeholder( stakeholderId, current[2], current[1] );
				
				tmpStakeholders.put( stakeholderId, stakeholder );
				tmpIdStakeholdersManager.setIdUsed(stakeholderId);
			}
			
		} catch( NullPointerException e ) {
			throw new InvalidPropertiesFormatException("The file "+csvFileName+" is not in a valid format.");
		}
		stakeholders = tmpStakeholders;
		idStakeholdersManager = tmpIdStakeholdersManager;
	}
	

	/**
	 * Loads {@link Stakeholder}s from a CSV formatted file.
	 * <br>
	 * The file must be formatted as follow : 
	 * idProject;idGroup;idSubject;idClient;idSupervisor;idTechnicalSupport
	 * <br>
	 * You should also notice that as the first line of the file
	 * corresponds to columns' name it is not read by this function.
	 * <br>
	 * This method provide unchanged state if an error occurs. 
	 * 
	 * @param csvFileName path toward the CSV formatted file
	 * @param studentManager the instance managing students
	 * @param subjectManager the instance managing subjects
	 * 
	 * @throws IOException if an error occurs while reading the file
	 * @throws UnsupportedEncodingException if the file is not UTF8-encoded 
	 * @throws InvalidPropertiesFormatException if the file is not in a valid format
	 * @throws FileNotFoundException if the file isn't reachable
	 */
	public void loadProjectsFromFile( String csvFileName, StudentManager studentManager, SubjectManager subjectManager ) 
			throws UnsupportedEncodingException, FileNotFoundException, InvalidPropertiesFormatException, IOException {
		
		IdManager tmpIdProjectsManager = new IdManager();
		Hashtable <Integer,Project> tmpProjects = new Hashtable <Integer,Project> ();
		
		ArrayList <String[]> data = CSVReader.fromFile(csvFileName);
		
		Iterator <String[]> projectData = data.iterator();
		projectData.next();	// Skip first line

		try {
			while( projectData.hasNext() ) {
				String[] current = projectData.next();
				
				// Project's id
				
				int projectId = Integer.parseInt(current[0]);
				
				if( idProjectsManager.isIdUsed(projectId) )
					throw new InvalidPropertiesFormatException("The ID "+projectId+" is associated to at least two projects");
				
				// Associated Group
				
				String groupId = current[1];
				Group group = studentManager.getGroup(groupId);
				
				if( group == null )
					throw new InvalidPropertiesFormatException("The ID "+groupId+" does not refer to a valid group");
						
				// the Subject
				
				int subjectId = Integer.parseInt(current[2]);
				Subject subject = subjectManager.getSubject(subjectId);
				
				if( subject == null )
					throw new InvalidPropertiesFormatException("The ID "+subjectId+" does not refer to a valid subject");
				
				// the Client
				
				int clientId = -1;
				
				try {
					clientId = Integer.parseInt(current[3]);
					
					if( ! idStakeholdersManager.isIdUsed(clientId) )
						throw new InvalidPropertiesFormatException("The ID "+clientId+" does not refer to a valid client");

				} catch( NumberFormatException nfe ) {}
				
				
				// the Supervisor
				
				int supervisorId = -1;
				
				try {
					supervisorId = Integer.parseInt(current[4]);
					
					if( ! idStakeholdersManager.isIdUsed(supervisorId) )
						throw new InvalidPropertiesFormatException("The ID "+supervisorId+" does not refer to a valid supervisor");

				} catch( Exception e ) {}
				
				// the Supervisor
				
				int technicalSupportId = -1;
				
				try {
					Integer.parseInt(current[5]);
					
					if( ! idStakeholdersManager.isIdUsed(technicalSupportId) )
						throw new InvalidPropertiesFormatException("The ID "+technicalSupportId+" does not refer to a valid stakeholder");

				} catch( Exception e ) {}
				
				// Project initialization

				Stakeholder client = stakeholders.get(clientId), 
							supervisor = stakeholders.get(supervisorId),
							technicalSupport = stakeholders.get(technicalSupportId);

				Project project = new Project(group, subject);
				project.setStakeholder(Stakeholder.Category.CLIENT, client);
				project.setStakeholder(Stakeholder.Category.SUPERVISOR, supervisor);
				project.setStakeholder(Stakeholder.Category.TECHNICAL_SUPPORT, technicalSupport);
				
				project.setId(projectId);
				project.setGroup(group);
				
				tmpProjects.put(projectId, project);
				tmpIdProjectsManager.setIdUsed(projectId);
			}
			
		} catch( NullPointerException e ) {
			e.printStackTrace();
			throw new InvalidPropertiesFormatException("The file "+csvFileName+" is not in a valid format.");
		}
		projects = tmpProjects;
		idProjectsManager = tmpIdProjectsManager;
	}
	
	/**
	 * Loads {@link Project}s from a CSV formatted file.
	 * 
	 * The file will be formatted as follow : 
	 * idProject;idGroup;idSubject;idClient;idSupervisor;idTechnicalSupport
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

	public void saveInFile(File file) throws IOException {
		
		ArrayList<String[]> output = new ArrayList<String[]>(projects.size());
		
		String[] line = new String[6];
		line[0] = "id";
		line[1] = "groupe";
		line[2] = "sujet";
		line[3] = "client";
		line[4] = "superviseur";
		line[5] = "support_technique";
		output.add(line);
		
		for( Project project : projects.values() ) {
			
			line = new String[6];
			line[0] = project.getId()+"";
			line[1] = project.getGroup().getId();
			line[2] = project.getSubject().getId()+"";
			line[3] = project.getStakeholder(Stakeholder.Category.CLIENT).getId()+"";
			line[4] = project.getStakeholder(Stakeholder.Category.SUPERVISOR).getId()+"";
			line[5] = project.getStakeholder(Stakeholder.Category.TECHNICAL_SUPPORT).getId()+"";

			output.add(line);
		}
		
		CSVWriter.toFile(file, output);
		
	}
}
