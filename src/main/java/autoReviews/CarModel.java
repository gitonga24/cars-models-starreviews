package autoReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CarModel {

	@Id
	@GeneratedValue
	private Long id;
	

	private String carModelName;	
	private String size;
	
	@Lob
	private String description;

	//OneToMany??
	@ManyToMany(mappedBy = "carModels")
	private Collection<Car> cars;

	@OneToMany(mappedBy = "carModel")
	private Collection<StarRatings> reviews;
	
	
	public Long getId() {
		return id;
	}

	
	public String getModelName() {
		return carModelName;
	}
	
	

	public String getSize() {
		return size;
	}

	
	public Collection<Car> getCars() {
		return cars;
	}


	public String getDescription() {
		return description;
	}
	
	public Collection<StarRatings> getReviews() {
		return reviews;
	}
	
	public CarModel(Long id, String modelName, String size, String description, StarRatings...reviews) {
		this.id = id;
		this.carModelName = modelName;
		this.size = size;
		this.description = description;		
		this.reviews = new HashSet<>(Arrays.asList(reviews));
	}
	
	public CarModel() {
		
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
		CarModel other = (CarModel) obj;
		if (id != other.id)
			return false;
		return true;
	}


	

}
