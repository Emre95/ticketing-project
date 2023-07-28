package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> listAll();

    ProjectDTO findById(Long id);

    ProjectDTO save(ProjectDTO dto);

    ProjectDTO update(ProjectDTO dto);

    void delete(Long id);

    void complete(Long id);

    List<ProjectDTO> findAllByAssignedManager(String username);

    List<ProjectDTO> findAllByAssignedManagerWithCompletedAndNonCompletedTaskCounts();

    boolean areAllProjectsCompletedByManager(Long managerId);

}
