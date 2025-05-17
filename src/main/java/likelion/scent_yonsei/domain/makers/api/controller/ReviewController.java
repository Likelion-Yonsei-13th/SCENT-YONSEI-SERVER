package likelion.scent_yonsei.domain.makers.api.controller;

import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.makers.api.dto.ReviewReq;
import likelion.scent_yonsei.domain.makers.api.dto.ReviewRes;
import likelion.scent_yonsei.domain.makers.api.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<Response<ReviewRes>> createReviews(@RequestBody ReviewReq req){
        return ResponseEntity.ok(reviewService.save(req.content()));
    }
}
