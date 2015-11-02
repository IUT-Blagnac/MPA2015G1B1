package data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import data.Stakeholder.Category;
 
/**
 * Represents a project.
 * <br/>
 * A Project is composed of :
 * <ul>
 * 	<li>a {@link Group}</li>
 * 	<li>a {@link Subject}</li>
 * 	<li>several {@link Stakeholder}s</li>
 * </ul>
 * 
 * <br/>
 * 
 * The number of {@link Stakeholder}s corresponds to 
 * the value of {@link Category#NBR_CATEGORIES}.
 * For each {@link Category}, only one {@link Stakeholder}
 * is allowed.
 * 
 * @author Emmanuel Chebbi
 */
public class Project extends MyObject {
	
	// *****************************************************************************
	//					ATTRIBUTES
	// **********************************************************
	
	private Group group = null;
	private Subject subject = null;
	
	private Stakeholder stakeholders[] = null;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************
	
	/**
	 * Creates a Project with neither
	 * {@link Group} nor {@link Subject}
	 */
	public Project() {
		this(null, null);
	}
	
	/**
	 * Creates a Project.
	 * @param group		the Group to assign
	 * @param subject	the Subject to assign
	 */
	public Project(Group group, Subject subject) {
		super();
		this.group = group;
		this.subject = subject;
		this.stakeholders = new Stakeholder [Stakeholder.Category.NBR_CATEGORIES];
	}
	
	/**
	 * Creates a new {@link Project} with the same values than another.
	 * <br>
	 * Caution : the new {@link Project} will have exactly the same 
	 * references and attributes.
	 * <br>
	 * The only exception is the new {@link Project}'s id, which will be
	 * set to {@link MyObject#DEFAULT_INVALID_ID}.
	 * 
	 * @param original the Project to copy
	 */
	public Project(Project original) {
		super();
		this.group = original.group;
		this.subject = original.subject;
		this.stakeholders = original.stakeholders;
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************
	
	/**
	 * Returns the {@link Group} in charge of the Project.
	 * @return the Group in charge of the Project
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * Returns the {@link Subject} of the {@link Project}.
	 * @return the Subject of the Project
	 */
	public Subject getSubject() {
		return subject;
	}
	
	/**
	 * Returns the {@link Stakeholder} corresponding to a {@link Category}.
	 * <br>
	 * Returns null if none {@link Stakeholder} is set for the given {@link Category}.
	 * 
	 * @param category the Category of the {@link Stakeholder} to retrieve
	 * @return the corresponding Stakeholder
	 */
	public Stakeholder getStakeholder(Stakeholder.Category category) {
		return stakeholders[category.id()];
	}
	
	/**
	 * Returns the {@link Stakeholder}s of the Project.
	 * 
	 * @return the {@link Stakeholder}s of the Project.
	 */
	public Set <Stakeholder> getStakeholders() {
		return new HashSet <Stakeholder> (Arrays.asList(stakeholders));
	}
	
	// *****************************************************************************
	//					SETTERS
	// **********************************************************
	
	/**
	 * Sets the {@link Group} in charge of the {@link Project}.
	 * <br>
	 * This method should be called instead of 
	 * {@link Group#setProject(Project)}.
	 * 
	 * @param group the Group in charge of the Project
	 */
	public void setGroup(Group group) {
		Project lastProject = group.getProject();
		
		if( lastProject != null )
			lastProject.removeGroup();
		
		group.setProject(this);
		this.group = group;
	}
	
	/**
	 * Removes the {@link Group} associated.
	 */
	private void removeGroup() {
		group.setProject(null);
		group = null;
	}

	/**
	 * Sets the {@link Stakeholder} corresponding to a {@link Category}.
	 * 
	 * @param category	the Category of the Stakeholder to set
	 * @param stakeholder the Stakeholder to assign
	 * 
	 * @throws IllegalArgumentException if the Client and the Supervisor are equal
	 */
	public void setStakeholder(Stakeholder.Category category, Stakeholder stakeholder) throws IllegalArgumentException {
		
		if( category == Category.CLIENT && stakeholder == stakeholders[Category.SUPERVISOR.id()] )
			throw new IllegalArgumentException("Le Client ne peut pas �tre le Superviseur");
		
		if( category == Category.SUPERVISOR && stakeholder == stakeholders[Category.CLIENT.id()] )
			throw new IllegalArgumentException("Le Client ne peut pas �tre le Superviseur");
		
		Stakeholder previousStakeholder = this.stakeholders[category.id()];
		
		if( previousStakeholder != null ) 
			previousStakeholder.disfollow(this);
		
		if( stakeholder != null )
			stakeholder.follow(this);
		
		this.stakeholders[category.id()] = stakeholder;
	}
	
	// *****************************************************************************
	//					METHODS
	// **********************************************************
	
	/**
	 * Creates a new Project similar to the current one.
	 * <br/>
	 * The new Project has the same Subject and the same 
	 * {@link Stakeholder}s than the current one.
	 * However, it has no group and his id is set to 
	 * {@link MyObject#DEFAULT_INVALID_ID}.
	 * 
	 * @return a new Project similar to the current one
	 */
	public Project clone() {
		Project clone = new Project(null, this.subject);
		clone.setId(DEFAULT_INVALID_ID);
		clone.stakeholders = this.stakeholders.clone();
		
		return clone;
	}

	/*@Override
	public int compareTo(Object o) {
		if( o instanceof Project ) {
			String mine = (subject == null) ? "" : subject.getTitle();
			String his = (((Project)o).getSubject() == null) ? "" : ((Project)o).getSubject().getTitle();
			
			return mine.compareTo( his );
		}
			
		return 0;
	}*/	
	
	@Override
	public String toString() {
		return subject.getTitle();
	}
}
