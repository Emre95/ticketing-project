package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectCode;

    @NotNull
    private UserDTO assignedManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String projectDetail;
    private Status projectStatus;

    private int completedTaskCount;
    private int unfinishedTaskCount;

}
