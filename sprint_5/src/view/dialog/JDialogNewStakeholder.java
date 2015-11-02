package view.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Controller;
import net.miginfocom.swing.MigLayout;
import data.Person;
import data.Stakeholder;

@SuppressWarnings("serial") 
public class JDialogNewStakeholder extends JDialogNew {
	
	private JPanel content;
	private JLabel nomText;
	private JTextField nomTF;
	private JLabel prenomText;
	private JTextField prenomTF;
	private JLabel projetsText;
	private JComboBox projetsList;
	private JLabel roleText;
	private JComboBox roleList;
	private JButton registerIntervenant;
	private JButton cancel;

	public JDialogNewStakeholder(JFrame frame) {
		
		super(frame, "Saisir un Intervenant", true);
		initContent();
		setVisible(true);
	}
	
	public JDialogNewStakeholder(JFrame frame, Person intervenant) {
		
		super(frame, "Saisir un Intervenant", true);
		initContent();
		nomTF.setText(intervenant.getName());
		prenomTF.setText(intervenant.getFirstName());
		setVisible(true);
		
	}
	
	public void initContent() {
		
		content = new JPanel(new MigLayout());
		content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nouveau Intervenant"));
		
		nomText = new JLabel("Nom : ");
		nomTF = new JTextField();
		prenomText = new JLabel("Prenom : ");
		prenomTF = new JTextField();
		projetsText = new JLabel("Projet : ");
		projetsList = new JComboBox<String>();
		roleText = new JLabel("R�le : ");
		roleList = new JComboBox<String>();
		
		registerIntervenant = new JButton("Enregistrer");
		registerIntervenant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataValid(checkData());
				dispose();
			}
		});
		
		cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataValid(false);
				dispose();
			}
		});
		
		content.add(nomText);
		content.add(nomTF, "width 100");
		content.add(prenomText, "gapleft 30");
		content.add(prenomTF, "wrap, width 100");
		content.add(projetsText);
		content.add(projetsList, "width 60");
		content.add(roleText);
		content.add(roleList, "width 60");
		content.add(registerIntervenant, "cell 1 4");
		content.add(cancel, "cell 2 4");
		add(content);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public Stakeholder toObject() {
		return isDataValid() ? new Stakeholder(nomTF.getText(), prenomTF.getText()) : null;
	}

}
