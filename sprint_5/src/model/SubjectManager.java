package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;

import data.MyObject;
import data.Subject;
import utils.csv.CSVReader;
import utils.csv.CSVWriter;

/**
 * Manage every {@link Subject} loaded by the program.
 * 
 * The SubjectManager has been conceived as a delegate-class of {@link Model};
 * 
 * @author Emmanuel Chebbi
 */
public class SubjectManager {
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** Indicates if the list has change since last sort.
	 * 
	 * 	Used to avoid useless calculations.
	 */
	private boolean isListChangedSinceLastSort;
	
	/** The list of Subjects */
	private Hashtable <Integer,Subject> subjects; //FunctionalList <Subject> subjects;
	
	/** Manage Subjects'id to avoid doubloon */
	private IdManager idManager;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************
	
	/**
	 * Creates a new Collection of {@link Subject}s
	 */
	public SubjectManager() {
		subjects = new Hashtable <Integer,Subject>();// new FunctionalList <Subject>();
		isListChangedSinceLastSort = true;
		
		idManager = new IdManager();
	}
	
	// *****************************************************************************
	//					ACCESSORS
	// **********************************************************
	
	/**
	 * Get a {@link Subject} from his id.
	 * 
	 * @param id the Subject's id
	 * @return the Subject corresponding to the id or null if it is not found
	 */
	public Subject getSubject( int id ) {
		return subjects.get(id);
	}
	
	/**
	 * Return the list of subjects
	 * @return the list of subjects
	 */
	public Collection <Subject> getSubjects() {
		return subjects.values();
	}
	
	// *****************************************************************************
	//					MUTATORS
	// **********************************************************
	
	/**
	 * Adds a new {@link Subject}.
	 * 
	 * @param 	newSubject the Subject to add.
	 */
	public void addOrReplaceSubject( Subject newSubject ) {
		isListChangedSinceLastSort = true;
		
		subjects.put(newSubject.getId(), newSubject);
		idManager.setIdUsed(newSubject.getId());
	}
	
	/**
	 * Add a Subject only if it doesn't already exist
	 * 
	 * @param newSubject the Subject to add
	 * @return whether the Subject has been added
	 */
	public boolean addSubject( Subject newSubject ) {
		if( newSubject.getId() == MyObject.DEFAULT_INVALID_ID )
			newSubject.setId( idManager.getId() );
		
		if( subjects.containsKey(newSubject.getId()) )
			return false;
		
		addOrReplaceSubject(newSubject);
		return true;
	}
	
	/**
	 * Removes a Subject.
	 * 
	 * @param 	oldSubject the Subject to remove.
	 */
	public void removeSubject( Subject oldSubject ) {
		isListChangedSinceLastSort = true;
		
		subjects.remove(oldSubject.getId());
		idManager.setIdUnused(oldSubject.getId());
	}
	
	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	/**
	 * Load {@link Subject}s from a CSV formatted file.
	 * 
	 * The file must be formatted as follow : 
	 * idSubject;subjectTitle;subjectDescription
	 * 
	 * You should also notice that as the first line of the line 
	 * corresponds to columns' name it is not read by this function.
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
		
		Hashtable <Integer,Subject> tmpSubjects = new Hashtable <Integer,Subject>();
		IdManager tmpIdManager = new IdManager();
		
		ArrayList <String[]> data = CSVReader.fromFile(csvFileName);
		
		Iterator <String[]> subjectData = data.iterator();
		subjectData.next();	// Skip first line
		
		try {
			while( subjectData.hasNext() ) {
				
				String[] current = subjectData.next();
				int id = Integer.parseInt(current[0]);
				
				tmpIdManager.setIdUsed(id);
				tmpSubjects.put( id, new Subject(id, current[1], current[2]) );
			}
			
		} catch( Exception e ) {
			throw new InvalidPropertiesFormatException("The file "+csvFileName+" is not in a valid format.");
		}
		
		subjects = tmpSubjects;
		idManager = tmpIdManager;
		isListChangedSinceLastSort = true;
	}
	
	/**
	 * Returns the Subjects alphabetically sorted
	 * @return the Subjects alphabetically sorted
	 */
	public Collection <Subject> alphabeticSort() {
		
		if( isListChangedSinceLastSort );
		
		List <Subject> sortedSubjects = new ArrayList <Subject>( getSubjects() );
		
		Collections.sort(sortedSubjects, new Comparator<Subject>() {
		    public int compare(Subject result1, Subject result2) {
		        return result1.getTitle().compareTo( result2.getTitle() );
		    }
		});
		
		return sortedSubjects;
	}

	public void saveInFile(File file) throws IOException{
		
		ArrayList<String[]> output = new ArrayList<String[]>(subjects.size());
		
		String[] line = new String[3];
		line[0] = "id";
		line[1] = "nom";
		line[2] = "titre";
		output.add(line);
		
		for( Subject subject : subjects.values() ) {
			
			line = new String[3];
			line[0] = "" + subject.getId();
			line[1] = subject.getTitle();
			line[2] = subject.getDescription();
			
			output.add(line);
			
		}
		
		CSVWriter.toFile(file, output);
		
	}
}