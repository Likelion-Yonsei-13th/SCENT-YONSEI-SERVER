package likelion.scent_yonsei.domain.show.api.dto;

import java.util.List;

public record ShowDataRes(
        List<LiveRes> live,
        List<ShowRes> show
) {
}
