package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final TaskService taskService;
    private final MapperUtil mapperUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskService taskService, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProjectDTO> listAll() {
        return projectRepository.findAll().stream()
                .map(project -> mapperUtil.convert(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findById(Long id) {
        return mapperUtil.convert(projectRepository.findById(id), new ProjectDTO());
    }

    @Override
    public ProjectDTO save(ProjectDTO dto) {

        dto.setProjectStatus(Status.OPEN);

        Project project = mapperUtil.convert(dto, new Project());
        projectRepository.save(project);

        return findById(project.getId());
    }

    @Override
    public ProjectDTO update(ProjectDTO dto) {

        Project project = projectRepository.findById(dto.getId()).orElseThrow();
        Project updatedProject = mapperUtil.convert(dto, new Project());
        updatedProject.setId(project.getId());
        updatedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(updatedProject);

        return findById(updatedProject.getId());
    }

    @Override
    public void delete(Long id) {

        Project project = projectRepository.findById(id).orElseThrow();
        project.setDeleted(true);
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());
        projectRepository.save(project);
        taskService.deleteByProject(project.getId());
    }

    @Override
    public void complete(Long id) {

        Project project = projectRepository.findById(id).orElseThrow();
        project.setProjectStatus(Status.COMPLETED);
        projectRepository.save(project);
        taskService.completeByProject(project.getId());

    }

    @Override
    public List<ProjectDTO> findAllByAssignedManager(String username) {

        return projectRepository.findAllByAssignedManagerUserName(username).stream()
                .map(project -> mapperUtil.convert(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findAllByAssignedManagerWithCompletedAndNonCompletedTaskCounts() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

         return findAllByAssignedManager(username).stream().map(project -> {

             project.setCompletedTaskCount((int)taskService.findAllByProjectId(project.getId()).stream()
                     .filter(task -> task.getTaskStatus().equals(Status.COMPLETED)).count());

             project.setUnfinishedTaskCount((int)taskService.findAllByProjectId(project.getId()).stream()
                     .filter(task -> !task.getTaskStatus().equals(Status.COMPLETED)).count());

             return project;

         }

         ).collect(Collectors.toList());

    }

    @Override
    public boolean areAllProjectsCompletedByManager(Long managerId) {
        List<Project> list = projectRepository.findAllByProjectStatusIsNotAndAssignedManagerId(Status.COMPLETED, managerId);

        return list.size() == 0;
    }

}
