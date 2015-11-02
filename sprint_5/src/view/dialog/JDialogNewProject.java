package view.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Group;
import data.Project;
import data.Subject;
import net.miginfocom.swing.MigLayout;
 
@SuppressWarnings("serial")
public class JDialogNewProject extends JDialogNew {
	
	private JPanel contentPan;
		private JLabel groupeLab;
		private JLabel sujetLab;
		private JComboBox <Group> groupeCBx;
		private JComboBox <Subject> sujetCBx;
		private JButton addGroupBut;
		private JButton registerProjectBut;
		private JButton cancelBut;
		
	private boolean isDataValid = false;

	public JDialogNewProject(JFrame sourceFrame, Collection <Group> groups, Collection <Subject> subjects) {
		super(sourceFrame, "Saisir un projet", true);

		//ArrayList <Group> groupsArray = new ArrayList <Group> (groups);
		//Collections.sort(groupsArray);
		
		initContent(groups, subjects);
		
		if( groups.size() != 0 )
			groupeCBx.setSelectedIndex(0);

		setVisible(true);
	}
	
	public JDialogNewProject(JFrame sourceFrame, Collection <Group> groups, Collection <Subject> subjects, Project project) {
		super(sourceFrame, "Saisir un projet", true);
		
		initContent(groups, subjects);

		ArrayList <Group> groupsArray = new ArrayList <Group> (groups);
		ArrayList <Subject> subjectsArray = new ArrayList <Subject> (subjects);

		// Set current project's group selected
		for( int i = 0 ; ; i++ )
			if( groupsArray.get(i) == project.getGroup() ) {
				groupeCBx.setSelectedIndex(i);
				break;
			}
		
		// Set current project's subject selected
		for( int i = 0 ; ; i++ )
			if( subjectsArray.get(i) == project.getSubject() ) {
				sujetCBx.setSelectedIndex(i);
				break;
			}
		
		setVisible(true);
	}
	
	
	public void initContent( Collection <Group> groups, Collection <Subject> subjects ) {
		contentPan = new JPanel(new MigLayout());
		contentPan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nouvel Etudiant"));
		add(contentPan);
		
		groupeLab = new JLabel("Groupe PTUT : ");
		contentPan.add(groupeLab);
		
		// Removes the Group of "Students without Group"
		Iterator <Group> it = groups.iterator();
		while( it.hasNext() ) {
			Group current = it.next();
			
			if( current.getId().equals(" ") ) {
				it.remove();
				break;
			}
		}
		
		groupeCBx = new JComboBox <Group> (groups.toArray(new Group[groups.size()]));
		groupeCBx.setSelectedIndex(0);
		
		sujetCBx = new JComboBox <Subject> (subjects.toArray(new Subject[subjects.size()]));
		sujetCBx.setSelectedIndex(0);
		
		contentPan.add(groupeCBx, "width 60");
		addGroupBut = new JButton("Ajouter Groupe PTUT");
		contentPan.add(addGroupBut, "wrap, width 30, gapleft 30");
		
		sujetLab = new JLabel("Sujet du projet : ");
		contentPan.add(sujetLab);
		contentPan.add(sujetCBx, "width 60");
		
		registerProjectBut = new JButton("Enregistrer");
		contentPan.add(registerProjectBut, "cell 1 4");
		cancelBut = new JButton("Annuler");
		contentPan.add(cancelBut, "cell 2 4");
		
		registerProjectBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isDataValid = checkData();
				dispose();
			}
		});
		
		cancelBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isDataValid = false;
				dispose();
			}
		});
		
		add(contentPan);

		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public boolean checkData() {
		return true;
	}
	
	@Override
	public Project toObject() {
		if( ! isDataValid )
			return null;
		
		Project project = new Project(groupeCBx.getItemAt(groupeCBx.getSelectedIndex()), sujetCBx.getItemAt(sujetCBx.getSelectedIndex()));
		Group group = (Group)groupeCBx.getSelectedItem();
		Subject subject= (Subject)sujetCBx.getSelectedItem();
		
		/*if( group != null )
			group.addProject(project);*/
		
		return project;
	}

}