package com.toxin.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GoalService {

    private static final String GOAL_CACHE_KEY = "lastGoal";

    private final ReactiveRedisTemplate<String, Goal> redisTemplate;

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public Mono<GoalDTO> findLast() {
        return redisTemplate.opsForValue().get(GOAL_CACHE_KEY)
                .switchIfEmpty(Mono
                        .fromCallable(goalRepository::findTop1ByOrderByCreatedDesc)
                        .flatMap(g -> redisTemplate.opsForValue().set(GOAL_CACHE_KEY, g).thenReturn(g)))
                .map(goalMapper::toGoalDTO);
    }

    public Goal create(GoalDTO goalDTO) {
        Goal goal = goalMapper.toEntity(goalDTO);
        redisTemplate.delete(GOAL_CACHE_KEY).subscribe();
        return goalRepository.save(goal);
    }
}
