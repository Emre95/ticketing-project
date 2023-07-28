package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;

    @NotNull
    private ProjectDTO project;

    @NotNull
    private UserDTO assignedEmployee;

    private LocalDate assignedDate;

    @NotBlank
    private String taskSubject;

    @NotBlank
    private String taskDetail;

    private Status taskStatus;

}
