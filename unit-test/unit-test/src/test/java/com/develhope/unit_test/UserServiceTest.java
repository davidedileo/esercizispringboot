package com.develhope.unit_test;

import com.develhope.unit_test.dtos.UserDTO;
import com.develhope.unit_test.entities.UserEntity;
import com.develhope.unit_test.repositories.UserRepository;
import com.develhope.unit_test.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Mario");
        userDTO.setLastname("Rossi");

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Mario");
        userEntity.setLastname("Rossi");

        UserEntity userEntitySaved = new UserEntity();
        userEntitySaved.setId(1L);
        userEntitySaved.setFirstName("Mario");
        userEntitySaved.setLastname("Rossi");

        when(modelMapper.map(userDTO, UserEntity.class)).thenReturn(userEntity);
        when(userRepository.saveAndFlush(any(UserEntity.class))).thenReturn(userEntitySaved);
        when(modelMapper.map(userEntitySaved, UserDTO.class)).thenReturn(userDTO);

        UserDTO newUser = userService.createUser(userDTO);
        assertEquals("Mario", newUser.getFirstName());
        assertEquals("Rossi", newUser.getLastname());
    }

    @Test
    public void testGetUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Mario");
        userEntity.setLastname("Rossi");

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        UserDTO userFound = userService.getUserById(1L);
        assertEquals("Mario", userFound.getFirstName());
        assertEquals("Rossi", userFound.getLastname());
    }

    @Test
    public void testUpdateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Mario");
        userEntity.setLastname("Rossi");

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("UpdatedMario");
        userDTO.setLastname("UpdatedRossi");

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.saveAndFlush(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO userUpdated = userService.updateUser(1L, userDTO);
        assertEquals("UpdatedMario", userUpdated.getFirstName());
        assertEquals("UpdatedRossi", userUpdated.getLastname());
    }
}
