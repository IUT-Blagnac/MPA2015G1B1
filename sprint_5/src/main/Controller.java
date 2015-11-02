package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Model;
import utils.function.Arg0;
import utils.function.Arg1;
import utils.function.Function;
import utils.function.Procedure;
import view.View;
import view.dialog.JDialogDetailsProject;
import view.dialog.JDialogNew;
import view.dialog.JDialogNewGroup;
import view.dialog.JDialogNewProject;
import view.dialog.JDialogNewStakeholder;
import view.dialog.JDialogNewStudent;
import view.dialog.JDialogNewSubject;
import constants.Constants;
import data.Group;
import data.Project;
import data.Stakeholder;
import data.Stakeholder.Category;
import data.Student;
import data.Subject;

/**
 * Manage events and interactions between the {@link View} and the <b>User</b>.
 * 
 * @author Emmanuel Chebbi
 */
public class Controller implements Constants, ActionListener, MouseListener {
	
	// *****************************************************************************
	//					MAIN
	// **********************************************************
	
	public static void main( String[] args ) {
		new Controller();
	}
	
	// *****************************************************************************
	//					ATTRIBUTS
	// **********************************************************
	
	/** Reference to itself. (needed by anonymous classes) */
	private Controller controller = this;
	
	/** The software's main window. */
	private View view;
	
	/** The model containing data */
	private Model model;
	
	/** Associative table containing events' command and the associated function */
	private HashMap <String, Procedure<Arg1<ActionEvent>> > commands;
	
	
	private HashMap <String, Function<JDialogNew, Arg0>> dialogNewElement;
	
	// *****************************************************************************
	//					CONSTRUCTORS
	// **********************************************************

	/**
	 * Create a default {@link View} and launch the program.
	 */
	public Controller() {
		
		view = new View(this);

		String  csvSubjects = "data/sujets2014_2015.csv",
				csvStudents = "data/etudiants2014_2015.csv",
				csvIntervenants = "data/intervenants2014_2015.csv",
				csvProjects = "data/projets2014_2015.csv";
		
		model = new Model();
		
		try {
			
			model.loadSubjects(csvSubjects);
			view.updateListSubject(model.getSubjects());
			
			model.initStudents(csvStudents);
			view.updateListStudent(model.getStudents());
			
			view.updateListGroup(model.getGroups());
			
			model.loadStakeholders(csvIntervenants);
			view.updateListStakeholders(model.getStakeholders());
			
			model.loadProjects(csvProjects);
			view.updateListProject(model.getProjects());
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
		
		commands = new HashMap <String, Procedure<Arg1<ActionEvent>> >();
		
//		initDialogNew();
		
		handleMenuEvents();
		handleEventsOnGroups();
		handleEventsOnStudents();
		handleEventsOnSubjects();
		handleEventsOnProjects();
		handleEventsOnStakeholders();
	}
	
	// ****************************************************************************************************************
	//					CATCH EVENTS
	// ****************************************************************************************************************


	/* --------------------------------------------------------------------------------- INIT EVENTS ------------------------------------ */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	@SuppressWarnings("unused")
	private void initDialogNew() {
		dialogNewElement = new HashMap <String, Function<JDialogNew, Arg0>> ();
		
		dialogNewElement.put( EVT_ADD_STUDENT, new Function<JDialogNew, Arg0>() {
			
			@Override
			public JDialogNew invoke(Arg0 arguments ) {
				return new JDialogNewStudent(view, model.getGroups());
			}
		});
		
		dialogNewElement.put( EVT_ADD_SUBJECT, new Function<JDialogNew, Arg0>() {
			
			@Override
			public JDialogNew invoke(Arg0 arguments ) {
				return new JDialogNewSubject(view);
			}
		});
		
		dialogNewElement.put( EVT_ADD_PROJECT, new Function<JDialogNew, Arg0>() {
			
			@Override
			public JDialogNew invoke(Arg0 arguments ) {
				return new JDialogNewProject(view, model.getGroups(), model.getSubjects());
			}
		});
	
//		commands.put( EVT_ADD, new Procedure<Arg1<String>>() {
//			
//			@Override
//			public void invoke(Arg1 <String> arguments ) {
//				Object added = dialogNewElement.get(arguments.getArg1())	// display the JDialog corresponding to the element to add
//						.invoke(null).toObject();							// then retrieve the object created
//				
//				Object o = added.getClass().cast(added);
//
//				if( added == null )
//					return;
//				
//				if( added instanceof Student) model.addStudent( (Student)added );
//				else if( added instanceof Subject ) model.addSubject( (Subject)added );
//				else if( added instanceof Project ) model.addProject( (Project)added );
//			}
//		});
//
//		commands.put( EVT_REMOVE, new Procedure<Arg1<String>>() {
//			
//			@Override
//			public void invoke(Arg1 <String> arguments) {
//				Student student = view.getSelectedStudent();
//				
//				if( student == null ) {
//					JOptionPane.showMessageDialog(view, "Aucun étudiant n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
//					return;
//				}
//				
//				int option = JOptionPane.showConfirmDialog( view, 
//								"Voulez-vous vraiment supprimer l'étudiant(e) "+student.getFirstName()+" "+student.getName()+" ?", "Confirmation", 
//								JOptionPane.YES_NO_OPTION );
//				
//				if( option == JOptionPane.YES_OPTION )
//					model.removeStudent(student);
//			}
//		});
	}
	
	/**
	 * Make the Controller able to handle events on {@link Student}s
	 */
	private void handleEventsOnStudents() {
		
		commands.put( EVT_SELECT_STUDENT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				ActionEvent e = arguments.getArg1();
				Student student = (Student)e.getSource();
				view.setStudentSelected(student);
			}
		});
		
