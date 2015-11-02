package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import data.Group;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;
import main.Controller;
import net.miginfocom.swing.MigLayout;
import view.dialog.JDialogNewGroup;

/** Tab displaying a {@link Group}
 * 
 * @author Flotos
 *
 */
public class JPanelGroup extends JPanel implements Constants {

	//Liste des composants du panel de gauche
	private JLabel sortByLabel;
	private JComboBox sortByGroup;
	private ListPanel <Group> listGroup;
	private JButton newGroup;
	//Liste des composants du panel de droite
	private JLabel numero;
	private Controller controller;
	private JFrame frame;
	private JTextField fieldNumero;
	private JButton modifyGroup;
	private JButton deleteGroup;
	private JLabel labelStakeholder;
	private JLabel labelMember;
	private JLabel labelVoeux;
	
	//JPanels
	private JPanel globalPanel = new JPanel(new MigLayout());
	private JPanel eastSide;
	
	private JPanel westSide = new JPanel(new MigLayout("wrap 1"));
	
	// ListPanels
	private ListPanel <Subject> listWishes;
	private ListPanel <Student> listMembers;
	private ListPanel <Stakeholder> listStakeholders;
	
	/** Create the panel displaying a group details.
	 * 
	 * @param controller - Parent Controller
	 * @param student - Should link to the student of whom we want the {@link Group } displayed, Null if we display the panel without the {@link Group} group selection.
	 */
	public JPanelGroup(Controller controller, Student student) {
		super(new MigLayout());
		
		//initialisation des composants du panel
		if( student == null ) {
			
			sortByLabel = new JLabel("Trier par");
			sortByGroup = new JComboBox();
			listGroup = new ListPanel <Group> (controller,EVT_SELECT_GROUP); 
			newGroup = new JButton("Nouveau");

			newGroup.setActionCommand(EVT_ADD_GROUP);
			newGroup.addActionListener(controller);
			
			//=Panel de gauche
			westSide.add(sortByLabel, "span, grow");
			westSide.add(sortByGroup, "span, grow");
			westSide.add(listGroup, "span, grow");
			westSide.add(newGroup, "span, grow");
			
			globalPanel.add(westSide, "grow, width 30%");

			eastSide = groupPanel(controller,null );
			globalPanel.add(eastSide, "grow");
			this.add(globalPanel, "grow");
		} 
		else {
			eastSide = groupPanel(controller, student);
			globalPanel.add(eastSide, "grow");
			this.add(globalPanel, "grow");
		}
	}
	
	public JPanel groupPanel(Controller controller, Student student){
		
		JPanel jpanel = new JPanel(new MigLayout("wrap 4",	 // Layout Constraints
			      "[100][100][100][100]",  				 // Column constraints
			      "[20][100][25][25]"));     					 // Row constraints
		
		numero = new JLabel("Numéro");
		fieldNumero = new JTextField();
		modifyGroup = new JButton("Modifier");
		deleteGroup = new JButton("Supprimer");
		labelStakeholder = new JLabel("Intervenant");
		listStakeholders = new ListPanel <Stakeholder> (controller,""); //TODO [JPanelGroup] ActionCommand event - listIntervenantt
		labelMember = new JLabel("Membres");
		listMembers = new ListPanel <Student> (controller,""); //TODO [JPanelGroup] ActionCommand event - listMember
		labelVoeux = new JLabel("Voeux");
		listWishes = new ListPanel <Subject> (controller,""); //TODO [JPanelGroup] ActionCommand event - listVoeux
		

		
		//=Panel de droite
		jpanel.add(numero);
		if( student == null )
			jpanel.add(fieldNumero, "grow");
		else
			jpanel.add(fieldNumero, "grow, wrap");
		
		if( student == null ) {
			jpanel.add(modifyGroup, "grow");
			jpanel.add(deleteGroup, "grow");
		}
		
		if(student != null && student.getGroup() != null)
			setSelected(student.getGroup());
		
		jpanel.add(labelStakeholder);
		jpanel.add(listStakeholders, "span 3, height 100, grow");
		jpanel.add(labelMember);
		jpanel.add(listMembers, "span 3, grow");
		jpanel.add(labelVoeux);
		jpanel.add(listWishes, "span 3, grow");
		jpanel.setBorder(BorderFactory.createTitledBorder("Groupe"));
		
		// TODO [JPanelGroup] Add the actionControllers
		return jpanel;
	}
	
	public JTextField getFieldNumero(){
		return fieldNumero;
	}
	
	public void setMemberList(Collection<Student> memberList){
		listMembers.updateList(memberList);
	}
	
	public void setStakeholdersList(Collection<Stakeholder> interList){
		/*Iterator <Stakeholder> it = interList.iterator();
		
		while( it.hasNext() ) {
			Stakeholder stakeholder = it.next();
			if( stakeholder != null )
				listStakeholders.addItem(stakeholder);
		}*/
		listStakeholders.updateList(interList);
	}
	
	public void setWishesList(Collection<Subject> voeuxList){
		listWishes.updateList(voeuxList);
	}
	
	public void setSelected(Group group){
		 //numero
		
		fieldNumero.setText(group.getId());
		
		// intervenant
		if( group.getProject() != null ) 
			setStakeholdersList(group.getProject().getStakeholders());
		
		setMemberList(group.getStudents());
		setWishesList(group.getWishes());
		
		this.validate();
	}
	
	/* public void afficherJDialogNewGroup() {
		new JDialogNewGroup(frame, model.getStudents());
		
	} */ 
	
	public ListPanel<Group> getListPanel() {
		return listGroup;
	}

	public void setEditable(boolean b) {
		fieldNumero.setEditable(b);
	}
}
