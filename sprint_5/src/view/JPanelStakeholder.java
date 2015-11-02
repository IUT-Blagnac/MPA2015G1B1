package view;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import data.Project;
import data.Stakeholder;
import main.Controller;
import net.miginfocom.swing.MigLayout;

/** Onglet des Intervenants
 * 
 * @author Flotos
 */
@SuppressWarnings("serial")
public class JPanelStakeholder extends JPanel implements Constants {

	//Liste des composants du panel
	private JLabel sortByLabel;
	private JComboBox <String> sortByIntervenant;
	private ListPanel <Stakeholder> listStakeholders;
	private JCheckBox sortAffected;
	private JButton newIntervenant;
	private JButton modifyStakeholder;
	private JButton deleteStakeholder;
	private JLabel labelName;
	private JTextField name;
	private JLabel labelFirstName;
	private JTextField firstName;
	private JLabel labelId;
	private JTextField id;
	private JLabel labelProject;
	private ListPanel <Project> fieldProject;
	private JButton detailsProject;
	
	//JPanels
	private JPanel globalPanel = new JPanel(new MigLayout());
	
	private JPanel westSide = new JPanel(new MigLayout("wrap 3"));
	private JPanel eastSide = new JPanel(new MigLayout("wrap 3"));
	
	public JPanelStakeholder(Controller controller, JFrame frame) {
		
		super(new MigLayout());
		
		//initialisation des composants du panel
		sortByLabel = new JLabel("Trier par");
		sortByIntervenant = new JComboBox <String> ();
		listStakeholders = new ListPanel <Stakeholder> (controller, EVT_SELECT_STAKEHOLDER ); //TODO [JPanelIntervenant] ActionCommand event
		sortAffected = new JCheckBox("Non-affectés");
		newIntervenant = new JButton("Nouveau");
		modifyStakeholder = new JButton("Modifier");
		deleteStakeholder = new JButton("Supprimer");
		labelName = new JLabel("Nom :");
		name = new JTextField();
		labelFirstName = new JLabel("Prénom :");
		firstName = new JTextField();
		labelId = new JLabel("Identifiant :");
		id = new JTextField();
		labelProject = new JLabel("Projects :");
		fieldProject = new ListPanel <Project> (controller);
		detailsProject = new JButton("Détails du projet");
		
		//actionsListener
		newIntervenant.addActionListener(controller);
		newIntervenant.setActionCommand(EVT_ADD_STAKEHOLDER);
		
		detailsProject.addActionListener(controller);
		detailsProject.setActionCommand(EVT_DETAILS_PROJECT);
		
		modifyStakeholder.addActionListener(controller);
		modifyStakeholder.setActionCommand(EVT_MODIFY_STAKEHOLDER);
		
		deleteStakeholder.addActionListener(controller);
		deleteStakeholder.setActionCommand(EVT_REMOVE_STAKEHOLDER);
		
		//Les ajoute à l'onglet et les organise
		//=Panel de gauche
		westSide.add(sortByLabel, "span, grow");
		westSide.add(sortByIntervenant, "span, grow");
		westSide.add(listStakeholders, "span, grow");
		westSide.add(sortAffected, "span, grow");
		westSide.add(newIntervenant, "span, grow");
		
		//=Panel de droite
		eastSide.add(modifyStakeholder, "cell 1 0");
		eastSide.add(deleteStakeholder, "cell 2 0");
		eastSide.add(labelName, "grow");
		eastSide.add(name, "span 2, grow, wrap");
		eastSide.add(labelFirstName);
		eastSide.add(firstName, "span 2, grow, wrap");
		eastSide.add(labelId);
		eastSide.add(id, "span 2, grow");
		eastSide.add(labelProject, "grow");
		eastSide.add(fieldProject, "span 2, grow, wrap");
		eastSide.add(detailsProject, "cell 1 5, span 22 1, grow");

		globalPanel.add(westSide, "grow");
		globalPanel.add(eastSide, "grow, width 100%");
		eastSide.setBorder(BorderFactory.createTitledBorder("Détail de l'intervenant"));
		this.add(globalPanel, "grow");
		
	}

	ListPanel<Stakeholder> getListPanel() {
		return listStakeholders;
	}
	
	public void setSelected(Stakeholder stakeholder){
		name.setText(stakeholder.getName());
		firstName.setText(stakeholder.getFirstName());
		id.setText(Integer.toString(stakeholder.getId()));
		
		fieldProject.updateList(stakeholder.getProjects());
	}
	
	public Project getSelectedProject(){
		return fieldProject.getSelectedItem();
	}

}
