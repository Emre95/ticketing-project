package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAll();

    TaskDTO save(TaskDTO dto);

    TaskDTO update(TaskDTO dto);

    void delete(Long id);

    TaskDTO findById(Long id);

    List<TaskDTO> findAllByProjectId(Long projectId);

    List<TaskDTO> listAllByTaskStatus(Status status);

    List<TaskDTO> listAllByTaskStatusIsNot(Status status);

    void deleteByProject(Long projectId);

    void completeByProject(Long projectId);

    boolean areAllTasksCompletedByEmployee(String username);

}
