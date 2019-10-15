package autoReviews;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private StarRatings fiveStar;
	
	@Mock
	private StarRatings fourStar;
	
	@Mock
	private StarRatingsRepository reviewRepo;
	
	@Mock
	private CarModelRepository carModelRepo;
	
	@Mock
	private CarModel accord;
	
	@Mock
	private CarModel civic;
	
	
	
	@Mock
	private Model model;
	
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
/*	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(fiveStar));
	
		underTest.findOneStarRating(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", fiveStar);
	}
	*/

	@Test
	public void shouldAddAllReviewsToModel() throws ReviewNotFoundException {
		Collection<StarRatings> allReviews = Arrays.asList(fiveStar, fourStar);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllStarRatingsReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	//*****************************
	@Test
	public void shouldAddSingleCarModelToModel() throws ReviewNotFoundException{
		long arbitraryCarModelId = 1;
		
		when(carModelRepo.findById(arbitraryCarModelId)).thenReturn(Optional.of(accord));
		
		underTest.findOneCarModel(arbitraryCarModelId, model);
		verify(model).addAttribute("carModel", accord);	
	}
	
	@Test
	public void shouldAddAllCarModelsToModel() throws CarModelNotFoundException {
		Collection<CarModel> allCarModels = Arrays.asList(accord, civic);
		when(carModelRepo.findAll()).thenReturn(allCarModels);
		
		underTest.findAllCarModels(model);
		verify(model).addAttribute("carModels", allCarModels);
		
	}
}
