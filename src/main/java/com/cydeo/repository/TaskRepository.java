package com.cydeo.repository;

import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectId(Long projectId);

    List<Task> findAllByTaskStatus(Status status);

    List<Task> findAllByTaskStatusIsNot(Status status);


    List<Task> findAllByTaskStatusAndAssignedEmployeeUserName(Status status, String username);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployeeUserName(Status status, String username);
}
