package view.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Controller;
import net.miginfocom.swing.MigLayout;
import constants.Constants;
import data.Group;
import data.MyObject;
import data.Project;
import data.Stakeholder;
import data.Student;

public class JDialogNewGroup extends JDialogNew {

	
	private JPanel content;
		JLabel studentsText;
		JComboBox <Student> student1;
		JComboBox <Student> student2;
		JComboBox <Student> student3;
		JComboBox <Student> student4;
		JComboBox <Student> student5;
		
		JLabel stkText;
		JComboBox <Stakeholder> stakeholderCBx;
		
		JLabel grpText;
		JTextField grp;
		JButton registerGroup;
		JButton cancel;
		Controller controler;
		
		private boolean isDataValid = false;
		
		public JDialogNewGroup(JFrame sourceFrame, Collection <Student> student, Collection <Stakeholder> stakeholder) {
			super(sourceFrame, "Saisir un groupe", true);
			initContent(student, stakeholder);
			setVisible(true);
			
				}

	public JDialogNewGroup(JFrame sourceFrame, Collection <Student> student, Collection <Group> group, Collection <Stakeholder> stakeholder) {
		super(sourceFrame, "Saisir un groupe", true);
		initContent(student, stakeholder);
		
		ArrayList <Student> studentsArray = new ArrayList <Student> (student);
		ArrayList <Stakeholder> stakeholderArray = new ArrayList <Stakeholder> (stakeholder);
		
		setVisible(true);
	}
	
	
	public void initContent( Collection <Student> student, Collection <Stakeholder> stakeholder) {
		setLayout(new MigLayout());
		content = new JPanel(new MigLayout());
		content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nouveau groupe"));
		add(content);
		
		
		grpText = new JLabel("Nouveau groupe : ");
		content.add(grpText);
		grp = new JTextField();
		content.add(grp, "width 100");
		
		studentsText = new JLabel("Etudiants : ");
		content.add(studentsText);
		student1 = new JComboBox<Student>(student.toArray(new Student[student.size()]));
		content.add(student1, "width 60");
		
		student2 = new JComboBox<Student>(student.toArray(new Student[student.size()]));
		content.add(student2, "width 60");

		student3 = new JComboBox<Student>(student.toArray(new Student[student.size()]));
		content.add(student3, "width 60");
		
		student4 = new JComboBox<Student>(student.toArray(new Student[student.size()]));
		content.add(student4, "width 60");
		
		student5 = new JComboBox<Student>(student.toArray(new Student[student.size()]));
		content.add(student5, "width 60");
		
		stkText = new JLabel("Intervenant");
		stakeholderCBx = new JComboBox<Stakeholder>(stakeholder.toArray(new Stakeholder[stakeholder.size()]));
		content.add(stkText, "width 60");
		content.add(stakeholderCBx, "width 60");
		
		registerGroup = new JButton("Enregistrer");
		content.add(registerGroup, "cell 1 4");
		cancel = new JButton("Annuler");
		content.add(cancel, "cell 2 4");
		
		registerGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isDataValid = checkData();
				dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isDataValid = false;
				dispose();
			}
		});
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	/*public void createGroup(ActionEvent e) {
		Group group = new Group(grp.getText());
		group.addStudent(student1.getItemAt(student1.getSelectedIndex()));
		group.addStudent(student2.getItemAt(student2.getSelectedIndex()));
		group.addStudent(student3.getItemAt(student3.getSelectedIndex()));
		group.addStudent(student4.getItemAt(student4.getSelectedIndex()));
		group.addStudent(student5.getItemAt(student5.getSelectedIndex()));
		controler.actionPerformed(e);
	}*/

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public Group toObject() {
		if( ! isDataValid )
			return null;
		
		Group group = new Group(grp.getText());
		group.addStudent(student1.getItemAt(student1.getSelectedIndex()));
		group.addStudent(student2.getItemAt(student2.getSelectedIndex()));
		group.addStudent(student3.getItemAt(student3.getSelectedIndex()));
		group.addStudent(student4.getItemAt(student4.getSelectedIndex()));
		group.addStudent(student5.getItemAt(student5.getSelectedIndex()));
		
		return group;
	}

}
