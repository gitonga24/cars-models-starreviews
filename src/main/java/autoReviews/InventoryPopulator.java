package autoReviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InventoryPopulator implements CommandLineRunner {
	
	@Resource
	private CarRepository carRepo;
	
	@Resource
	private CarModelRepository carModelRepo;
	
	@Resource
	private StarRatingsRepository reviewRepo;
	
	
	
	
	public void run(String... args) throws Exception{			
		StarRatings accordFiveStar = new StarRatings (1L, "Five Star Rating", "750 votes");
		StarRatings accordFourStar = new StarRatings (2L, "Four Star Rating", "600 Votes");
		StarRatings civicFiveStar = new StarRatings (3L, "Five Star Rating", "800 votes");
		StarRatings civicFourStar = new StarRatings (4L, "Four Star Rating", "650 votes");

		accordFiveStar = reviewRepo.save(accordFiveStar);
		accordFourStar = reviewRepo.save(accordFourStar);
		civicFiveStar = reviewRepo.save(civicFiveStar);
		civicFourStar = reviewRepo.save(civicFourStar);
		
		CarModel accord = new CarModel (1L, "Accord", "35L x 40L", "One of the best newer models", accordFiveStar, accordFourStar);
		CarModel civic = new CarModel (2L, "Civic", "30L x 45L", "Another One of the best selling Honda models", civicFourStar, civicFiveStar);
			
		accord = carModelRepo.save(accord);
		civic = carModelRepo.save(civic);
		
		Car honda = new Car("Honda", accord, civic);
		honda = carRepo.save(honda);			
			
	}
	
	

}
