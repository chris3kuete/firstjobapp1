package com.embarkx.firstjobapp1.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp1.company.Company;
import com.embarkx.firstjobapp1.company.CompanyService;
import com.embarkx.firstjobapp1.review.Review;
import com.embarkx.firstjobapp1.review.ReviewRepository;
import com.embarkx.firstjobapp1.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		super();
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
			
	}

	@Override
	public boolean addReview(Review review, Long companyId) {
		Company company = companyService.getSpecificCompany(companyId);
		if(company != null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		}
		return false;
	
	}

	@Override
	public Review getReviewById(Long companyId, Long reviewId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
		
	}


	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		Company company = companyService.getSpecificCompany(companyId);
		if(company != null) {
			updatedReview.setCompany(companyService.getSpecificCompany(companyId));
			updatedReview.setId(reviewId);
			reviewRepository.save(updatedReview);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId, Review review) {
		Company company = companyService.getSpecificCompany(companyId);
		if(company != null) {
			reviewRepository.delete(review);
			return true;
		}
		return false;
	}
	
	

}
