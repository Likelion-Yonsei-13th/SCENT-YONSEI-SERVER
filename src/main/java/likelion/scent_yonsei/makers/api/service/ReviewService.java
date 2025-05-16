package likelion.scent_yonsei.makers.api.service;

import likelion.scent_yonsei.makers.core.Review;
import likelion.scent_yonsei.makers.core.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Long save(String content) {
        Review review = new Review();
        review.setReview(content);
        reviewRepository.save(review);

        return review.getId();
    }
}
