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
		CarModel accord = new CarModel (1L, "Accord", "35L x 40L", "One of the best newer models");
		CarModel civic = new CarModel (2L, "Civic", "30L x 45L", "Another One of the best selling Honda models");

		accord = carModelRepo.save(accord);
		civic = carModelRepo.save(civic);

		StarRatings accordFiveStar = new StarRatings ("Five Star Rating", "750 votes", accord);
		StarRatings accordFourStar = new StarRatings ("Four Star Rating", "600 Votes", accord);
		StarRatings civicFiveStar = new StarRatings ("Five Star Rating", "800 votes", civic);
		StarRatings civicFourStar = new StarRatings ("Four Star Rating", "650 votes", civic);

		accordFiveStar = reviewRepo.save(accordFiveStar);
		accordFourStar = reviewRepo.save(accordFourStar);
		civicFiveStar = reviewRepo.save(civicFiveStar);

		civicFourStar = reviewRepo.save(civicFourStar);
		Car honda = new Car("Honda", accord, civic);
		honda = carRepo.save(honda);			
			
	}
	
	

}
