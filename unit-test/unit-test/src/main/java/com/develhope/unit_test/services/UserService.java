package com.develhope.unit_test.services;

import com.develhope.unit_test.dtos.UserDTO;
import com.develhope.unit_test.entities.UserEntity;
import com.develhope.unit_test.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity entity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity saved = userRepository.saveAndFlush(entity);
        return modelMapper.map(saved, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<UserEntity> optionalEntity = userRepository.findById(id);
        return optionalEntity.map(entity -> modelMapper.map(entity, UserDTO.class)).orElse(null);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<UserEntity> optionalEntity = userRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            return null;
        }
        UserEntity entityToUpdate = optionalEntity.get();
        entityToUpdate.setFirstName(userDTO.getFirstName());
        entityToUpdate.setLastname(userDTO.getLastname());
        UserEntity updatedEntity = userRepository.saveAndFlush(entityToUpdate);
        return modelMapper.map(updatedEntity, UserDTO.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
