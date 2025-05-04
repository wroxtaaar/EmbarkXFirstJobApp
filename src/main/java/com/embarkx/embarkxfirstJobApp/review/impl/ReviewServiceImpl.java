package com.embarkx.embarkxfirstJobApp.review.impl;

import com.embarkx.embarkxfirstJobApp.company.Company;
import com.embarkx.embarkxfirstJobApp.company.CompanyService;
import com.embarkx.embarkxfirstJobApp.review.Review;
import com.embarkx.embarkxfirstJobApp.review.ReviewRepository;
import com.embarkx.embarkxfirstJobApp.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);

    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            return reviews.stream()
                    .filter(review -> review.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
    }

//#####--(Method 1)--#########
//    @Override
//    public boolean updateReview(Long companyId, Long reviewId, Review review) {
//
//        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//        Review reviewToUpdate = reviews.stream()
//                .filter(r -> r.getId().equals(reviewId))
//                .findFirst()
//                .orElse(null);
//
//        if (reviewToUpdate != null) {
//            reviewToUpdate.setTitle(review.getTitle());
//            reviewToUpdate.setDescription(review.getDescription());
//            reviewToUpdate.setRating(review.getRating());
//            reviewRepository.save(reviewToUpdate);
//            return true;
//        }
//        return false;
//    }



//#####--(Method 2)--#########
//This approach has an issue, it is transferring the review from one company to another
    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            return true;
        }        // If the company with the given ID does not exist, return false
        return false;
    }


}
