package ftn.project.eObrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Page<Subject> findAllByNameLike(String filter1, Pageable page);

}
