package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import constants.Constants;
import data.Group;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;
import main.Controller;

/**
 * The main software's view.
 * 
 * According to MVC Pattern, this class is linked to :
 * <ul>
 * 	<li>{@link Controller}</li>
 * </ul>
 * 
 * @author Emmanuel Chebbi
 */

@SuppressWarnings("serial")
public class View extends JFrame implements Constants {

	// **********************************************************
	//					ATTRIBUTS
	// ***************************************
	
	/** The MainWindow's listener */
	private Controller controller;
	
	private JMenuBar menuBar;
		private JMenu menuStudent;
			private JMenuItem miSaveStudent;
			private JMenuItem miLoadStudent;
		private JMenu menuSubject;
			private JMenuItem miSaveSubject;
			private JMenuItem miLoadSubject;
		private JMenu menuProject;
			private JMenuItem miSaveProject;
			private JMenuItem miLoadProject;
		private JMenu menuGroup;
			private JMenuItem miSaveGroup;
			private JMenuItem miLoadGroup;

		private JMenu menuHelp;
			private JMenuItem miHelp;
			private JMenuItem miAboutUs;

	private JTabbedPane tabCenter;
		private JPanelSubject panSubject;
		private JPanelGroup panGroup;
		private JPanelProject panProject;
		private JPanelStudent panStudent;
		private JPanelStakeholder panIntervenant;
		private JPanel panAPropos;
		private JPanel panHelp;
	
	private JPanel panSouthInfo;
		private JProgressBar progressBar;
		
	// **********************************************************
	//					CONSTRUCTORS
	// ***************************************

	/**
	 * Default constructor.
	 * 
	 * Create the MainWindow initializing its different components.
	 */
		
