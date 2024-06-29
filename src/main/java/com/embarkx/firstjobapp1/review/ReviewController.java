package com.embarkx.firstjobapp1.review;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
	
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	//public List<Review> getReviews(@PathVariable Long companyId) {
	public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId){
		
		//return reviews;
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addRev(@RequestBody Review review, @PathVariable Long companyId){
		
		boolean isReviewSaved = reviewService.addReview(review, companyId);
		if(isReviewSaved) {
			return new ResponseEntity<>("Review Successfully Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not Added", HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		Review review = reviewService.getReviewById(companyId, reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> makeUpdate(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody Review review){
		boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
		if(isUpdated) {
			return new ResponseEntity<>("Review Updated", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteArev(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
		boolean deleted = reviewService.deleteReview(companyId, reviewId, review);
		if(deleted) {
			return new ResponseEntity<>("Review deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	

}
