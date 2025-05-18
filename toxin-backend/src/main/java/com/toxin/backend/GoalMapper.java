package com.toxin.backend;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GoalMapper {

    Goal toEntity(GoalDTO goalDTO);

    GoalDTO toGoalDTO(Goal goal);
}