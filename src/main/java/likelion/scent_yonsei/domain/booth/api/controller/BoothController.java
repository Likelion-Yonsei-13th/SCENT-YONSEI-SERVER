package likelion.scent_yonsei.domain.booth.api.controller;

import likelion.scent_yonsei.domain.booth.api.dto.BoothListResponseDto;
import likelion.scent_yonsei.domain.booth.api.dto.DetailResponseDto;
import likelion.scent_yonsei.domain.booth.api.service.BoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booth")
public class BoothController {

    private final BoothService boothService;

    @GetMapping
    public ResponseEntity<BoothListResponseDto> listBooths(
            @RequestParam(defaultValue = "")    String search,
            @RequestParam(defaultValue = "백양로")    String section,
            @RequestParam(defaultValue = "부스")    String category,
            @RequestParam(defaultValue = "3")   int day,
            @RequestParam(required = false, defaultValue = "전체") String foodType
    ) {
        var resp = boothService.getBooths(search, section, category, day, foodType);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailResponseDto<?>> getDetail(
            @PathVariable Long id,
            @RequestParam(value = "category", defaultValue = "부스") String category
    ) {
        var resp = boothService.getDetail(id, category);
        return ResponseEntity.ok(resp);
    }
}
