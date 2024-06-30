package com.develhope.crud_test.services;

import com.develhope.crud_test.dtos.StudentDto;
import com.develhope.crud_test.entities.StudentEntity;
import com.develhope.crud_test.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper mapper;

    public StudentDto createUser(StudentDto user) {
        StudentEntity entity = mapper.map(user, StudentEntity.class);
        StudentEntity saved = studentRepository.saveAndFlush(entity);
        mapper.map(saved, user);
        return user;
    }

    public StudentDto findById(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElse(null);
        if (entity == null) return null;
        return mapper.map(entity, StudentDto.class);
    }

    public List<StudentDto> findAll() {
        List<StudentEntity> entities = studentRepository.findAll();
        List<StudentDto> dtos = new ArrayList<>();
        for (StudentEntity entity : entities) {
            dtos.add(mapper.map(entity, StudentDto.class));
        }
        return dtos;
    }

    public StudentDto isWorkingSwitch(Long id, Boolean working) {
        Optional<StudentEntity> foundedEntity = studentRepository.findById(id);
        if (!foundedEntity.isPresent()) return null;
        StudentEntity foundStudentEntity = foundedEntity.get();
        foundStudentEntity.setIsWorking(working);
        studentRepository.saveAndFlush(foundStudentEntity);
        return mapper.map(foundedEntity, StudentDto.class);
    }

    public StudentDto changeInfo(Long id, StudentDto user) {
        Optional<StudentEntity> foundedEntity = studentRepository.findById(id);
        if (!foundedEntity.isPresent()) return null;
        user.setId(id);

        StudentEntity updatedEntity = studentRepository.saveAndFlush(mapper.map(user, StudentEntity.class));
        return mapper.map(updatedEntity, StudentDto.class);
    }

    public void deleteUser(Long id) {
        studentRepository.deleteById(id);
    }
}
