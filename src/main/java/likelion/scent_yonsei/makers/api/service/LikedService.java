package likelion.scent_yonsei.makers.api.service;

import likelion.scent_yonsei.makers.core.Liked;
import likelion.scent_yonsei.makers.core.LikedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikedService {

    private final LikedRepository likedRepository;

    public LikedService(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Transactional
    public int addCheer() {
        likedRepository.save(new Liked());
        return (int) likedRepository.count();
    }
}
