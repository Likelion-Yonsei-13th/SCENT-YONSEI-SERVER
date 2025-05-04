package likelion.scent_yonsei.domain.foodBooth.api.controller;

import likelion.scent_yonsei.domain.foodBooth.api.dto.FoodBoothCreateReq;
import likelion.scent_yonsei.domain.foodBooth.api.dto.FoodBoothRes;
import likelion.scent_yonsei.domain.foodBooth.api.service.FoodBoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodBoothController {

    private final FoodBoothService foodBoothService;

    @PostMapping("sample/create")
    public ResponseEntity<?> create(@RequestBody FoodBoothCreateReq req) {
        foodBoothService.createFoodBooth(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("sample/get-all")
    public ResponseEntity<List<FoodBoothRes>> list() {
        return ResponseEntity.ok(foodBoothService.getAllFoodBooths());
    }

    @GetMapping("sample/{id}")
    public ResponseEntity<FoodBoothRes> detail(@PathVariable Long id) {
        return ResponseEntity.ok(foodBoothService.getFoodBoothById(id));
    }

}
