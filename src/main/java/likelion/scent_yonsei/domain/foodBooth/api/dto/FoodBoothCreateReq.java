package likelion.scent_yonsei.domain.foodBooth.api.dto;

public record FoodBoothCreateReq(
        String name,
        String organization,
        String manager,
        String contactNumber,
        int date,
        String section,
        Integer location,
        String description
) {
}
