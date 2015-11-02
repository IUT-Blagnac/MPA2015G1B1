package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import constants.Constants;
import main.Controller;

/**
 * Panel containing a JList
 * 
 * @author Emmanuel Chebbi
 */
@SuppressWarnings("serial")
public class ListPanel <T> extends JPanel implements Constants, ListSelectionListener {
	
	/**
	 * Inner class which make able real Component displaying
	 * (in front of "toString()" classical display).
	 * 
	 * @author Emmanuel Chebbi
	 *
	private class PanelRenderer implements ListCellRenderer<Subject> {
		
		public Component getListCellRendererComponent(JList<? extends Subject> list, Subject subject, int index, boolean isSelected, boolean cellHasFocus) {
	         
			return new JLabel( subject.getName() );
	    }
	}*/
	
	// *****************************************************************************
	//					ATTRIBUTES
	// **********************************************************

	/** Selected item's index */
	private int selectedIndex = -1;
	
	/** The ActionCommand of the Event to throw when selected value changes */
	private String eventAtSelection = "";
	
	/** Set whether the JList change itself its width */
	private boolean isAutoResizable = true;
	
	/** The listener */
	private Controller controller;
	
	private DefaultListModel <T> listModel;
	private JList <T> listView;
	
	/** The JScrollPane in which display the JList */
	private JScrollPane scrollPane;
	
	// *****************************************************************************
	//					CONTROLLERS
	// **********************************************************
	
	public ListPanel( Controller listener ) {
		this(listener, "");
	}
	
	public ListPanel( Controller listener, String eventAtSelection ) {
		
		controller = listener;
		listModel = new DefaultListModel <T>();
		listView = new JList <T>( listModel );
		this.eventAtSelection = eventAtSelection;
		//listData.setCellRenderer( new PanelRenderer() );
		
		listView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listView.getSelectionModel().addListSelectionListener(this);
		
//		listView.setDropMode(DropMode.INSERT);
//		listView.setTransferHandler(new ListTransferHandler<T> (listView));
		//setSourceActions(TransferHandler.MOVE);
		
//		listView.setDragEnabled(true);
		
		listView.addMouseListener(controller);
		
		setLayout( new BoxLayout(this,BoxLayout.PAGE_AXIS) );
		scrollPane = new JScrollPane(listView);
		add(scrollPane);
	}
	
	public void setSourceActions(int action) {
		listView.setTransferHandler( new ListTransferHandler<T> (listView, action) );
	}
	
	// *****************************************************************************
	//					GETTERS
	// **********************************************************
	
	/**
	 * Calculate selected index.<br/>
	 * 
	 * Conceived only to be called when selected value changes
	 * (call to method {@link ListPanel#valueChanged(ListSelectionEvent)})
	 * 
	 * @param lsm
	 * 			
	 * @return selected item index
	 */
	private int getSelectedIndex( ListSelectionModel lsm ) {
		
		try {
			for( int i = 0 ; i >= 0 && i <= lsm.getMaxSelectionIndex() ; i++ )
				if( lsm.isSelectedIndex(i) )
					return i;
			
		} catch( Exception e ) {
			return -1;
		}
		return -1;
	}
	
	/**
	 * Returns selected index
	 * @return selected index
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	/**
	 * Returns selected item
	 * @return selected item
	 */
	public T getSelectedItem() {
		return selectedIndex < 0 ? null : listModel.getElementAt(selectedIndex);
	}
	
	/**
	 * Returns the ActionCommand of the Event to throw when selected value changes 
	 * @return the ActionCommand of the Event to throw when selected value changes
	 */
	public String getEventAtSelection() {
		return eventAtSelection;
	}
	
	// *****************************************************************************
	//					SETTERS
	// **********************************************************
	
	/**
	 * Changes the item at given index.
	 * 
	 * If none item already exists at given index,
	 * this method will throw a {@link NullPointerException}
	 * 
	 * @param index the index of the idem to replace
	 * @param item the item to set at the index
	 */
	public void setItemAt(int index, T item) {
		listModel.set(index, item);
	}
	
	public void setEventAtSelection( String eventAtSelection ) {
		this.eventAtSelection = eventAtSelection;
	}
	
	// *****************************************************************************
	//					MUTATORS
	// **********************************************************
	
	/**
	 * Adds an item to the JList
	 * @param s the item to add
	 */
	public void addItem( T s ) {
		listModel.addElement(s);
	}
	
	/**
	 * Removes an item from the JList
	 * @param o the item to remove
	 */
	public void removeItem( T o ) {
		int previousIndex = listView.getSelectedIndex();
		
		listModel.removeElement(o);
		
		if( previousIndex < listModel.size() ) {
			listView.setSelectedIndex(previousIndex);
			selectedIndex = previousIndex;
		}
		else if( previousIndex-1 < listModel.size() ) {
			listView.setSelectedIndex(previousIndex-1);
			selectedIndex = previousIndex-1;
		}
		else {
			selectedIndex = -1;
		}
		
		/*if( nbrElements == 0 )
			selectedIndex = -1;
		else if( nbrElements > selectedIndex )*/
	}

	/**
	 * Remove all items contained in the JList, then 
	 * add every ones of the Collection.
	 * 
	 * @param list the item with which replace the older ones
	 */
	public void updateList( Collection<T> list ) {
		listModel.removeAllElements();
		
		Iterator<T> it = list.iterator();
		
		while( it.hasNext() ) {
			listModel.addElement( it.next() );
		}
		
		if( isAutoResizable ) {
			scrollPane.setPreferredSize( listView.getPreferredScrollableViewportSize() );
			setMinimumSize( new Dimension( (int)scrollPane.getPreferredSize().getWidth()+scrollPane.getVerticalScrollBarPolicy(), 
										   (int)scrollPane.getPreferredSize().getHeight() ));
			//System.out.println( "Hauteur="+listView.getFixedCellHeight() * listModel.getSize() );
		}
	}
	
	// *****************************************************************************
	//					LISTENERS
	// **********************************************************

	/**
	 * @brief Called when the selected item changes
	 * 
	 * Call controller's actionPerformed method.
	 * The value of the ActionEvent's ActionCommand is equal to eventAtSelection attribute.
	 */
	public void valueChanged(ListSelectionEvent e) {
		
		if( e.getValueIsAdjusting() )
			return;
		
		selectedIndex = getSelectedIndex( (ListSelectionModel)e.getSource() );	// On récupère l'indice de l'item sélectionné
		
		if( selectedIndex >= 0 )
			controller.actionPerformed( new ActionEvent(listModel.getElementAt(selectedIndex), 0, eventAtSelection) );
	}
}
