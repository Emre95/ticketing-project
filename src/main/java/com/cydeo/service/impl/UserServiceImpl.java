package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    private final ProjectService projectService;

    private final TaskService taskService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, ProjectService projectService, TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserDTO> listAll() {
        return userRepository.findAllByIsDeleted(false).stream()
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return mapperUtil.convert(userRepository.findByIdAndIsDeleted(id, false), new UserDTO());
    }

    @Override
    public UserDTO save(UserDTO dto) {

        User user = mapperUtil.convert(dto, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return findById(user.getId());
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User user = userRepository.findByIdAndIsDeleted(dto.getId(), false);
        User updatedUser = mapperUtil.convert(dto, new User());
        updatedUser.setId(user.getId());
        userRepository.save(updatedUser);

        return findById(updatedUser.getId());
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findByIdAndIsDeleted(id, false);

        if (canUserBeDeleted(user)) {
            user.setDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId());
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        return userRepository.findAllByRoleDescriptionIgnoreCaseAndIsDeleted(role, false).stream()
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }


    private boolean canUserBeDeleted(User user) {

        if (user.getRole().getDescription().equals("Manager") && projectService.areAllProjectsCompletedByManager(user.getId())) {
            return true;
        }else if (user.getRole().getDescription().equals("Employee") && taskService.areAllTasksCompletedByEmployee(user.getUserName())) {
            return true;
        }else {
            return false;
        }
    }

}
