package data;

public class Subject extends MyObject { 

	private String 	title,
					description;
	
	public Subject() {
		this("Title", "Description");
	}
	
	public Subject(String title, String desc) {
		this(DEFAULT_INVALID_ID, title, desc);
	}
	
	public Subject( int id, String title, String desc ) {
		super(id);
		this.title = title;
		this.description = desc;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return this.title;
	}
	
	@Override
	public boolean equals( Object o ) {
		if( o instanceof Subject )
			return equals( (Subject)o );
		
		return false;
	}
	
	public boolean equals( Subject s ) {
		return  this.getId() == s.getId()
				&& this.title.equals(s.title)
				&& this.description.equals(s.description);
	}
}
