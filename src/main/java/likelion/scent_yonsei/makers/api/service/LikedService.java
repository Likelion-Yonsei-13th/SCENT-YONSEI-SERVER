package likelion.scent_yonsei.makers.api.service;

import likelion.scent_yonsei.makers.core.Liked;
import likelion.scent_yonsei.makers.core.LikedRepository;
import org.springframework.stereotype.Service;

@Service
public class LikedService {

    private final LikedRepository likedRepository;

    public LikedService(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    public int addCheer() {
        likedRepository.save(new Liked());
        return (int) likedRepository.count();
    }

    public int getCheerCount() {
        return (int) likedRepository.count();
    }
}
