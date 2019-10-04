package autoReviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	private String starRating;
	private String reviewCount;

	@Id
	@GeneratedValue
	private long id;
	
	

//	This passes the tests!
	@ManyToOne
	private CarModel carModel;
	
	
	public long getId() {
		return id;
	}
	
	
	public String getStarRating() {
		return starRating;
	}
	
	public String getReviewCount () {
		return reviewCount;
	}
	
	
	
	public Review(String starRating, String reviewCount) {
		this.starRating = starRating;
		this.reviewCount = reviewCount;
	}

	public Review() {
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
