package likelion.scent_yonsei.domain.makers.api.service;

import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.makers.api.dto.ReviewRes;
import likelion.scent_yonsei.domain.makers.core.Review;
import likelion.scent_yonsei.domain.makers.core.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Response<ReviewRes> save(String content) {

        Review review = new Review();
        review.setReview(content);
        reviewRepository.save(review);
        ReviewRes data = new ReviewRes(review.getId());
        return new Response<>(201, true, "리뷰 생성 성공", data);
    }
}
