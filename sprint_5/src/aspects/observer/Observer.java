package aspects.observer;

public interface Observer { 
	
	void setObservable( Observable obs );
	Observable getObservable();
	void update();
}