	public View( Controller controller ) {

		super("OPTI");
		
		this.controller = controller;
		
		setLayout( new BorderLayout() );
		
		createComponentMenuBar();
		createTabbedArea();
		createComponentSouthPanel();

		setJMenuBar(menuBar);
		
		add(tabCenter, 		BorderLayout.CENTER);
		add(panSouthInfo,	BorderLayout.SOUTH);
		
		setMinimumSize( new Dimension(700, 450) );
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// **********************************************************
	//				COMPONENTS CREATION
	// ***************************************
	
	/* -------------------------------------------------- MENU BAR ------------------
	 * Crée la barre de menu
	 */
	private void createComponentMenuBar() {
		
		/* ----------------------------------------------------------------- MENU STUDENT --------------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */

			// ------------------------------------
			// Item sauvegarde des étudiants
			
	        miSaveStudent = new JMenuItem("Sauvegarder...");
	        miSaveStudent.addActionListener(controller);
	        miSaveStudent.setActionCommand(MENU_EVT_SAVE_STUDENTS);
	        miSaveStudent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	
			// ------------------------------------
			// Item chargement des étudiants
	        
	        miLoadStudent = new JMenuItem("Charger...");
	        miLoadStudent.addActionListener(controller);
	        miLoadStudent.setActionCommand(MENU_EVT_LOAD_STUDENTS);
	        miLoadStudent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	        
	        // -----------------------------------
	        // Ajout des items au Menu Student
	        
	        menuStudent = new JMenu("Etudiants");
	        menuStudent.add(miSaveStudent);
	        menuStudent.add(miLoadStudent);
	        
	    /* ----------------------------------------------------------------- MENU SUBJECT --------------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */
        
	        // ------------------------------------
	     	// Item sauvegarde des sujets
	     		
	        miSaveSubject = new JMenuItem("Sauvegarder...");
	        miSaveSubject.addActionListener(controller);
	        miSaveSubject.setActionCommand(MENU_EVT_SAVE_SUBJECTS);
	        miSaveSubject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	
	     	// ------------------------------------
	     	// Item chargement des sujets
	             
	        miLoadSubject = new JMenuItem("Charger...");
	        miLoadSubject.addActionListener(controller);
	        miLoadSubject.setActionCommand(MENU_EVT_LOAD_SUBJECTS);
	        miLoadSubject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	        
	        // -----------------------------------
	        // Ajout des items au Menu Subject
	        
	        menuSubject = new JMenu("Sujets");
	        menuSubject.add(miSaveSubject);
	        menuSubject.add(miLoadSubject);
	        
	    /* ----------------------------------------------------------------- MENU PROJECT --------------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */
	        
	        // ------------------------------------
	     	// Item sauvegarde des projets
	     		
	        miSaveProject = new JMenuItem("Sauvegarder...");
	        miSaveProject.addActionListener(controller);
	        miSaveProject.setActionCommand(MENU_EVT_SAVE_PROJECTS);
	        miSaveProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	
	     	// ------------------------------------
	     	// Item chargement des projets
	             
	        miLoadProject = new JMenuItem("Charger...");
	        miLoadProject.addActionListener(controller);
	        miLoadProject.setActionCommand(MENU_EVT_LOAD_PROJECTS);
	        miLoadProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	        
	        // -----------------------------------
	        // Ajout des items au Menu Project
	        
	        menuProject = new JMenu("Projets");
	        menuProject.add(miSaveProject);
	        menuProject.add(miLoadProject);
	        
	    /* ----------------------------------------------------------------- MENU GROUP --------------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */
	        
	        // ------------------------------------
	     	// Item sauvegarde des groupes
	     		
	        miSaveGroup = new JMenuItem("Sauvegarder...");
	        miSaveGroup.addActionListener(controller);
	        miSaveGroup.setActionCommand(MENU_EVT_SAVE_GROUPS);
	        miSaveGroup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	
	     	// ------------------------------------
	     	// Item chargement des groupes
	             
	        miLoadGroup = new JMenuItem("Charger...");
	        miLoadGroup.addActionListener(controller);
	        miLoadGroup.setActionCommand(MENU_EVT_LOAD_GROUPS);
	        miLoadGroup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	        
	        // -----------------------------------
	        // Ajout des items au Menu Group
	        
	        menuGroup = new JMenu("Groupes");
	        menuGroup.add(miSaveGroup);
	        menuGroup.add(miLoadGroup);
	        
	        
	        //TODO: JMenu Intervenant !
	        
	        
	    /* ----------------------------------------------------------------- MENU INTERVENANT ----------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */
	        
	        // ------------------------------------
	     	// Item sauvegarde des sujets
	     		
	        miSaveSubject = new JMenuItem("Sauvegarder...");
	        miSaveSubject.addActionListener(controller);
	        miSaveSubject.setActionCommand(MENU_EVT_SAVE_SUBJECTS);
	        miSaveSubject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	
	     	// ------------------------------------
	     	// Item chargement des sujets
	             
	        miLoadSubject = new JMenuItem("Charger...");
	        miLoadSubject.addActionListener(controller);
	        miLoadSubject.setActionCommand(MENU_EVT_LOAD_SUBJECTS);
	        miLoadSubject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	        
	        // -----------------------------------
	        // Ajout des items au Menu Subject
	        
	        menuSubject = new JMenu("Sujets");
	        menuSubject.add(miSaveSubject);
	        menuSubject.add(miLoadSubject);

		/* ----------------------------------------------------------------- MENU HELP ----------------------------------------------- */
		/* ---------------------------------------------------------------------------------------------------------------------------------- */
        
	        miHelp = new JMenuItem("Aide...");
	        miHelp.addActionListener(controller);
	        miHelp.setActionCommand("");
	        miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));

			// -----------------------------------
			// Item à propos
	        
	        miAboutUs = new JMenuItem("A Propos...");
	        miAboutUs.addActionListener(controller);
	        miAboutUs.setActionCommand("");
	        
	        // -----------------------------------
	        // Ajout des items au Menu Help
	        
	        menuHelp = new JMenu("Aide");
	        menuHelp.add(miHelp);
	        menuHelp.add(miAboutUs);
	        
        
		// -----------------------------------
		// Ajout des menus à la barre de menus
        
