package com.cydeo.converter;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOConverter implements Converter<String, ProjectDTO> {

    private final ProjectService projectService;

    public ProjectDTOConverter(@Lazy ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectDTO convert(String source) {

        if (source == null || source.equals("")) {
            return null;
        }

        return projectService.findById(Long.parseLong(source));
    }
}
