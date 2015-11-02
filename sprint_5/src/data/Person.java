package data;
 
public class Person extends MyObject {
	
	private String 	name,
					firstname;
	
	public Person() {
		this("Name", "FirstName");
	}
	
	public Person( String name, String firstname ) {
		this(DEFAULT_INVALID_ID, name, firstname);
	}
	
	public Person( int id, String name, String firstname ) {
		super(id);
		this.name = name;
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstname;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return getFirstName()+" "+getName();
	}
	
	public void setEqualsTo(Person rhs) {
		this.name = rhs.name;
		this.firstname = rhs.firstname;
	}
}
