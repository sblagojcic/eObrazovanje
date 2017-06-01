package ftn.project.eObrazovanje.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
