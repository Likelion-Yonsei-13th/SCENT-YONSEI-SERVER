package likelion.scent_yonsei.makers.api.controller;

import likelion.scent_yonsei.makers.api.service.LikedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/liked")
public class LikedController {

    private final LikedService likedService;

    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }

    @PostMapping
    public int addCheer() {
        return likedService.addCheer();
    }

    @GetMapping
    public int getCheerCount() {
        return likedService.getCheerCount();
    }
}
