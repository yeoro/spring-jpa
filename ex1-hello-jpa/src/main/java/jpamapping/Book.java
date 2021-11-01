package jpamapping;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
	
	private String isbn;
	private String author;
}
