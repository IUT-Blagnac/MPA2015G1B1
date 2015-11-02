package constants;

/**
 * Contains program's constants.
 * 
 * @author Emmanuel CHEBBI
 */
public interface Constants {
	
	/* *********************** EVENT TYPE *********************** */
	
	public static final String EVT_ADD = "add";
	
	public static final String EVT_REMOVE = "remove";
	
	public static final String EVT_MODIFY = "modify";
	
	/* *********************** SUBJECTS ************************* */
	
	/** The ActionCommand to select a {@link Subject} */
	public final static String EVT_SELECT_SUBJECT = "selectSubject";
	
	/** The ActionCommand to create a {@link Subject} */
	public final static String EVT_ADD_SUBJECT = "addSubject";
	
	/** The ActionCommand to modify a {@link Subject} */
	public final static String EVT_MODIFY_SUBJECT = "modifySubject";
	
	/** The ActionCommand to delete a {@link Subject} */
	public final static String EVT_REMOVE_SUBJECT = "removeSubject";
	
	/* *********************** STUDENTS ************************* */
	
	/** The ActionCommand to select a {@link Student} */
	public final static String EVT_SELECT_STUDENT = "selectStudent";
	
	/** The ActionCommand to create a {@link Student} */
	public final static String EVT_ADD_STUDENT = "addStudent";
	
	/** The ActionCommand to modify a {@link Student} */
	public final static String EVT_MODIFY_STUDENT = "modifyStudent";
	
	/** The ActionCommand to delete a {@link Student} */
	public final static String EVT_REMOVE_STUDENT = "removeStudent";
	
	/* *********************** PROJECTS ************************* */
	
	/** The ActionCommand to select a {@link Project} */
	public final static String EVT_SELECT_PROJECT = "selectProject";
	
	/** The ActionCommand to create a {@link Project} */
	public final static String EVT_ADD_PROJECT = "addProject";
	
	/** The ActionCommand to modify a {@link Project} */
	public final static String EVT_MODIFY_PROJECT = "modifyProject";
	
	/** The ActionCommand to delete a {@link Project} */
	public final static String EVT_REMOVE_PROJECT = "removeProject";
	
	/** The ActionCommand to clone a {@link Project} */
	public final static String EVT_CLONE_PROJECT = "cloneProject";

	/* *********************** StakeHolder ************************* */

	/** The ActionCommand to select a {@link Stakeholder} */
	public final static String EVT_SELECT_STAKEHOLDER = "selectStakeholder";
	
	/** The ActionCommand to create a {@link Project} */
	public final static String EVT_ADD_STAKEHOLDER = "addStakeholder";
	
	/** The ActionCommand to modify a {@link Project} */
	public final static String EVT_MODIFY_STAKEHOLDER = "modifyStakeholder";
	
	/** The ActionCommand to delete a {@link Project} */
	public final static String EVT_REMOVE_STAKEHOLDER = "removeStakeholder";

	/** The ActionCommand to show the details of a {@link Project} */
	public final static String EVT_DETAILS_PROJECT = "detailsProject";
	
	/* *********************** Group ************************* */
	
	/** The ActionCommand to select a {@link Group} */
	public final static String EVT_SELECT_GROUP = "selectGroup";
	
	/** The ActionCommand to create a {@link Project} */
	public final static String EVT_ADD_GROUP = "addGroup";
	
	/** The ActionCommand to modify a {@link Project} */
	public final static String EVT_MODIFY_GROUP = "modifyGroup";
	
	/** The ActionCommand to delete a {@link Project} */
	public final static String EVT_REMOVE_GROUP = "removeGroup";
	
	/* *********************** JMenuBar ************************* */
	
	/** The ActionCommand to save {@link Students} */
	public final static String MENU_EVT_SAVE_STUDENTS = "saveStudents";
	
	/** The ActionCommand to load {@link Students} */
	public final static String MENU_EVT_LOAD_STUDENTS = "loadStudents";
	
	/** The ActionCommand to save {@link Subjects} */
	public final static String MENU_EVT_SAVE_SUBJECTS = "saveSubjects";
	
	/** The ActionCommand to load {@link Subjects} */
	public final static String MENU_EVT_LOAD_SUBJECTS = "loadSubjects";
	
	/** The ActionCommand to save {@link Projects} */
	public final static String MENU_EVT_SAVE_PROJECTS = "saveProjects";
	
	/** The ActionCommand to load {@link Projects} */
	public final static String MENU_EVT_LOAD_PROJECTS = "loadProjects";
	
	/** The ActionCommand to save {@link Projects} */
	public final static String MENU_EVT_SAVE_GROUPS = "saveGroups";
	
	/** The ActionCommand to load {@link Projects} */
	public final static String MENU_EVT_LOAD_GROUPS = "loadGroups";
	

	/** Text to be displayed in the "A Propos" tab */
	public final static String PAN_ABOUTUS_CONTENT =
			"<html>"
		+	" Nom du Projet : OPTI<br/>"
		+	" SPRINT n°0<br/><br/>"
					
		+ 	" Réalisé par les membres du groupe 1B1 :<br/>"
		+	"	* CALVET Nicolas<br/>"
		+	"	* CHEBBI Emmanuel<br/>"
		+	"	* LE POGAM Léo<br/>"
		+	"	* NOEL Alexa<br/>"
		+	"	* PEYRON Florian<br/>"
		+	"	* POUBLAN Jehan<br/><br/>"
		
		+	" Dans le cadre d'un projet de DUT INFO S3/Module MPA à l'IUT de Blagnac (Université Toulouse 2)"
		+	"</html>";
}
