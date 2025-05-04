package likelion.scent_yonsei.domain.foodBooth.api.service;

import likelion.scent_yonsei.domain.foodBooth.api.dto.FoodBoothCreateReq;
import likelion.scent_yonsei.domain.foodBooth.api.dto.FoodBoothRes;
import likelion.scent_yonsei.domain.foodBooth.core.FoodBooth;
import likelion.scent_yonsei.domain.foodBooth.core.FoodBoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodBoothService {

    private final FoodBoothRepository foodBoothRepository;

    @Transactional
    public void createFoodBooth(FoodBoothCreateReq req) {
        FoodBooth newFoodBooth = new FoodBooth(
                req.name(),
                req.organization(),
                req.manager(),
                req.contactNumber(),
                req.date(),
                req.section(),
                req.location(),
                req.description()
        );
        FoodBooth saved = foodBoothRepository.save(newFoodBooth);
    }

    public List<FoodBoothRes> getAllFoodBooths() {
        return foodBoothRepository.findAll()
                .stream()
                .map(foodBooth -> new FoodBoothRes(
                        foodBooth.getId(),
                        foodBooth.getName(),
                        foodBooth.getOrganization(),
                        foodBooth.getManager(),
                        foodBooth.getContactNumber(),
                        foodBooth.getDate(),
                        foodBooth.getSection(),
                        foodBooth.getLocation(),
                        foodBooth.getDescription()
                ))
                .toList();
    }

    public FoodBoothRes getFoodBoothById(Long id) {
        FoodBooth foodBooth = foodBoothRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));

        return new FoodBoothRes(
                foodBooth.getId(),
                foodBooth.getName(),
                foodBooth.getOrganization(),
                foodBooth.getManager(),
                foodBooth.getContactNumber(),
                foodBooth.getDate(),
                foodBooth.getSection(),
                foodBooth.getLocation(),
                foodBooth.getDescription()
        );
    }

}
