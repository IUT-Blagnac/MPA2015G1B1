package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import view.dialog.JDialogNewSubject;
import main.Controller;
import constants.Constants;
import data.Student;
import data.Subject;
/** Onglet des Sujets
 * 
 * @author Nicolas Calvet
 *
 */ 
public class JPanelSubject extends JPanel implements Constants {
	
	// **********************************************************
	//					ATTRIBUTS
	// ***************************************
	
	JFrame frame = null;
	
	//Liste des composants du panel
	JLabel sortByLabel;
	JComboBox sortBySubject;
	JButton newSubject;
	JButton modify;
	JButton delete;
	JLabel labelTitle;
	JTextField title;
	JLabel labelDetails;
	JTextArea textAreaDetails;
	JLabel labelVoeux;
	JTable voeux;
	
	String[][] data = {
		      {"Groupe 1", "2"},
		      {"Groupe 2", "3"},
		      {"Groupe 3", "1"},
		      {"Groupe 4", "1"}
		    };
	String[] columnNames = {"Groupe", "Voeu"};
		
	//JPanels
	JPanel globalPanel = new JPanel(new MigLayout("wrap 2"));
	
	JPanel westSide = new JPanel(new MigLayout("wrap 1"));
	JPanel eastSide = new JPanel(new MigLayout("wrap 3",	 // Layout Constraints
		      "[100][100][100]",  				 // Column constraints
		      ""));     					 // Row constraints
	
	/** The listener */
	Controller controller;
	
	/** JPanel containing the JList of {@link Subject} */
	ListPanel <Subject> listSubjects = null;
	
	// **********************************************************
	//					CONSTRUCTOR
	// ***************************************
	
	public JPanelSubject( final Controller controller ) {	
		super(new MigLayout("fill"));
		
		sortByLabel = new JLabel("Trier par");
		sortBySubject = new JComboBox();
		listSubjects = new ListPanel <Subject> (controller, EVT_SELECT_SUBJECT);
		newSubject = new JButton("Nouveau");
		newSubject.addActionListener(controller);
		newSubject.setActionCommand(EVT_ADD_SUBJECT);
		
		modify = new JButton("Modifier");
		modify.addActionListener(controller);
		modify.setActionCommand(EVT_MODIFY_SUBJECT);
		
		delete = new JButton("Supprimer");
		delete.addActionListener(controller);
		delete.setActionCommand(EVT_REMOVE_SUBJECT);

		labelTitle = new JLabel("Titre");
		title = new JTextField();
		title.setEditable(false);
		labelDetails = new JLabel("D�tails");
		textAreaDetails = new JTextArea(5, 5);
		textAreaDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		textAreaDetails.setEditable(false);
		textAreaDetails.setLineWrap(true);
		textAreaDetails.setWrapStyleWord(true);
		labelVoeux = new JLabel("Voeux");
		voeux = new JTable(data, columnNames); //TODO: data et columnNames � impl�menter
		voeux.setAutoCreateRowSorter(true);
		voeux.setEnabled(false);

		//Les ajoute � l'onglet et les organise
		//=Panel de gauche
		westSide.add(sortByLabel, "span, growx");
		westSide.add(sortBySubject, "span, growx");
		westSide.add(listSubjects, "width 100%, height 100%, growy");
		westSide.add(newSubject, "align center, span, shrink");
		
		//=Panel de droite
		eastSide.add(labelTitle, "grow");
		eastSide.add(modify, "grow");
		eastSide.add(delete, "grow, wrap");
		eastSide.add(title, "grow, span 3, wrap");
		eastSide.add(labelDetails, "grow, wrap");
		eastSide.add(textAreaDetails, "grow, span 5, wrap");
		eastSide.add(labelVoeux, "grow, wrap");
		eastSide.add(voeux.getTableHeader(), "grow, span 4, wrap");
		eastSide.add(voeux, "grow, span 4 4, wrap");
		
		eastSide.setBorder(BorderFactory.createTitledBorder("D�tails du Sujet"));
		
		//=Panel G�n�ral
		globalPanel.add(westSide, "west");
		globalPanel.add(eastSide, "grow, width 100%");

		this.add(globalPanel, "grow");
			
	}
	
	public ListPanel <Subject> getListPanel() {
		return this.listSubjects;
	}
	
	public void setSelected(Subject subject){
		title.setText(subject.getTitle());
		textAreaDetails.setText(subject.getDescription());
	}

}