        menuBar = new JMenuBar();
        menuBar.add(menuStudent);
        menuBar.add(menuSubject);
        menuBar.add(menuProject);
        menuBar.add(menuGroup);
        menuBar.add(menuHelp);
        
	}

	/**
	 * Creates the JPanel "A Propos".
	 * 
	 * @return the "A Propos" JPanel.
	 */
	
	private JTabbedPane createTabbedArea() {
		
		JLabel labAProposContent = new JLabel(PAN_ABOUTUS_CONTENT);
		
		panAPropos = new JPanel();
		panAPropos.add(labAProposContent);
		
		panSubject = new JPanelSubject(controller);
		panGroup = new JPanelGroup(controller, null);
		panStudent = new JPanelStudent(controller, this);
		panProject = new JPanelProject(controller, this, null);
		panIntervenant = new JPanelStakeholder(controller, this);
		panHelp = new JPanel();
		JLabel aide = new JLabel("En cas de difficultés, contacter Emmanuel Chebbi, le génialissime chef de projet à cette adresse : emmanuel.chebbi@etu.univ-tlse2.fr");
		panHelp.add(aide);
		
		tabCenter = new JTabbedPane();
		tabCenter.add( panProject, "Projets" );
		tabCenter.add( panStudent, "Etudiants" );
		tabCenter.add( panGroup, "Groupes" );
		tabCenter.add( panSubject, "Sujets" );
		tabCenter.add( panIntervenant, "Intervenants" );
		tabCenter.add( panHelp, "Aide" );
		tabCenter.add( panAPropos, "A Propos" );
		
		return this.tabCenter;
	}
	
	private void createComponentSouthPanel() {
		
		progressBar = new JProgressBar();
		
		panSouthInfo = new JPanel();
		panSouthInfo.add(progressBar);
	}
	
	/**
	 * Modify the value of the progress bar.<BR/>
	 * (precondition : 0 <= progress <= 100)
	 * 
	 * @param progress
	 * 			The value of the progress bar.
	 */
	public void setProgressValue( int progress ) {
		progressBar.setIndeterminate(false);
		progressBar.setValue(progress);
	}
	
	/**
	 * Set the progress bar to an indeterminate one.
	 * 
	 * @param b - the boolean to set indeterminate or not.
	 */
	public void setProgressIndeterminate( boolean b ) {
		progressBar.setIndeterminate(b);
	}
	
	/* ----------------------------------------------------------------- ACTION ON SUBJECTS --------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Return the selected Subject in the JList.
	 * 
	 * @return the selected subject.
	 */
	
	public Subject getSelectedSubject() {
		return (Subject) panSubject.getListPanel().getSelectedItem();
	}
	
	/**
	 * Add a subject to the JList.
	 * 
	 * @param subject - the subject to add.
	 */
	
	public void addSubject( Subject subject ) {
		panSubject.getListPanel().addItem(subject);
	}
	
	/**
	 * Remove a subject from the JList.
	 * 
	 * @param subject - the subject to remove.
	 */
	
	public void removeSubject( Subject subject ) {
		panSubject.getListPanel().removeItem(subject);
	}
	
	/**
	 * Update the JList of subjects.
	 * 
	 * @param subjects - the subjects to show.
	 */
	
	public void updateListSubject( Collection <Subject> subjects ) {
		panSubject.getListPanel().updateList(subjects);
	}
	
	/**
	 * Set a subject selected in the JList.
	 * 
	 * @param subject - the subject to set selected.
	 */
	
	public void setSubjectSelected( Subject subject ) {
		panSubject.setSelected(subject);
		panSubject.revalidate();
	}
	
	/**
	 * Modify a subject in the JList.
	 * 
	 * @param subject - the subject to modify.
	 */
	
	public void modifySelectedSubject(Subject subject) {
		panSubject.getListPanel().setItemAt(panSubject.getListPanel().getSelectedIndex(), subject);
	}
	
	/* ----------------------------------------------------------------- ACTION ON STUDENTS --------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Return the selected Student in the JList.
	 * 
	 * @return the selected student.
	 */
	
	public Student getSelectedStudent() {
		return (Student) panStudent.getListPanel().getSelectedItem();
	}
	
	/**
	 * Add a student to the JList.
	 * 
	 * @param student - the student to add.
	 */
	
	public void addStudent (Student student) {
		panStudent.getListPanel().addItem(student);
	}
	
	/**
	 * Remove a student of the JList.
	 * 
	 * @param student - the student to remove.
	 */
	
	public void removeStudent( Student student ) {
		panStudent.getListPanel().removeItem(student);
	}
	
	/**
	 * Update the JList of students.
	 * 
	 * @param students - the students to show.
	 */
	
	public void updateListStudent( Collection <Student> students ) {
		panStudent.getListPanel().updateList(students);
	}
	
	/**
	 * Set a student selected in the JList.
	 * 
	 * @param student - the student to set selected.
	 */
	
	public void setStudentSelected(Student student){
		panStudent.setSelected(student);
	}

	/**
	 * Modify a student of the JList.
	 * 
	 * @param student - the student to modify.
	 */
	
	public void modifySelectedStudent(Student student) {
		panStudent.getListPanel().setItemAt(panStudent.getListPanel().getSelectedIndex(), student);
	}
	
	/* ----------------------------------------------------------------- ACTION ON GROUPS ----------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Return the select group of the JList.
	 * 
	 * @return the selected group.
	 */
	
	public Group getSelectedGroup() {
		return (Group) panGroup.getListPanel().getSelectedItem();
	}
	
	/**
	 * Add a group to the JList.
	 * 
	 * @param group - the group to add.
	 */
	
	public void addGroup(Group group){
		panGroup.getListPanel().addItem(group);
	}
	
	/**
	 * Remove a group to the JList.
	 * 
	 * @param group - the group to remove.
	 */
	
	public void removeGroup(Group group){
		panGroup.getListPanel().removeItem(group);
	}
	
	/**
	 * Update the JList of groups.
	 * 
	 * @param groups - the groups to show.
	 */
	
	public void updateListGroup(Collection<Group> groups) {
		ListPanel <Group> list = panGroup.getListPanel();
		
		Iterator <Group> it = groups.iterator();
		while( it.hasNext() ) {
			Group current = it.next();
			
			if( ! current.getId().equals(" ") )
				list.addItem(current);
		}
	}

	/**
	 * Set a group selected in the JList.
	 * 
	 * @param group - the group to set selected.
	 */
	
	public void setGroupSelected(Group group){
		panGroup.setSelected(group);
	}
	
	/**
	 * Modify a group of the JList.
	 * 
	 * @param group - the group to modify.
	 */
	
	public void modifySelectedGroup(Group group){
		panGroup.getListPanel().setItemAt(panGroup.getListPanel().getSelectedIndex(), group);
	}
	
	/* ----------------------------------------------------------------- ACTION ON PROJECTS --------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Return the select project of the JList.
	 * 
	 * @return the selected project.
	 */
	
	public Project getSelectedProject() {
		return panProject.getListPanel().getSelectedItem();
	}
	
	/**
	 * Add a project to the JList.
	 * 
	 * @param project - the project to add.
	 */
	
	public void addProject(Project project) {
		panProject.getListPanel().addItem(project);
	}
	
	/**
	 * Remove a project to the JList.
	 * 
	 * @param project - the project to remove.
	 */
	
	public void removeProject(Project project) {
		panProject.getListPanel().removeItem(project);
	}
	
	/**
	 * Update the JList of projects.
	 * 
	 * @param projects - the projects to show.
	 */
	
	public void updateListProject( Collection <Project> projects ) {
		panProject.getListPanel().updateList(projects);
	}
	
	/**
	 * Set a project selected in the JList.
	 * 
	 * @param project - the project to set selected.
	 */
	
	public void setProjectSelected(Project project) {
		panProject.setSelected(project);
	}
	
	/**
	 * Modify a project to the JList.
	 * 
	 * @param project - the project to modify.
	 */
	
	public void modifySelectedProject(Project project) {
		panProject.getListPanel().setItemAt(panProject.getListPanel().getSelectedIndex(), project);
	}
	
	/* ----------------------------------------------------------------- ACTION ON STAKEHOLDERS ----------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Return the select stakeholder of the JList.
	 * 
	 * @return the selected stakeholder.
	 */
	
	public Stakeholder getSelectedStakeholder() {
		return panIntervenant.getListPanel().getSelectedItem();
	}
	
	/**
	 * Add a stakeholder to the JList.
	 * 
	 * @param stakeholder - the stakeholder to add.
	 */
	
	public void addStakeholder(Stakeholder stakeholder) {
		panIntervenant.getListPanel().addItem(stakeholder);
	}
	
	/**
	 * Remove a stakeholder to the JList.
	 * 
	 * @param stakeholder - the stakeholder to remove.
	 */
	
	public void removeStakeholder(Stakeholder stakeholder) {
		panIntervenant.getListPanel().removeItem(stakeholder);
	}
	
	/**
	 * Update the JList of stakeholders.
	 * 
	 * @param stakeholders - the stakeholders to show.
	 */
	
	public void updateListStakeholders(Collection <Stakeholder> stakeholders) {
		panIntervenant.getListPanel().updateList(stakeholders);
	}
	
	/**
	 * Set a stakeholder selected in the JList.
	 * 
	 * @param stakeholder - the stakeholder to set selected
	 */
	
	public void setStakeholderSelected(Stakeholder stakeholder){
		panIntervenant.setSelected(stakeholder);
	}
	
	/**
	 * Modify a stakeholder to the JList.
	 * 
	 * @param stakeholder - the stakeholder to modify.
	 */
	
	public void modifySelectedStakeholder(Stakeholder stakeholder) {
		panIntervenant.getListPanel().setItemAt(panIntervenant.getListPanel().getSelectedIndex(), stakeholder);
	}
	
	/**
	 * Get the details on a project selected on the stakeholder panel.
	 * 
	 * @param project - the project selected.
	 */
	
	public Project getSelectedProjectDetails(Stakeholder stakeholder) {
		System.out.println(panIntervenant.getSelectedProject());
		return panIntervenant.getSelectedProject();
	}

}
