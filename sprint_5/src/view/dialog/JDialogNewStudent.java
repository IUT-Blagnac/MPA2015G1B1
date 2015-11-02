package view.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Group;
import data.Student;
import net.miginfocom.swing.MigLayout;
 
@SuppressWarnings("serial")
public class JDialogNewStudent extends JDialogNew {
	
	private JPanel contentPan;
		private JLabel nameLab;
		private JTextField nameTF;
		private JLabel firstnameLab;
		private JTextField firstnameTF;
		private JLabel groupeLab;
		private JComboBox <Group> groupeCBx;
		private JButton addGroupBut;
		private JButton registerStudentBut;
		private JButton cancelBut;
		
	private boolean isDataValid = false;

	public JDialogNewStudent(JFrame sourceFrame, Collection <Group> groups) {
		super(sourceFrame, "Saisir un �tudiant", true);

		initContent(groups);

		setVisible(true);
	}
	
	public JDialogNewStudent(JFrame sourceFrame, Collection <Group> groups, Student student) {
		super(sourceFrame, "Saisir un �tudiant", true);

		
		initContent(groups);
		
		nameTF.setText(student.getName());
		firstnameTF.setText(student.getFirstName());

		ArrayList <Group> groupsArray = new ArrayList <Group> (groups);

		for( int i = 0 ; ; i++ )
			if( groupsArray.get(i) == student.getGroup() ) {
				groupeCBx.setSelectedIndex(i);
				break;
			}
		
		setVisible(true);
	}
	
	
	public void initContent( Collection <Group> groups ) {
		contentPan = new JPanel(new MigLayout());
		contentPan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nouvel Etudiant"));
		add(contentPan);
		
		nameLab = new JLabel("Nom : ");
		contentPan.add(nameLab);
		nameTF = new JTextField();
		contentPan.add(nameTF, "width 100");
		
		firstnameLab = new JLabel("Prenom : ");
		contentPan.add(firstnameLab, "gapleft 30");
		firstnameTF = new JTextField();
		contentPan.add(firstnameTF, "wrap, width 100");
		
		groupeLab = new JLabel("Groupe PTUT : ");
		contentPan.add(groupeLab);
		
		groupeCBx = new JComboBox <Group> (groups.toArray(new Group[groups.size()]));
		groupeCBx.setSelectedIndex(0);
		
		contentPan.add(groupeCBx, "width 60");
		addGroupBut = new JButton("Ajouter Groupe PTUT");
		contentPan.add(addGroupBut, "wrap, width 30, gapleft 30");
		
		registerStudentBut = new JButton("Enregistrer");
		contentPan.add(registerStudentBut, "cell 1 4");
		cancelBut = new JButton("Annuler");
		contentPan.add(cancelBut, "cell 2 4");
		
		registerStudentBut.addActionListener(new ActionListener() {
			
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
	public Student toObject() {
		if( ! isDataValid )
			return null;
		
		Student student = new Student(nameTF.getText(), firstnameTF.getText());
		Group group = (Group)groupeCBx.getSelectedItem();
		
		if( group != null )
			group.addStudent(student);
		
		return student;
	}

}
