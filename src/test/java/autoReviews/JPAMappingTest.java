package autoReviews;

import java.util.Optional;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import javax.annotation.Resource;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTest {

	@Resource
	private TestEntityManager entityManager;
	
	
	@Resource
	private CarRepository carRepo;
	
	@Resource
	private CarModelRepository carModelRepo;
	
	@Resource
	private StarRatingsRepository reviewRepo;
	
	@Test
	public void shouldSaveAndLoadCar() {
		Car car = carRepo.save(new Car("car"));
		long carId = car.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Car> result = carRepo.findById(carId);
		result.get();
		assertThat(car.getName(), is("car"));
	}
	
	@Test
	public void shouldGenerateCarId() {
		Car car = carRepo.save(new Car("car"));
		long carId = car.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(carId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadModel() {
		CarModel model = carModelRepo.save(new CarModel(1L, "Accord", "size", "description"));
		long modelId = model.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<CarModel> result = carModelRepo.findById(modelId);
		result.get();		
		assertThat(model.getModelName(), is("Accord"));		
	} 
	
	@Test
	public void shouldEstablishRelationshipBetweenCarAndModel() {
		CarModel accord = carModelRepo.save(new CarModel(1L, "Accord", "Accord size", "Accord description"));
		CarModel civic = carModelRepo.save(new CarModel(2L, "Civic", "civic size", "civic description"));
		CarModel pilot = carModelRepo.save(new CarModel(3L, "pilot", "pilot size", "civic description"));
		
		Car nissan = carRepo.save(new Car("Nissan", accord, civic, pilot));
		long carId = nissan.getId();
		
		entityManager.flush();
		entityManager.clear();		
		
		Optional<Car> result = carRepo.findById(carId);
		nissan = result.get();	
		assertThat(nissan.getModels(), containsInAnyOrder(accord, civic, pilot));	
	}
	
	@Test
	public void establishReviewRelationshipWithModel() {
	//	StarRatings review = reviewRepo.save(new StarRatings ("Star Rating", "StarRatings count"));
		StarRatings accordOneStar = reviewRepo.save(new StarRatings (1L, "One Star", "350"));
		StarRatings accordTwoStar = reviewRepo.save(new StarRatings (2L, "Two Star", "400"));
		StarRatings accordThreeStar = reviewRepo.save(new StarRatings(3L, "Three Star", "750"));
		
		CarModel accord = carModelRepo.save(new CarModel (1L, "Accord", "Size", "Description", accordOneStar, accordTwoStar, accordThreeStar));
		long modelId = accord.getId();
		
		entityManager.flush();
		entityManager.clear();
				
		
		assertThat(modelId, is(greaterThan(0L)));
		
//		assertThat(accord.getReviews(), containsInAnyOrder(accordOneStar, accordTwoStar, accordThreeStar));			
	}
	
	
	
}
