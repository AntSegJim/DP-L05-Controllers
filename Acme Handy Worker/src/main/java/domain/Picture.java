
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Picture extends DomainEntity {

	private String	picture;	//url


	//Getters
	@URL
	@NotBlank
	@NotNull
	public String getPicture() {
		return this.picture;
	}

	//Setters
	public void setPicture(final String pic) {
		this.picture = pic;
	}

}
