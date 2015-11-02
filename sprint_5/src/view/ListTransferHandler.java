package view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class ListTransferHandler <T> extends TransferHandler implements DragGestureListener, DragSourceListener {
	
    private int[] indices = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
    
    private DataFlavor localObjectFlavor;
    private Object[] transferedObjects = null;
    
    private JList <T> myList;
    
    private int actionInside = TransferHandler.MOVE,
    			actionOutside = TransferHandler.COPY;
    
    private boolean booool = false;
    private boolean isInside = false;
    
    private class MyDataHandler extends DataHandler {
    	JList <T> myList;
    	MyDataHandler(JList <T> list, Object o, String s) {
    		super(o,s);
    		this.myList = list;
    	}
    }
    
    public ListTransferHandler( JList <T> list ) {
    	this( list, TransferHandler.COPY_OR_MOVE );
    }
    
    public ListTransferHandler( JList <T> list, int action ) {
    	localObjectFlavor = new ActivationDataFlavor(
    			Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items" );
    	this.myList = list;
    	
        DragGestureRecognizer dgr = new DragSource()
            .createDefaultDragGestureRecognizer(list,
                DnDConstants.ACTION_COPY, this);
        booool = false;
    }
            
    /**
     * We only support importing strings.
     */
    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        // Check for String flavor
        /*if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }*/
    	
    	//info.getDropLocation()
    	
    	Object source = SwingUtilities.getAccessibleAt(myList, info.getDropLocation().getDropPoint());
    	
    	if( source == null )
    		isInside = false;//System.out.println("Rien !");
    	else
    		isInside = true; //System.out.println(source);
    	
    	booool = isInside;
    	
    	System.out.println("Inside ? "+isInside);
    	
    	if( ! info.isDrop() || ! info.isDataFlavorSupported(localObjectFlavor) ) {
    		System.out.println("Not supported");
    		return false;
    	}
    	
    	//System.out.println(info.getTransferable()+"|"+info.getTransferable().getClass());
    	//JList <T> source = null;//((MyDataHandler)info.getTransferable()).myList;
    	//Object source = 
    	
    	
    	if( source != myList && (COPY & info.getSourceDropActions()) == COPY ) {
    		//System.out.println("On va copier");
    		info.setDropAction(COPY);
    	}
    	else 
    		;//System.out.println("pas copie :"+(info.getComponent() == myList));
    	
    	System.out.println("Inside ? #2 "+isInside);
    	
        return true;
   }

    /**
     * Bundle up the selected items in a single list for export.
     * Each line is separated by a newline.
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
    	
        JList <T> list = (JList <T>)c;
        indices = list.getSelectedIndices();
        for( int i : indices )
        	;//System.out.println(i+" is selected");
        transferedObjects = list.getSelectedValues();
        
        return new MyDataHandler(myList, transferedObjects, localObjectFlavor.getMimeType());
       /* StringBuffer buff = new StringBuffer();

        for (int i = 0; i < values.size(); i++) {
            Object val = values.get(i);
            buff.append(val == null ? "" : val.toString());
            if (i != values.size() - 1) {
                buff.append("\n");
            }
        }
        
        return new StringSelection(buff.toString());*/
    }
    
    /**
     * We support both copy and move actions.
     */
    public int getSourceActions(JComponent c) {
   		//System.out.println( ":"+(c == myList) );
    	System.out.println("On demande les sources");
        return isInside ? actionInside : actionOutside; //action;//TransferHandler.COPY_OR_MOVE;
    }
    
    /**
     * Perform the actual import.  This demo only supports drag and drop.
     */
    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport info) {
    	
    	//JList.DropLocation dl
    	
        if (!info.isDrop()) {
            return false;
        }
        
        JList <T> target = (JList <T>)info.getComponent();
        DefaultListModel <T> listModel = (DefaultListModel <T>)target.getModel();
        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        int index = dl.getIndex();
        int max = listModel.getSize();
        
        if( index < 0 || max < index )
        	index = max;
        
        //boolean insert = dl.isInsert();
        addIndex = index;
        
        try {
        	T[] values = (T[])info.getTransferable().getTransferData(localObjectFlavor);
        	addCount = values.length;
        	
        	for( int i = 0 ; i < values.length ; i++ ) {
        		int idx = index++;
        		listModel.add(idx, values[i]);
        		//target.addSelectionInterval(idx, idx);
        	}
        	return true;
        	
        } catch( UnsupportedFlavorException ufe ) {
        	ufe.printStackTrace();
        } catch( IOException ioe ) {
        	ioe.printStackTrace();
        }
        
        return false;

        // Get the string that is being dropped.
        /*Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        } 
        catch (Exception e) { return false; }
                                
        // Wherever there is a newline in the incoming data,
        // break it into a separate item in the list.
        String[] values = data.split("\n");
        
        addIndex = index;
        addCount = values.length;
        
        // Perform the actual import.  
        for (int i = 0; i < values.length; i++) {
            if (insert) {
                listModel.add(index++, values[i]);
            } else {
                // If the items go beyond the end of the current
                // list, add them in.
                if (index < listModel.getSize()) {
                    listModel.set(index++, values[i]);
                } else {
                    listModel.add(index++, values[i]);
                }
            }
        }
        return true;*/
    }

    /**
     * Remove the items moved from the list.
     */
    protected void exportDone(JComponent c, Transferable data, int action) {
        /*JList source = (JList)c;
        DefaultListModel listModel  = (DefaultListModel)source.getModel();

        if (action == TransferHandler.MOVE) {
            for (int i = indices.length - 1; i >= 0; i--) {
                listModel.remove(indices[i]);
            }
        }
        
        indices = null;
        addCount = 0;
        addIndex = -1;*/
    	System.out.println("Before clean : "+booool);
    	cleanup(c, booool );//action == MOVE);
    }
    
    private void cleanup( JComponent c, boolean remove ) {
    	
    	System.out.println("in clean "+remove);
    	
    	if( remove && indices != null ) {
    		if( c == myList )
    			System.out.println("Preparing to remove");
    		else
    			System.out.println("Pas pareil");
    		JList <T> source = (JList <T>)c;
    		DefaultListModel<T> model = (DefaultListModel<T>)source.getModel();
    		
    		if( addCount > 0 ) {
    			for( int i = 0 ; i < indices.length ; i++ ) {
    				if( indices[i] >= addIndex ) {
    				//	indices[i] += addCount;
    				}
    			}
    		}
    		for( int i = indices.length-1 ; i >= 0 ; i-- ) {
    			//System.out.println(indices.length+" remaining. current is "+i);
    			model.remove(indices[i]);
    		}
    	}
    	indices = null;
    	addCount = 0;
    	addIndex = -1;
    }

	@Override
	public void dragDropEnd(DragSourceDropEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println( dsde.getSource() );
	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		System.out.println( dse.getSource() );
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		System.out.println("Mouvement reconnu");
		//dge.startDrag(null, null, this);
	}
}
