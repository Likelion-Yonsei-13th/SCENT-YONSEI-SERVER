package likelion.scent_yonsei.domain.booth.api.dto;

import java.util.List;

public record BoothListDataDto(
        List<BoothSummaryDto>      booth,
        List<FoodTruckSummaryDto>  foodTruck
) {}
