package com.embarkx.embarkxfirstJobApp.review;


import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
    boolean deleteReview(Long companyId, Long reviewId);
}

