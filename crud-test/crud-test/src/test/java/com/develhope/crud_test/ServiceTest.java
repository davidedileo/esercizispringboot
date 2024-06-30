package com.develhope.crud_test;

import com.develhope.crud_test.dtos.StudentDto;
import com.develhope.crud_test.entities.StudentEntity;
import com.develhope.crud_test.repositories.StudentRepository;
import com.develhope.crud_test.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(value="test")
@ExtendWith(SpringExtension.class)
public class ServiceTest {

    @Autowired
    private StudentService studentService;

    @Mock
    private ModelMapper mapper;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testCreateStudent() {
        StudentDto studentDtoInput = new StudentDto();
        studentDtoInput.setName("Mario");
        studentDtoInput.setSurname("Rossi");
        studentDtoInput.setIsWorking(true);

        StudentDto studentDtoOutput = new StudentDto();
        studentDtoOutput.setId(1L);
        studentDtoOutput.setName("Mario");
        studentDtoOutput.setSurname("Rossi");
        studentDtoOutput.setIsWorking(true);

        StudentEntity studentEntityInput = new StudentEntity();
        studentEntityInput.setName("Mario");
        studentEntityInput.setSurname("Rossi");
        studentEntityInput.setIsWorking(true);

        StudentEntity studentEntityOutput = new StudentEntity();
        studentEntityOutput.setId(1L);
        studentEntityOutput.setName("Mario");
        studentEntityOutput.setSurname("Rossi");
        studentEntityOutput.setIsWorking(true);


        when(mapper.map(studentDtoInput, StudentEntity.class)).thenReturn(studentEntityInput);
        when(studentRepository.saveAndFlush(any(StudentEntity.class))).thenReturn(studentEntityOutput);
        when(mapper.map(studentEntityOutput, StudentDto.class)).thenReturn(studentDtoOutput);


        StudentDto StudentDtoOutput_returnedByService = studentService.createUser(studentDtoInput);


        assertEquals("Mario", StudentDtoOutput_returnedByService.getName());
        assertEquals("Rossi", StudentDtoOutput_returnedByService.getSurname());
        assertEquals(true, StudentDtoOutput_returnedByService.getIsWorking());
        assertEquals(1L, StudentDtoOutput_returnedByService.getId());
    }

    @Test
    public void testGetStudentById() {
        StudentDto studentDtoOutput = new StudentDto();
        studentDtoOutput.setId(1L);
        studentDtoOutput.setName("Mario");
        studentDtoOutput.setSurname("Rossi");
        studentDtoOutput.setIsWorking(true);

        StudentEntity studentEntityOutput = new StudentEntity();
        studentEntityOutput.setId(1L);
        studentEntityOutput.setName("Mario");
        studentEntityOutput.setSurname("Rossi");
        studentEntityOutput.setIsWorking(true);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntityOutput));
        when(mapper.map(studentEntityOutput, StudentDto.class)).thenReturn(studentDtoOutput);

        StudentDto StudentDtoOutput_returnedByService = studentService.findById(1L);
        assertEquals("Mario", StudentDtoOutput_returnedByService.getName());
        assertEquals("Rossi", StudentDtoOutput_returnedByService.getSurname());
        assertEquals(true, StudentDtoOutput_returnedByService.getIsWorking());
        assertEquals(1L, StudentDtoOutput_returnedByService.getId());
    }

    @Test
    public void testUpdateIsWorking() {
        StudentDto studentDtoOutput = new StudentDto();
        studentDtoOutput.setId(1L);
        studentDtoOutput.setName("Mario");
        studentDtoOutput.setSurname("Rossi");
        studentDtoOutput.setIsWorking(true);

        StudentEntity studentEntityNotWorking = new StudentEntity();
        studentEntityNotWorking.setId(1L);
        studentEntityNotWorking.setName("Mario");
        studentEntityNotWorking.setSurname("Rossi");
        studentEntityNotWorking.setIsWorking(false);

        StudentEntity studentEntityWorking = new StudentEntity();
        studentEntityWorking.setId(1L);
        studentEntityWorking.setName("Mario");
        studentEntityWorking.setSurname("Rossi");
        studentEntityWorking.setIsWorking(true);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntityNotWorking));
        when(studentRepository.saveAndFlush(any(StudentEntity.class))).thenReturn(studentEntityWorking);
        when(mapper.map(studentEntityWorking, StudentDto.class)).thenReturn(studentDtoOutput);

        StudentDto StudentDtoOutput_returnedByService = studentService.isWorkingSwitch(1L, true);
        assertEquals("Mario", StudentDtoOutput_returnedByService.getName());
        assertEquals("Rossi", StudentDtoOutput_returnedByService.getSurname());
        assertEquals(true, StudentDtoOutput_returnedByService.getIsWorking());
        assertEquals(1L, StudentDtoOutput_returnedByService.getId());
    }
}
