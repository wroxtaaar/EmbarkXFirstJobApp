package com.embarkx.embarkxfirstJobApp.review;

import com.embarkx.embarkxfirstJobApp.company.Company;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

//    @GetMapping("/reviews/{reviewId}")
//    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
//        Review review = reviewService.getReviewById(reviewId);
//        if(review == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(review, HttpStatus.OK);
//    }


    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean IsReviewSaved = reviewService.addReview(companyId, review);
        if (IsReviewSaved) {
            return new ResponseEntity<>("Review created successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(!isUpdated) {
            return new ResponseEntity<>("Review not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted) {
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found!", HttpStatus.NOT_FOUND);
    }




}
