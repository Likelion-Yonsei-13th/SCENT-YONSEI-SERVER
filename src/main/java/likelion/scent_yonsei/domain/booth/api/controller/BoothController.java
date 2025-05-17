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
            @RequestParam(defaultValue = "")    String section,
            @RequestParam(defaultValue = "")    String category,
            @RequestParam(defaultValue = "1")   int day
    ) {
        var resp = boothService.getBooths(search, section, category, day);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailResponseDto<?>> getDetail(
            @PathVariable Long id,
            @RequestParam("category") String category
    ) {
        var resp = boothService.getDetail(id, category);
        return ResponseEntity.ok(resp);
    }
}
