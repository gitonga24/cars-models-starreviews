package autoReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Car {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	@OneToMany
	private Collection<Model> models;
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
	public Car(String name, Model...models) {
		this.name = name;
		this.models = new HashSet<>(Arrays.asList(models));
	}
	
	
	public Car() {
		
	}

	public Collection<Model> getModels() {
		return models;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
}
