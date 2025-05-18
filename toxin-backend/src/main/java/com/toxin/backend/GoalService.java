package com.toxin.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public GoalDTO findLast() {
        Goal lastGoal = goalRepository.findTop1ByOrderByCreatedDesc();
        return goalMapper.toGoalDTO(lastGoal);
    }

    public Goal create(GoalDTO goalDTO) {
        Goal goal = goalMapper.toEntity(goalDTO);
        return goalRepository.save(goal);
    }
}
