package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constants.Constants;
import net.miginfocom.swing.MigLayout;
import view.dialog.JDialogNewProject;
import main.Controller;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;

@SuppressWarnings("serial")
public class JPanelProject extends JPanel implements Constants{ 
	
	// ********************************************************** 
	//					ATTRIBUTS
	// ***************************************
	
	/** The listener */
	private Controller controller;
	
	/** JPanel containing the JList of {@link Subject} */
	private ListPanel <Project> listProject;
	//Components
	private JLabel sortByLabel;
	private JComboBox <String> sortByProject;
	private JButton newProject;
	private JButton cloneProject;
	private JButton modifyProject;
	private JButton deleteProject;
	private JLabel labelSubject;
	private JTextField txtSubjectTitle;
	private JLabel labelDetailsSubject;
	private JTextArea txtSubjectDescription;
	private JLabel labelGroupe;
	private JTextField txtGroupId;
	private JLabel labelListStudents;
	private ListPanel <Student> listStudents;
	private JLabel labelIntervenant;
	private ListPanel <Stakeholder> listIntervenant;
	
	//JPanels
	private JPanel globalPanel = new JPanel(new MigLayout("wrap 2"));
		
	private JPanel westSide = new JPanel(new MigLayout("wrap 1"));
	private JPanel panel;

	// **********************************************************
	//					CONSTRUCTOR
	// ***************************************
	
	public JPanelProject(Controller controller,JFrame frame, Project project) {
		
		super(new MigLayout("fill"));
		this.controller = controller;
		
		if(project == null){
			//components west
			sortByLabel = new JLabel("Trier par");
			sortByProject = new JComboBox <String>();
			listProject = new ListPanel <Project> (controller, EVT_SELECT_PROJECT);
			newProject = new JButton("Nouveau");
			//=Panel de gauche
			westSide.add(sortByLabel, "span, growx");
			westSide.add(sortByProject, "span, growx");
			westSide.add(listProject, "width 100%, height 100%, growy");
			westSide.add(newProject, "align center, span, shrink");

			globalPanel.add(westSide, "west");
			newProject.addActionListener(controller);
			newProject.setActionCommand(EVT_ADD_PROJECT);
		}
		
		panel = projectPanel(controller, project);
		this.add(globalPanel, "grow");
		globalPanel.add(panel, "grow, width 100%");
		panel.setBorder(BorderFactory.createTitledBorder("D�tails du Projet"));
	}
	
	public JPanel projectPanel(Controller controller, Project project){
		
		JPanel panelTemp = new JPanel(new MigLayout("wrap 3",	 // Layout Constraints
			      "[100][100][100]",  				 // Column constraints
			      ""));     					 // Row constraints
		//components
		modifyProject = new JButton("Modifier");
		modifyProject.addActionListener(controller);
		modifyProject.setActionCommand(EVT_MODIFY_PROJECT);
		
		cloneProject = new JButton("Cloner");
		cloneProject.addActionListener(controller);
		cloneProject.setActionCommand(EVT_CLONE_PROJECT);
		
		deleteProject = new JButton("Supprimer");
		deleteProject.addActionListener(controller);
		deleteProject.setActionCommand(EVT_REMOVE_PROJECT);
		
		labelSubject = new JLabel("Titre du Sujet");
		txtSubjectTitle = new JTextField();
		labelDetailsSubject = new JLabel("D�tails du Sujet");
		txtSubjectDescription = new JTextArea(3, 3);
		labelGroupe = new JLabel("Groupe : ");
		txtGroupId = new JTextField();
		labelListStudents = new JLabel("Liste �tudiants");
		listStudents = new ListPanel <Student> (controller,"");
		labelIntervenant = new JLabel("Intervenants");
		listIntervenant = new ListPanel <Stakeholder> (controller, "");
		
		txtGroupId.setEditable(false);
		txtSubjectTitle.setEditable(false);
		txtSubjectDescription.setEditable(false);
		
		if(project != null)
			this.setSelected(project);
		
		//Adding to the JPanel
		if(project == null)
			panelTemp.add(labelSubject);
		else
			panelTemp.add(labelSubject, "wrap");
		if(project == null){
			panelTemp.add(modifyProject);
			panelTemp.add(deleteProject);
			panelTemp.add(cloneProject, "cell 1 3, wrap"); 
		}
		panelTemp.add(txtSubjectTitle, "grow, span 3, wrap");
		panelTemp.add(labelDetailsSubject, "wrap");
		panelTemp.add(txtSubjectDescription, "grow, span 3, wrap");
		panelTemp.add(labelGroupe);
		panelTemp.add(txtGroupId, "grow, wrap");
		panelTemp.add(labelListStudents, "wrap");
		panelTemp.add(listStudents, "wrap, span 3, grow");
		panelTemp.add(labelIntervenant, "wrap");
		panelTemp.add(listIntervenant, "wrap, span 3, height 100, grow");
		
		return panelTemp;
	}
	
	public ListPanel <Project> getListPanel() {
		return this.listProject;
	}
	
	public void setSelected(Project project) {
		
		// On affiche le sujet
		txtSubjectTitle.setText(project.getSubject().getTitle());
		txtSubjectDescription.setText(project.getSubject().getDescription());
		
		// On affiche les �tudiants
		txtGroupId.setText(project.getGroup().getId());
		listStudents.updateList(project.getGroup().getStudents());
		
		// On affiche les intervenants
		listIntervenant.updateList(project.getStakeholders());
	}
}
