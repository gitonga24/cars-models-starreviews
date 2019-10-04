package autoReviews;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class InventoryPopulator {
	
	@Resource
	private CarRepository carRepo;
	
	@Resource
	private CarModelRepository carModelRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	public void run(String... args) throws Exception{
		
		Review accordFiveStar = new Review ("Five Star Rating", "750 votes");
		Review accordFourStar = new Review ("Four Star Rating", "600 Votes");

		Review civicFiveStar = new Review ("Five Star Rating", "800 votes");
		Review civicFourStar = new Review ("Four Star Rating", "650 votes");


		//public CarModel(String modelName, String size, String description, Review...reviews)	
			CarModel accord = new CarModel ("Accord", "35L x 40L", "One of the best newer models", accordFiveStar, accordFourStar);
			CarModel civic = new CarModel ("Civic", "30L x 45L", "Another One of the best selling Honda models", civicFourStar, civicFiveStar);
			
			Car honda = new Car("Honda", accord, civic);
	}
	
	

}
