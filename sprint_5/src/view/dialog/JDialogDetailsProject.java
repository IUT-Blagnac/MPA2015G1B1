package view.dialog;
 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import view.JPanelProject;
import constants.Constants;
import data.MyObject;
import data.Project;
import data.Student;
import data.Subject;
/**
 * G�re la fen�tre qui affiche les d�tails du projet s�lectionn� depuis l'onglet {@link JPanelIntervenant}
 * 
 * @author Flotos
 *
 */
public class JDialogDetailsProject extends JDialog implements Constants {
	
	JPanel content;
	
	Controller controler;

	/**
	 * Default constructor.
	 * 
	 * @param sourceFrame - frame parent
	 * @param controller - controller g�rant le programme
	 * @param project - projet duquel on affiche les d�tails
	 */
	public JDialogDetailsProject(JFrame sourceFrame,Controller controller, Project project) {
		super(sourceFrame);
		this.setLayout(new MigLayout());
		System.out.println("In DialogDetail : " + project);
		content = new JPanel(new MigLayout());
		content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "D�tails du projet"));
		content.add(new JPanelProject(controller, sourceFrame, project));
		this.add(content);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
