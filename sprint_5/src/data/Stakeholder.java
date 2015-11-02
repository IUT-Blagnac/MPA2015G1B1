package data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Emmanuel Chebbi
 */
public class Stakeholder extends Person {
	
	// *****************************************************************************
	//					ATTRIBUTES
	// **********************************************************
	
	public enum Category {
		CLIENT(0),
		SUPERVISOR(1),
		TECHNICAL_SUPPORT(2);
		
		final static int NBR_CATEGORIES = Category.values().length;
		
		int id = -1;
		private Category(int id) {
			this.id = id;
		}
		
		public int id() {
			return id;
		}
	}
	
	private Category category;
	private Set <Project> projectsFollowed;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************
	
	public Stakeholder() {
		this("Name", "FirstName");
	}

	public Stakeholder(String name, String firstname) {
		this(DEFAULT_INVALID_ID, name, firstname);
	}
	
	public Stakeholder( int id, String name, String firstname ) {
		super(id, name, firstname);
		projectsFollowed = new HashSet <Project>();
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************
	
	public Category getCategory() {
		return category;
	}
	
	public Collection <Project> getProjects() {
		return projectsFollowed;
	}
	
	// *****************************************************************************
	//					SETTERS
	// **********************************************************
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * Adds a {@link Project} to the list of followed {@link Project}s.
	 * <br/>
	 * Caution : this method shouldn't be called directly,
	 * because it keeps the given {@link Project} unchanged.
	 * <br/>
	 * Prefer call the method {@link Project#setStakeholder(Category, Stakeholder)}
	 * instead, because this one manages all the modifications.
	 * 
	 * @param newProject the Project to follow
	 * @return true if the Stakeholder did not already follow the specified Project
	 */
	public boolean follow( Project newProject ) {
		return projectsFollowed.add(newProject);
	}
	
	/**
	 * Removes a {@link Project} from the list of followed {@link Project}s.
	 * <br/>
	 * Caution : this method shouldn't be called directly,
	 * because it keeps the given {@link Project} unchanged.
	 * <br/>
	 * Prefer call the method {@link Project#setStakeholder(Category, Stakeholder)}
	 * with a null {@link Stakeholder} instead, because this one manages all the modifications.
	 * 
	 * @param newProject the Project to disfollow
	 * @return true if the Stakeholder followed the specified Project
	 */
	public boolean disfollow( Project oldProject ) {
		return projectsFollowed.remove(oldProject);
	}
}