		commands.put( EVT_ADD_STUDENT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Student newStudent = new JDialogNewStudent(view, model.getGroups())
						.toObject();

				if( newStudent != null )
					model.addStudent(newStudent);
			}
		});

		commands.put( EVT_REMOVE_STUDENT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Student student = view.getSelectedStudent();
				
				if( student == null ) {
					JOptionPane.showMessageDialog(view, "Aucun étudiant n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog( view, 
								"Voulez-vous vraiment supprimer l'étudiant(e) "+student.getFirstName()+" "+student.getName()+" ?", "Confirmation", 
								JOptionPane.YES_NO_OPTION );
				
				if( option == JOptionPane.YES_OPTION )
					model.removeStudent(student);
			}
		});
	
		commands.put( EVT_MODIFY_STUDENT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Student student = view.getSelectedStudent();
				
				if( student == null ) {
					JOptionPane.showMessageDialog(view, "Aucun étudiant n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Student modifiedStudent = new JDialogNewStudent(view, model.getGroups(), student).
						toObject();
				
				if( modifiedStudent != null )
					model.modifyStudent(student.getId(), modifiedStudent);
			}
		});
	}
	
	/**
	 * Make the Controller able to handle events on {@link Subject}s
	 */
	private void handleEventsOnSubjects() {
		
		commands.put( EVT_SELECT_SUBJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				ActionEvent e = arguments.getArg1();
				Subject subject = (Subject)e.getSource();

				view.setSubjectSelected(subject);
			}
		});
		
		commands.put( EVT_ADD_SUBJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Subject newSubject = new JDialogNewSubject(view).toObject();

				if( newSubject != null )
					model.addSubject(newSubject);
			}
		});

		commands.put( EVT_REMOVE_SUBJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Subject subject = view.getSelectedSubject();
				
				if( subject == null ) {
					JOptionPane.showMessageDialog(view, "Aucun sujet n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog( view, 
								"Voulez-vous vraiment supprimer le sujet " + subject.getTitle() + " ?", "Confirmation", 
								JOptionPane.YES_NO_OPTION );
				
				if( option == JOptionPane.YES_OPTION )
					model.removeSubject(subject);
			}
		});
	
		commands.put( EVT_MODIFY_SUBJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Subject subject = view.getSelectedSubject();
				
				if( subject == null ) {
					JOptionPane.showMessageDialog(view, "Aucun sujet n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Subject modifiedSubject = new JDialogNewSubject(view, subject).
						toObject();
				
				if( modifiedSubject != null )
					model.modifySubject(subject.getId(), modifiedSubject);
			}
		});
	}
	
	/**
	 * Make the Controller able to handle events on {@link Project}s
	 */
	private void handleEventsOnProjects() {
		
		commands.put( EVT_SELECT_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				ActionEvent e = arguments.getArg1();
				Project project = (Project)e.getSource();

				view.setProjectSelected(project);
			}
		});
		
		commands.put(EVT_ADD_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Project newProject = new JDialogNewProject(view,  model.getGroups(), model.getSubjects()).toObject();

				if( newProject != null )
					model.addProject(newProject);
			}
		});
		
		commands.put(EVT_CLONE_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
				public void invoke(Arg1 <ActionEvent> arguments ) {
					Project project = view.getSelectedProject();
					Project newProject;
					if( project != null ){
						newProject = new Project(project.getGroup(), project.getSubject());
						if(project.getStakeholder(Category.CLIENT) != null)
							newProject.setStakeholder(Category.CLIENT, project.getStakeholder(Category.CLIENT));
						if(project.getStakeholder(Category.SUPERVISOR) != null)
							newProject.setStakeholder(Category.SUPERVISOR, project.getStakeholder(Category.SUPERVISOR));
						if(project.getStakeholder(Category.TECHNICAL_SUPPORT) != null)
							newProject.setStakeholder(Category.TECHNICAL_SUPPORT, project.getStakeholder(Category.TECHNICAL_SUPPORT));
						if( newProject != null )
						model.addProject(newProject);
					}
						
						
						
			}
		}); 
		
		commands.put(EVT_ADD_GROUP, new Procedure<Arg1<ActionEvent>>() {
					
					@Override
					public void invoke(Arg1 <ActionEvent> arguments ) {
						System.out.println("in");
						Group newGroup = new JDialogNewGroup(view, model.getStudents(), model.getGroups(), model.getStakeholders()).toObject();
		
						if( newGroup != null )
							model.addGroup(newGroup);
					}
				});

		commands.put( EVT_REMOVE_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Project project = view.getSelectedProject();
				
				if( project == null ) {
					JOptionPane.showMessageDialog(view, "Aucun projet n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog( view, 
								"Voulez-vous vraiment supprimer le projet " + project.getSubject().getTitle() + " ?", "Confirmation", 
								JOptionPane.YES_NO_OPTION );
				
				if( option == JOptionPane.YES_OPTION )
					model.removeProject(project);
			}
		});
	
		commands.put( EVT_MODIFY_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Project project = view.getSelectedProject();
				
				if( project == null ) {
					JOptionPane.showMessageDialog(view, "Aucun projet n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Project modifiedProject = new JDialogNewProject(view,  model.getGroups(), model.getSubjects()).toObject();
				
				if( modifiedProject != null )
					model.modifyProject(project.getId(), modifiedProject);
			}
		});
	}
	
	/** 
	 * Make the Controller able to handle events on {@link Stakeholder}s
	 */
	private void handleEventsOnStakeholders() {
		
		commands.put( EVT_SELECT_STAKEHOLDER, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				ActionEvent e = arguments.getArg1();
				Stakeholder stakeholder = (Stakeholder)e.getSource();
				view.setStakeholderSelected(stakeholder);
			}
		});
		
		commands.put(EVT_ADD_STAKEHOLDER, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Stakeholder newStakeholder = new JDialogNewStakeholder(view).toObject();

				if( newStakeholder != null )
					model.addStakeholder(newStakeholder);
			}
		});

		commands.put( EVT_REMOVE_STAKEHOLDER, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Stakeholder stakeholder = view.getSelectedStakeholder();
				
				if( stakeholder == null ) {
					JOptionPane.showMessageDialog(view, "Aucun(e) intervenant(e) n'est sélectionné(e) !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog( view, 
								"Voulez-vous vraiment supprimer l'intervenant(e) " + stakeholder.getFirstName() + " " + stakeholder.getName() + " ?", "Confirmation", 
								JOptionPane.YES_NO_OPTION );
				
				if( option == JOptionPane.YES_OPTION )
					model.removeStakeholder(stakeholder);
			}
		});
	
		commands.put( EVT_MODIFY_STAKEHOLDER, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				Stakeholder stakeholder = view.getSelectedStakeholder();
				
				if( stakeholder == null ) {
					JOptionPane.showMessageDialog(view, "Aucun(e) intervenant(e) n'est sélectionné(e) !", "Attention", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Stakeholder modifiedStakeholder = new JDialogNewStakeholder(view, stakeholder).toObject();
				
				if( modifiedStakeholder != null )
					model.modifyStakeholder(stakeholder.getId(), modifiedStakeholder);
			}
		});
		
		commands.put(EVT_DETAILS_PROJECT, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				JDialogDetailsProject dialog = new JDialogDetailsProject(view, controller, view.getSelectedProjectDetails(view.getSelectedStakeholder()));
				
			}
		});
		

	}
	
	/**
	 * Make the Controller able to handle events on {@link Group}s
	 */
	private void handleEventsOnGroups() {
		
		commands.put( EVT_SELECT_GROUP, new Procedure<Arg1<ActionEvent>>() {
			
			@Override
			public void invoke(Arg1 <ActionEvent> arguments ) {
				ActionEvent e = arguments.getArg1();
				Group group = (Group)e.getSource();
				view.setGroupSelected(group);
			}
		});
	}
	
	/**
	 * Make the Controller able to handle menu events.
	 */
	private void handleMenuEvents() {
		
		commands.put(MENU_EVT_SAVE_STUDENTS, new Procedure<Arg1<ActionEvent>>() {

			@Override
			public void invoke(Arg1<ActionEvent> arguments) {
				
				File file = getFile();
				
				if( file == null )
					return;
				
				try {
					model.saveStudents(file);
					
					JOptionPane.showMessageDialog( view,
							   "Fichier sauvegardé avec succès.", "Success !",
							   JOptionPane.INFORMATION_MESSAGE );
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog( view,
							   "Une erreur s'est produite lors de la sauvegarde du fichier.", "Erreur",
							   JOptionPane.ERROR_MESSAGE );
				}
				
			}
			
		});
		
		commands.put(MENU_EVT_LOAD_STUDENTS, new Procedure<Arg1<ActionEvent>>() {

			@Override
			public void invoke(Arg1<ActionEvent> arguments) {
				File file = getFile();
				
				if( file == null )
					return;
				
				try {
					model.initStudents(file.getPath());
					view.updateListStudent(model.getStudents());
					
				} catch(InvalidPropertiesFormatException ipfe) {
					JOptionPane.showConfirmDialog( view,
								"Format invalide : impossible de charger le fichier.", "Format invalide",
								JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE );
				
				} catch(IOException ie) {
					JOptionPane.showConfirmDialog( view,
							   "Une erreur s'est produite lors de la lecture du fichier.", "Erreur",
							   JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE );
				}
			}
			
		});
		
		commands.put(MENU_EVT_SAVE_SUBJECTS, new Procedure<Arg1<ActionEvent>>() {

			@Override
			public void invoke(Arg1<ActionEvent> arguments) {
				File file = getFile();
				
				if( file == null )
					return;
				
				try {
					model.saveSubjects(file);
					
					JOptionPane.showMessageDialog( view,
							   "Fichier sauvegardé avec succès.", "Success !",
							   JOptionPane.INFORMATION_MESSAGE );
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog( view,
							   "Une erreur s'est produite lors de la sauvegarde du fichier.", "Erreur",
							   JOptionPane.ERROR_MESSAGE );
				}
			}
			
		});
		
		commands.put(MENU_EVT_LOAD_SUBJECTS, new Procedure<Arg1<ActionEvent>>() {

			@Override
			public void invoke(Arg1<ActionEvent> arguments) {
				File file = getFile();
				
				if( file == null )
					return;
				
				try {
					model.loadSubjects(file.getPath());
					view.updateListSubject(model.getSubjects());
					
				} catch(InvalidPropertiesFormatException ipfe) {
					JOptionPane.showConfirmDialog( view,
								"Format invalide : impossible de charger le fichier.", "Format invalide",
								JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE );
				
				} catch(IOException ie) {
					JOptionPane.showConfirmDialog( view,
							   "Une erreur s'est produite lors de la lecture du fichier.", "Erreur",
							   JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE );
				}
			}
			
		});
	}
	
	
	
	/* --------------------------------------------------------------------------------- ACTION EVENTS ---------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		// Retrieve the functor associated to the event
		Procedure< Arg1<ActionEvent> > func = commands.get(command);
		
		if( func == null )
			return;
		
		func.invoke( new Arg1<ActionEvent>(e) );
	}
	
	/* --------------------------------------------------------------------------------- MOUSE EVENTS ---------------------------------- */
	/* --------------------------------------------------------------------------------------------------------------------------------- */

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	// ****************************************************************************************************************
	//					METHODS
	// ****************************************************************************************************************

	/* -------------------------------------------------------------------------- SAVE - LOAD ------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * Asks the user to choose a file.<br>
	 * <br>
	 * If none file is chosen, this function will return null.<br>
	 * <br>
	 * Uses a JFileChooser.
	 * 
	 * @return the File selected by the user
	 */
	private static File getFile() {
		
		JFileChooser fileChooser = new JFileChooser(new File("."));

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
		fileChooser.setFileFilter(filter);
		
		if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getPath();
			return new File(path);
		}

		return null;
	}
	
}
