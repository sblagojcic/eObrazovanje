package ftn.project.eObrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
