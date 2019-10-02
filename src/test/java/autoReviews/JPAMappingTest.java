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
	private ModelRepository modelRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
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
		Model model = modelRepo.save(new Model("Accord", "size", "description"));
		long modelId = model.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Model> result = modelRepo.findById(modelId);
		result.get();		
		assertThat(model.getModelName(), is("Accord"));		
	} 
	
	@Test
	public void shouldEstablishRelationshipBetweenCarAndModel() {
		Model accord = modelRepo.save(new Model("Accord", "Accord size", "Accord description"));
		Model civic = modelRepo.save(new Model("Civic", "civic size", "civic description"));
		Model pilot = modelRepo.save(new Model("pilot", "pilot size", "civic description"));
		
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
	//	Review review = reviewRepo.save(new Review ("Star Rating", "Review count"));
		Review accordOneStar = reviewRepo.save(new Review ("One Star", "350"));
		Review accordTwoStar = reviewRepo.save(new Review ("Two Star", "400"));
		Review accordThreeStar = reviewRepo.save(new Review("Three Star", "750"));
		
		Model accord = modelRepo.save(new Model ("Accord", "Size", "Description", accordOneStar, accordTwoStar, accordThreeStar));
		long modelId = accord.getId();
		
		entityManager.flush();
		entityManager.clear();
				
		
		assertThat(modelId, is(greaterThan(0L)));
		
		assertThat(accord.getReviews(), containsInAnyOrder(accordOneStar, accordTwoStar, accordThreeStar));			
	}
	
	
	
}
