package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAll();

    UserDTO findById(Long id);

    UserDTO save(UserDTO dto);

    UserDTO update(UserDTO dto);

    void delete(Long id);

    List<UserDTO> listAllByRole(String role);


}
