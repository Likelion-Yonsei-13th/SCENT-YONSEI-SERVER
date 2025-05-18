package likelion.scent_yonsei.domain.booth.api.dto;

import java.util.List;

public record BoothListDataDto(
        String search,
        int    day,
        String section,
        String category,
        List<BoothSummaryDto>      booth,
        List<FoodTruckSummaryDto>  foodTruck
) {}
