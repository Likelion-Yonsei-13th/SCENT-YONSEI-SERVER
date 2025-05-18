package likelion.scent_yonsei.domain.makers.api.service;

import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.makers.api.dto.LikedRes;
import likelion.scent_yonsei.domain.makers.core.Liked;
import likelion.scent_yonsei.domain.makers.core.LikedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikedService {

    private final LikedRepository likedRepository;

    public LikedService(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Transactional
    public Response<?> addCheer() {
        likedRepository.save(new Liked());
        Long count = likedRepository.count();
        LikedRes likedRes = new LikedRes(count);
        return new Response<>(201, true, "응원하기 추가 성공", likedRes);
    }

    @Transactional(readOnly = true)
    public Response<?> count() {
        Long count = likedRepository.count();
        LikedRes likedRes = new LikedRes(count);
        return new Response<>(200, true, "응원하기 수 반환 성공", likedRes);
    }
}
