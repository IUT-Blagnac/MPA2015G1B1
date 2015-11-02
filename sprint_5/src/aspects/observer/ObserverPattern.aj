/*package aspects.observer;
 
import java.util.ArrayList;

import aspects.GlobalPointcuts;

public abstract aspect ObserverPattern extends GlobalPointcuts {
	
	// **********************************************************
	//					OBSERVABLE
	// ***************************************
	
	// Ajout d'une liste d'Observer � l'Observable
	private ArrayList<Observer> Observable.observers = new ArrayList<Observer>();
	
	public void Observable.addObserver( Observer obs ) {
		observers.add(obs);
		obs.setObservable(this);
	}
	
	public void Observable.removeObserver( Observer obs ) {
		observers.remove(obs);
		obs.setObservable(null);
	}
	
	public ArrayList<Observer> Observable.getObservers() {
		return observers;
	}
	
	// **********************************************************
	//					OBSERVER
	// ***************************************
	
	// Ajout d'un Observable � l'Observer
	private Observable Observer.observable = null;
	
	public void Observer.setObservable( Observable obs ) {
		observable = obs;
	}
	
	public Observable Observer.getObservable() {
		return observable;
	}
	
	// **********************************************************
	//					CHECK CHANGEMENTS 
	// ***************************************
	
	// Point de coupure � surveiller
	//abstract pointcut stateChanges( Observable obs );
	
	// Si le point de coupure est d�pass�, on met les Observers � jour
	/*after( Observable observable ) : stateChanges(observable) {
		for( Observer observer : observable.getObservers() )
			observer.update();
	}
}*/
