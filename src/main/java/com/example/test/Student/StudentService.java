package com.example.test.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// to say it is a bean we should use service or component annotation
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
       Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
       if(studentByEmail.isPresent()) {
           throw new IllegalArgumentException("Student with email already exists");
       }
       studentRepository.save(student);
       System.out.println(student);
    }

    public List<Student> getStudents() {
      return studentRepository.findAll();
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
           throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
@Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalArgumentException("Student with id " + studentId + " does not exist") );
        if(name!=null&& name.length()>0&&!Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email!=null&& email.length()>0&&!Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
            if(studentByEmail.isPresent()) {
                throw new IllegalArgumentException("Student with email already exists");
            }
            student.setEmail(email);
        }
    }
}
