package view.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.Subject;
import net.miginfocom.swing.MigLayout;
 
@SuppressWarnings("serial")
public class JDialogNewSubject extends JDialogNew {

	private JPanel content;
	private JLabel labelTitle;
	private JTextField txtTitle;
	private JLabel labelDetails;
	private JTextArea txtDetails;
	private JButton butRegister;
	private JButton butCancel;
	
	public JDialogNewSubject(JFrame frame) {
		super(frame, "Saisir un sujet", true);
		initContent();
		setVisible(true);
	}
	
	public JDialogNewSubject(JFrame frame, Subject subject) {
		super(frame, "Saisir un sujet", true);
		initContent();
		
		txtTitle.setText(subject.getTitle());
		txtDetails.setText(subject.getDescription());

		setVisible(true);
	}
	
	public void initContent() {
		content = new JPanel(new MigLayout("wrap 4", "[100][100][100][100]"));
		content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "D�tails du Sujet"));
		
		labelTitle = new JLabel("Titre : ");
		txtTitle =  new JTextField();
		labelDetails = new JLabel("D�tails : ");
		txtDetails = new JTextArea(5,5);
		txtDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		butRegister = new JButton("Enregistrer");
		butRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataValid(checkData());
				dispose();
			}
		});
		
		butCancel = new JButton("Annuler");
		butCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDataValid(false);
				dispose();
			}
		});
		
		content.add(labelTitle, "grow");
		content.add(txtTitle, "grow, wrap");
		content.add(labelDetails, "wrap, grow");
		content.add(txtDetails, "grow, span 4, wrap");
		content.add(butRegister,"grow, cell 1 3");
		content.add(butCancel, "grow, cell 2 3");
		
		add(content);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public Subject toObject() {
		return isDataValid() ? new Subject(txtTitle.getText(), txtDetails.getText()) : null;
	}

	@Override
	public boolean checkData() {
		return true;
	}

}
