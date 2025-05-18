package likelion.scent_yonsei.domain.makers.api.controller;

import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.makers.api.service.LikedService;
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
    public ResponseEntity<Response<?>> addCheer() {
        return ResponseEntity.status(201).body(likedService.addCheer());
    }

    @GetMapping
    public ResponseEntity<Response<?>> retCheer() {
        return ResponseEntity.ok(likedService.count());
    }
}
