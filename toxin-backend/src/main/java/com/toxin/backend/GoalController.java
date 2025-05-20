package com.toxin.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/last")
    public Mono<GoalDTO> findLast() {
        return goalService.findLast();
    }

    @PostMapping("/create")
    public Mono<Goal> create(@RequestBody final GoalDTO goalDTO) {
        return Mono.just(goalService.create(goalDTO));
    }
}
