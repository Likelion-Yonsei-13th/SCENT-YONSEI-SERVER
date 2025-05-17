package likelion.scent_yonsei.domain.show.api.controller;

import likelion.scent_yonsei.common.Response;
import likelion.scent_yonsei.domain.show.api.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/show")
    public ResponseEntity<Response<?>> showAll(@RequestParam(defaultValue = "2") Integer day) {
        return ResponseEntity.ok(showService.showAll(day));
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<Response<?>> show(@PathVariable Long showId) {
        return ResponseEntity.ok(showService.showDetail(showId));
    }

}
