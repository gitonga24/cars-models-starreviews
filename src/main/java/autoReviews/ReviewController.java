package autoReviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	private StarRatingsRepository starRatingsRepo;
	
	@Resource 
	private CarModelRepository carModelRepo;
	
	@Resource
	private CarRepository carRepo;
	
	
	
	@GetMapping("/show-cars")
	public String findAllCars(Model model) {
		model.addAttribute("reviewsModel", carRepo.findAll());
		return "cars-template";	
	}
	
	@GetMapping("/show-one-car-model")	
	public String findOneCarModel(@RequestParam(value ="id")long id, Model model) throws ReviewNotFoundException {
		Optional<CarModel> accord = carModelRepo.findById(id);
		
		if(accord.isPresent()) {
			model.addAttribute("carModel", accord.get());
			return "one-car-model-template";
		}
		throw new ReviewNotFoundException();			
	}
	
	
	@RequestMapping("/show-carModels-model")
	public String findAllCarModels(Model model) {
		model.addAttribute("carModels", carModelRepo.findAll());
		return("car-models-template");		
	}
		
	
	@GetMapping("/show-single-starRating-model")
	public String findOneStarRating(@RequestParam(value ="id")long id, Model model) throws ReviewNotFoundException {
		Optional<StarRatings> review = starRatingsRepo.findById(id);		
		if(review.isPresent()) {
			model.addAttribute("starRatingsModel", review.get());
			return "starRating-template";
		}
		throw new ReviewNotFoundException();
	}


	
	
	@RequestMapping("/show-starRatings-model")
	public String findAllStarRatingsReviews(Model model) {
		model.addAttribute("starRatingsModel", starRatingsRepo.findAll());
		return("starRatings-template");	
	}

	
	
	

}
