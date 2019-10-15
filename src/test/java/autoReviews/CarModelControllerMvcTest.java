package autoReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class CarModelControllerMvcTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CarModelRepository carModelRepo;
	
	@MockBean
	private StarRatingsRepository starRatingsRepo;
	
	@MockBean
	private CarRepository carRepo;
	
	@Mock
	private CarModel accord;
	
	@Mock
	private CarModel civic;
	
	@Mock 
	private StarRatings fiveStar;
	
	@Mock 
	private StarRatings fourStar;
	
	@Mock 
	private StarRatings threeStar;
	
	@Test
	public void shouldRouteToSingleCarModelView() throws Exception {
		long arbitraryCarModelId = 1;
		when(carModelRepo.findById(arbitraryCarModelId)).thenReturn(Optional.of(accord));
		mvc.perform(get("/show-one-car-model?id=1")).andExpect(view().name(is("one-car-model-template")));		
	}
	
	@Test
	public void shouldReturnOkForSingleModelReview() throws Exception {
		long arbitraryCarModelId = 1;
		when(carModelRepo.findById(arbitraryCarModelId)).thenReturn(Optional.of(accord));
		mvc.perform(get("/show-one-car-model?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAddSingleCarModelInTheModel() throws Exception {
		when(carModelRepo.findById(1L)).thenReturn(Optional.of(accord));	
		mvc.perform(get("/show-one-car-model?id=1")).
		andExpect(model().attribute("carModel", is(accord)));
	}
	

	 
	@Test
	public void shouldAddAllCarModelsInTheModel() throws Exception {
		Collection <CarModel> allCarModels = Arrays.asList(accord, civic);
		when(carModelRepo.findAll()).thenReturn(allCarModels);
		mvc.perform(get("/show-carModels-model"))
		.andExpect(model().attribute("carModels", is(allCarModels)));
	}
	
	@Test
	public void shouldAddAllStarReviewsInTheModel() throws Exception {
		Collection <StarRatings> allStarRatings = Arrays.asList(fiveStar, fourStar, threeStar);
		when(starRatingsRepo.findAll()).thenReturn(allStarRatings);
		mvc.perform(get("/show-starRatings-model"))
		.andExpect(model().attribute("starRatingsModel", is(allStarRatings)));
		}
	
	
	
	
}
