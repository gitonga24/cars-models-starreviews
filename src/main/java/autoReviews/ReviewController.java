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
	ReviewRepository reviewRepo;
	
	@Resource 
	CarModelRepository carModelRepo;
	
	@Resource
	CarRepository carRepo;
	
	
	
	
	
	@GetMapping("/show-cars")
	public String findAllCars(Model model) {
		model.addAttribute("reviewsModel", carRepo.findAll());
		return "cars-template";	
	}
	
	@RequestMapping("/carModel")
	public String findOneCarModel(@RequestParam(value ="id")long id, Model model) throws ReviewNotFoundException {
		Optional<CarModel> accord = carModelRepo.findById(id);
		
		if(accord.isPresent()) {
			model.addAttribute("carModel", accord.get());
			return "carModel";
		}
		throw new ReviewNotFoundException();	
		
	}
	
	@RequestMapping("/show-carModels")
	public String findAllCarModels(Model model) {
		model.addAttribute("carModels", carModelRepo.findAll());
		return("carModels");		
	}
	
	
	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value ="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
		
		
	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return("reviews");
		
	}

	
	
	

}
