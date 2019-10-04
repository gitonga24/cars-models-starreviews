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
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CarRepository carRepo;
	
	@Mock
	private CarModel accord;
	
	@Mock
	private CarModel civic;
	
	@Test
	public void shouldRouteToSingleCarModelView() throws Exception {
		long arbitraryCarModelId = 1;
		when(carModelRepo.findById(arbitraryCarModelId)).thenReturn(Optional.of(accord));
		mvc.perform(get("/carModel?id=1")).andExpect(view().name(is("carModel")));		
	}
	
	@Test
	public void shouldReturnOkForSingleReview() throws Exception {
		long arbitraryCarModelId = 1;
		when(carModelRepo.findById(arbitraryCarModelId)).thenReturn(Optional.of(accord));
		mvc.perform(get("/carModel?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAddSingleCarModelInTheModel() throws Exception {
		when(carModelRepo.findById(1L)).thenReturn(Optional.of(accord));	
		mvc.perform(get("/carModel?id=1")).andExpect(model().attribute("carModel", is(accord)));
	}
	
	@Test
	public void shouldRouteToAllCarModelsTemplate() throws Exception {
		mvc.perform(get("/show-carModels")).andExpect(view().name(is("carModels")));	
		
	}
	 
	@Test
	public void shouldPutAllCarModelsInTheModel() throws Exception {
		Collection <CarModel> allCarModels = Arrays.asList(accord, civic);
		when(carModelRepo.findAll()).thenReturn(allCarModels);
		mvc.perform(get("/show-carModels")).andExpect(model().attribute("carModels", is(allCarModels)));
	}
	
}
