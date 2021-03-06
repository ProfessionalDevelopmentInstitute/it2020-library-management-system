package com.mds.libraryMgmtSystem.service;

import com.mds.libraryMgmtSystem.entity.*;
import com.mds.libraryMgmtSystem.pojo.LibrarianPojo;
import com.mds.libraryMgmtSystem.pojo.StudentPojo;
import com.mds.libraryMgmtSystem.repository.CredentialRepository;
import com.mds.libraryMgmtSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private LibraryCardService libraryCardService;

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(StudentPojo studentPojo) {
        Optional<LibraryCard> optionalLibraryCard = libraryCardService.findByRollNo(studentPojo.getLibraryCard());

        Student student = new Student();
        student.setName(studentPojo.getName());
        student.setAddress(studentPojo.getAddress());
        student.setPhone(studentPojo.getPhone());
        student.setDateOfBirth(studentPojo.getDateOfBirth());
        student.setLibraryCard(optionalLibraryCard.get());
        studentRepository.save(student);
        Credential credential = new Credential();
        credential.setEmail(studentPojo.getEmail());
        credential.setPassword(studentPojo.getPassword());
        credential.setRole(studentPojo.getRole());
        credential.setUser(student);
        credentialRepository.save(credential);

        return student;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> searchStudent(String name) {
        return studentRepository.findByName(name);
    }


    public List<Student> getStudent(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Student> pagedResult = studentRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }
}
