package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import data.Group;
import data.Student;
import main.Controller;
import net.miginfocom.swing.MigLayout;

/** Onglet des Students
 * 
 * @author Flotos
 *
 */
public class JPanelStudent extends JPanel implements Constants {

	//Liste des composants du panel
	private JLabel sortByLabel;
	private JComboBox <String> sortByStudent;
	private ListPanel <Student> listStudent;
	private JButton newStudent;
	private JButton modifyStudent;
	private JButton deleteStudent;
	private JLabel labelName;
	private JTextField name;
	private JLabel labelFirstName;
	private JTextField firstName;
	private JPanelGroup groupPanel;
	
	//JPanels
	private JPanel globalPanel = new JPanel(new MigLayout("wrap 2"));
	
	private JPanel westSide = new JPanel(new MigLayout("wrap 1"));
	private JPanel eastSide = new JPanel(new MigLayout("wrap 4",	 // Layout Constraints
							"[100][100][100][100]",  				 // Column constraints
		      							""));     					 // Row constraints
	
	public JPanelStudent(Controller controller, JFrame frame) {
		super(new MigLayout("fill"));
		groupPanel = new JPanelGroup(controller, new Student());
		groupPanel.setEditable(false);
		
		//initialisation des composants du panel
		sortByLabel = new JLabel("Trier par");
		sortByStudent = new JComboBox <String> ();
		listStudent = new ListPanel <Student> (controller, EVT_SELECT_STUDENT);
		
		newStudent = new JButton("Nouveau");
		newStudent.addActionListener(controller);
		newStudent.setActionCommand(EVT_ADD_STUDENT);
		
		modifyStudent = new JButton("Modifier");
		modifyStudent.addActionListener(controller);
		modifyStudent.setActionCommand(EVT_MODIFY_STUDENT);
		
		deleteStudent = new JButton("Supprimer");
		deleteStudent.addActionListener(controller);
		deleteStudent.setActionCommand(EVT_REMOVE_STUDENT);
		
		labelName = new JLabel("Nom");
		name = new JTextField();
		name.setEditable(false);
		labelFirstName = new JLabel("Prénom");
		firstName = new JTextField();
		firstName.setEditable(false);
		
		//Les ajoute à l'onglet et les organise
		//=Panel de gauche
		westSide.add(sortByLabel, "span, growx");
		westSide.add(sortByStudent, "span, growx");
		westSide.add(listStudent, "width 100%, height 100%, growy");
		westSide.add(newStudent, "align center, span, shrink");
		
		//=Panel de droite
		eastSide.add(labelName, "grow, span 2");
		eastSide.add(labelFirstName, "grow, span2");
		eastSide.add(name, "span 2, grow");
		eastSide.add(firstName, "span 2, grow");
		
		
		eastSide.add(modifyStudent, "grow, cell 1 3");
		eastSide.add(deleteStudent, "grow");
		
		globalPanel.add(westSide, "west");
		globalPanel.add(eastSide, "grow, width 100%, wrap");
		
		globalPanel.add(groupPanel, "grow"); //TODO à changer null par l'étudiant sélectionné
		eastSide.setBorder(BorderFactory.createTitledBorder("Détails de l'étudiant"));

		this.add(globalPanel, "grow");
	}

	public ListPanel <Student> getListPanel() {
		return listStudent;
	}
	
	public void setSelected(Student student){
		name.setText(student.getName());
		firstName.setText(student.getFirstName());
		
		Group group = student.getGroup();
		
		if( group != null ) {
			groupPanel.setSelected(group);
//			groupPanel.getFieldNumero().setText(group.getId());
//			groupPanel.setMemberList(group.getStudents());
//			groupPanel.setWishesList(group.getWishes());
//			
//			if( group.getProject() != null )
//				groupPanel.setStakeholdersList(group.getProject().getStakeholders());
		}
	}
	
	
}
