package com.develhope.crud_test.controllers;

import com.develhope.crud_test.dtos.StudentDto;
import com.develhope.crud_test.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto userDto){
        StudentDto StudentDtocreated=  studentService.createUser(userDto);
        return new ResponseEntity<>(StudentDtocreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<StudentDto> findById(@PathVariable Long id){
        StudentDto studentDtoFound=  studentService.findById(id);
        return ResponseEntity.ok().body(studentDtoFound);
    }


    @GetMapping("/list")
    public ResponseEntity<List<StudentDto>> findAll(){
        List<StudentDto> studentsDtoFound=  studentService.findAll();
        return ResponseEntity.ok().body(studentsDtoFound);
    }

    @PatchMapping("/workingSwitch/{id}")
    public ResponseEntity<StudentDto> workingSwitch(@PathVariable Long id, @RequestParam boolean working){
        StudentDto studentDtoUpdated =  studentService.isWorkingSwitch(id,working);
        return ResponseEntity.ok().body(studentDtoUpdated);
    }
    @PutMapping("/changeInfo/{id}")
    public ResponseEntity<StudentDto> changeInfo(@PathVariable Long id, @RequestBody StudentDto user){
        StudentDto studentDtoUpdated=  studentService.changeInfo(id,user);
        return ResponseEntity.ok().body(studentDtoUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        studentService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
