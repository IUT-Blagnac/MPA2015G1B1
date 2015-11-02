package model;

import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Manage a set of integer ids. 
 *  
 * @author Emmanuel Chebbi
 */
class IdManager {
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** The ids already associated to an object */
	private TreeSet <Integer> associatedIds;
	
	/** The ids unused between lower and higher ids associated */
	private LinkedHashSet <Integer> unusedIds;
	
	private int firstId = 1;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************

	public IdManager() {
		associatedIds = new TreeSet <Integer> ();
		unusedIds = new LinkedHashSet <Integer> ();
		
		// First ids initialization
		firstId = 1;
		unusedIds.add(firstId);
	}
	
	// *****************************************************************************
	//					ACCESSORS
	// **********************************************************
	
	/**
	 * Get a valid id.
	 * 
	 * Valid means that the id has not already been used.
	 * 
	 * @return a valid id
	 */
	public int getId() {
		int nextId = firstId;
		
		if( unusedIds.isEmpty() ) {
			nextId = associatedIds.last();
			associatedIds.add( ++nextId );
		}
		else {
			nextId = unusedIds.iterator().next();
			unusedIds.remove(nextId);
		}
		return nextId;
	}
	
	/**
	 * Returns whether an id has already been used.
	 * 
	 * @param id the id to check
	 * @return whether the id has already been used
	 */
	public boolean isIdUsed(int id) {
		return associatedIds.contains(id);
	}
	
	// *****************************************************************************
	//					MUTATORS
	// **********************************************************

	/**
	 * Indicates to the manager that an id has been used
	 * @param id the id which has been used
	 */
	public void setIdUsed(int id) {
		
		int lastIdUsed = associatedIds.isEmpty() ? -1 : associatedIds.last();
		
		// Si l'id est trop grand, on ajoute tous ceux qui
		// sont 'oubli�s' � la liste des ids � utiliser
		if( id > lastIdUsed+1 ) 
			for( int unusedId = lastIdUsed+1 ; unusedId < id ; unusedId++ )
				unusedIds.remove( new Integer(unusedId) );
		
		unusedIds.remove(id);
		associatedIds.add(id);
	}
	
	/**
	 * Indicates to the manager that an id is not used
	 * @param id the id which is unused
	 */
	public void setIdUnused(int id) {
		unusedIds.add(id);
		associatedIds.remove(id);
	}
}
