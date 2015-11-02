package data;
 
public abstract class MyObject {

	private int id;
	public final static int DEFAULT_INVALID_ID = -1;
	
	public MyObject() {
		id = DEFAULT_INVALID_ID;
	}
	
	public MyObject(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return getId();
	}
}
