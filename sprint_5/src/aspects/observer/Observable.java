package aspects.observer;

import java.util.Collection;

public interface Observable {
	
	public void addObserver( Observer obs );
	public void removeObserver( Observer obs );
	Collection<Observer> getObservers();
}
