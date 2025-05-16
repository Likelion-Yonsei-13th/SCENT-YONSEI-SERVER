package likelion.scent_yonsei.makers.api.controller;

import likelion.scent_yonsei.makers.api.dto.ReviewReq;
import likelion.scent_yonsei.makers.api.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<?> createReviews(@RequestBody ReviewReq req){

        Long reviewId = reviewService.save(req.content());
        return ResponseEntity.ok(reviewId);
    }
}
