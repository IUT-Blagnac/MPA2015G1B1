package view.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import constants.Constants;
 
public abstract class JDialogNew extends JDialog implements Constants {
	
	private boolean isDataValid = false;
	
	public JDialogNew(JFrame sourceFrame, String title, boolean isModal ) {
		super(sourceFrame, title, isModal);
	}
	
	public boolean isDataValid() {
		return isDataValid;
	}
	
	public void setDataValid(boolean b) {
		isDataValid = b;
	}

	public abstract boolean checkData();
	
	public abstract Object toObject();
}

