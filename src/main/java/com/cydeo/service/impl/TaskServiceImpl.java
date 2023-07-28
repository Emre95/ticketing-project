package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TaskDTO> listAll() {
        return taskRepository.findAll().stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO save(TaskDTO dto) {

        Task task = mapperUtil.convert(dto, new Task());
        task.setTaskStatus(Status.OPEN);
        task.setAssignedDate(LocalDate.now());
        taskRepository.save(task);

        return findById(task.getId());
    }

    @Override
    public TaskDTO update(TaskDTO dto) {

        Task task = taskRepository.findById(dto.getId()).orElseThrow();
        Task updatedTask = mapperUtil.convert(dto, new Task());

        if (dto.getTaskStatus() == null) {
            updatedTask.setTaskStatus(task.getTaskStatus());
        }else {
            updatedTask.setTaskStatus(dto.getTaskStatus());
        }

        updatedTask.setAssignedDate(task.getAssignedDate());
        taskRepository.save(updatedTask);

        return findById(updatedTask.getId());
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).orElseThrow();
        task.setDeleted(true);
        taskRepository.save(task);

    }

    @Override
    public TaskDTO findById(Long id) {
        return mapperUtil.convert(taskRepository.findById(id), new TaskDTO());
    }


    @Override
    public List<TaskDTO> findAllByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId).stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllByTaskStatus(Status status) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return taskRepository.findAllByTaskStatusAndAssignedEmployeeUserName(status,username).stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllByTaskStatusIsNot(Status status) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return taskRepository.findAllByTaskStatusIsNotAndAssignedEmployeeUserName(status, username).stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByProject(Long projectId) {
        listAll().stream().map(task -> {
            if (task.getProject().getId().equals(projectId)) {
                delete(task.getId());
            }
            return task;
        }).collect(Collectors.toList());
    }

    @Override
    public void completeByProject(Long projectId) {
        listAll().stream().map(task -> {
            if (task.getProject().getId().equals(projectId)) {
                task.setTaskStatus(Status.COMPLETED);
                update(task);
            }
            return task;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean areAllTasksCompletedByEmployee(String username) {

        List<Task> list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployeeUserName(Status.COMPLETED, username);

        return list.size() == 0;
    }

}
