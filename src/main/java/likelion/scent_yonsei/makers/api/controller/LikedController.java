package likelion.scent_yonsei.makers.api.controller;

import likelion.scent_yonsei.makers.api.dto.CommonApiResponse;
import likelion.scent_yonsei.makers.api.dto.LikedReq;
import likelion.scent_yonsei.makers.api.service.LikedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liked")
public class LikedController {

    private final LikedService likedService;

    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }

    @PostMapping
    public ResponseEntity<CommonApiResponse<LikedReq>> addCheer() {
        int count = likedService.addCheer();
        LikedReq data = new LikedReq(count);

        return ResponseEntity.status(201).body(
                new CommonApiResponse<>(201, true, "응원하기 추가 성공", data)
        );
    }
}
